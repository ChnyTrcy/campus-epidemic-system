package chnytrcy.xyz.campusepidemicsystem.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.utils
 * @ClassName: PrintFileContent
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/11/25 20:44
 * @Version: 1.0
 */
public class PrintFileContent {

  public static void printFileContent(Object obj) throws IOException {
    if (null == obj) {
      throw new RuntimeException("参数为空");
    }
    BufferedReader reader = null;
    // 如果是文件路径
    if (obj instanceof String) {
      reader = new BufferedReader(new FileReader(new File((String) obj)));
      // 如果是文件输入流
    } else if (obj instanceof InputStream) {
      reader = new BufferedReader(new InputStreamReader((InputStream) obj));
    }
    String line = null;
    while ((line = reader.readLine()) != null) {
      System.out.println(line);
    }
    reader.close();
  }
}
