package chnytrcy.xyz.campusepidemicsystem.controller.pc;

import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.analysis.CountDeptEpidemicNumVO;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.analysis.CountDeptPeopleProportionVO;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.analysis.CountNewIsolationListVO;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.analysis.CountPeopleDistributionVO;
import chnytrcy.xyz.campusepidemicsystem.service.pc.IsolationPersonService;
import chnytrcy.xyz.campusepidemicsystem.service.pc.TeacherService;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.controller.pc
 * @ClassName: StatisticalAnalysisController
 * @Author: ChnyTrcy
 * @Description: 统计分析接口
 * @Date: 2022/10/20 08:55
 * @Version: 1.0
 */
@RestController
@RequestMapping("${mvc.url.perfix.pc}/statisticalAnalysis")
@Api(value = "统计分析Controller",tags = "PC - 统计分析")
public class StatisticalAnalysisController {

  @Autowired private IsolationPersonService isolationPersonService;

  @Autowired private TeacherService teacherService;

  @GetMapping("/countPeopleDistribution")
  @ApiOperation("统计人数分布")
  public Result<CountPeopleDistributionVO> countPeopleDistribution(){
    return isolationPersonService.countPeopleDistribution();
  }

  @GetMapping("/countDeptPeopleProportion")
  @ApiOperation("各学院防疫人员和隔离人员配比")
  public Result<CountDeptPeopleProportionVO> countDeptPeopleProportion(){
    return isolationPersonService.countDeptPeopleProportion();
  }

  @GetMapping("/countDeptEpidemicNum")
  @ApiOperation("各学院防疫人数占比")
  public Result<CountDeptEpidemicNumVO> countDeptEpidemicNum(){
    return teacherService.countDeptEpidemicNum();
  }

  @GetMapping("/countNewIsolationList")
  @ApiOperation("新增隔离人数趋势")
  public Result<CountNewIsolationListVO> countNewIsolationList(){
    return isolationPersonService.countNewIsolationList();
  }

}
