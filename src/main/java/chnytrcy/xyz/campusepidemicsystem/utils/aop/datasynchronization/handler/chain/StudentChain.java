package chnytrcy.xyz.campusepidemicsystem.utils.aop.datasynchronization.handler.chain;

import chnytrcy.xyz.campusepidemicsystem.model.entity.HolidayStreet;
import chnytrcy.xyz.campusepidemicsystem.model.entity.IsolationDetail;
import chnytrcy.xyz.campusepidemicsystem.model.entity.IsolationPerson;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Leave;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Student;
import chnytrcy.xyz.campusepidemicsystem.model.enums.EntityEnums;
import chnytrcy.xyz.campusepidemicsystem.service.pc.HolidayStreetService;
import chnytrcy.xyz.campusepidemicsystem.service.pc.IsolationDetailService;
import chnytrcy.xyz.campusepidemicsystem.service.pc.IsolationPersonService;
import chnytrcy.xyz.campusepidemicsystem.service.pc.LeaveService;
import chnytrcy.xyz.campusepidemicsystem.service.pc.StudentService;
import chnytrcy.xyz.campusepidemicsystem.utils.aop.datasynchronization.handler.SynchronizationAbstract;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.utils.aop.datasynchronization.handler.chain
 * @ClassName: StudentChain
 * @Author: ChnyTrcy
 * @Description: 同步holiday_street、isolation_detail、isolation_person、leave_daily表
 * @Date: 2022/12/13 14:55
 * @Version: 1.0
 */
@Component
@Slf4j
public class StudentChain extends SynchronizationAbstract {

  static {
    log.info(INJECTION_MESSAGE,"学生");
  }

  @Autowired private StudentService studentService;

  @Autowired private HolidayStreetService holidayStreetService;

  @Autowired private IsolationDetailService isolationDetailService;

  @Autowired private IsolationPersonService isolationPersonService;

  @Autowired private LeaveService leaveService;

  @Override
  protected String getTableComment() {
    return EntityEnums.STUDENT.getTableComment();
  }

  @Override
  public void mainIn() {
    log.info(START_MESSAGE,getTableComment());

    Map<String, String> studentMap = studentService.list().stream()
        .collect(Collectors.toMap(Student::getCode, Student::getName));
    List<HolidayStreet> holidayStreetList = holidayStreetService.list();
    holidayStreetList.forEach(e -> {
      e.setName(studentMap.get(e.getCode()));
    });
    holidayStreetService.updateBatchById(holidayStreetList);

    List<IsolationDetail> isolationDetailList = isolationDetailService.list();
    isolationDetailList.forEach(e -> {
      e.setName(studentMap.get(e.getCode()));
    });
    isolationDetailService.updateBatchById(isolationDetailList);

    List<IsolationPerson> isolationPersonList = isolationPersonService.list();
    isolationPersonList.forEach(e -> {
      e.setName(studentMap.get(e.getCode()));
    });
    isolationPersonService.updateBatchById(isolationPersonList);

    List<Leave> leaveList = leaveService.list();
    leaveList.forEach(e -> {
      e.setName(studentMap.get(e.getCode()));
    });
    leaveService.updateBatchById(leaveList);

    log.info(END_MESSAGE,getTableComment());
  }
}
