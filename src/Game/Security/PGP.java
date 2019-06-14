package Security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class PGP implements Crypto{
	
    public   SecretKey generateKey() throws NoSuchAlgorithmException {

        KeyGenerator keygenerator = KeyGenerator.getInstance("DESede");
        SecretKey desKey = keygenerator.generateKey();    
        return desKey;
    }
    public byte[] encrypt(String message,Object secretKey) throws Exception {
    	
    	Cipher desCipher = Cipher.getInstance("DESede");
    	desCipher.init(Cipher.ENCRYPT_MODE, (SecretKey) secretKey);
    	  
        	  
    	byte[] ciphertext = desCipher.doFinal(message.getBytes());
        
		return ciphertext;
    }

    public String decrypt(byte [] encrypted,Object secretKey) throws Exception {
       
        	Cipher desCipher = Cipher.getInstance("DESede");
        	desCipher.init(Cipher.DECRYPT_MODE, (SecretKey) secretKey);
        	  
        	byte[] cleartext = desCipher.doFinal(encrypted);

        return new String(cleartext);
    
    }


    public static void main(String [] args) throws  Exception
    {
    
    	Crypto cryp = new trippleDES();
    	Object caca = cryp.generateKey();
    	byte [] caca2 =cryp.encrypt("this dick", caca);
    	System.out.println(cryp.decrypt(caca2, caca));
    }

}
