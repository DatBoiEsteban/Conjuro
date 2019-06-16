package Net;

import Lib.*;
import Lib.Observable;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

import ConjuroNet.ConjuroComms;
import ConjuroNet.ConjuroMsg;
import Game.Card;
import Game.Deck;

public class ServerNet extends Observable implements Consts, Runnable {
    private ServerNet server;
    private Socket client;
    private ObjectInputStream inputReader;
    private ObjectOutputStream outputWriter;
    private ServerSocket serverSocket;
    private boolean isListening;
    private boolean connected;

    private IObserver observer;
    public ServerNet(IObserver pObserver) throws Exception {
        this.isListening = true;
        serverSocket = new ServerSocket(PORT_NUMBER);
        this.observer = pObserver;
        this.server = this;



    }

    /* public List<ClientSocket> getClients() {
         return clients;
     }
     public void setClients(List<ClientSocket> clients) {
         this.clients = clients;
     }*/
    public void sendMessage(ConjuroMsg pMsg) {
        try {
            outputWriter.writeObject(pMsg);
            outputWriter.flush();
            System.out.println("Enviando");
        } catch (Exception ex) {
            Logger.Log(ex.getMessage());
        }
    }
    public synchronized void startListening(IObserver pObserver) {
        try {
            if (server == null) {
                server = new ServerNet(pObserver);
            }
            Thread listenThread = new Thread(server);
            listenThread.start();
        } catch (Exception ex) {
            Logger.Log(ex.getMessage());
        }


    }

    public void run() {

        while (isListening) {
            if(!connected){
                try {

                    System.out.println("Waiting For join");

                    client = serverSocket.accept();
                    initReaders();
                /*ClientSocket pclient = new ClientSocket(newSocket);
                //client.addObserver(this.observer);
                this.client = (pclient);*/
                    System.out.println("Conectado");
                    connected=true;
                    Thread.sleep(THREAD_SLEEP_TIME);
                } catch (Exception ex) {
                    // Logger.Log(ex.getMessage());
                }
            }
            try {
                ConjuroMsg msg = (ConjuroMsg)inputReader.readObject();
                this.notifyObservers(msg);
                Thread.sleep(THREAD_SLEEP_TIME);
            } catch (Exception ex) {
                //Logger.Log(ex.getMessage());
            }
        }
    }

    public void stopListen() {
        isListening = false;
    }
    public void initReaders() {
        if(client != null) {
            try {
                isListening = true;

                Thread newThread = new Thread(this);
                newThread.start();

                outputWriter = new ObjectOutputStream(client.getOutputStream());
                inputReader = new ObjectInputStream(client.getInputStream());

            }
            catch (Exception ex) {
                Logger.Log(ex.getMessage());
            }
        }
    }

    public Socket getClient() {
        return this.client;
    }

    public static void main(String [] args) throws  Exception
    {
        //ConjuroComms server2 = new ConjuroComms();


    }
}
