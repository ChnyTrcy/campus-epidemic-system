package chnytrcy.xyz.campusepidemicsystem.config.serializable;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.config.serializable
 * @ClassName: MessageConverter
 * @Author: ChnyTrcy
 * @Description: 指定序列化方式
 * @Date: 2022/8/29 5:42 PM
 * @Version: 1.0
 */
@Configuration
public class MessageSerializable {

  @Bean
  public MessageConverter messageConverter(){
    return new Jackson2JsonMessageConverter();
  }
}
