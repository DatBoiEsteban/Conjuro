package Game;


import Lib.FileManager;

import java.util.ArrayList;

public class Player {
	private Deck deck;
	private Card[] oponentCards ;
	private ArrayList<Card> cardsToSend;

	public Player() throws Exception{
		cardsToSend = new ArrayList<Card>();
		FileManager file = new FileManager();
		deck=(Deck) file.ReadObjectToFile("deck.dck");
		System.out.println(deck);
		if(deck == null){
			deck= new Deck();
			deck.generateCards();
			deck.cryptCards();
			file.WriteObjectToFile("deck.dck", deck);
		}
			
		
	}

	public Deck getDeck() {
		return this.deck;
	}

	public static void main(String [] args) throws  Exception
    {
		Player deck = new Player();
    }

    public Boolean addCardsToSend(Card pCard) {
		if (this.cardsToSend.size() < 3) {
			this.cardsToSend.add(pCard);
			return true;
		}
		return false;
	}

}
