package com.example.invigilator.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author zwz
 * @date 2020/9/30 23:34
 * @description 加密算法
 */
public class EncryptionUtils {

    /**
     * Base64加密
     * @param password
     * @return
     */
    public static String encodeToBase64(String password){
        Base64 base64 = new Base64();
        String cipher = base64.encodeToString(password.getBytes());
        return cipher;
    }

    /**
     * Base64解密
     * @param encode
     * @return
     */
    public static String decodeToBase64(String encode){
        Base64 base64 = new Base64();
        String plain = new String(base64.decode(encode));
        return plain;
    }

    /**
     * SHA1加密
     * @param password
     * @return
     */
    public static String encodeToSha1(String password){
        String sha1Hex = DigestUtils.sha1Hex(password.getBytes());
        return sha1Hex;
    }
}
