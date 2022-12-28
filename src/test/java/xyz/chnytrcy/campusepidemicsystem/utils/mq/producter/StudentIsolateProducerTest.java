package xyz.chnytrcy.campusepidemicsystem.utils.mq.producter;

import xyz.chnytrcy.campusepidemicsystem.model.dto.AddMessageInfoDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
public class StudentIsolateProducerTest {

  @Autowired private StudentIsolateProducer studentIsolateProducer;

  @Test
  public void addMessage() {
    AddMessageInfoDTO addMessageInfoDTO = new AddMessageInfoDTO(
        "测试A",
        "华城街道",
        2,
        "校园集中隔离点C",
        "18358889334"
    );
    studentIsolateProducer.addMessage(addMessageInfoDTO);
  }
}