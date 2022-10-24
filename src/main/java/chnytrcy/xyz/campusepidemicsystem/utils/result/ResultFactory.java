package chnytrcy.xyz.campusepidemicsystem.utils.result;

import chnytrcy.xyz.campusepidemicsystem.model.enums.AuthenticationError;
import chnytrcy.xyz.campusepidemicsystem.model.enums.BusinessError;
import chnytrcy.xyz.campusepidemicsystem.model.enums.SuccessReturnCode;
import java.time.LocalDateTime;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.utils.result
 * @ClassName: ResultFactory
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/8/20 11:11 AM
 * @Version: 1.0
 */
public class ResultFactory {

  private static final Boolean SUCCESS_RETURN = true;

  private static final Boolean ERROR_RETURN = false;

  public static <T> Result<T> successResult(){
    Result result = Result.instance();
    result.setCode(SuccessReturnCode.NORMAL.getCode());
    result.setMessage(SuccessReturnCode.NORMAL.getDesc());
    result.setTime(LocalDateTime.now());
    result.setSuccess(SUCCESS_RETURN);
    return result;
  }

  public static <T> Result<T> successResult(T data){
    Result result = Result.instance();
    result.setData(data);
    result.setCode(SuccessReturnCode.NORMAL.getCode());
    result.setMessage(SuccessReturnCode.NORMAL.getDesc());
    result.setTime(LocalDateTime.now());
    result.setSuccess(SUCCESS_RETURN);
    return result;
  }

  public static <T> Result<T> successResult(SuccessReturnCode successReturnCode){
    Result result = Result.instance();
    result.setMessage(successReturnCode.getDesc());
    result.setCode(successReturnCode.getCode());
    result.setTime(LocalDateTime.now());
    result.setSuccess(SUCCESS_RETURN);
    return result;
  }

  public static <T> Result<T> successResult(SuccessReturnCode successReturnCode,T data){
    Result result = Result.instance();
    result.setCode(successReturnCode.getCode());
    result.setMessage(successResult().getMessage());
    result.setData(data);
    result.setTime(LocalDateTime.now());
    result.setSuccess(SUCCESS_RETURN);
    return result;
  }

  public static <T> Result<T> errorResult(BusinessError businessError){
    Result result = Result.instance();
    result.setCode(businessError.getCode());
    result.setMessage(businessError.getDesc());
    result.setTime(LocalDateTime.now());
    result.setSuccess(ERROR_RETURN);
    return result;
  }

  public static <T> Result<T> errorResultValid(BusinessError businessError,String message){
    Result result = Result.instance();
    result.setCode(businessError.getCode());
    result.setTime(LocalDateTime.now());
    result.setMessage(message);
    result.setSuccess(ERROR_RETURN);
    return result;
  }

  public static <T> Result<T> errorResultBusiness(String msg,Integer errorCode){
    Result result = Result.instance();
    result.setCode(errorCode);
    result.setSuccess(ERROR_RETURN);
    result.setTime(LocalDateTime.now());
    result.setMessage(msg);
    return result;
  }

  public static <T> Result<T> errorResultAuthentication(AuthenticationError errorCode){
    Result result = Result.instance();
    result.setCode(errorCode.getCode());
    result.setSuccess(ERROR_RETURN);
    result.setTime(LocalDateTime.now());
    result.setMessage(errorCode.getDesc());
    return result;
  }

}
