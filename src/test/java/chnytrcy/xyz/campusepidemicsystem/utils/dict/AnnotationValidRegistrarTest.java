package chnytrcy.xyz.campusepidemicsystem.utils.dict;

import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
public class AnnotationValidRegistrarTest {

  @Autowired private AnnotationValidRegistrar annotationValidRegistrar;

  @Test
  public void test1() throws IOException, ClassNotFoundException {
    annotationValidRegistrar.initEnums();
  }
}