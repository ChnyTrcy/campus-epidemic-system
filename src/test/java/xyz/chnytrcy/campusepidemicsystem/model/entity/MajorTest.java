package xyz.chnytrcy.campusepidemicsystem.model.entity;


import xyz.chnytrcy.campusepidemicsystem.mapper.MajorMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
public class MajorTest {

  @Autowired
  private MajorMapper majorMapper;

  @Test
  public void addMajor(){
     Major major = new Major(
         "0201",
         "计算机科学与技术",
         "02",
         "计算机学院",
         "计算机学院计算机科学与技术专业"
     );
     major.setCreateUser("supAdmin");
     major.setUpdateUser("supAdmin");
     majorMapper.insert(major);
     major.setId(null);
     major.setCode("0202");
     major.setName("数字媒体");
     major.setFullName("计算机学院数字媒体专业");
     majorMapper.insert(major);
     major.setId(null);
     major.setCode("0203");
     major.setName("软件工程");
     major.setFullName("计算机学院软件工程专业");
     majorMapper.insert(major);
     major.setId(null);
     major.setCode("0204");
     major.setName("大数据统计与技术");
     major.setFullName("计算机学院大数据统计与技术专业");
     majorMapper.insert(major);
  }
}