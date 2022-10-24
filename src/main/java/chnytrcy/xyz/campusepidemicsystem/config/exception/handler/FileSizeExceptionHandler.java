package chnytrcy.xyz.campusepidemicsystem.config.exception.handler;

import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import chnytrcy.xyz.campusepidemicsystem.utils.result.ResultFactory;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.config.exception.handler
 * @ClassName: FileSizeExceptionHandler
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/9/15 12:06 AM
 * @Version: 1.0
 */
@RestControllerAdvice
public class FileSizeExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(FileSizeExceptionHandler.class);

  @ExceptionHandler(SizeLimitExceededException.class)
  public Result businessException(SizeLimitExceededException exception){
    String error = "传输错误 --> 单个文件超出传输限制大小";
    logger.error(error);
    return ResultFactory.errorResultBusiness(error, 750);
  }

}
