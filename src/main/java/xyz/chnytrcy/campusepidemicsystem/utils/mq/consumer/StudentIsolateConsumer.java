package xyz.chnytrcy.campusepidemicsystem.utils.mq.consumer;

import xyz.chnytrcy.campusepidemicsystem.model.constance.MQConstance;
import xyz.chnytrcy.campusepidemicsystem.model.dto.AbnormalStudentMessageDTO;
import xyz.chnytrcy.campusepidemicsystem.model.dto.AddMessageInfoDTO;
import com.zhenzi.sms.ZhenziSmsClient;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.chnytrcy.core.utils.sms.ZhenziSmsUtil;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.utils.mq.consumer
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
      value = @Queue(name = "direct.isolate"),
      exchange = @Exchange(name = MQConstance.EXCHANGE_QUARANTINE_NOTIFICATION),
      key = MQConstance.STUDENT_ISOLATE
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
      value = @Queue(name = "direct.abnormal"),
      exchange = @Exchange(name = MQConstance.EXCHANGE_ABNORMAL_MESSAGE),
      key = MQConstance.STUDENT_TREAT
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
