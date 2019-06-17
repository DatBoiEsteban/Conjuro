package Game;

import java.util.ArrayList;
import java.util.Collections;

import Lib.FileManager;
import Security.AES;
import Security.Crypto;
import Security.MD5;
import Security.PGP;
import Security.Plain;
import Security.RSA;
import Security.Sha256;
import Security.trippleDES;

public class Deck implements java.io.Serializable {
	private Card[] DeckCards = new Card[7];



	public void generateCards(){
		FileManager file = new FileManager();
		ArrayList<String> lines = file.ReadFileLines("Spells.txt");
        Collections.shuffle(lines); 

        for(int i=0;i!=lines.size();i++){
        	
			String Descripcion = lines.get(i);
	        int index = Descripcion.indexOf(' ');
	        String Nombre = Descripcion.substring(0, index);
			Card card = new Card(Nombre, Descripcion,Nombre+".jpg");
			DeckCards[i]=card;
			
		}
	}
	public void cryptCards() throws Exception{
		Crypto crypt = new RSA();
		Object key1 = crypt.generateKey();
		DeckCards[0].encryptData(crypt.encrypt(DeckCards[0].getDescripcion(),key1 ), key1, key1, "RSA");
		
		crypt = new AES();
		key1 = crypt.generateKey();
		DeckCards[1].encryptData(crypt.encrypt(DeckCards[1].getDescripcion(),key1 ), key1, key1, "AES");
		
		crypt = new MD5();
		key1 = crypt.generateKey();
		DeckCards[2].encryptData(crypt.encrypt(DeckCards[2].getDescripcion(),key1 ), DeckCards[2].getDescripcion(), DeckCards[2].getDescripcion(), "MD5");
		
		crypt = new PGP();
		key1 = crypt.generateKey();
		DeckCards[3].encryptData(crypt.encrypt(DeckCards[3].getDescripcion(),key1 ), key1, key1, "PGP");
		

		crypt = new Sha256();
		key1 = crypt.generateKey();
		DeckCards[4].encryptData(crypt.encrypt(DeckCards[4].getDescripcion(),key1 ), DeckCards[4].getDescripcion(), DeckCards[4].getDescripcion(), "Sha256");
		
		crypt = new trippleDES();
		key1 = crypt.generateKey();
		DeckCards[5].encryptData(crypt.encrypt(DeckCards[5].getDescripcion(),key1 ), key1, key1, "trippleDES");
		
		crypt = new Plain();
		key1 = crypt.generateKey();
		DeckCards[6].encryptData(crypt.encrypt(DeckCards[6].getDescripcion(),key1 ), DeckCards[6].getDescripcion(), DeckCards[6].getDescripcion(), "Plain");

		
	}
    public Card[] getDeckCards() {
		return DeckCards;
	}
	public void setDeckCards(Card[] deckCards) {
		DeckCards = deckCards;
	}

	
}
