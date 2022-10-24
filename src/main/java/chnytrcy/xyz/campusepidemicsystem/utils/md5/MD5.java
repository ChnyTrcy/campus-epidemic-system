package chnytrcy.xyz.campusepidemicsystem.utils.md5;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @author chnytrcy
 */
public class MD5 {

    public static String SysMd5(String username, String password) {
        String hashAlgorithmName = "MD5";
        ByteSource salt = ByteSource.Util.bytes(username);
        int hashIterations = 5;
        return new SimpleHash(hashAlgorithmName, password, salt, hashIterations).toHex();
    }
}
