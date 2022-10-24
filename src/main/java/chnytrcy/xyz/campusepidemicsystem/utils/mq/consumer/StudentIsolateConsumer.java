package chnytrcy.xyz.campusepidemicsystem.utils.mq.consumer;

import static chnytrcy.xyz.campusepidemicsystem.model.constance.MQConstance.EXCHANGE_NAME;

import chnytrcy.xyz.campusepidemicsystem.model.dto.AbnormalStudentMessageDTO;
import chnytrcy.xyz.campusepidemicsystem.model.dto.AddMessageInfoDTO;
import chnytrcy.xyz.campusepidemicsystem.utils.sms.ZhenziSmsUtil;
import com.zhenzi.sms.ZhenziSmsClient;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.utils.mq.consumer
 * @ClassName: StudentIsolateConsumer
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/8/29 5:44 PM
 * @Version: 1.0
 */
@Component
@Slf4j
public class StudentIsolateConsumer {

  @Autowired private ZhenziSmsUtil zhenziSmsClient;

  /**
   * 发送短信消费体
   * @param msg
   * @throws Exception
   */
  @RabbitListener(bindings = @QueueBinding(
      value = @Queue(name = "topic.queue"),
      exchange = @Exchange(name = EXCHANGE_NAME,type = ExchangeTypes.TOPIC),
      key = "student-isolate"
  ))
  public void listenStudentIsolate(AddMessageInfoDTO msg) throws Exception {
    ZhenziSmsClient client = zhenziSmsClient.zhenziSmsClient();
    Map<String, Object> params = new HashMap<>();
    params.put("number", msg.getPhone());
    params.put("templateId", "9949");
    String[] templateParams = new String[4];
    templateParams[0] = msg.getName();
    templateParams[1] = msg.getStreetName();
    String target = "";
    if (msg.getRiskLevel() == 1) {
      target = "中";
    } else {
      target = "高";
    }
    templateParams[2] = target;
    templateParams[3] = msg.getQuarantineLocation();
    params.put("templateParams", templateParams);
    String result = client.send(params);
    log.info("SMS短信服务" + result);
  }

  @RabbitListener(bindings = @QueueBinding(
      value = @Queue(name = "topic.queue"),
      exchange = @Exchange(name = EXCHANGE_NAME,type = ExchangeTypes.TOPIC),
      key = "student_treat"
  ))
  public void listenStudentAbnormal(AbnormalStudentMessageDTO msg) throws Exception {
    ZhenziSmsClient client = zhenziSmsClient.zhenziSmsClient();
    Map<String, Object> params = new HashMap<>();
    params.put("number", msg.getPhone());
    params.put("templateId", "10279");
    String[] templateParams = new String[1];
    templateParams[0] = msg.getName();
    params.put("templateParams", templateParams);
    String result = client.send(params);
    log.info("SMS短信服务" + result);
  }
}
