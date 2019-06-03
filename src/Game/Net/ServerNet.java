package Net;

import Lib.*;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class ServerNet implements Consts, Runnable {
    private static ServerNet server;
    private List<ClientSocket> clients;
    private ServerSocket serverSocket;
    private boolean isListening;
    private IObserver observer;
    private ServerNet(IObserver pObserver) throws Exception {
        this.clients = new ArrayList<>();
        this.isListening = true;
        serverSocket = new ServerSocket(PORT_NUMBER);
        this.observer = pObserver;
    }
    public synchronized static void startListening(IObserver pObserver) {
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
            try {
                Socket newSocket = serverSocket.accept();

                ClientSocket client = new ClientSocket(newSocket);
                client.addObserver(this.observer);
                this.clients.add(client);

                Thread.sleep(THREAD_SLEEP_TIME);
            } catch (Exception ex) {
                Logger.Log(ex.getMessage());
            }
        }
    }

    public void stopListen() {
        isListening = false;
    }
}
