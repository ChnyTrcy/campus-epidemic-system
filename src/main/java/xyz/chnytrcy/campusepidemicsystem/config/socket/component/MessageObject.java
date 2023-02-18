package xyz.chnytrcy.campusepidemicsystem.config.socket.component;

import lombok.Builder;
import lombok.Data;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.config.socket.component
 * @ClassName: ConnectionObject
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2023/1/10 13:23
 * @Version: 1.0
 */
@Data
@Builder
public class MessageObject {
  private String from;
  private String message;

}
