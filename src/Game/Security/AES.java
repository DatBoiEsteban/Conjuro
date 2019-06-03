package Security;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES implements Crypto{
    public   Object generateKey() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String key = UUID.randomUUID().toString();
        int ivSize = 16;
        byte[] iv = new byte[ivSize];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // Hashing key.
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(key.getBytes("UTF-8"));
        byte[] keyBytes = new byte[16];
        System.arraycopy(digest.digest(), 0, keyBytes, 0, keyBytes.length);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
        return new Object[] {secretKeySpec , ivParameterSpec};
    }
	public  byte[] encrypt(String value,Object secretKey) {
	    try {
	    	SecretKeySpec skeySpec =  (SecretKeySpec)( ((Object[]) secretKey)[0]);
	    	IvParameterSpec iv =  (IvParameterSpec)( ((Object[]) secretKey)[1]);

	    	 
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
	 
	        byte[] encrypted = cipher.doFinal(value.getBytes());
	        return encrypted;
	        
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    return null;
	}
    public String decrypt(byte [] encrypted,Object secretKey) throws Exception {
	    try {
	    	SecretKeySpec skeySpec =  (SecretKeySpec)( ((Object[]) secretKey)[0]);
	    	IvParameterSpec iv =  (IvParameterSpec)( ((Object[]) secretKey)[1]);

	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
	        byte[] original = cipher.doFinal(encrypted);
	 
	        return new String(original);
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	 
	    return null;
	}

    public static void main(String [] args) throws  Exception
    {
    
    	Crypto cryp = new AES();
    	Object caca = cryp.generateKey();
    	byte [] caca2 =cryp.encrypt("this dick200", caca);
    	System.out.println(cryp.decrypt(caca2, caca));
    }
}
