package chnytrcy.xyz.campusepidemicsystem.mapper;

import chnytrcy.xyz.campusepidemicsystem.model.command.pc.feedbackacceptance.QueryPageEpidemicFeedbackAcceptanceCommand;
import chnytrcy.xyz.campusepidemicsystem.model.entity.FeedbackAcceptance;
import chnytrcy.xyz.campusepidemicsystem.model.entity.IsolationPerson;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.feedbackacceptance.QueryPageEpidemicFeedbackAcceptanceVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.mapper
 * @InterfaceName: FeedbackAcceptanceMapper
 * @Author: ChnyTrcy
 * @Description: 反馈受理Mapper
 * @Date: 2022/8/24 3:35 PM
 * @Version: 1.0
 */
@Mapper
public interface FeedbackAcceptanceMapper extends BaseMapper<FeedbackAcceptance> {

  /**
   * 分页查询反馈受理
   * @param feedbackType 反馈类型
   * @param queryType 查看类型（1：只看自己，2：查看所有）
   * @param code 管理员工号
   * @return
   */
  List<FeedbackAcceptance> queryPageAdminFeedbackAcceptance(
      @Param("feedbackType") Integer feedbackType,
      @Param("queryType") Integer queryType,
      @Param("code") Long code);

  /**
   * 分页查询防疫人员查看本院系的反馈受理情况
   * @param feedbackType 反馈类型
   * @param account 防疫人员账号
   */
  List<QueryPageEpidemicFeedbackAcceptanceVO> queryPageEpidemicFeedbackAcceptance(
      @Param("feedbackType") Integer feedbackType,
      @Param("account") String account,
      @Param("sponsor") Integer sponsor);


  /**
   * 防疫人员分析与自己相关的反馈受理
   * @param deptCode 院系编号
   */
  List<FeedbackAcceptance> analyzeEpidemicFeedbackAcceptance(
      @Param("deptCode") String deptCode);
}
