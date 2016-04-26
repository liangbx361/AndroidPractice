package com.liangbx.android.practice.security;

import android.util.Base64;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * <p>Title: <／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2016<／p>
 * <p>Company: 网龙公司<／p>
 *
 * @author liangbx
 * @version 1.0
 * @data 2016/2/26.
 */
public class RSAUtil {

    private static final String ALGORITHM_RSA = "RSA";

//    private static final String PUCLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDC/lted25yCOomvybrILp/mU+5\n" +
//            "GB3RBjjlLtZSWkKizV4lWtgwlEZ+xedVZqy5h5vcQf1xaUCgiOV6VvMT5dKXF1dk\n" +
//            "DIJOtlI8IA+EnTTf7C9LTY0eSyE1Z3LHx+JxGxlfSmKJTZw4Z/WL/HhshfqBPmfT\n" +
//            "OqxUSCyWHk+Uiv4ZzQIDAQAB";

    private static final String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAML+W153bnII6ia/\n" +
            "Jusgun+ZT7kYHdEGOOUu1lJaQqLNXiVa2DCURn7F51VmrLmHm9xB/XFpQKCI5XpW\n" +
            "8xPl0pcXV2QMgk62UjwgD4SdNN/sL0tNjR5LITVncsfH4nEbGV9KYolNnDhn9Yv8\n" +
            "eGyF+oE+Z9M6rFRILJYeT5SK/hnNAgMBAAECgYA8KfkNG7KTMtb0X5RlhfKlPiuQ\n" +
            "A7XncZEKfboZLMpUqyL6SZ5c1tMteGiZPBXdJM9dVWsanAwZvEN5zOwxXn7sH5J1\n" +
            "EzQfvVz4DJuqh/JmxWT5ubHmVpD6CPtLDe0/fwKFJSAEvfqR007OEqkr4BBnEYK1\n" +
            "kzD+NiHyzTZDIw0y7QJBAOZTROQbl0Wf7l7Hq5a+eG3QFiHZ9fwi/kCU3kmYNXSI\n" +
            "UWNXnrTWIk54gtddGAxrSz/rBvSSggXSIPwSKoiYyk8CQQDYutY6LGrg3mTGxB4a\n" +
            "itQes+O+vTJN3L9GGF0DlZseA7cFTFhy09ZyM9SGDgR1wgOlXPgnryEnxxt/iAp/\n" +
            "Xz8jAkAsU9hNYnYBo18YBKukbnH1CHhJ+Y3bAc0XFs+VMGMRU27+hI151kByA8WC\n" +
            "t1r6gOfRnGURSuUVQ7Svbrua4HUHAkB8MZyfW2Knl2liaXHivdlN+sGwRJS1/6Bw\n" +
            "F3f9nNZ1vaU8WQJ6UqAZc0/+vbuai2IxeXQOsZGrTLKaQyfY04jHAkEAiQYuMF0m\n" +
            "EgidWSxV0eRrEb+gGVSDpBrlwdesqA6LorgG3TbZRdIY4sAlX0QcY/yo10P7C+X7\n" +
            "Yi5Id/yfXsQopA==";

//    private static final String ENCRYPT_TXT = "wrcIx2NCenhRKNvQGMd672j+mNdzvBgNG/EGKh0Aa6D1SO9hGfVHTL8Z1Vki6O1lj9Q4PC75vTEmSOde407dVD4+MNdR594ZAUZAKufvjFnC2WnHZt1pzbdkshmtXxWft+4pDQ1ZwMeZByaEqbzCKNQefS3EuUE5bdNKhxSqhyY=";

    public static void test() {
//        String sourceTxt = "1200";
//
//        try {
//            //加密
////            PublicKey publicKey = loadPublicKey(PUCLIC_KEY);
////            byte[] encryptByte = encryptData(sourceTxt.getBytes(), publicKey);
//
//            //解密
//            PrivateKey privateKey = loadPrivateKey(PRIVATE_KEY);
//            byte[] decryByte = decryptData(Base64.decode(ENCRYPT_TXT, Base64.DEFAULT), privateKey);
//
//            String result = new String(decryByte);
//            System.out.println(result);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public static String decrypt(String encryptContent) throws Exception {
        byte[] encryptByte = Base64.decode(encryptContent, Base64.DEFAULT);
        PrivateKey privateKey = loadPrivateKey(PRIVATE_KEY);
        byte[] decryByte = decryptData(encryptByte, privateKey);
        return new String(decryByte != null ? decryByte : new byte[0]);
    }

    /**
     * 从字符串中加载公钥
     *
     * @param publicKeyStr
     *            公钥数据字符串
     * @throws Exception
     *             加载公钥时产生的异常
     */
    public static PublicKey loadPublicKey(String publicKeyStr) throws Exception
    {
        try
        {
            byte[] buffer = Base64.decode(publicKeyStr, Base64.DEFAULT);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            return keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e)
        {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e)
        {
            throw new Exception("公钥非法");
        } catch (NullPointerException e)
        {
            throw new Exception("公钥数据为空");
        }
    }

    /**
     * 用公钥加密 <br>
     * 每次加密的字节数，不能超过密钥的长度值减去11
     *
     * @param data
     *            需加密数据的byte数据
     * @param publicKey
     *            公钥
     * @return 加密后的byte型数据
     */
    public static byte[] encryptData(byte[] data, PublicKey publicKey)
    {
        try
        {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            // 编码前设定编码方式及密钥
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            // 传入编码数据并返回编码结果
            return cipher.doFinal(data);
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 从字符串中加载私钥<br>
     * 加载时使用的是PKCS8EncodedKeySpec（PKCS#8编码的Key指令）。
     *
     * @param privateKeyStr
     * @return
     * @throws Exception
     */
    public static PrivateKey loadPrivateKey(String privateKeyStr) throws Exception
    {
        try
        {
            byte[] buffer = Base64.decode(privateKeyStr, Base64.DEFAULT);
            // X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            return keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e)
        {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e)
        {
            throw new Exception("私钥非法");
        } catch (NullPointerException e)
        {
            throw new Exception("私钥数据为空");
        }
    }

    /**
     * 用私钥解密
     *
     * @param encryptedData
     *            经过encryptedData()加密返回的byte数据
     * @param privateKey
     *            私钥
     * @return
     */
    public static byte[] decryptData(byte[] encryptedData, PrivateKey privateKey)
    {
        try
        {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(encryptedData);
        } catch (Exception e)
        {
            return null;
        }
    }
}
