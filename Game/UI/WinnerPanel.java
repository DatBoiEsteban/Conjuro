package UI;

import javax.swing.*;

import Game.Game;

import java.awt.*;

public class WinnerPanel extends IPanel {
	private String key;
    private JLabel MainText;
    private int IWon;
    private JTextField ToDecrypt;
    public WinnerPanel(int Width, int Height, int AmITheWinner, String pKey) {
    	key=pKey;
        this.setBounds(0, 0, Width, Height);
        this.setBackground(new Color(100));
        this.setLayout(null);
        this.IWon = AmITheWinner;
        initComponents();
        
    }

    private void initComponents() {
        this.ToDecrypt = new JTextField();
        this.ToDecrypt.setSize(400, 30);
        this.ToDecrypt.setLocation(getWidth() - this.ToDecrypt.getWidth() - 50, 50);
        this.ToDecrypt.setText(key);
        this.add(ToDecrypt);
        this.MainText = new JLabel();
        if (this.IWon==2) {
            this.MainText.setText("You WIN!");
            this.MainText.setSize(500, 200);
        }else if(this.IWon==1){
            this.MainText.setText("DRAW");
            this.MainText.setSize(300, 200);
        }
    	else {

            this.MainText.setText("You LOOSE");
            this.MainText.setSize(600, 200);
        }
        this.MainText.setFont(new Font("Serif", Font.PLAIN, 100));
        this.MainText.setLocation(getWidth() / 2 - this.MainText.getWidth() / 2, getHeight() / 2 - this.MainText.getHeight() / 2);

        this.add(this.MainText);
    }
}
