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
    private List<CardLabel> otherPlayer;

    JLabel ElapsedTime;
    private Long StartTime;
    JTextField ToDecrypt;
    private ClientSocket client;
    private ServerNet server;

    public GamePanel(int Width, int Height,Game pGame, ClientSocket pClient) {
    	this.game= pGame;
        this.client = pClient;
        this.cardLabels = new ArrayList<>();
        try {
            this.game.startGame();
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
            this.game.startGame();
        } catch (Exception e) {
            Logger.Log(e.getMessage());
        }
        this.setBounds(0, 0, Width, Height);
        this.setBackground(new Color(100));
        this.setLayout(null);
        initComponents();
    }
    private void initComponents() {

    	//restarted=false;


        PlayerDeck = this.game.getPlayer().getDeck().getDeckCards();
        printPlayerCards();
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
                setText(game.getPlayer().getKey());
                ArrayList<Card> cardsToSend = this.game.getPlayer().getCardsToSend();
                if (cardsToSend.size() > 2 &&   !this.game.getPlayer().isCardsSent()) {
                    ConjuroMsg msg = new ConjuroMsg(ArrayList.class);
                    msg.addObject(cardsToSend);
                    this.game.getPlayer().setCardsSent(true);
                    if (client != null) {
                        client.sendMessage(msg);
                    } else {
                        server.sendMessage(msg);
                    }

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
    public void printPlayerCards(){
    	cardLabels = new ArrayList<CardLabel>();
        for (int pos = 0; pos < PlayerDeck.length; pos++) {
            CardLabel cardLabel = new CardLabel(PlayerDeck[pos].getImagen(), (CARD_WIDTH + 10)* pos + 17, getHeight() - CARD_HEIGHT - 50, pos, this.game.getPlayer());
            cardLabels.add(cardLabel);
            this.add(cardLabel);
        }
    }
    public void removeCards() {
        for (int i = 0; i < otherPlayer.size(); i++) {
        	this.remove(otherPlayer.get(i));

        }
    }
    public void setText(String pText) {
        this.ToDecrypt.setText(pText);
    }
    public void setOtherPlayerCards(ArrayList<Card> pCards) {
    	otherPlayer= new ArrayList<CardLabel>();
        for (int i = 0; i < pCards.size(); i++) {
            CardLabel cardLabel = new CardLabel(pCards.get(i).getImagen(), 300 * (i + 1), 450 - CARD_HEIGHT / 2, i);
        	otherPlayer.add(cardLabel);

            this.add(cardLabel);
        }
    }

}
