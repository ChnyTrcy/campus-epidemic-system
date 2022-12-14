package chnytrcy.xyz.campusepidemicsystem.utils.aop.datasynchronization.handler;

import lombok.extern.slf4j.Slf4j;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.utils.aop.datasynchronizatiion.handler
 * @ClassName: SynchronizationAbstract
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/12/3 22:31
 * @Version: 1.0
 */
@Slf4j
public abstract class SynchronizationAbstract implements BaseDeal{

  protected static final String INJECTION_MESSAGE = "<{}>同步链注入成功";

  protected static final String START_MESSAGE = "<{}>数据表开始同步";

  protected static final String END_MESSAGE = "<{}>数据表同步结束";

  protected abstract String getTableComment();

  public abstract void mainIn();

  protected static SynchronizationAbstract buildEmpty(){
    return new SynchronizationAbstract() {
      @Override
      protected String getTableComment() {
        return null;
      }

      @Override
      public void mainIn() {
      }
    };
  }

}
