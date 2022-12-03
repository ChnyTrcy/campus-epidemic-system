package chnytrcy.xyz.campusepidemicsystem.utils.aop.datasynchronizatiion.handler.chain;

import chnytrcy.xyz.campusepidemicsystem.utils.aop.datasynchronizatiion.handler.SynchronizationAbstract;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.utils.aop.datasynchronizatiion.handler.chain
 * @ClassName: IsolationDetailChain
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/12/3 22:38
 * @Version: 1.0
 */
@Component
@Slf4j
public class IsolationDetailChain extends SynchronizationAbstract {

  @Override
  public String getTableComment() {
    return null;
  }

  @Override
  public void mainIn() {

  }
}
