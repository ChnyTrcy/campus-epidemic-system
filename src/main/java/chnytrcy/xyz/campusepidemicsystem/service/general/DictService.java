package chnytrcy.xyz.campusepidemicsystem.service.general;

import chnytrcy.xyz.campusepidemicsystem.model.dto.DictEnumsEntityDTO;
import chnytrcy.xyz.campusepidemicsystem.model.dto.DictEnumsValueDTO;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import java.io.IOException;
import java.util.Set;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.general
 * @InterfaceName: DictService
 * @Author: ChnyTrcy
 * @Description: 字典服务接口
 * @Date: 2022/11/8 18:59
 * @Version: 1.0
 */
public interface DictService {

  /**
   * 根据AppKey获得字典列表
   */
  Result<Set<DictEnumsValueDTO>> getDictListByAppKey(String appKey) throws IOException, ClassNotFoundException;

  /**
   * 获得全部字典列表
   */
  Result<Set<DictEnumsEntityDTO>> getAllDictList() throws IOException, ClassNotFoundException;

  /**
   * 根据AppKeyComplex获得描述值
   */
  Result<String> getDictDescByAppKeyComplex(String appKey)
      throws IOException, ClassNotFoundException;

  /**
   * 获得全部异常字典列表
   */
  Result<Set<DictEnumsEntityDTO>> getAllErrorDictList() throws IOException, ClassNotFoundException;

  /**
   * 根据AppKeyComplex获得异常枚举描述值
   */
  Result<String> getDictErrorDescByAppKeyComplex(String appKeyComplex)
      throws IOException, ClassNotFoundException;

  /**
   * 根据AppKeyTypeCode获得对应组的枚举列表
   */
  Result<Set<DictEnumsValueDTO>> getDictListByAppKeyTypeCode(String appKeyTypeCode,String enumsType)
      throws IOException, ClassNotFoundException;

  /**
   * 根据AppKeyCode获得对应组描述
   * @return
   */
  Result<String> getAppKeyTypeDescByAppKeyTypeCode(String appKeyTypeCode)
      throws IOException, ClassNotFoundException;
}
