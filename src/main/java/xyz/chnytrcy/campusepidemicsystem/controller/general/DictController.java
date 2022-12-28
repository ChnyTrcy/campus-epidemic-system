package xyz.chnytrcy.campusepidemicsystem.controller.general;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.chnytrcy.campusepidemicsystem.model.dto.DictEnumsEntityDTO;
import xyz.chnytrcy.campusepidemicsystem.model.dto.DictEnumsValueDTO;
import xyz.chnytrcy.campusepidemicsystem.service.general.DictService;
import xyz.chnytrcy.core.utils.result.Result;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.controller.general
 * @ClassName: DictController
 * @Author: ChnyTrcy
 * @Description: 字典
 * @Date: 2022/11/8 18:55
 * @Version: 1.0
 */
@RestController
@RequestMapping("${mvc.url.perfix}/dict")
@Api(value = "字典Controller",tags = "通用 - 字典接口")
public class DictController {

  @Autowired private DictService dictService;

  @ApiOperation("获得全部字典列表")
  @GetMapping("/getAllDictList")
  public Result<Set<DictEnumsEntityDTO>> getAllDictList()
      throws IOException, ClassNotFoundException {
    return dictService.getAllDictList();
  }

  @ApiOperation("根据AppKeyComplex获得描述值")
  @GetMapping("/getDictDescByAppKeyComplex")
  public Result<String> getDictDescByAppKeyComplex(String appKeyComplex)
      throws IOException, ClassNotFoundException {
    return dictService.getDictDescByAppKeyComplex(appKeyComplex);
  }

  @ApiOperation("根据AppKey获得枚举列表")
  @GetMapping("/getDictListByAppKey")
  public Result<Set<DictEnumsValueDTO>> getDictListByAppKey(String appKey)
      throws IOException, ClassNotFoundException {
    return dictService.getDictListByAppKey(appKey);
  }

  @ApiOperation("根据AppKeyTypeCode获得对应组的枚举列表")
  @GetMapping("/getDictListByAppKeyTypeCode")
  public Result<Set<DictEnumsValueDTO>> getDictListByAppKeyTypeCode(String appKeyTypeCode,String enumsType)
      throws IOException, ClassNotFoundException {
    return dictService.getDictListByAppKeyTypeCode(appKeyTypeCode,enumsType);
  }

  @ApiOperation("根据AppKeyCode获得对应组描述")
  @GetMapping("/getAppKeyTypeDescByAppKeyTypeCode")
  public Result<String> getAppKeyTypeDescByAppKeyTypeCode(String appKeyTypeCode)
      throws IOException, ClassNotFoundException {
    return dictService.getAppKeyTypeDescByAppKeyTypeCode(appKeyTypeCode);
  }

  @ApiOperation("获得全部异常字典列表")
  @GetMapping("/getAllErrorDictList")
  public Result<Set<DictEnumsEntityDTO>> getAllErrorDictList()
      throws IOException, ClassNotFoundException {
    return dictService.getAllErrorDictList();
  }

  @ApiOperation("根据AppKeyComplex获得异常枚举描述值")
  @GetMapping("/getDictErrorDescByAppKeyComplex")
  public Result<String> getDictErrorDescByAppKeyComplex(String appKeyComplex)
      throws IOException, ClassNotFoundException {
    return dictService.getDictErrorDescByAppKeyComplex(appKeyComplex);
  }

  @ApiOperation("根据错误代码获得描述值")
  @GetMapping("/getDictErrorDescByCode")
  public Result<String> getDictErrorDescByCode(String code)
      throws IOException, ClassNotFoundException {
    return dictService.getDictErrorDescByCode(code);
  }


}
