package Security;

import java.math.BigInteger;
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
	public String decrypt(byte[] message, Object secretKey) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object generateKey() throws NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		return null;
	} 
}
