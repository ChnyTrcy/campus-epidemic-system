package chnytrcy.xyz.campusepidemicsystem.config.exception.handler;

import chnytrcy.xyz.campusepidemicsystem.model.enums.BusinessError;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import chnytrcy.xyz.campusepidemicsystem.utils.result.ResultFactory;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName BadRequestExceptionHandler
 * @Description 自定义参数校验异常捕捉
 * @Author chnytrcy
 * @DATE 2022/8/21 8:00 PM
 * @Version 1.0
 */
@RestControllerAdvice
public class BadRequestExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(BadRequestExceptionHandler.class);

  /**
   * 校验错误拦截处理
   * @param exception 错误信息集合
   * @return 错误信息
   */
  @ExceptionHandler({BindException.class})
  public Result validationBodyException(BindException exception){
    BindingResult result = exception.getBindingResult();
    if (result.hasErrors()) {
      List<ObjectError> errors = result.getAllErrors();
      FieldError fieldError = (FieldError) errors.get(0);
      StringBuilder sb = new StringBuilder();
      sb.append("[对象：").append(fieldError.getObjectName()).append("] --> [字段:").append(fieldError.getField())
              .append("] --> [错误信息:").append(fieldError.getDefaultMessage()).append("]");
      logger.error(sb.toString());
      return ResultFactory.errorResultValid(BusinessError.VALID_DATA_ERROR,sb.toString());
    }
    return null;
  }

}
