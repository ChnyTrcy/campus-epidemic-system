package chnytrcy.xyz.campusepidemicsystem.config.exception;


import chnytrcy.xyz.campusepidemicsystem.model.enums.BusinessError;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.config.exception
 * @ClassName: BusinessException
 * @Author: ChnyTrcy
 * @Description: 业务异常类
 * @Date: 2022/8/23 1:39 PM
 * @Version: 1.0
 */

public class BusinessException extends RuntimeException{

  protected ErrorInfo errorInfo;

  public BusinessException() {
  }

  public BusinessException(BusinessError code, Object... args) {
    super(String.format(code.getDesc(), args));
    this.errorInfo = new ErrorInfo(code.getCode(), String.format(code.getDesc(), args));
  }

  public BusinessException(BusinessError code, Throwable cause, Object... args) {
    super(String.format(code.getDesc(), args),cause);
    this.errorInfo = new ErrorInfo(code.getCode(), String.format(code.getDesc(), args));
  }

  public BusinessException(Throwable cause) {
    super(cause);
  }

  protected BusinessException(BusinessError code, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Object... args) {
    super(String.format(code.getDesc(), args),cause,enableSuppression,writableStackTrace);
    this.errorInfo = new ErrorInfo(code.getCode(), String.format(code.getDesc(), args));
  }

  @Override
  public String getMessage() {
    return super.getMessage();
  }

  public Integer getErrorCode() {
    return this.errorInfo.getCode();
  }

  public ErrorInfo getErrorInfo() {
    return this.errorInfo;
  }

}
