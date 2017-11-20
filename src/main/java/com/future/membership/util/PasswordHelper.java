package com.future.membership.util;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.future.membership.bean.bo.UserBo;


/**
 * @author Administrator
 * <p>Version: 1.0
 */
public class PasswordHelper {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

//    @Value("${password.algorithmName}")
    private String algorithmName = "md5";
//    @Value("${password.hashIterations}")
    private int hashIterations = 2;

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }

    public void encryptPassword(UserBo user) {
        //user.setSalt(randomNumberGenerator.nextBytes().toHex());
    	user.setSalt("b15759287d8eba77ebfe6bcd44f68953");
        System.out.println("user.getCredentialsSalt() :" + user.getCredentialsSalt());
        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                hashIterations).toHex();
        user.setPassword(newPassword);
    }
    
    public static void main(String[] args) {
		UserBo user = new UserBo();
		user.setUsername("shigen.wang");
		user.setPassword("123456");
		//user.setSalt("b15759287d8eba77ebfe6bcd44f68953");
		PasswordHelper ps = new PasswordHelper();
		ps.encryptPassword(user);
		System.out.println(user.getPassword());
	}
}
