package ConjuroNet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Game.Card;
import Game.Deck;
import Game.Game;
import Lib.Consts;
import Lib.IObserver;
import Lib.Logger;
import Net.ClientSocket;
import Net.ServerNet;

public class ConjuroComms implements IObserver, Consts {
    private ClientSocket client;
    private static ServerNet server;
    private ArrayList<Card> otherPlayerCards;

    public ConjuroComms() {

    }

    public void conectarAJuego(String pHost) {

        client = new ClientSocket(pHost, PORT_NUMBER);
        client.addObserver(this);
    }

    public void iniciarJuegoNuevo() {
        try {
            server = new ServerNet(this);
        }catch (Exception e ) {
            Logger.Log(e.getMessage());
        }
    	server.startListening(this);
        conectarAJuego("127.0.0.1");

    }

    public void notify(Object pData) {
        ConjuroMsg msg = (ConjuroMsg)pData;
        System.out.println("Recibido");
        if (msg.getType()==Deck.class) {
        	System.out.println("Deck");
        }
        else if (msg.getType() == ArrayList.class) {
            this.otherPlayerCards = ((ArrayList<Card>) msg.getObjs().get(0));
        }

        
    }

    public ArrayList<Card> getOtherPlayerCards() {
        return this.otherPlayerCards;
    }
    public void clearOtherPlayerCards() {
        this.otherPlayerCards = null;
    }

    public ClientSocket getClient() {
		return client;
	}

	public void setClient(ClientSocket client) {
		this.client = client;
	}

	public ServerNet getServer() {
		return server;
	}

	public void setServer(ServerNet server) {
		this.server = server;
	}

	public Boolean isConnected() {
        return this.client.isConnected();
    }

	public static void main(String [] args) throws  Exception
    {
    	
    	
    	ConjuroComms server2 = new ConjuroComms();

    	server2.conectarAJuego("127.0.0.1");


}
    
}
