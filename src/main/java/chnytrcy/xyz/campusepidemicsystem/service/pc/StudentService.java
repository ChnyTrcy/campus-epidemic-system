package chnytrcy.xyz.campusepidemicsystem.service.pc;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BasePageVO;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.student.AddStudentCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.student.DeleteStudentCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.student.QueryStudentByKeywordCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.student.UpdateStudentCommand;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Student;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.student.QueryStudentByKeywordVO;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.pc
 * @InterfaceName: StudentService
 * @Author: ChnyTrcy
 * @Description: 学生服务接口
 * @Date: 2022/8/24 3:14 PM
 * @Version: 1.0
 */
public interface StudentService extends IService<Student> {

  /**
   * 添加单个学生
   */
  Result<Void>addStudent(AddStudentCommand command);

  /**
   * 根据关键词查询学生数据表
   */
  Result<BasePageVO<QueryStudentByKeywordVO>>  queryStudentByKeyword(
      QueryStudentByKeywordCommand command);

  /**
   * 修改学生信息
   * @param command 学生信息
   */
  Result<Void> updateStudent(UpdateStudentCommand command);

  /**
   * 删除该学生
   */
  Result<Void> deleteStudent(DeleteStudentCommand command);
}
