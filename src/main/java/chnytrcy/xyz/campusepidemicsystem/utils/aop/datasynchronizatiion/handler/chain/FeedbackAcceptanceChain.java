package chnytrcy.xyz.campusepidemicsystem.utils.aop.datasynchronizatiion.handler.chain;

import chnytrcy.xyz.campusepidemicsystem.model.entity.ClassEntity;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Student;
import chnytrcy.xyz.campusepidemicsystem.model.enums.EntityEnums;
import chnytrcy.xyz.campusepidemicsystem.utils.aop.datasynchronizatiion.handler.SynchronizationAbstract;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.utils.aop.datasynchronizatiion.handler.chain
 * @ClassName: FeedbackAcceptanceChain
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/12/3 22:37
 * @Version: 1.0
 */
@Component
@Slf4j
public class FeedbackAcceptanceChain extends SynchronizationAbstract {

  static {
    log.info(INJECTION_MESSAGE,"反馈受理");
  }

  @Override
  public String getTableComment() {
    return EntityEnums.FEEDBACK_ACCEPTANCE.getTableComment();
  }

  @Override
  public void mainIn() {
    log.info(START_MESSAGE,getTableComment());




    log.info(END_MESSAGE,getTableComment());
  }
}
