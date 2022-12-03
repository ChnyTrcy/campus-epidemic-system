package chnytrcy.xyz.campusepidemicsystem.utils.mq.producter;

import static chnytrcy.xyz.campusepidemicsystem.model.constance.MQConstance.EXCHANGE_NAME;

import chnytrcy.xyz.campusepidemicsystem.model.constance.MQConstance;
import chnytrcy.xyz.campusepidemicsystem.model.dto.AbnormalStudentMessageDTO;
import chnytrcy.xyz.campusepidemicsystem.model.dto.AddMessageInfoDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.utils.mq.producter
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
    rabbitTemplate.convertAndSend(EXCHANGE_NAME, MQConstance.STUDENT_ISOLATE,dto);
  }

  /**
   * 体温异常短信提醒
   */
  public void abnormalMessage(AbnormalStudentMessageDTO msg){
    rabbitTemplate.convertAndSend(EXCHANGE_NAME,MQConstance.STUDENT_TREAT,msg);
  }

}
