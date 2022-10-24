package chnytrcy.xyz.campusepidemicsystem.config.minio;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.config
 * @ClassName: MinioClientConfig
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/9/14 10:26 PM
 * @Version: 1.0
 */
@Configuration
public class MinioClientConfig {

  @Value("${minio.endpoint}")
  private String endPoint;

  @Value("${minio.username}")
  private String username;

  @Value("${minio.password}")
  private String password;

  @Value("${minio.port}")
  private Integer port;

  @Bean
  public MinioClient minioClient(){
    return MinioClient.builder()
        .endpoint(endPoint,port,false)
        .credentials(username,password)
        .build();
  }
}
