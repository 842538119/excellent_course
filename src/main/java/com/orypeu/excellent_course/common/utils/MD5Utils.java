package com.orypeu.excellent_course.common.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

/**
 * @ClassName MD5Utils
 * @Description TODO
 * @Author LJH
 * @Date 2020/1/20 16:53
 * @Version 1.0
 */
public class MD5Utils {

    private static final Logger log = LoggerFactory.getLogger(MD5Utils.class);

    private static final String SALT = "1qazxsw2";

    private static final String ALGORITH_NAME = "md5";

    private static final int HASH_ITERATIONS = 2;

    public static String encrypt(String pswd) {
        String newPassword = new SimpleHash(ALGORITH_NAME, pswd, ByteSource.Util.bytes(SALT), HASH_ITERATIONS).toHex();
        return newPassword;
    }

    public static String encrypt(String username, String pswd) {
        String newPassword = new SimpleHash(ALGORITH_NAME, pswd, ByteSource.Util.bytes(username + SALT),
                HASH_ITERATIONS).toHex();
        return newPassword;
    }

    public static String hash(String s) {
        try {
            return new String(toHex(md5(s)).getBytes("UTF-8"), "UTF-8");
        } catch (Exception e) {
            log.error("not supported charset...{}", e);
            return s;
        }
    }

    private static final String toHex(byte hash[]) {
        if (hash == null) {
            return null;
        }
        StringBuffer buf = new StringBuffer(hash.length * 2);
        int i;

        for (i = 0; i < hash.length; i++) {
            if ((hash[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString(hash[i] & 0xff, 16));
        }
        return buf.toString();
    }


    private static byte[] md5(String s) {
        MessageDigest algorithm;
        try {
            algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(s.getBytes("UTF-8"));
            byte[] messageDigest = algorithm.digest();
            return messageDigest;
        } catch (Exception e) {
            log.error("MD5 Error...", e);
        }
        return null;
    }

    public static void main(String[] args) {

        //System.out.println(MD5Utils.encrypt("admin", "1"));
    }
}
