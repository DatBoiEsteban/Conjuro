package Security;

import java.security.KeyPairGenerator;

public class Plain implements Crypto{

	@Override
	public byte[] encrypt(String message, Object secretKey) throws Exception {
		// TODO Auto-generated method stub
		return message.getBytes();
	}

	@Override
	public String decrypt(byte[] message, Object secretKey) throws Exception {
		// TODO Auto-generated method stub
		return new String(message);
	}

	@Override
	public Object generateKey() throws Exception {
        final int keySize = 2048;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);      
        return keyPairGenerator.genKeyPair().getPrivate().getEncoded();
	}

}
