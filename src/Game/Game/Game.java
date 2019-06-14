package Game;

import Security.AES;
import Security.Crypto;
import Security.MD5;
import Security.PGP;
import Security.Plain;
import Security.RSA;
import Security.Sha256;
import Security.trippleDES;

public class Game {
	
	
	private Player player;
	
	public void start() throws Exception{
		player = new Player();
		
		
	}
	
	private void sendCards(Card[] Cards){
		
	}
	private void decryptCards(Card[] Cards) throws Exception{
		Crypto crypt = new Sha256();
		String res= "";
		for(int i=0; i<Cards.length;i++){
			//sha256			
			res=crypt.decrypt(Cards[i].getDescripcionCifrada(), Cards[i].getLlave1());
			if(res==Cards[i].getDescripcion()){
				Cards[i].setTipo("Sha256");
				  continue;
			}
			// md5
			crypt = new MD5();
			res=crypt.decrypt(Cards[i].getDescripcionCifrada(), Cards[i].getLlave1());
			if(res==Cards[i].getDescripcion()){
				Cards[i].setTipo("MD5");
				  continue;
			}
			// 3des
			crypt = new trippleDES();
			res=crypt.decrypt(Cards[i].getDescripcionCifrada(), Cards[i].getLlave1());
			if(res==Cards[i].getDescripcion()){
				Cards[i].setTipo("trippleDES");
				  continue;
			}
		//  aes
			crypt = new AES();
			res=crypt.decrypt(Cards[i].getDescripcionCifrada(), Cards[i].getLlave1());
			if(res==Cards[i].getDescripcion()){
				Cards[i].setTipo("AES");
				  continue;
			}
		//   plain
			crypt = new Plain();
			res=crypt.decrypt(Cards[i].getDescripcionCifrada(), Cards[i].getLlave1());
			if(res==Cards[i].getDescripcion()){
				Cards[i].setTipo("Plain");
				  continue;
			}
			//   rsa
			crypt = new RSA();
			res=crypt.decrypt(Cards[i].getDescripcionCifrada(), Cards[i].getLlave1());
			if(res==Cards[i].getDescripcion()){
				Cards[i].setTipo("RSA");
				  continue;
			}
			//   PGP
			crypt = new PGP();
			res=crypt.decrypt(Cards[i].getDescripcionCifrada(), Cards[i].getLlave1());
			if(res==Cards[i].getDescripcion()){
				Cards[i].setTipo("PGP");
				  continue;
			}
		}
		
		

	}
}
