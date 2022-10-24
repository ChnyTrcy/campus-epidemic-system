package chnytrcy.xyz.campusepidemicsystem.config.exception;

import chnytrcy.xyz.campusepidemicsystem.model.enums.AuthenticationError;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.config.exception
 * @ClassName: UserAuthenticationException
 * @Author: ChnyTrcy
 * @Description: 用户认证异常类
 * @Date: 2022/8/23 5:42 PM
 * @Version: 1.0
 */
public class UserAuthenticationException extends RuntimeException{

  protected ErrorInfo errorInfo;

  public UserAuthenticationException() {
  }

  public UserAuthenticationException(AuthenticationError code, Object... args) {
    super(String.format(code.getDesc(), args));
    this.errorInfo = new ErrorInfo(code.getCode(), String.format(code.getDesc(), args));
  }

  public UserAuthenticationException(AuthenticationError code, Throwable cause, Object... args) {
    super(String.format(code.getDesc(), args),cause);
    this.errorInfo = new ErrorInfo(code.getCode(), String.format(code.getDesc(), args));
  }

  public UserAuthenticationException(Throwable cause) {
    super(cause);
  }

  protected UserAuthenticationException(AuthenticationError code, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Object... args) {
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
