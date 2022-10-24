package chnytrcy.xyz.campusepidemicsystem.utils.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.utils.result
 * @ClassName: Result
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/8/20 11:02 AM
 * @Version: 1.0
 */

@ApiModel("返回类")
public class Result<T> implements Serializable {

  private static final long serialVersionUID = 8485491078405247622L;


  private Result() {
  }

  protected static Result instance(){
    return new Result();
  }

  @ApiModelProperty("消息")
  private String message;

  @ApiModelProperty("是否正确返回")
  private Boolean success;

  @ApiModelProperty("状态码")
  private Integer code;

  @ApiModelProperty("返回数据")
  private T data;

  @ApiModelProperty("方法返回时间")
  @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  private LocalDateTime time;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public LocalDateTime getTime() {
    return time;
  }

  public void setTime(LocalDateTime time) {
    this.time = time;
  }

  public Boolean getSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }
}
