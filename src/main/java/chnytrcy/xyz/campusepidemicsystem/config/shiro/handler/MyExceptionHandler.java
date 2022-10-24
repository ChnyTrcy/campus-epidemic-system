package chnytrcy.xyz.campusepidemicsystem.config.shiro.handler;

import chnytrcy.xyz.campusepidemicsystem.model.enums.AuthenticationError;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import chnytrcy.xyz.campusepidemicsystem.utils.result.ResultFactory;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description: 登陆异常全局处理
 * @Author: ChnyTrcy
 * @Date: 2022/8/20 6:22 PM
 */
@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = AuthorizationException.class)
    public Result handleException(AuthorizationException e) {
        Result result;
        //获取错误中中括号的内容
        String message = e.getMessage();
        //判断是角色错误还是权限错误
        if (message.contains("role")) {
            result = ResultFactory.errorResultAuthentication(AuthenticationError.LOGIN_ROLE_ERROR);
        } else if (message.contains("permission")) {
            result = ResultFactory.errorResultAuthentication(AuthenticationError.LOGON_PERMISSION_MISSING_ERROR);
        } else {
            result = ResultFactory.errorResultAuthentication(AuthenticationError.LOGIN_PERMISSION_ERROR);
        }
        return result;
    }
}
