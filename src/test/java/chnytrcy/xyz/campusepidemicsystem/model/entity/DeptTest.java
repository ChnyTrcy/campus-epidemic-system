package chnytrcy.xyz.campusepidemicsystem.model.entity;


import chnytrcy.xyz.campusepidemicsystem.mapper.DeptMapper;
import java.util.Scanner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
public class DeptTest {

  @Autowired
  private DeptMapper deptMapper;

  @Test
  public void addDept(){
    int i = 1;
    Scanner scanner = new Scanner(System.in);
    while (true){
      String deptName = scanner.next();
      String deptNo;
      if(i < 10){
        deptNo = "0" + i;
      }else {
        deptNo = String.valueOf(i);
      }
      Dept entity = new Dept(deptNo,deptName);
      entity.setCreateUser("supAdmin");
      entity.setUpdateUser("supAdmin");
      deptMapper.insert(entity);
      i++;
    }
  }
}