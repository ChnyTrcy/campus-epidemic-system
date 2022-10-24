package chnytrcy.xyz.campusepidemicsystem.mapper;

import chnytrcy.xyz.campusepidemicsystem.model.command.pc.leave.QueryPageLeaveListCommand;
import chnytrcy.xyz.campusepidemicsystem.model.dto.QueryPageLeaveDTO;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Leave;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.mapper
 * @InterfaceName: LeaveMapper
 * @Author: ChnyTrcy
 * @Description: 日常出入校请假Mapper
 * @Date: 2022/8/24 4:45 PM
 * @Version: 1.0
 */
@Mapper
public interface LeaveMapper extends BaseMapper<Leave> {

  /**
   * 查看本院系的请假列表
   * @param command 过滤条件
   * @param adminCode 管理员工号
   */
  List<QueryPageLeaveDTO> queryPageLeaveList(
      @Param("command") QueryPageLeaveListCommand command,
      @Param("adminCode") Long adminCode);

  /**
   * 查询是否有未完成的请假
   * @param code 学号
   */
  Leave queryUnfinishedLeave(String code);
}
