package chnytrcy.xyz.campusepidemicsystem.utils.aop.datasynchronization.handler.chain;

import chnytrcy.xyz.campusepidemicsystem.model.entity.IsolationPerson;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Teacher;
import chnytrcy.xyz.campusepidemicsystem.model.enums.EntityEnums;
import chnytrcy.xyz.campusepidemicsystem.service.pc.IsolationPersonService;
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
 * @ClassName: TeacherChain
 * @Author: ChnyTrcy
 * @Description: 同步isolation_person表
 * @Date: 2022/12/13 14:55
 * @Version: 1.0
 */
@Component
@Slf4j
public class TeacherChain extends SynchronizationAbstract {

  static {
    log.info(INJECTION_MESSAGE,"教职工");
  }

  @Autowired private TeacherService teacherService;

  @Autowired private IsolationPersonService isolationPersonService;

  @Override
  protected String getTableComment() {
    return EntityEnums.TEACHER.getTableComment();
  }

  @Override
  public void mainIn() {
    log.info(START_MESSAGE,getTableComment());

    Map<String, String> teacherMap = teacherService.list().stream()
        .collect(Collectors.toMap(Teacher::getCode, Teacher::getName));
    List<IsolationPerson> isolationPersonList = isolationPersonService.list();
    isolationPersonList.forEach(e -> {
      e.setPreventionPersonnelName(teacherMap.get(e.getPreventionPersonnelCode()));
    });
    isolationPersonService.updateBatchById(isolationPersonList);

    log.info(END_MESSAGE,getTableComment());
  }
}
