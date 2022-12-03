package chnytrcy.xyz.campusepidemicsystem.config.annotation;

import chnytrcy.xyz.campusepidemicsystem.model.enums.EntityEnums;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.config.annotation
 * @ClassName: DataSynchronous
 * @Author: ChnyTrcy
 * @Description: 数据全量同步
 * @Date: 2022/12/2 15:06
 * @Version: 1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSynchronous {

  EntityEnums type();
}
