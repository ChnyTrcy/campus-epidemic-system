package xyz.chnytrcy.campusepidemicsystem.utils.aop.datasynchronization;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import xyz.chnytrcy.campusepidemicsystem.model.enums.EntityEnums;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.utils.aop.datasynchronization
 * @ClassName: DataSynchronous
 * @Author: ChnyTrcy
 * @Description: 数据全量同步注解
 * @Date: 2022/12/2 15:06
 * @Version: 1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSynchronous {

  EntityEnums type();
}