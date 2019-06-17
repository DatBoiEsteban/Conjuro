package Game;

import java.util.ArrayList;

import ConjuroNet.ConjuroComms;
import ConjuroNet.ConjuroMsg;
import Lib.Consts;
import Security.AES;
import Security.Crypto;
import Security.MD5;
import Security.PGP;
import Security.Plain;
import Security.RSA;
import Security.Sha256;
import Security.trippleDES;

public class Game implements Consts, Runnable {


	private Player player;
	private ArrayList<Card>  oponentCards ;
	private ConjuroComms Connection;
	private boolean GameStarted;
	private boolean Decrypted;
	private int Round=1;
	private String Key;
	
	public boolean isDecrypted() {
		return Decrypted;
	}
	public void setDecrypted(boolean decrypted) {
		Decrypted = decrypted;
	}

	
	public int getRound() {
		return Round;
	}
	public void setRound(int round) {
		Round = round;
	}
	public void startGame() throws Exception{
		player = new Player();
		GameStarted=true;
        Thread listenThread = new Thread((Runnable) this);
        listenThread.start();


	}
	public void GameReset(){
    	getPlayer().ClearCardsSend();
    	getPlayer().setCardsSent(false);
    	setDecrypted(false);
    	getConnection().ClearOtherPlayerCards();
    	setOponentCards(new ArrayList<Card>());
	}
    public ConjuroComms getConnection() {
		return Connection;
	}
	public void setConnection(ConjuroComms connection) {
		Connection = connection;
	}
	public void run() {
        while(GameStarted) {

    		if(Connection.getOtherPlayerCards()!=null&&Connection.getOtherPlayerCards().size()==3&&!Decrypted&&player.isCardsSent()){
    			oponentCards=Connection.getOtherPlayerCards();
    			try {
					decryptCards() ;
		        	if(!Connection.isLoser()){
		        	
					ConjuroMsg pMsg = new ConjuroMsg(boolean.class);
					pMsg.addObject(true);
					if(Connection.getServer()!=null)
						Connection.getServer().sendMessage(pMsg );
					else{
						Connection.getClient().sendMessage(pMsg);

						}
		        	}	
		        	player.setKey(Key.substring(0, 16*Round));
		        	player.getLoses().add(Connection.isLoser());

					Decrypted=true;
					Round++;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
            try {
				Thread.sleep(THREAD_SLEEP_TIME*10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


        }
     
    }
	public  Game(ConjuroComms pConnection, String pKey){
		Key = pKey;
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
			System.out.println("Failure");
		}
	}

	public Player getPlayer() {
		return player;
	}


}
