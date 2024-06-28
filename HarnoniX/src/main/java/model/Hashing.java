package model;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Hashing{
    private static final String ENCRYPT_ALGO = "AES/GCM/NoPadding";
    private static final int TAG_LENGTH_BIT = 128;
    private static final int TV_LENGTH_BYTE = 12;
//    private static final int SALT_LENGTH_BYTE = 16;
    private static final Charset UTF_8 = StandardCharsets.UTF_8;

    public static byte[] getRandomNonce(int numBytes) {
        byte[] nonce = new byte[numBytes];
        new SecureRandom().nextBytes(nonce);
        return nonce;
    }

    public static String encryptPassword(String password, String keyString) throws Exception {
    	System.out.println(keyString);
        byte[] keyBytes = keyString.getBytes(UTF_8);
        SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");

        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
        byte[] nonce = getRandomNonce(TV_LENGTH_BYTE);
        GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH_BIT, nonce);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, spec);

        byte[] encrypted = cipher.doFinal(password.getBytes(UTF_8));

        byte[] combined = new byte[nonce.length + encrypted.length];
        System.arraycopy(nonce, 0, combined, 0, nonce.length);
        System.arraycopy(encrypted, 0, combined, nonce.length, encrypted.length);

        return Base64.getEncoder().encodeToString(combined);
    }

    public static String decryptPassword(String encryptedPassword, String keyString) throws Exception {
        byte[] keyBytes = keyString.getBytes(UTF_8);
        SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");

        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);

        byte[] combined = Base64.getDecoder().decode(encryptedPassword);
        byte[] nonce = new byte[TV_LENGTH_BYTE];
        byte[] encrypted = new byte[combined.length - TV_LENGTH_BYTE];
        System.arraycopy(combined, 0, nonce, 0, nonce.length);
        System.arraycopy(combined, nonce.length, encrypted, 0, encrypted.length);

        GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH_BIT, nonce);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, spec);

        byte[] decrypted = cipher.doFinal(encrypted);

        return new String(decrypted, UTF_8);
    }

    
    
    public static String generateKey(int keyLength) {
        byte[] keyBytes = new byte[keyLength];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(keyBytes);
        return Base64.getEncoder().encodeToString(keyBytes);
    }
}
    
    
   