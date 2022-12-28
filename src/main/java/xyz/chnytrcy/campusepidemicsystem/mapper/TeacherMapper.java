package xyz.chnytrcy.campusepidemicsystem.mapper;

import xyz.chnytrcy.campusepidemicsystem.model.command.pc.teacher.QueryTeacherPageCommand;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.mapper
 * @InterfaceName: TeacherMapper
 * @Author: ChnyTrcy
 * @Description: 教职工Mapper
 * @Date: 2022/8/24 4:58 PM
 * @Version: 1.0
 */
@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {

  /**
   * 根据关键词分页查询
   * @param command 关键词
   */
  List<Teacher> queryTeacherPage(QueryTeacherPageCommand command);

  /**
   * 查询本院系的防疫人员
   * @param wordType 搜索类型(1:姓名,2:工号)
   * @param keyword 关键词
   * @param deptCode 院系编号
   * @return
   */
  List<Teacher> queryEpidemicPerson(
      @Param("wordType") Integer wordType,
      @Param("keyword") String keyword,
      @Param("deptCode") String deptCode);
}
