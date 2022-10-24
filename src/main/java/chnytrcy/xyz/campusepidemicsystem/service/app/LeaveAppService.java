package chnytrcy.xyz.campusepidemicsystem.service.app;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BasePageVO;
import chnytrcy.xyz.campusepidemicsystem.model.command.app.leave.AddNewLeaveApplicationCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.app.leave.BackSchoolCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.app.leave.QueryHistoryListCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.app.leave.QueryLeaveDetailCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.app.leave.ReturnLeaveToAdminMessageCommand;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Leave;
import chnytrcy.xyz.campusepidemicsystem.model.vo.app.leave.OutSchoolVO;
import chnytrcy.xyz.campusepidemicsystem.model.vo.app.leave.QueryHistoryListVO;
import chnytrcy.xyz.campusepidemicsystem.model.vo.app.leave.QueryLeaveDetailVO;
import chnytrcy.xyz.campusepidemicsystem.model.vo.app.leave.QueryLeaveMessagesNumberVO;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.app
 * @InterfaceName: LeaveAppService
 * @Author: ChnyTrcy
 * @Description: 移动端 - 请假服务接口
 * @Date: 2022/9/1 2:06 PM
 * @Version: 1.0
 */
public interface LeaveAppService extends IService<Leave> {

  /**
   * 添加新的请假记录
   */
  Result<Void> addNewLeaveApplication(AddNewLeaveApplicationCommand command) throws ParseException;

  /**
   * 出校
   */
  Result<OutSchoolVO> outSchool();

  /**
   * 返校
   */
  Result<Void> backSchool(String nucleicAcid, MultipartFile healthCodePicture)
      throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;

  /**
   * 查询自己的历史请假记录
   */
  Result<BasePageVO<QueryHistoryListVO>> queryHistoryList(QueryHistoryListCommand command);

  /**
   * 查询请假记录详情
   */
  Result<QueryLeaveDetailVO> queryLeaveDetail(QueryLeaveDetailCommand command);

  /**
   * 查询该学生的未读和所有请假数量
   */
  Result<QueryLeaveMessagesNumberVO> queryLeaveMessagesNumber();
}
