package xyz.chnytrcy.campusepidemicsystem.utils.mq.producter;

import xyz.chnytrcy.campusepidemicsystem.model.constance.MQConstance;
import xyz.chnytrcy.campusepidemicsystem.model.enums.EntityEnums;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.utils.mq.producter
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
