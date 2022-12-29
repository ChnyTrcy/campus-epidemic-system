package xyz.chnytrcy.campusepidemicsystem.utils.mq.consumer;


import xyz.chnytrcy.campusepidemicsystem.model.constance.MQConstance;
import xyz.chnytrcy.campusepidemicsystem.model.enums.EntityEnums;
import xyz.chnytrcy.campusepidemicsystem.utils.aop.datasynchronization.handler.SynchronizationAbstract;
import xyz.chnytrcy.campusepidemicsystem.utils.aop.datasynchronization.handler.SynchronizationFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.utils.mq.consumer
 * @ClassName: SynchronizationConsumer
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/12/3 22:22
 * @Version: 1.0
 */
@Slf4j
@Component
@RabbitListener(bindings = @QueueBinding(
    value = @Queue(name = "direct.queue",declare = "true",exclusive = "false",autoDelete = "false"),
    exchange = @Exchange(name = MQConstance.EXCHANGE_DATA_SYNCHRONIZATION),
    key = MQConstance.DATA_SYNCHRONOUS
))
public class SynchronizationConsumer {

  @Autowired private SynchronizationFactory factory;

  @RabbitHandler
  public void dataSynchronous(EntityEnums entityEnum){
    SynchronizationAbstract consumerAbstract = factory.consumerAbstract(entityEnum);
    consumerAbstract.mainIn();
  }

}
