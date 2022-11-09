package chnytrcy.xyz.campusepidemicsystem.config.basic.model;

import lombok.Getter;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.config.basic.model
 * @ClassName: BaseEnum
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/8/20 11:24 AM
 * @Version: 1.0
 */
public interface BaseEnum {

  Integer getNumber();

  String getName();

  String getSimplePinYin();

  String getFullPinYin();
}
