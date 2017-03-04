package scau.zzf.base.util;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import scau.zzf.entity.User;


/**
 * Created by Administrator on 2016/9/7.
 */
public class PasswordHelper {
    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private static String algorithmName = "md5";
    private static int hashIterations = 2;

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }

    public static void encryptPassword(User user) {

        user.setSalt(randomNumberGenerator.nextBytes().toHex());

        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                hashIterations
        ).toHex();

        user.setPassword(newPassword);
    }


    public static boolean comparePassword(User user, String inputPassword){

        String newPassword = new SimpleHash(
                algorithmName,
                inputPassword,
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                hashIterations
        ).toHex();
        if(user.getPassword().equals(newPassword)){
            return true;
        }else {
            return false;
        }
    }


}
