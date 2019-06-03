package Security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;

public interface  Crypto {
	  public byte[] encrypt(String message,Object secretKey) throws Exception ;
	  public String decrypt(byte[] message,Object secretKey) throws Exception ;
	    public   Object generateKey() throws Exception;

}
