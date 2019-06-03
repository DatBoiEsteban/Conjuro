package ConjuroNet;

import Lib.Consts;
import Lib.IObserver;
import Net.ClientSocket;
import Net.ServerNet;

public class ConjuroComms implements IObserver, Consts {
    private ClientSocket client;
    public ConjuroComms() {

    }

    public void conectarAJuego(String pHost) {
        client = new ClientSocket(pHost, PORT_NUMBER);
        client.addObserver(this);
    }

    public void iniciarJuegoNuevo() {
        ServerNet.startListening(this);

    }

    public void notify(Object pData) {
        ConjuroMsg msg = (ConjuroMsg)pData;
        switch (msg.getType()) {

        }
    }
}
