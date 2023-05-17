package xyz.chnytrcy.campusepidemicsystem.controller.app;

import xyz.chnytrcy.campusepidemicsystem.model.command.app.leave.AddNewLeaveApplicationCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.app.leave.BackSchoolCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.app.leave.QueryHistoryListCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.app.leave.QueryLeaveDetailCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.app.leave.ReturnLeaveToAdminMessageCommand;
import xyz.chnytrcy.campusepidemicsystem.model.vo.app.leave.OutSchoolVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.app.leave.QueryHistoryListVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.app.leave.QueryLeaveDetailVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.app.leave.QueryLeaveMessagesNumberVO;
import xyz.chnytrcy.campusepidemicsystem.service.app.LeaveAppService;
import xyz.chnytrcy.campusepidemicsystem.service.app.LeaveDetailAppService;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import javax.validation.Valid;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xyz.chnytrcy.core.config.basic.model.BasePageVO;
import xyz.chnytrcy.core.utils.result.Result;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.controller.app
 * @ClassName: LeaveAppController
 * @Author: ChnyTrcy
 * @Description: 移动端离校模块
 * @Date: 2022/9/1 2:03 PM
 * @Version: 1.0
 */
@RestController
@RequestMapping("${mvc.url.perfix.app}/leave")
@Api(value = "移动端 - 请假模块Controller",tags = "App - 请假接口")
public class LeaveAppController {

  @Autowired private LeaveAppService leaveAppService;

  @Autowired private LeaveDetailAppService leaveDetailAppService;

  @PostMapping("/addNewLeaveApplication")
  @RequiresPermissions("student:leave:insert")
  @ApiOperation("添加新的请假记录")
  public Result<Void> addNewLeaveApplication(@RequestBody @Valid AddNewLeaveApplicationCommand command)
      throws ParseException {
    return leaveAppService.addNewLeaveApplication(command);
  }

  @PostMapping("/returnLeaveToAdminMessage")
  @RequiresPermissions("student:leave:update")
  @ApiOperation("学生回复管理员请假理由")
  public Result<Void> returnLeaveToAdminMessage(@RequestBody @Valid ReturnLeaveToAdminMessageCommand command){
    return leaveDetailAppService.returnLeaveToAdminMessage(command);
  }

  @GetMapping("/outSchool")
  @RequiresPermissions("student:leave:update")
  @ApiOperation("出校")
  public Result<OutSchoolVO> outSchool(){
    return leaveAppService.outSchool();
  }

//  @PostMapping(value = "/backSchool",
//      consumes = "multipart/*",
//      headers = "content-type=multipart/form-data")
  @PostMapping(value = "/backSchool")
  @RequiresPermissions("student:leave:update")
  @ApiOperation("返校")
  public Result<Void> backSchool(@RequestBody @Valid BackSchoolCommand command)
      throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
    return leaveAppService.backSchool(command.getNucleicAcid(),command.getHealthCodePicture());
  }

  @GetMapping("/queryHistoryList")
  @RequiresPermissions("student:leave:select")
  @ApiOperation("分页查询自己的历史请假记录")
  public Result<BasePageVO<QueryHistoryListVO>> queryHistoryList(@Valid QueryHistoryListCommand command){
    return leaveAppService.queryHistoryList(command);
  }

  @GetMapping("/queryLeaveDetail")
  @RequiresPermissions("student:leave:select")
  @ApiOperation("查询请假记录详情")
  public Result<QueryLeaveDetailVO> queryLeaveDetail(@Valid QueryLeaveDetailCommand command){
    return leaveAppService.queryLeaveDetail(command);
  }

  @GetMapping("/queryLeaveMessagesNumber")
  @RequiresPermissions("student:leave:select")
  @ApiOperation("查询该学生的未读和所有请假数量")
  public Result<QueryLeaveMessagesNumberVO> queryLeaveMessagesNumber(){
    return leaveAppService.queryLeaveMessagesNumber();
  }

}
