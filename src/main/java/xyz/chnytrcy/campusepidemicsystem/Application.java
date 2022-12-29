package xyz.chnytrcy.campusepidemicsystem;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication(scanBasePackages = {"xyz.chnytrcy.*"})
@EnableTransactionManagement
@EnableCaching
@EnableAsync
@EnableScheduling
@Slf4j
public class Application {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
    Environment environment = context.getBean(Environment.class);
    System.out.println("\n===========================================================项目启动成功=========================================================\n"
        + "   _____                                    _____      _     _                _         _____           _                 \n"
        + "  /  __ \\                                  |  ___|    (_)   | |              (_)       /  ___|         | |                \n"
        + "  | /  \\/ __ _ _ __ ___  _ __  _   _ ___   | |__ _ __  _  __| | ___ _ __ ___  _  ___   \\ `--. _   _ ___| |_ ___ _ __ ___  \n"
        + "  | |    / _` | '_ ` _ \\| '_ \\| | | / __|  |  __| '_ \\| |/ _` |/ _ \\ '_ ` _ \\| |/ __|   `--. \\ | | / __| __/ _ \\ '_ ` _ \\ \n"
        + "  | \\__/\\ (_| | | | | | | |_) | |_| \\__ \\  | |__| |_) | | (_| |  __/ | | | | | | (__   /\\__/ / |_| \\__ \\ ||  __/ | | | | |\n"
        + "   \\____/\\__,_|_| |_| |_| .__/ \\__,_|___/  \\____/ .__/|_|\\__,_|\\___|_| |_| |_|_|\\___|  \\____/ \\__, |___/\\__\\___|_| |_| |_|\n"
        + "                        | |                     | |                                            __/ |                      \n"
        + "                        |_|                     |_|                                           |___/                       \n");
    log.debug("访问地址为:http://localhost:"+environment.getProperty("server.port"+"\n"));
  }

}
