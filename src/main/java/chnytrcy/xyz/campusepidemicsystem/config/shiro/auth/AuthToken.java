package chnytrcy.xyz.campusepidemicsystem.config.shiro.auth;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author chnytrcy
 */
public class AuthToken extends UsernamePasswordToken {

    private String token;

    public AuthToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}