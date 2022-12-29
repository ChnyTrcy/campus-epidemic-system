package xyz.chnytrcy.campusepidemicsystem.model.constance;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.constance
 * @InterfaceName: MQConstance
 * @Author: ChnyTrcy
 * @Description: 消息队列常量
 * @Date: 2022/9/13 4:03 PM
 * @Version: 1.0
 */
public interface MQConstance {
  String EXCHANGE_ABNORMAL_MESSAGE = "abnormal_message";

  String EXCHANGE_QUARANTINE_NOTIFICATION = "quarantine_notification";

  String EXCHANGE_DATA_SYNCHRONIZATION = "data_synchronization";

  String STUDENT_ISOLATE = "student-isolate";

  String STUDENT_TREAT = "student_treat";

  String DATA_SYNCHRONOUS = "data-synchronous";
}
