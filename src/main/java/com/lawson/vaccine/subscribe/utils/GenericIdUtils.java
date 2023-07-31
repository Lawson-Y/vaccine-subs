package com.lawson.vaccine.subscribe.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GenericIdUtils {

    public static String zfsw() {
        long l = (System.currentTimeMillis() / 1000);
        String zfsw = "zfsw_" + l;
        // 创建MessageDigest对象，并指定算法为MD5
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        // 将明文转换为字节数组
        byte[] plaintextBytes = zfsw.getBytes(StandardCharsets.UTF_8);
        // 计算MD5哈希值
        byte[] hashBytes = md.digest(plaintextBytes);
        // 将哈希值转换为十六进制字符串
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        String md5Hash = sb.toString();
        System.out.println("zfsw Hash: " + md5Hash);
        return md5Hash;
    }

}
