package chnytrcy.xyz.campusepidemicsystem.config.exception;

import java.io.Serializable;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.config.exception
 * @ClassName: ErrorInfo
 * @Author: ChnyTrcy
 * @Description: 错误信息
 * @Date: 2022/8/23 1:44 PM
 * @Version: 1.0
 */
public class ErrorInfo implements Serializable {

  private static final long serialVersionUID = -8341070242774078741L;
  private Integer code = -1;
  private String msg = "";

  public Integer getCode() {
    return code;
  }

  public ErrorInfo setCode(Integer code) {
    this.code = code;
    return this;
  }

  public String getMsg() {
    return msg;
  }

  public ErrorInfo setMsg(String msg) {
    this.msg = msg;
    return this;
  }

  public ErrorInfo() {
  }

  public ErrorInfo(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
  }
}
