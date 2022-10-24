package chnytrcy.xyz.campusepidemicsystem.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.common
 * @ClassName: FileCommon
 * @Author: ChnyTrcy
 * @Description: 文件常用类
 * @Date: 2022/8/28 4:23 PM
 * @Version: 1.0
 */
@Component
public class FileCommon {

  public String getStr(File jsonFile){
    String jsonStr = "";
    try {
      FileReader fileReader = new FileReader(jsonFile);
      Reader reader = new InputStreamReader(new FileInputStream(jsonFile), StandardCharsets.UTF_8);
      int ch = 0;
      StringBuilder sb = new StringBuilder();
      while ((ch = reader.read()) != -1) {
        sb.append((char) ch);
      }
      fileReader.close();
      reader.close();
      jsonStr = sb.toString();
      return jsonStr;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
