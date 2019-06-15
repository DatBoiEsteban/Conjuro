package Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSA implements Crypto{
	
	

    public   Object generateKey() throws NoSuchAlgorithmException {
        final int keySize = 2048;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);      
        return keyPairGenerator.genKeyPair();
    }
    public   byte[] encrypt(String message,Object secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.ENCRYPT_MODE,((KeyPair) secretKey).getPrivate());  
        byte[] cipherText = cipher.doFinal(message.getBytes());
        return cipherText;  
    }
    
    public String decrypt(byte [] encrypted,Object secretKey){
    	try{
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.DECRYPT_MODE, ((KeyPair) secretKey).getPublic());
		System.out.println("Decrypt by RSA");
		String res = new String(cipher.doFinal(encrypted));
        return res;
    	} catch (Exception e) {
			// TODO Auto-generated catch block
	        return "";
		}

     
}

	





}