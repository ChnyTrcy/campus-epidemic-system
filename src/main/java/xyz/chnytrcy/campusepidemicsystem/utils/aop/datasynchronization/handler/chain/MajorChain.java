package xyz.chnytrcy.campusepidemicsystem.utils.aop.datasynchronization.handler.chain;

import xyz.chnytrcy.campusepidemicsystem.model.entity.ClassEntity;
import xyz.chnytrcy.campusepidemicsystem.model.entity.IsolationPerson;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Major;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Student;
import xyz.chnytrcy.campusepidemicsystem.model.enums.EntityEnums;
import xyz.chnytrcy.campusepidemicsystem.service.pc.ClassService;
import xyz.chnytrcy.campusepidemicsystem.service.pc.IsolationPersonService;
import xyz.chnytrcy.campusepidemicsystem.service.pc.MajorService;
import xyz.chnytrcy.campusepidemicsystem.service.pc.StudentService;
import xyz.chnytrcy.campusepidemicsystem.utils.aop.datasynchronization.handler.SynchronizationAbstract;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.utils.aop.datasynchronization.handler.chain
 * @ClassName: MajorChain
 * @Author: ChnyTrcy
 * @Description: 同步class、isolation_person、student表
 * @Date: 2022/12/13 14:54
 * @Version: 1.0
 */
@Component
@Slf4j
public class MajorChain extends SynchronizationAbstract {

  static {
    log.info(INJECTION_MESSAGE,"专业");
  }

  @Autowired private MajorService majorService;

  @Autowired private ClassService classService;

  @Autowired private IsolationPersonService isolationPersonService;

  @Autowired private StudentService studentService;

  @Override
  protected String getTableComment() {
    return EntityEnums.MAJOR.getTableComment();
  }

  @Override
  public void mainIn() {
    log.info(START_MESSAGE,getTableComment());

    Map<String, String> majorMap = majorService.list().stream()
        .collect(Collectors.toMap(Major::getCode, Major::getName));
    List<ClassEntity> classEntityList = classService.list();
    classEntityList.forEach(e -> {
      e.setName(majorMap.get(e.getMajorCode()) + e.getYearHalf() + e.getCode().substring(e.getCode().length() - 2));
    });
    classService.updateBatchById(classEntityList);

    List<IsolationPerson> isolationPersonList = isolationPersonService.list();
    isolationPersonList.forEach(e -> {
      e.setMajorName(majorMap.get(e.getMajorCode()));
    });
    isolationPersonService.updateBatchById(isolationPersonList);

    List<Student> studentList = studentService.list();
    studentList.forEach(e -> {
      e.setMajorName(majorMap.get(e.getMajorCode()));
    });
    studentService.updateBatchById(studentList);

    log.info(END_MESSAGE,getTableComment());
  }
}
