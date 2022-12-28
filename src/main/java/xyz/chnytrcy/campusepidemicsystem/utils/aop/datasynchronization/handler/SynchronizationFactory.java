package xyz.chnytrcy.campusepidemicsystem.utils.aop.datasynchronization.handler;

import xyz.chnytrcy.campusepidemicsystem.model.enums.EntityEnums;
import xyz.chnytrcy.campusepidemicsystem.utils.aop.datasynchronization.handler.chain.ClassEntityChain;
import xyz.chnytrcy.campusepidemicsystem.utils.aop.datasynchronization.handler.chain.DeptChain;
import xyz.chnytrcy.campusepidemicsystem.utils.aop.datasynchronization.handler.chain.MajorChain;
import xyz.chnytrcy.campusepidemicsystem.utils.aop.datasynchronization.handler.chain.StudentChain;
import xyz.chnytrcy.campusepidemicsystem.utils.aop.datasynchronization.handler.chain.TeacherChain;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.utils.aop.datasynchronizatiion.handler
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

  @Autowired private DeptChain deptChain;

  @Autowired private MajorChain majorChain;

  @Autowired private StudentChain studentChain;

  @Autowired private TeacherChain teacherChain;

  public SynchronizationAbstract consumerAbstract(EntityEnums enums){
    SynchronizationAbstract key = null;
    switch (enums){
      case CLASS_ENTITY:
        key = classEntityChain;
        break;
      case DEPT:
        key = deptChain;
        break;
      case MAJOR:
        key = majorChain;
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
