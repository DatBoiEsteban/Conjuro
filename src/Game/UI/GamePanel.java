package UI;

import Game.*;
import Lib.Consts;
import Lib.Logger;

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

    public GamePanel(int Width, int Height) {
        this.cardLabels = new ArrayList<>();
        this.game = new Game();
        try {
            this.game.start();
        } catch (Exception e) {
            Logger.Log(e.getMessage());
        }
        this.setBounds(0, 0, Width, Height);
        this.setBackground(new Color(100));
        this.setLayout(null);
        initComponents();
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
            }
        });
        timeTread.start();
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
    }



}
