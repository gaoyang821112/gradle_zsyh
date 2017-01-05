package com.gaoyang.util;

import java.security.MessageDigest;

public abstract class MD5Tools
{  
    public final static String getMd5LowerCase(String paramString) {
//        paramString = paramString.toLowerCase();
        byte[] arrayOfByte = new byte[0];
        try {
            MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
            localMessageDigest.update(paramString.getBytes("utf-8"));
            arrayOfByte = localMessageDigest.digest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder localStringBuilder = new StringBuilder();
        for (int i = 0; i < arrayOfByte.length; i++) {
            localStringBuilder.append(Character.forDigit(
                    0xF & arrayOfByte[i] >>> 4, 16));
            localStringBuilder.append(Character.forDigit(0xF & arrayOfByte[i],
                    16));
        }
        return localStringBuilder.toString().toLowerCase();
    }  
}  