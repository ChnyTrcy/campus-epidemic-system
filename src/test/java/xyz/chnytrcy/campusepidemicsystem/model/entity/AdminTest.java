package xyz.chnytrcy.campusepidemicsystem.model.entity;


import xyz.chnytrcy.campusepidemicsystem.mapper.AdminMapper;
import xyz.chnytrcy.campusepidemicsystem.mapper.UserMapper;
import xyz.chnytrcy.campusepidemicsystem.model.entity.user.User;
import xyz.chnytrcy.campusepidemicsystem.model.enums.entity.RoleEnums;
import java.util.Scanner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.chnytrcy.core.utils.md5.MD5;

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