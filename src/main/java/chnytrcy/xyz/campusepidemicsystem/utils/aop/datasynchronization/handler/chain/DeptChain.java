package chnytrcy.xyz.campusepidemicsystem.utils.aop.datasynchronization.handler.chain;

import chnytrcy.xyz.campusepidemicsystem.model.entity.Dept;
import chnytrcy.xyz.campusepidemicsystem.model.entity.IsolationPerson;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Major;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Student;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Teacher;
import chnytrcy.xyz.campusepidemicsystem.model.enums.EntityEnums;
import chnytrcy.xyz.campusepidemicsystem.service.pc.DeptService;
import chnytrcy.xyz.campusepidemicsystem.service.pc.IsolationPersonService;
import chnytrcy.xyz.campusepidemicsystem.service.pc.MajorService;
import chnytrcy.xyz.campusepidemicsystem.service.pc.StudentService;
import chnytrcy.xyz.campusepidemicsystem.service.pc.TeacherService;
import chnytrcy.xyz.campusepidemicsystem.utils.aop.datasynchronization.handler.SynchronizationAbstract;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.utils.aop.datasynchronization.handler.chain
 * @ClassName: DeptChain
 * @Author: ChnyTrcy
 * @Description: 同步isolation_person、major、student、teacher表
 * @Date: 2022/12/13 14:55
 * @Version: 1.0
 */
@Component
@Slf4j
public class DeptChain extends SynchronizationAbstract {

  @Autowired private DeptService deptService;

  @Autowired private IsolationPersonService isolationPersonService;

  @Autowired private MajorService majorService;

  @Autowired private StudentService studentService;

  @Autowired private TeacherService teacherService;

  static {
    log.info(INJECTION_MESSAGE,"院系");
  }

  @Override
  protected String getTableComment() {
    return EntityEnums.DEPT.getTableComment();
  }

  @Override
  public synchronized void mainIn() {
    log.info(START_MESSAGE,getTableComment());

    Map<String, String> deptMap = deptService.list().stream()
        .collect(Collectors.toMap(Dept::getCode, Dept::getName));
    List<IsolationPerson> isolationPersonList = isolationPersonService.list();
    isolationPersonList.forEach(e -> {
      e.setDeptName(deptMap.get(e.getDeptCode()));
    });
    isolationPersonService.updateBatchById(isolationPersonList);

    List<Major> majorList = majorService.list();
    majorList.forEach(e -> {
      e.setDeptName(deptMap.get(e.getDeptCode()));
      e.setFullName(e.getDeptName() + e.getName() + "专业");
    });
    majorService.updateBatchById(majorList);

    List<Student> studentList = studentService.list();
    studentList.forEach(e -> {
      e.setDeptName(deptMap.get(e.getDeptCode()));
    });
    studentService.updateBatchById(studentList);

    List<Teacher> teacherList = teacherService.list();
    teacherList.forEach(e -> {
      e.setDeptName(deptMap.get(e.getDeptCode()));
    });
    teacherService.updateBatchById(teacherList);

    log.info(END_MESSAGE,getTableComment());
  }
}
