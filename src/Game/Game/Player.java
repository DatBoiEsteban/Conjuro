package Game;

import Lib.FileManager;

public class Player {
	private Deck deck;
	private Card[] oponentCards ;

	public Player() throws Exception{
		
		FileManager file = new FileManager();
		deck=(Deck) file.ReadObjectToFile("Deck.dck");
		if(deck==null){
			deck= new Deck();
			deck.generateCards();
			deck.cryptCards();
		}
	}


}
