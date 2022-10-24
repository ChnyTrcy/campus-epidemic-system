package chnytrcy.xyz.campusepidemicsystem.config.mybatisplus;

import chnytrcy.xyz.campusepidemicsystem.config.shiro.utils.HttpContextUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import java.time.LocalDateTime;
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
public class MyMetaObjectHandler implements MetaObjectHandler {

  @Autowired
  private HttpContextUtil httpContextUtil;

  private static final Logger LOGGER = LoggerFactory.getLogger(MyMetaObjectHandler.class);

  public MyMetaObjectHandler() {
  }

  @Override
  public void insertFill(MetaObject metaObject) {
//    LOGGER.debug("start insert fill ....");
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
//    LOGGER.debug("start update fill ....");
    String userName = httpContextUtil.getUsername();
    if (StringUtils.isNotBlank(userName)) {
      this.setFieldValByName("updateUser", userName, metaObject);
    }
    this.setFieldValByName("updateDate", LocalDateTime.now(), metaObject);
  }
}
