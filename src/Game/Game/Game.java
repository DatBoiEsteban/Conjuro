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
		Crypto crypt;
		String res= "";
		for(int i=0; i<Cards.length;i++){
			//sha256			
			crypt = new Sha256();
			res=crypt.decrypt(Cards[i].getDescripcionCifrada(), Cards[i].getLlave1());
			if(res.equals(Cards[i].getDescripcion())){
				Cards[i].setTipo("Sha256");
				System.out.println("Carta : "+ i + " es Sha256");
				  continue;
			}
			// md5
			crypt = new MD5();
			res=crypt.decrypt(Cards[i].getDescripcionCifrada(), Cards[i].getLlave1());
			if(res.equals(Cards[i].getDescripcion())){
				Cards[i].setTipo("MD5");
				System.out.println("Carta : "+ i + " es MD5");

				  continue;
			}
			// 3des
			crypt = new trippleDES();
			res=crypt.decrypt(Cards[i].getDescripcionCifrada(), Cards[i].getLlave1());
			if(res.equals(Cards[i].getDescripcion())){
				Cards[i].setTipo("trippleDES");
				System.out.println("Carta : "+ i + " es trippleDES");

				  continue;
			}
		//  aes
			crypt = new AES();
			res=crypt.decrypt(Cards[i].getDescripcionCifrada(), Cards[i].getLlave1());
			if(res.equals(Cards[i].getDescripcion())){
				Cards[i].setTipo("AES");
				System.out.println("Carta : "+ i + " es AES");

				  continue;
			}
		//   plain
			crypt = new Plain();
			res=crypt.decrypt(Cards[i].getDescripcionCifrada(), Cards[i].getLlave1());
			if(res.equals(Cards[i].getDescripcion())){
				Cards[i].setTipo("Plain");
				System.out.println("Carta : "+ i + " es Plain");

				  continue;
			}
			//   rsa
			crypt = new RSA();
			res=crypt.decrypt(Cards[i].getDescripcionCifrada(), Cards[i].getLlave1());
			if(res.equals(Cards[i].getDescripcion())){
				Cards[i].setTipo("RSA");
				System.out.println("Carta : "+ i + " es RSA");

				  continue;
			}
			//   PGP
			crypt = new PGP();
			res=crypt.decrypt(Cards[i].getDescripcionCifrada(), Cards[i].getLlave1());
			if(res.equals(Cards[i].getDescripcion())){
				Cards[i].setTipo("PGP");
				System.out.println("Carta : "+ i + " es PGP");

				  continue;
			}
		}
		
		

	}
    public static void main(String [] args) throws  Exception
    {
    	Deck deck = new Deck();
    	deck.generateCards();
    	deck.cryptCards();
    	Game game = new Game();
    	game.decryptCards(deck.getDeckCards());


    }
}
