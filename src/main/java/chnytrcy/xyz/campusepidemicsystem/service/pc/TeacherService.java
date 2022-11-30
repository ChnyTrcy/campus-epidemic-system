package chnytrcy.xyz.campusepidemicsystem.service.pc;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BasePageVO;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.teacher.AddTeacherCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.teacher.DeleteTeacherCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.teacher.QueryEpidemicPersonCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.teacher.QueryTeacherPageCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.teacher.SetTeacherToEpidemicPersonCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.teacher.UpdateTeacherCommand;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Teacher;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.analysis.CountDeptEpidemicNumVO;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.teacher.QueryEpidemicPersonVO;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.teacher.QueryTeacherPageVO;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.pc
 * @InterfaceName: TeacherService
 * @Author: ChnyTrcy
 * @Description: 教职工服务接口
 * @Date: 2022/8/24 4:58 PM
 * @Version: 1.0
 */
public interface TeacherService extends IService<Teacher> {

  /**
   * 添加教职工
   */
  Result<Void> addTeacher(AddTeacherCommand command);

  /**
   * 根据关键词分页查询
   */
  Result<BasePageVO<QueryTeacherPageVO>> queryTeacherPage(QueryTeacherPageCommand command);

  /**
   * 修改教职工
   * @param command 教职工信息
   */
  Result<Void> updateTeacher(UpdateTeacherCommand command);

  /**
   * 删除教职工
   */
  Result<Void> deleteTeacher(DeleteTeacherCommand command);

  /**
   * 设该教职工为防疫人员
   */
  Result<Void> setTeacherToEpidemicPerson(SetTeacherToEpidemicPersonCommand command);

  /**
   * 查询本院系的防疫人员
   */
  Result<BasePageVO<QueryEpidemicPersonVO>>  queryEpidemicPerson(QueryEpidemicPersonCommand command);

  /**
   * 各学院防疫人数占比
   */
  Result<CountDeptEpidemicNumVO> countDeptEpidemicNum();

  /**
   * 下载教职工批量添加模版文件
   */
  void downloadTemplate(HttpServletRequest request, HttpServletResponse response)
      throws IOException;

  /**
   * 上传并解析教职工模版文件
   */
  Result uploadAndParseTemplate(MultipartFile file) throws IOException;
}
