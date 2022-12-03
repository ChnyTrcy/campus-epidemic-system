package chnytrcy.xyz.campusepidemicsystem.utils.aop.datasynchronizatiion.handler.chain;

import chnytrcy.xyz.campusepidemicsystem.mapper.ClassMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.MajorMapper;
import chnytrcy.xyz.campusepidemicsystem.model.entity.ClassEntity;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Major;
import chnytrcy.xyz.campusepidemicsystem.model.enums.EntityEnums;
import chnytrcy.xyz.campusepidemicsystem.service.pc.ClassService;
import chnytrcy.xyz.campusepidemicsystem.service.pc.MajorService;
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

  @Autowired private ClassService classService;

  @Autowired private MajorService majorService;

  @Override
  public String getTableComment() {
    return EntityEnums.CLASS_ENTITY.getTableComment();
  }

  @Override
  public synchronized void mainIn() {
    log.info(START_MESSAGE,getTableComment());
    Map<String, String> majorMap = majorService.list(null).stream()
        .collect(Collectors.toMap(Major::getCode, Major::getName));
    List<ClassEntity> classList = classService.list(null);
    classList.forEach(e -> {
      String code = e.getCode();
      String substring = code.substring(0, 2) + code.substring(code.length() - 2);
      e.setName(majorMap.get(e.getMajorCode()) + substring);
    });
    log.info(END_MESSAGE,getTableComment());
  }
}
