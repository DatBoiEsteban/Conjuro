package Game;

import java.util.ArrayList;

import ConjuroNet.ConjuroComms;
import ConjuroNet.ConjuroMsg;
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
	private ArrayList<Card>  oponentCards ;
	private ConjuroComms Connection;
	public void start() throws Exception{
		player = new Player();
		
		if(Connection.getOtherPlayerCards().size()==3){
			oponentCards=Connection.getOtherPlayerCards();
			decryptCards() ;
		}


	}

	public  Game(ConjuroComms pConnection){
		Connection=pConnection;
	}

	
	public ArrayList<Card> getOponentCards() {
		return oponentCards;
	}


	public void setOponentCards(ArrayList<Card> oponentCards) {
		this.oponentCards = oponentCards;
	}
	private void decryptCards() throws Exception{
		Crypto crypt;
		String res= "";
		for(int i=0; i<oponentCards.size();i++){
			//sha256			
			crypt = new Sha256();
			res=crypt.decrypt(oponentCards.get(i).getDescripcionCifrada(), oponentCards.get(i).getLlave1());
			if(res.equals(oponentCards.get(i).getDescripcion())){
				oponentCards.get(i).setTipo("Sha256");
				System.out.println("Carta : "+ i + " es Sha256");
				continue;
			}
			// md5
			crypt = new MD5();
			res=crypt.decrypt(oponentCards.get(i).getDescripcionCifrada(), oponentCards.get(i).getLlave1());
			if(res.equals(oponentCards.get(i).getDescripcion())){
				oponentCards.get(i).setTipo("MD5");
				System.out.println("Carta : "+ i + " es MD5");

				continue;
			}
			// 3des
			crypt = new trippleDES();
			res=crypt.decrypt(oponentCards.get(i).getDescripcionCifrada(), oponentCards.get(i).getLlave1());
			if(res.equals(oponentCards.get(i).getDescripcion())){
				oponentCards.get(i).setTipo("trippleDES");
				System.out.println("Carta : "+ i + " es trippleDES");

				continue;
			}
			//  aes
			crypt = new AES();
			res=crypt.decrypt(oponentCards.get(i).getDescripcionCifrada(), oponentCards.get(i).getLlave1());
			if(res.equals(oponentCards.get(i).getDescripcion())){
				oponentCards.get(i).setTipo("AES");
				System.out.println("Carta : "+ i + " es AES");

				continue;
			}
			//   plain
			crypt = new Plain();
			res=crypt.decrypt(oponentCards.get(i).getDescripcionCifrada(), oponentCards.get(i).getLlave1());
			if(res.equals(oponentCards.get(i).getDescripcion())){
				oponentCards.get(i).setTipo("Plain");
				System.out.println("Carta : "+ i + " es Plain");

				continue;
			}
			//   rsa
			crypt = new RSA();
			res=crypt.decrypt(oponentCards.get(i).getDescripcionCifrada(), oponentCards.get(i).getLlave1());
			if(res.equals(oponentCards.get(i).getDescripcion())){
				oponentCards.get(i).setTipo("RSA");
				System.out.println("Carta : "+ i + " es RSA");

				continue;
			}
			//   PGP
			crypt = new PGP();
			res=crypt.decrypt(oponentCards.get(i).getDescripcionCifrada(), oponentCards.get(i).getLlave1());
			if(res.equals(oponentCards.get(i).getDescripcion())){
				oponentCards.get(i).setTipo("PGP");
				System.out.println("Carta : "+ i + " es PGP");

				continue;
			}
		}
	}

	public Player getPlayer() {
		return player;
	}


}
