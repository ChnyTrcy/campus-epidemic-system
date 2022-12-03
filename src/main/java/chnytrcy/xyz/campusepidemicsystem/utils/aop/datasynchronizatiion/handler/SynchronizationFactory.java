package chnytrcy.xyz.campusepidemicsystem.utils.aop.datasynchronizatiion.handler;

import chnytrcy.xyz.campusepidemicsystem.model.enums.EntityEnums;
import chnytrcy.xyz.campusepidemicsystem.utils.aop.datasynchronizatiion.handler.chain.ClassEntityChain;
import chnytrcy.xyz.campusepidemicsystem.utils.aop.datasynchronizatiion.handler.chain.FeedbackAcceptanceChain;
import chnytrcy.xyz.campusepidemicsystem.utils.aop.datasynchronizatiion.handler.chain.HolidayStreetChain;
import chnytrcy.xyz.campusepidemicsystem.utils.aop.datasynchronizatiion.handler.chain.IsolationDetailChain;
import chnytrcy.xyz.campusepidemicsystem.utils.aop.datasynchronizatiion.handler.chain.IsolationPersonChain;
import chnytrcy.xyz.campusepidemicsystem.utils.aop.datasynchronizatiion.handler.chain.LeaveDetailChain;
import chnytrcy.xyz.campusepidemicsystem.utils.aop.datasynchronizatiion.handler.chain.StudentChain;
import chnytrcy.xyz.campusepidemicsystem.utils.aop.datasynchronizatiion.handler.chain.TeacherChain;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.utils.aop.datasynchronizatiion.handler
 * @ClassName: SynchronizationFactory
 * @Author: ChnyTrcy
 * @Description: 数据全量同步抽象子类工厂
 * @Date: 2022/12/3 22:26
 * @Version: 1.0
 */
@Service
@Slf4j
public class SynchronizationFactory {

  @Autowired private ClassEntityChain classEntityChain;

  @Autowired private FeedbackAcceptanceChain feedbackAcceptanceChain;

  @Autowired private HolidayStreetChain holidayStreetChain;

  @Autowired private IsolationDetailChain isolationDetailChain;

  @Autowired private IsolationPersonChain isolationPersonChain;

  @Autowired private LeaveDetailChain leaveDetailChain;

  @Autowired private StudentChain studentChain;

  @Autowired private TeacherChain teacherChain;

  public SynchronizationAbstract consumerAbstract(EntityEnums enums){
    SynchronizationAbstract key = null;
    switch (enums){
      case CLASS_ENTITY:
        key = classEntityChain;
        break;
      case FEEDBACK_ACCEPTANCE:
        key = feedbackAcceptanceChain;
        break;
      case HOLIDAY_STREET:
        key = holidayStreetChain;
        break;
      case ISOLATION_DETAIL:
        key = isolationDetailChain;
        break;
      case ISOLATION_PERSON:
        key = isolationPersonChain;
        break;
      case LEAVE_DETAIL:
        key = leaveDetailChain;
        break;
      case STUDENT:
        key = studentChain;
        break;
      case TEACHER:
        key = teacherChain;
        break;
      default:
        break;
    }
    if(ObjectUtil.isNull(key)){
      log.warn("构造子类失败,请检查枚举类型是否符合允许条件");
    }
    return key;
  }
}
