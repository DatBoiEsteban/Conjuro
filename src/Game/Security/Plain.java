package Security;

import java.security.KeyPairGenerator;

public class Plain implements Crypto{

	@Override
	public byte[] encrypt(String message, Object secretKey) throws Exception {
		// TODO Auto-generated method stub
		return message.getBytes("UTF-8");
	}

	@Override
	public String decrypt(byte[] message, Object secretKey) {

		try {
			String key = ((String)secretKey);

		if(new String(message).equals(key)){
			System.out.println("Decrypt by Plain");
			String res  = new String(message,"UTF-8");	
			return 	res;}
		}catch (Exception e){
	        return "";

		}
		return "";

	}

	@Override
	public Object generateKey() throws Exception {
        final int keySize = 2048;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);      
        return keyPairGenerator.genKeyPair().getPrivate().getEncoded();
	}

}
