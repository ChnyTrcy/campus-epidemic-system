package xyz.chnytrcy.campusepidemicsystem.utils.mq.producter;

import xyz.chnytrcy.campusepidemicsystem.model.constance.MQConstance;
import xyz.chnytrcy.campusepidemicsystem.model.dto.AbnormalStudentMessageDTO;
import xyz.chnytrcy.campusepidemicsystem.model.dto.AddMessageInfoDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.utils.mq.producter
 * @ClassName: StudentIsolateProducter
 * @Author: ChnyTrcy
 * @Description: 学生需要隔离发送消息
 * @Date: 2022/8/29 5:26 PM
 * @Version: 1.0
 */
@Component
public class StudentIsolateProducer {



  @Autowired private RabbitTemplate rabbitTemplate;

  /**
   * 隔离短信提醒
   */
  public void addMessage(AddMessageInfoDTO dto){
    rabbitTemplate.convertAndSend(MQConstance.EXCHANGE_QUARANTINE_NOTIFICATION, MQConstance.STUDENT_ISOLATE,dto);
  }

  /**
   * 体温异常短信提醒
   */
  public void abnormalMessage(AbnormalStudentMessageDTO msg){
    rabbitTemplate.convertAndSend(MQConstance.EXCHANGE_ABNORMAL_MESSAGE,MQConstance.STUDENT_TREAT,msg);
  }

}
