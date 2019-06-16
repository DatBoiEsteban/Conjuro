package Security;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES implements Crypto, java.io.Serializable{
    public   Object generateKey() throws NoSuchAlgorithmException, UnsupportedEncodingException     {
        MessageDigest sha = null;
        try {
            byte[] array = new byte[7]; // length is bounded by 7
            new Random().nextBytes(array);
            String generatedString = new String(array, Charset.forName("UTF-8"));
        	byte[]  key = generatedString.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            SecretKeySpec  secretKey = new SecretKeySpec(key, "AES");
    		return secretKey;

        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
		return null;
    }
	public  byte[] encrypt(String value,Object secretKey) {
	    
	        try
	        {
	            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
	            cipher.init(Cipher.ENCRYPT_MODE, (SecretKeySpec) secretKey);
	            return cipher.doFinal(value.getBytes("UTF-8"));
	        }
	        catch (Exception e)
	        {
				return null;
	        }
	}
    public String decrypt(byte [] encrypted,Object secretKey) throws Exception {
        try
        {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, (SecretKeySpec) secretKey);
			System.out.println("Decrypt by AES");
			String res = new String(cipher.doFinal(encrypted), "UTF-8");
            return res;
        }
        catch (Exception e)
        {
	        return "";
        }
    }
	


}
