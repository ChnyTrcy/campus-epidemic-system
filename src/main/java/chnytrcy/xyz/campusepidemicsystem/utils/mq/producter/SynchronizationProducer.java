package chnytrcy.xyz.campusepidemicsystem.utils.mq.producter;

import chnytrcy.xyz.campusepidemicsystem.model.constance.MQConstance;
import chnytrcy.xyz.campusepidemicsystem.model.enums.EntityEnums;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.utils.mq.producter
 * @ClassName: SynchronizationProducer
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/12/3 22:17
 * @Version: 1.0
 */
@Component
public class SynchronizationProducer {

  @Autowired private RabbitTemplate rabbitTemplate;

  /**
   * 全量数据同步生产者入口
   */
  public void addDataSynchronousEntrance(EntityEnums entityEnum){
    rabbitTemplate.convertAndSend(MQConstance.EXCHANGE_DATA_SYNCHRONIZATION, MQConstance.DATA_SYNCHRONOUS,entityEnum);
  }

}
