package chnytrcy.xyz.campusepidemicsystem.config.shiro;

import com.alibaba.druid.sql.dialect.oracle.ast.expr.OracleDateTimeUnit;
import java.time.Year;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author chnytrcy
 */
@Component
public class SysTokenRepository {

    @Autowired
    private RedisTemplate redisTemplate;

    public TokenEntity findByToken(String token){
        TokenEntity t = (TokenEntity) redisTemplate.opsForValue().get(token);
        return (TokenEntity) redisTemplate.opsForValue().get(token);
    }

    public void addTokenEntity(TokenEntity tokenEntity){
        redisTemplate.opsForValue().set(tokenEntity.getToken(),tokenEntity,60, TimeUnit.DAYS);
    }

    public void updateTokenEntity(TokenEntity tokenEntity){
        redisTemplate.opsForValue().set(tokenEntity.getToken(),tokenEntity,60,TimeUnit.DAYS);
    }

    public void delTokenEntityByToken(String token) {
        redisTemplate.delete(token);
    }


}
