package chnytrcy.xyz.campusepidemicsystem.service.general.impl;

import chnytrcy.xyz.campusepidemicsystem.model.dto.DictEnumsEntityDTO;
import chnytrcy.xyz.campusepidemicsystem.model.dto.DictEnumsValueDTO;
import chnytrcy.xyz.campusepidemicsystem.service.general.DictService;
import chnytrcy.xyz.campusepidemicsystem.utils.dict.AnnotationValidRegistrar;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import chnytrcy.xyz.campusepidemicsystem.utils.result.ResultFactory;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.general.impl
 * @ClassName: DictServiceImpl
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/11/8 18:59
 * @Version: 1.0
 */
@Service
public class DictServiceImpl implements DictService {

  @Autowired private AnnotationValidRegistrar registrar;

  @Override
  public Result<Set<DictEnumsValueDTO>> getDictListByAppKey(String appKey)
      throws IOException, ClassNotFoundException {
    Set<DictEnumsEntityDTO> dictAllList = registrar.initEnums();
    Set<DictEnumsValueDTO> key = Sets.newHashSet();
    dictAllList.forEach(e -> {
      if(e.getEnumsEntityType().getAppKey().equals(appKey)){
        key.addAll(e.getValueDTOSet());
      }
    });
    return ResultFactory.successResult(key);
  }

  @Override
  public Result<Set<DictEnumsEntityDTO>> getAllDictList()
      throws IOException, ClassNotFoundException {
    return ResultFactory.successResult(registrar.initEnums());
  }

  @Override
  public Result<String> getDictDescByAppKeyComplex(String appKey)
      throws IOException, ClassNotFoundException {
    Map<String, DictEnumsValueDTO> map = map();
    String desc = map.get(appKey).getDesc();
    return ResultFactory.successResult(desc);
  }

  @Override
  public Result<Set<DictEnumsEntityDTO>> getAllErrorDictList()
      throws IOException, ClassNotFoundException {
    return ResultFactory.successResult(registrar.initError());
  }

  @Override
  public Result<String> getDictErrorDescByAppKeyComplex(String appKeyComplex)
      throws IOException, ClassNotFoundException {
    return ResultFactory.successResult(errorMap().get(appKeyComplex).getDesc());
  }

  @Override
  public Result<Set<DictEnumsValueDTO>> getDictListByAppKeyTypeCode(String appKeyTypeCode,String enumsType)
      throws IOException, ClassNotFoundException {
    Set<DictEnumsValueDTO> key = Sets.newHashSet();
    registrar.initEnums().forEach(e -> {
      if(e.getEnumsEntityType().getName().equals(enumsType)){
        Set<DictEnumsValueDTO> collect = e.getValueDTOSet()
            .stream()
            .filter(f -> f.getAppKeyTypeCode().equals(appKeyTypeCode))
            .collect(Collectors.toSet());
        key.addAll(collect);
      }
    });
    return ResultFactory.successResult(key);
  }

  @Override
  public Result<String> getAppKeyTypeDescByAppKeyTypeCode(String appKeyTypeCode)
      throws IOException, ClassNotFoundException {
    Set<DictEnumsEntityDTO> dtos = registrar.initEnums();
    List<String> key = Lists.newArrayList();
    for (DictEnumsEntityDTO dto : dtos) {
      key = dto.getValueDTOSet().stream()
          .filter(f -> f.getAppKeyTypeCode().equals(appKeyTypeCode))
          .map(DictEnumsValueDTO::getAppKeyType)
          .distinct()
          .collect(Collectors.toList());
      if(!key.isEmpty()){
        break;
      }
    }
    if(key.isEmpty()){
      return ResultFactory.errorResultBusiness("获取不到对应的AppKeyType",-1);
    }
    return ResultFactory.successResult(key.get(0));
  }


  public Map<String,DictEnumsValueDTO> map() throws IOException, ClassNotFoundException {
    Set<DictEnumsEntityDTO> init = registrar.initEnums();
    Map<String,DictEnumsValueDTO> key = Maps.newHashMap();
    init.forEach(e -> {
      e.getValueDTOSet().forEach(r -> {
        key.put(r.getAppKeyId(),r);
      });
    });
    return key;
  }

  public Map<String,DictEnumsValueDTO> errorMap() throws IOException, ClassNotFoundException {
    Set<DictEnumsEntityDTO> init = registrar.initError();
    Map<String,DictEnumsValueDTO> key = Maps.newHashMap();
    init.forEach(e -> {
      e.getValueDTOSet().forEach(r -> {
        key.put(r.getAppKeyId(),r);
      });
    });
    return key;
  }
}
