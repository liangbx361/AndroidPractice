package com.liangbx.android.practice.security;

import java.security.PrivateKey;

import javax.crypto.Cipher;

/**
 * <p>Title: <／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2016<／p>
 * <p>Company: 网龙公司<／p>
 *
 * @author liangbx
 * @version 1.0
 * @data 2016/2/15.
 */
public class RSATest {

    //解密结果12
    public static final String ENCRYPT_TXT = "HcoC/gaeRH8tBabVp4m/XPurIgXDae+GcZHhWvCT950wNBbkyxkgVrioTVAalKQP+e27aAhPOFhBMwxI2jAOzA==";

    // RAS 数据解密测试
    public static String decrypt(byte[] eBytes) throws java.lang.Exception {
        java.math.BigInteger m = new java.math.BigInteger("bb8bfc5d796b195a307b86e9490105b8ed4b4722b53e75335e5f9319e6052e02fd8196f354e72e776128ef33c4c3be2825904e9cec1d8b99d718b3a683f2c0a5", 16);
        java.math.BigInteger d = new java.math.BigInteger("88f2ffa58234229f29280aabef13400a79bcae8539b4120120b8c9c1efa578a2962504090f6c127d21203f52e877c67b3d0ef25da1d7b8d7452af9f1c5174f41", 16);
        java.security.spec.RSAPrivateKeySpec keySpec = new java.security.spec.RSAPrivateKeySpec(m, d);
        java.security.KeyFactory keyFactory = java.security.KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] eBstr = cipher.doFinal(eBytes);
        String outStr = new String(eBstr, "utf-8");
        return outStr;
    }

    public static byte[] encrypt(String source) throws Exception{
        byte[] eBytes = source.getBytes();
        java.math.BigInteger m = new java.math.BigInteger("bb8bfc5d796b195a307b86e9490105b8ed4b4722b53e75335e5f9319e6052e02fd8196f354e72e776128ef33c4c3be2825904e9cec1d8b99d718b3a683f2c0a5", 16);
        java.math.BigInteger d = new java.math.BigInteger("88f2ffa58234229f29280aabef13400a79bcae8539b4120120b8c9c1efa578a2962504090f6c127d21203f52e877c67b3d0ef25da1d7b8d7452af9f1c5174f41", 16);
        java.security.spec.RSAPrivateKeySpec keySpec = new java.security.spec.RSAPrivateKeySpec(m, d);
        java.security.KeyFactory keyFactory = java.security.KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(eBytes);
    }

    public static void main(String[] args) throws Exception {
        byte[] encryptTxt = encrypt("1200");
        System.out.println(encryptTxt);

        System.out.println(decrypt(encryptTxt));
    }
}
