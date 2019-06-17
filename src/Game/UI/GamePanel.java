package UI;

import ConjuroNet.ConjuroComms;
import ConjuroNet.ConjuroMsg;
import Game.*;
import Lib.Consts;
import Lib.Logger;
import Net.ClientSocket;
import Net.ServerNet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

public class GamePanel extends IPanel implements Consts {
    private Game game;
    private Card[] PlayerDeck;
    private List<CardLabel> cardLabels;
    private JLabel ElapsedTime;
    private Long StartTime;
    private JTextField ToDecrypt;
    private ClientSocket client;
    private ServerNet server;

    public GamePanel(int Width, int Height,Game pGame, ClientSocket pClient) {
    	this.game= pGame;
        this.client = pClient;
        this.cardLabels = new ArrayList<>();
        try {
            this.game.start();
        } catch (Exception e) {
            Logger.Log(e.getMessage());
        }
        this.setBounds(0, 0, Width, Height);
        this.setBackground(new Color(100));
        this.setLayout(null);
        initComponents();

    }

    public GamePanel (int Width, int Height,Game pGame, ServerNet pServer) {
    	this.game= pGame;

        this.server = pServer;
        this.cardLabels = new ArrayList<>();
        try {
            this.game.start();
        } catch (Exception e) {
            Logger.Log(e.getMessage());
        }
        this.setBounds(0, 0, Width, Height);
        this.setBackground(new Color(100));
        this.setLayout(null);
        initComponents();
    }
    private void initComponents() {




        PlayerDeck = this.game.getPlayer().getDeck().getDeckCards();
        for (int pos = 0; pos < PlayerDeck.length; pos++) {
            CardLabel cardLabel = new CardLabel(PlayerDeck[pos].getImagen(), (CARD_WIDTH + 10)* pos + 17, getHeight() - CARD_HEIGHT - 50, pos, this.game.getPlayer());
            cardLabels.add(cardLabel);
            this.add(cardLabel);
        }

        this.ElapsedTime = new JLabel();
        this.ElapsedTime.setBounds(40, 40, 100, 30);
        this.ElapsedTime.setForeground(new Color(255,255,255));

        this.ToDecrypt = new JTextField();
        this.ToDecrypt.setSize(400, 30);
        this.ToDecrypt.setLocation(getWidth() - this.ToDecrypt.getWidth() - 50, 50);

        this.add(this.ToDecrypt);
        this.add(this.ElapsedTime);

        this.StartTime = System.currentTimeMillis();
        Thread timeTread = new Thread(() -> {
            while(Thread.currentThread().isAlive()) {
                Long durationInMillis = System.currentTimeMillis()- this.StartTime;
                long millis = durationInMillis % 1000;
                long second = (durationInMillis / 1000) % 60;
                long minute = (durationInMillis / 60000) % 60;
                long hour = (durationInMillis / 120000) % 24;

                String time = String.format("%02d:%02d:%02d.%d", hour, minute, second, millis);
                this.ElapsedTime.setText(time);
                if (minute == 12) {
                    System.exit(0);
                }
                ArrayList<Card> cardsToSend = this.game.getPlayer().getCardsToSend();
                if (cardsToSend.size() > 2 ) {
                    ConjuroMsg msg = new ConjuroMsg(ArrayList.class);
                    msg.addObject(cardsToSend);
                    if (client != null) {
                        client.sendMessage(msg);
                    } else {
                        server.sendMessage(msg);
                    }

                    this.game.getPlayer().clearCardsToSend();
                }
                try {
                    Thread.sleep(THREAD_SLEEP_TIME);
                } catch (Exception e) {
                    Logger.Log(e.getMessage());
                }
            }
        });
        timeTread.start();
    }

    public void setOtherPlayerCards(ArrayList<Card> pCards) {
        for (int i = 0; i < pCards.size(); i++) {
            CardLabel cardLabel = new CardLabel(pCards.get(i).getImagen(), 300 * (i + 1), 450 - CARD_HEIGHT / 2, i);
            this.add(cardLabel);
        }
    }
    public void setText(String pText) {
        this.ToDecrypt.setText(pText);
    }
}
