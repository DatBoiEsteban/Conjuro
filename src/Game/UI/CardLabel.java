package UI;

import Game.Card;
import Game.Player;
import Lib.Consts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CardLabel extends JLabel implements Consts, MouseListener {

    private ImageIcon image;
    private String Name;
    private int ListPos;
    private Player Player;

    public CardLabel(String Name, int PosX, int PosY, int ListPos, Player pCard) {
        this.Name = Name;
        this.Player = pCard;
        this.image = new ImageIcon(Name);
        this.ListPos = ListPos;
        Image image = this.image.getImage();
        Image newimg = image.getScaledInstance(CARD_WIDTH, CARD_HEIGHT,  java.awt.Image.SCALE_SMOOTH);
        this.image = new ImageIcon(newimg);
        this.setIcon(this.image);
        this.setBounds(PosX, PosY, CARD_WIDTH, CARD_HEIGHT);
        this.addMouseListener(this);
    }
    public CardLabel(String Name, int PosX, int PosY, int ListPos) {
        this.Name = Name;
        this.Player = null;
        this.image = new ImageIcon(Name);
        this.ListPos = ListPos;
        Image image = this.image.getImage();
        Image newimg = image.getScaledInstance(CARD_WIDTH, CARD_HEIGHT,  java.awt.Image.SCALE_SMOOTH);
        this.image = new ImageIcon(newimg);
        this.setIcon(this.image);
        this.setBounds(PosX, PosY, CARD_WIDTH, CARD_HEIGHT);
    }


    @Override
    public void mouseClicked(MouseEvent e) {

        if (this.Player.addCardsToSend(this.Player.getDeck().getDeckCards()[this.ListPos])){
            System.out.println(this.Name);

            this.removeMouseListener(this);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
