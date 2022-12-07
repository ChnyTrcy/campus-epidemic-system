package chnytrcy.xyz.campusepidemicsystem.utils.aop.datasynchronizatiion.handler.chain;

import chnytrcy.xyz.campusepidemicsystem.mapper.ClassMapper;
import chnytrcy.xyz.campusepidemicsystem.model.entity.ClassEntity;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Major;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Student;
import chnytrcy.xyz.campusepidemicsystem.model.enums.EntityEnums;
import chnytrcy.xyz.campusepidemicsystem.service.pc.ClassService;
import chnytrcy.xyz.campusepidemicsystem.service.pc.MajorService;
import chnytrcy.xyz.campusepidemicsystem.service.pc.StudentService;
import chnytrcy.xyz.campusepidemicsystem.utils.aop.datasynchronizatiion.handler.SynchronizationAbstract;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.utils.aop.datasynchronizatiion.handler.chain
 * @ClassName: ClassEntityChain
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/12/3 22:37
 * @Version: 1.0
 */
@Component
@Slf4j
public class ClassEntityChain extends SynchronizationAbstract {

  static {
    log.info(INJECTION_MESSAGE,"班级");
  }

  @Autowired private ClassMapper classMapper;

  @Autowired private StudentService studentService;

  @Override
  public String getTableComment() {
    return EntityEnums.CLASS_ENTITY.getTableComment();
  }

  @Override
  public synchronized void mainIn() {
    log.info(START_MESSAGE,getTableComment());
    Map<String, String> classMap = classMapper.selectList(null).stream()
        .collect(Collectors.toMap(ClassEntity::getCode, ClassEntity::getName));
    List<Student> list = studentService.list();
    list.forEach(e -> {
      e.setClassName(classMap.get(e.getClassCode()));
    });
    studentService.updateBatchById(list);

    log.info(END_MESSAGE,getTableComment());

  }
}
