package Security;

import java.math.BigInteger;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 implements Crypto{
	public byte[] encrypt(String message, Object secretKey) throws Exception {
	

	        // Static getInstance method is called with hashing MD5 
	        MessageDigest md = MessageDigest.getInstance("MD5"); 

	        // digest() method is called to calculate message digest 
	        //  of an input digest() return array of byte 
	        byte[] messageDigest = md.digest(message.getBytes()); 

	        // Convert byte array into signum representation 
	        BigInteger no = new BigInteger(1, messageDigest); 

	        // Convert message digest into hex value 
	        String hashtext = no.toString(16); 
	        while (hashtext.length() < 32) { 
	            hashtext = "0" + hashtext; 
	        } 
	        return hashtext.getBytes(); 
	    

	    // For specifying wrong message digest algorithms 

	}


	@Override
	public String decrypt(byte[] message, Object secretKey) {
		try {
			if(new String(message).equals(new String(encrypt((String)secretKey,"")))){
				System.out.println("Decrypt by MD5");

				return (String)secretKey;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
	        return "";
		}
        return "";
	}
	@Override
	public Object generateKey() throws NoSuchAlgorithmException {
        final int keySize = 2048;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);      
        return keyPairGenerator.genKeyPair().getPrivate().getEncoded();
	} 
	public static void main(String [] args) throws  Exception
	{
		Crypto crypt = new MD5();
		Object key1 = crypt.generateKey();
		byte[] a = crypt.encrypt("caca", "");

		System.out.println(crypt.decrypt(a, "caca"));
	}
}
