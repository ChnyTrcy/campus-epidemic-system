package chnytrcy.xyz.campusepidemicsystem.config.exception.handler;

import chnytrcy.xyz.campusepidemicsystem.config.exception.BusinessException;
import chnytrcy.xyz.campusepidemicsystem.config.exception.ErrorInfo;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import chnytrcy.xyz.campusepidemicsystem.utils.result.ResultFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.config.exception.handler
 * @ClassName: BusinessExceptionHandler
 * @Author: ChnyTrcy
 * @Description: 业务异常捕捉
 * @Date: 2022/8/23 4:18 PM
 * @Version: 1.0
 */
@RestControllerAdvice
public class BusinessExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(BusinessExceptionHandler.class);

  @ExceptionHandler({BusinessException.class})
  public Result businessException(BusinessException exception){
    ErrorInfo errorInfo = exception.getErrorInfo();
    StringBuilder sb = new StringBuilder();
    sb.append("业务错误 --> ").append(errorInfo.getMsg());
    logger.error(sb.toString());
    return ResultFactory.errorResultBusiness(sb.toString(), errorInfo.getCode());
  }
}
