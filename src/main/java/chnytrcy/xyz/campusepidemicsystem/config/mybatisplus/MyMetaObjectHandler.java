package chnytrcy.xyz.campusepidemicsystem.config.mybatisplus;

import chnytrcy.xyz.campusepidemicsystem.config.shiro.utils.HttpContextUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName MyMetaObjectHandler
 * @Description mybatis-plus自动填充字段自定义
 * @Author chnytrcy
 * @DATE 2022/8/20 5:27 PM
 * @Version 1.0
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

  @Autowired
  private HttpContextUtil httpContextUtil;

  public MyMetaObjectHandler() {
  }

  @Override
  public void insertFill(MetaObject metaObject) {
    log.debug("start insert fill ....");
    this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
    this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    String userName = httpContextUtil.getUsername();
    if(StringUtils.isNotBlank(userName)){
      this.setFieldValByName("createUser", userName, metaObject);
      this.setFieldValByName("updateUser", userName, metaObject);
    }
  }

  @Override
  public void updateFill(MetaObject metaObject) {
    log.debug("start update fill ....");
    if (ObjectUtil.isNotNull(httpContextUtil.getUsername())) {
      this.setFieldValByName("updateUser", httpContextUtil.getUsername(), metaObject);
    }
    this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
  }
}
