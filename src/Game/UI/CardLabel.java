package UI;

import Lib.Consts;

import javax.swing.*;

public class CardLabel extends JLabel implements Consts {

    public ImageIcon image;

    public CardLabel(String Name, int PosX, int PosY) {
        this.image = new ImageIcon(Name + ".jpg");
        this.setIcon(this.image);
        this.setBounds(PosX, PosY, CARD_WIDTH, CARD_HEIGHT);
    }
}
