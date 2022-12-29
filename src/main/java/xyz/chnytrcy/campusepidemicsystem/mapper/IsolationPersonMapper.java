package xyz.chnytrcy.campusepidemicsystem.mapper;

import xyz.chnytrcy.campusepidemicsystem.model.command.pc.isolationperson.QueryPageIsolationPersonCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.isolationperson.QueryPageToBeIsolationPersonCommand;
import xyz.chnytrcy.campusepidemicsystem.model.dto.HolidayStreetRiskLevelDTO;
import xyz.chnytrcy.campusepidemicsystem.model.dto.QueryPageIsolationPersonByEpidemicDTO;
import xyz.chnytrcy.campusepidemicsystem.model.entity.IsolationPerson;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.isolationperson.QueryPageIsolationPersonVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.mapper
 * @InterfaceName: IsolationPersonMapper
 * @Author: ChnyTrcy
 * @Description: 隔离人员Mapper
 * @Date: 2022/8/24 4:37 PM
 * @Version: 1.0
 */
@Mapper
public interface IsolationPersonMapper extends BaseMapper<IsolationPerson> {

  /**
   * 获得涉案学生中已经在隔离的学生列表
   */
  List<String> queryStudentIsolationList(List<HolidayStreetRiskLevelDTO> list);

  /**
   * 获得本院系的隔离学生
   * @param keyword 搜索关键字
   * @param wordType 搜索类型
   * @param deptCode 院系编号
   * @return
   */
  List<IsolationPerson> queryPageIsolationPersonByAdmin(
      @Param("keyword") String keyword,
      @Param("wordType") Integer wordType,
      @Param("state") Integer state,
      @Param("deptCode") String deptCode);

  /**
   * 分页查询待隔离人员
   * @param teacherDeptCode 防疫人员所属院系编号
   */
  List<IsolationPerson> queryPageToBeIsolationPerson(
      @Param("command") QueryPageToBeIsolationPersonCommand command,
      @Param("deptCode") String teacherDeptCode);

  /**
   * 防疫人员分页查询已隔离人员
   * @param command 搜索类
   * @param teacherDeptCode 防疫人员所属院系编号
   */
  List<QueryPageIsolationPersonVO> queryPageIsolationPerson(
      @Param("command") QueryPageIsolationPersonCommand command,
      @Param("deptCode") String teacherDeptCode);

  /**
   * 防疫人员分页查询对应的隔离人员
   * @param code 防疫人员编号
   */
  List<QueryPageIsolationPersonByEpidemicDTO> queryPageIsolationPersonByEpidemic(String code);
}
