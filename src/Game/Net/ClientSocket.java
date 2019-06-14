package Net;

import ConjuroNet.ConjuroMsg;
import Lib.Consts;
import Lib.Logger;
import Lib.Observable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClientSocket extends Observable implements Consts, Runnable {
    private Socket client;
    private DataInputStream inputReader;
    private DataOutputStream outputWriter;
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
            outputWriter.writeChars(pMsg.getStringMsg());
            outputWriter.flush();
        } catch (Exception ex) {
            Logger.Log(ex.getMessage());
        }
    }

    public void run() {
        while (isListening) {
            try {
                String msgData = inputReader.readUTF();

                ConjuroMsg msg = new ConjuroMsg(msgData);
                this.notifyObservers(msg);
                Thread.sleep(THREAD_SLEEP_TIME);
            } catch (Exception ex) {
                Logger.Log(ex.getMessage());
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
            Logger.Log(ex.getMessage());
        }
    }
    public void initReaders() {
        if(client != null) {
            try {
                inputReader = new DataInputStream(client.getInputStream());
                outputWriter = new DataOutputStream(client.getOutputStream());

                Thread newThread = new Thread(this);
                newThread.start();
            } catch (Exception ex) {
                Logger.Log(ex.getMessage());
            }
        }
    }
    public void sendMessage() { //TODO

    }
}
