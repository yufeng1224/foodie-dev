package com.yufeng.utils;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * 描述:
 *     MD5是单向的加密, 是不存在解密过程的!
 * @author yufeng
 * @create 2020-10-20
 */
public class MD5Utils {

    /**
     *
     * @Title: MD5Utils.java
     * @Package com.imooc.utils
     * @Description: 对字符串进行md5加密
     */
    public static String getMD5Str(String strValue) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        // System.out.println(md5.digest(strValue.getBytes()).length); 16位
        // System.out.println(Arrays.toString(md5.digest(strValue.getBytes())));
        String newstr = Base64.encodeBase64String(md5.digest(strValue.getBytes()));
        return newstr;
    }

    public static void main(String[] args) {
        try {
            String md5 = getMD5Str("imooc");
            System.out.println(md5.length());
            System.out.println(md5);

            String md5Str = getMD5Str("yufeng1234567");
            System.out.println(md5Str.length());
            System.out.println(md5Str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
