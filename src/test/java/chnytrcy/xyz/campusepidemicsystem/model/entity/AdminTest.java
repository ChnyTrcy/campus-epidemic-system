package chnytrcy.xyz.campusepidemicsystem.model.entity;


import chnytrcy.xyz.campusepidemicsystem.mapper.AdminMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.UserMapper;
import chnytrcy.xyz.campusepidemicsystem.model.entity.user.User;
import chnytrcy.xyz.campusepidemicsystem.model.enums.entity.RoleEnums;
import chnytrcy.xyz.campusepidemicsystem.utils.md5.MD5;
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
public class AdminTest {

  @Autowired private UserMapper userMapper;

  @Autowired private AdminMapper adminMapper;

  @Test
  public void addAdmin(){
    Scanner scanner = new Scanner(System.in);
    int i = 1;
    while(scanner.hasNext()){
      String next = scanner.next();
      User user = new User();
      user.setPhone("18358889334");
      user.setCreateUser("supAdmin");
      user.setUpdateUser("supAdmin");
      user.setAccount(next);
      user.setPassword(MD5.SysMd5(next,"123456"));
      userMapper.insert(user);
      userMapper.addUserRole(user.getId(), RoleEnums.ADMIN.getNumber());
      Admin admin = new Admin();
      String t;
      if(i < 10){
        t = "0" + i;
      }else {
        t = String.valueOf(i);
      }
      admin.setDeptCode(t);
      admin.setUserId(user.getId());
      adminMapper.insert(admin);
      i++;
    }
  }
}