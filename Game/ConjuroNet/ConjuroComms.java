package ConjuroNet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import Lib.Observable;

import Game.Card;
import Game.Deck;
import Game.Game;
import Game.Player;
import Lib.Consts;
import Lib.IObserver;
import Lib.Logger;
import Net.ClientSocket;
import Net.ServerNet;

public class ConjuroComms extends Observable implements IObserver, Consts {
    private ClientSocket client;
    private static ServerNet server;
    private ArrayList<Card> otherPlayerCards;
    private boolean Hosting;
    private boolean Loser=false;

    public ConjuroComms() {
    	Hosting=false;
    	
    }

    public void conectarAJuego(String pHost) {

        client = new ClientSocket(pHost, PORT_NUMBER);
        client.addObserver(this);
        System.out.println("Joined");

    }

    public void iniciarJuegoNuevo() {
        try {
            server = new ServerNet(this);
            server.addObserver(this);
            Hosting=true;
            System.out.println("hosting");


        }catch (Exception e ) {
            Logger.Log(e.getMessage());
        }
        server.startListening(this);
        //  conectarAJuego("127.0.0.1");

    }

    public boolean isLoser() {
		return Loser;
	}

	public void setLoser(boolean loser) {
		Loser = loser;
	}

	public void notify(Object pData) {
        ConjuroMsg msg = (ConjuroMsg)pData;
        System.out.println("Recibido");
        if (msg.getType()==Deck.class) {
            System.out.println("Deck");
        }
        else if (msg.getType() == ArrayList.class) {
            System.out.println("Cards");

            this.otherPlayerCards = ((ArrayList<Card>) msg.getObjs().get(0));

            msg=null;
        }else if(msg.getType()==boolean.class){
        	Loser=(boolean) msg.getObjs().get(0);
        	System.out.println("You Lose");
        	
        }


    }

    public ArrayList<Card> getOtherPlayerCards() {
        return this.otherPlayerCards;
    }

    public void ClearOtherPlayerCards() {
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
    public Boolean isHosting() {
        return this.Hosting;
    }


}
