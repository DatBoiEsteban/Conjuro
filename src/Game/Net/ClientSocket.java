package Net;

import ConjuroNet.ConjuroMsg;
import Lib.Consts;
import Lib.Logger;
import Lib.Observable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSocket extends Observable implements Consts, Runnable {
    private Socket client;
    private ObjectInputStream inputReader;
    private ObjectOutputStream outputWriter;
    private boolean isListening;
    public ClientSocket(Socket pSocket) {
        this.client = pSocket;
        initReaders();
    }

    public ClientSocket(String pIp, int pPort) {
        try {
            client = new Socket(pIp, pPort);
            initReaders();
        } catch (Exception ex) {
            Logger.Log(ex.getMessage());
        }
    }

    public void sendMessage(ConjuroMsg pMsg) {
        try {
        	
            outputWriter.writeObject(pMsg);
            outputWriter.flush();
        } catch (Exception ex) {
            Logger.Log(ex.getMessage());
        }
    }

    public void run() {
        while (isListening) {
            try {

                ConjuroMsg msg = (ConjuroMsg)inputReader.readObject();
                this.notifyObservers(msg);
                Thread.sleep(THREAD_SLEEP_TIME);
            } catch (Exception ex) {
                //Logger.Log(ex.getMessage());
            }
        }
    }
    public void stop() {
        try {
            isListening = false;
            inputReader.close();
            outputWriter.close();
            client.close();
        } catch (Exception ex) {
            //Logger.Log(ex.getMessage());
        }
    }
    public void initReaders() {
        if(client != null) {
            try {
                Thread newThread = new Thread(this);
                newThread.start();

                outputWriter = new ObjectOutputStream(client.getOutputStream());               
                inputReader = new ObjectInputStream(client.getInputStream());
                isListening = true;
         
            }
             catch (Exception ex) {
                Logger.Log(ex.getMessage());
            }
        }
    }

    public Boolean isConnected() {
        return this.isListening;
    }

}
