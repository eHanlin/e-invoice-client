package com.eHanlin.api.invoice.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

/**
 * 加密工具
 * 智付寶指定 API 相關參數資料的傳遞須以 AES 加密
 */
public class CryptoUtil {

    private static final String DIGEST_ALGORITHM = "SHA-256";

    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

    private static final String SECRET_KEY_SPEC = "AES";

    private static final String UTF8 = "UTF-8";

    private byte[] secret;

    private byte[] iv;

    public CryptoUtil(String secret, String iv) {
        this.secret = toBytes(secret);
        this.iv = toBytes(iv);
    }

    public String sha256(String text) {
        MessageDigest messageDigest;

        try {
            messageDigest = MessageDigest.getInstance(DIGEST_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }

        messageDigest.update(text.getBytes());
        return byteArrayToHex(messageDigest.digest());
    }

    public String encrypt(String text) {
        byte[] data = toBytes(text);
        SecretKey secretKey = new SecretKeySpec(secret, SECRET_KEY_SPEC);
        AlgorithmParameterSpec algorithmParameterSpec = new IvParameterSpec(iv);
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, algorithmParameterSpec);
            return byteArrayToHex(cipher.doFinal(data));

        } catch (Exception e) {
            throw new RuntimeException("data encrypting error: " + e.getMessage());
        }
    }

    private byte[] toBytes(String str) {
        try {
            return str.getBytes(UTF8);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 unsupported.");
        }
    }

    private static String byteArrayToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }

        return builder.toString();
    }

}

