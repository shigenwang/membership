package com.future.membership.util;

import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
 * 加密解密类。支持MD5,SHA1,DES,AES<br>
 * 单向加密：MD5、SHA1<br>
 * 双向加密：DES、AES<br>
 * @package  com.future.membership.utils
 * @author 
 * @2015年7月2日 @下午3:39:47
 */
public class EncryptUtils {

	public static final String MD5 = "MD5";
	public static final String SHA1 = "SHA1";
	public static final String HmacMD5 = "HmacMD5";
	public static final String HmacSHA1 = "HmacSHA1";
	public static final String DES = "DES";
	public static final String AES = "AES";

	/**编码格式；默认null为GBK*/
	public String charset = "UTF-8";
	/**DES*/
	public int keysizeDES = 0;
	/**AES*/
	public int keysizeAES = 128;
	public static EncryptUtils encryptUtils;
	
	private EncryptUtils(){
        //单例
    }
	
	public static EncryptUtils getInstance(){
        if (encryptUtils==null) {
        	encryptUtils = new EncryptUtils();
        }
        return encryptUtils;
    }
	
	
	/**使用MessageDigest进行单向加密（无密码）*/
    private String messageDigest(String res,String algorithm){
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] resBytes = charset==null?res.getBytes():res.getBytes(charset);
            return parseByte2HexStr(md.digest(resBytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
     
    /**使用KeyGenerator进行单向/双向加密（可设密码）*/
    private String keyGeneratorMac(String res,String algorithm,String key){
        try {
            SecretKey sk = null;
            if (key==null) {
                KeyGenerator kg = KeyGenerator.getInstance(algorithm);
                sk = kg.generateKey();
            }else {
                byte[] keyBytes = charset==null?key.getBytes():key.getBytes(charset);
                sk = new SecretKeySpec(keyBytes, algorithm);
            }
            Mac mac = Mac.getInstance(algorithm);
            mac.init(sk);
            byte[] result = mac.doFinal(res.getBytes());
            return parseByte2HexStr(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
 
    /**使用KeyGenerator双向加密，DES/AES，注意这里转化为字符串的时候是将2进制转为16进制格式的字符串，不是直接转，因为会出错*/
    private String keyGeneratorES(String res,String algorithm,String key,int keysize,boolean isEncode){
        try {
            KeyGenerator kg = KeyGenerator.getInstance(algorithm);
            SecureRandom secureRandom = new SecureRandom().getInstance("SHA1PRNG");
            if (keysize == 0) {
                byte[] keyBytes = charset==null?key.getBytes():key.getBytes(charset);
                secureRandom.setSeed(keyBytes);
                kg.init(secureRandom);
            }else if (key==null) {
                kg.init(keysize);
            }else {
                byte[] keyBytes = charset==null?key.getBytes():key.getBytes(charset);
                kg.init(keysize, secureRandom);
            }
            SecretKey sk = kg.generateKey();
            SecretKeySpec sks = new SecretKeySpec(sk.getEncoded(), algorithm);
            Cipher cipher = Cipher.getInstance(algorithm);
            if (isEncode) {
                cipher.init(Cipher.ENCRYPT_MODE, sks);
                byte[] resBytes = charset==null?res.getBytes():res.getBytes(charset);
                return parseByte2HexStr(cipher.doFinal(resBytes));
            }else {
                cipher.init(Cipher.DECRYPT_MODE, sks);
                return new String(cipher.doFinal(parseHexStr2Byte(res)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

	/**
	 * 二进制转换成16进制
	 * @param buf
	 * @return
	 */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);  
            if (hex.length() == 1) {
                hex = '0' + hex;  
            }
            sb.append(hex.toUpperCase());  
        }
        return sb.toString();  
    }
    
    /**
     * 16进制转2进制
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);  
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);  
            result[i] = (byte) (high * 16 + low);  
        }
        return result;  
    }
	
	
    
    public String MD5(String res) {
        return messageDigest(res, MD5);
    }
 
    
    public String HmacMD5(String res, String key) {
        return keyGeneratorMac(res, HmacMD5, key);
    }
 
    /**
     * SHA1 加密
     * @param res
     * @return
     */
    public String SHA1(String res) {
        return messageDigest(res, SHA1);
    }
 
    
    /**
     *  HmacSHA1 加密
     * @param res
     * @param key
     * @return
     */
    public String HmacSHA1(String res, String key) {
        return keyGeneratorMac(res, HmacSHA1, key);
    }
 
    
    /**
     * DES加密
     * @param res
     * @param key
     * @return
     */
    public String DESencode(String res, String key) {
        return keyGeneratorES(res, DES, key, keysizeDES, true);
    }
 
    
    /**
     * DES解密
     * @param res
     * @param key
     * @return
     */
    public String DESdecode(String res, String key) {
        return keyGeneratorES(res, DES, key, keysizeDES, false);
    }
 
    
    /**
     * AES加密
     * @param res
     * @param key
     * @return
     */
    public String AESencode(String res, String key) {
        return keyGeneratorES(res, AES, key, keysizeAES, true);
    }
 
    /**
     * AES解密
     * @param res
     * @param key
     * @return
     */
    public String AESdecode(String res, String key) {
        return keyGeneratorES(res, AES, key, keysizeAES, false);
    }
 
    
    /**
     * 异或加密
     * @param res
     * @param key
     * @return
     */
    public String XORencode(String res, String key) {
        byte[] bs = res.getBytes();
        for (int i = 0; i < bs.length; i++) {
            bs[i] = (byte) ((bs[i]) ^ key.hashCode());
        }
        return parseByte2HexStr(bs);
    }
 
    
    /**
     * 异或解密
     * @param res
     * @param key
     * @return
     */
    public String XORdecode(String res, String key) {
        byte[] bs = parseHexStr2Byte(res);
        for (int i = 0; i < bs.length; i++) {
            bs[i] = (byte) ((bs[i]) ^ key.hashCode());
        }
        return new String(bs);
    }
 
    
    public int XOR(int res, String key) {
        return res ^ key.hashCode();
    }
    
    
    public static void main(String ...arg){
        String res = "2a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e22a98edcb2ca519f3b4ab2b965f611314f163a9e2";
        String key = "1111";
        String mw = "密文，临时用的";
//        System.out.println("--MD5--");
//        System.out.println(EncryptUtils.getInstance().MD5(res));
//        System.out.println(EncryptUtils.getInstance().HmacMD5(res,key));
//        System.out.println("--SHA1--");
//        System.out.println(EncryptUtils.getInstance().SHA1(res));
//        System.out.println(EncryptUtils.getInstance().HmacSHA1(res,key));
//        System.out.println("--DES--");
//        mw = EncryptUtils.getInstance().DESencode(res,key);
//        System.out.println(mw);
//        System.out.println(EncryptUtils.getInstance().DESdecode(mw, key));
//        System.out.println("--AES--");
//        mw = EncryptUtils.getInstance().AESencode(res,key);
//        System.out.println(mw);
//        System.out.println(EncryptUtils.getInstance().AESdecode(mw, key));
        System.out.println(EncryptUtils.getInstance().DESencode("jnwe0uQZ8","RONDUGAVIN"));
        System.out.println(EncryptUtils.getInstance().DESdecode("747C91A08FC5A674B337F009C77A0FFF", "RONDUGAVIN"));
        System.out.println(EncryptUtils.getInstance().DESdecode("747C91A08FC5A674B337F009C77A0FFF", "RONDUGAVIN"));


//        System.out.println("--异或加密--");
//        System.out.println(res);
//        String mw1 = EncryptUtils.getInstance().XORencode(res, key);
//        System.out.println(mw1);
//        System.out.println(EncryptUtils.getInstance().XORdecode(mw1, key));
//        int i = 12345;
//        int ii = EncryptUtils.getInstance().XOR(i, key);
//        int iii = EncryptUtils.getInstance().XOR(ii, key);
//        System.out.println(String.format(i+"异或一次：%s；异或两次：%s",ii,iii));
    }

}
