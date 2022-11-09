package chnytrcy.xyz.campusepidemicsystem.utils.dict;

import chnytrcy.xyz.campusepidemicsystem.config.annotation.EnumKey;
import chnytrcy.xyz.campusepidemicsystem.model.dto.DictEnumsEntityDTO;
import chnytrcy.xyz.campusepidemicsystem.model.dto.DictEnumsValueDTO;
import chnytrcy.xyz.campusepidemicsystem.model.enums.EnumsEntityType;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.utils.dict
 * @ClassName: AnnotationValidRegistrar
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/10/20 20:16
 * @Version: 1.0
 */
@Component
@Slf4j
public class AnnotationValidRegistrar {

  private final String BASE_ENUMS_PACKAGE = "chnytrcy/xyz/campusepidemicsystem/model/enums/entity/*class";

  private final String BASE_ERROR_PACKAGE = "chnytrcy/xyz/campusepidemicsystem/model/enums/*Error*";

  protected Set<DictEnumsEntityDTO> init(Boolean flag) throws IOException, ClassNotFoundException {
    Set<Class> handlerSet = Sets.newHashSet();
    ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
    String pattern;
    if(flag){
      pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
          ClassUtils.convertClassNameToResourcePath(BASE_ENUMS_PACKAGE);
    }else {
      pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
          ClassUtils.convertClassNameToResourcePath(BASE_ERROR_PACKAGE);
    }
    Resource[] resources = resourcePatternResolver.getResources(pattern);
    MetadataReaderFactory readerfactory = new CachingMetadataReaderFactory(resourcePatternResolver);
    for (Resource resource : resources) {
      //用于读取类信息
      MetadataReader reader = readerfactory.getMetadataReader(resource);
      //扫描到的class
      String classname = reader.getClassMetadata().getClassName();
      Class<?> clazz = Class.forName(classname);
      //判断是否有指定主解
      EnumKey anno = clazz.getAnnotation(EnumKey.class);
      if (anno != null) {
        //将注解中的类型值作为key，对应的类作为 value
        handlerSet.add(clazz);
      }
    }
    Set<DictEnumsEntityDTO> key = Sets.newHashSet();
    handlerSet.forEach(e -> {
      DictEnumsEntityDTO dictEnumsEntityDTO = new DictEnumsEntityDTO();
      if(e.isAnnotationPresent(EnumKey.class)) {
        EnumKey annotation = (EnumKey) e.getAnnotation(EnumKey.class);
        dictEnumsEntityDTO.setEnumsEntityType(annotation.type());
        String appKey = annotation.type().getAppKey();
        Object[] enumConstants = e.getEnumConstants();
        try {
          Set<DictEnumsValueDTO> dtos = Sets.newHashSet();
          if(!flag){
            Method getCode = e.getMethod("getNumber");
            Method getName = e.getMethod("getName");
            int i = 1;
            for (Object obj : enumConstants) {
              DictEnumsValueDTO dictEnumsValueDTO = new DictEnumsValueDTO();
              String code = String.valueOf(getCode.invoke(obj));
              String name = String.valueOf(getName.invoke(obj));
              dictEnumsValueDTO.setCode(code);
              dictEnumsValueDTO.setDesc(name);
              dictEnumsValueDTO.setName(obj.toString());
              dictEnumsValueDTO.setAppKeyId(appKey + "-" + i++);
              dtos.add(dictEnumsValueDTO);
            }
            dictEnumsEntityDTO.setValueDTOSet(dtos);
          }else {
            Method getCode = e.getMethod("getNumber");
            Method getName = e.getMethod("getName");
            Method getType = e.getMethod("getType");
            Method getTypeCode = e.getMethod("getTypeCode");
            for (Object obj : enumConstants) {
              DictEnumsValueDTO dictEnumsValueDTO = new DictEnumsValueDTO();
              String code = String.valueOf(getCode.invoke(obj));
              String name = String.valueOf(getName.invoke(obj));
              String typeCode = String.valueOf(getTypeCode.invoke(obj));
              String type = String.valueOf(getType.invoke(obj));
              dictEnumsValueDTO.setCode(code);
              dictEnumsValueDTO.setDesc(name);
              dictEnumsValueDTO.setName(obj.toString());
              dictEnumsValueDTO.setAppKeyType(type);
              dictEnumsValueDTO.setAppKeyTypeCode(typeCode);
              dictEnumsValueDTO.setAppKeyId(appKey + "-" + typeCode + "-" + code);
              dtos.add(dictEnumsValueDTO);
            }
            dictEnumsEntityDTO.setValueDTOSet(dtos);
          }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ex) {
          throw new RuntimeException(ex);
        }
      }
      key.add(dictEnumsEntityDTO);
    });
    return key;
  }

  public Set<DictEnumsEntityDTO> initEnums() throws IOException, ClassNotFoundException {
    return init(true);
  }

  public Set<DictEnumsEntityDTO> initError() throws IOException, ClassNotFoundException {
    return init(false);
  }

}
