package Game;


import Lib.FileManager;

import java.util.ArrayList;

public class Player {
	private Deck deck;
	private ArrayList<Card> cardsToSend;
	private String Key;
	private int Wins;
	private boolean cardsSent;
	public Player() throws Exception{
		Wins = 0;
		cardsToSend = new ArrayList<Card>();
		FileManager file = new FileManager();
		deck=(Deck) file.ReadObjectToFile("deck.dck");
		if(deck == null){
			deck= new Deck();
			deck.generateCards();
			deck.cryptCards();
			file.WriteObjectToFile("deck.dck", deck);
		}
			
		
	}





	public int getWins() {
		return Wins;
	}





	public void setWins(int wins) {
		Wins = wins;
	}





	public String getKey() {
		return Key;
	}





	public void setKey(String key) {
		Key = key;
	}









	public boolean isCardsSent() {
		return cardsSent;
	}





	public void setCardsSent(boolean cardsSent) {
		this.cardsSent = cardsSent;
	}



	public void ClearCardsSend() {
		System.out.println("cleaned");
		cardsToSend= new ArrayList<Card>();
	}


	public Deck getDeck() {
		return this.deck;
	}



    public Boolean addCardsToSend(Card pCard) {
		if (this.cardsToSend.size() < 3) {
			Card temp = new Card(null, null, null);
			pCard.CloneWithoutType(temp);
			this.cardsToSend.add(temp);
			return true;
		}
		return false;
	}

	public ArrayList<Card> getCardsToSend() {
		return this.cardsToSend;
	}



}
