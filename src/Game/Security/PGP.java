package Security;

//Java imports
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;
//Bouncy castle imports
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPCompressedData;
import org.bouncycastle.openpgp.PGPCompressedDataGenerator;
import org.bouncycastle.openpgp.PGPEncryptedDataGenerator;
import org.bouncycastle.openpgp.PGPEncryptedDataList;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPLiteralData;
import org.bouncycastle.openpgp.PGPObjectFactory;
import org.bouncycastle.openpgp.PGPOnePassSignatureList;
import org.bouncycastle.openpgp.PGPPrivateKey;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPPublicKeyEncryptedData;
import org.bouncycastle.openpgp.PGPPublicKeyRing;
import org.bouncycastle.openpgp.PGPPublicKeyRingCollection;
import org.bouncycastle.openpgp.PGPSecretKey;
import org.bouncycastle.openpgp.PGPSecretKeyRingCollection;
import org.bouncycastle.openpgp.PGPUtil;
import org.bouncycastle.openpgp.operator.PGPKeyEncryptionMethodGenerator;

import com.didisoft.pgp.KeyStore;
import com.didisoft.pgp.PGPLib;
//
public class PGP {
     
     
     public   Object generateKey() throws NoSuchAlgorithmException, UnsupportedEncodingException {
    	// creates new or opens existing file based KeyStore
    	 
    	// creates empty in-memory located KeyStore
    	KeyStore keyStore = new KeyStore();
    	keyStore.setPassword("caca");
    	return keyStore;
		}


     
     public String decrypt(byte[] message, KeyStore key) throws Exception {
    
     PGPLib pgp = new PGPLib();
     


     String decryptedMessage = pgp.decryptString(new String(message), key, "caca");

	return decryptedMessage;
 }

     private static byte[] encrypt(String stringToEncrypt, KeyStore key)
                         throws IOException, NoSuchProviderException, PGPException, com.didisoft.pgp.PGPException
     {
         


    	         // create an instance of the library
    	         PGPLib pgp = new PGPLib();


    	         // encrypt
    	         String encryptedString = 
    	              pgp.encryptString(stringToEncrypt,
    	            		  key,
    	            		  key.getKeyIdForKeyIdHex("A0324F1D"));
    	         
    	         return encryptedString.getBytes();
    	     }
    	 
    
     
 
      
     //
     // Public main method
     //
     public static void main(String[] args) throws Exception {
    	 PGP p = new PGP();
    	 Object a = p.generateKey();
    	 byte[] message = p.encrypt("caca", (KeyStore) a);
    	 System.out.println(p.decrypt(message, (KeyStore) a));
              
     }
}