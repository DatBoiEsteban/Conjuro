package Game.ConjuroNet;

import Game.Lib.Consts;
import Game.Lib.IObserver;
import Game.Net.ClientSocket;
import Game.Net.ServerNet;

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
