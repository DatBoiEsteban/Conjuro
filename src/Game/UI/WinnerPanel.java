package UI;

import javax.swing.*;
import java.awt.*;

public class WinnerPanel extends IPanel {

    private JLabel MainText;
    private Boolean IWon;

    public WinnerPanel(int Width, int Height, Boolean AmITheWinner) {
        this.setBounds(0, 0, Width, Height);
        this.setBackground(new Color(100));
        this.setLayout(null);
        this.IWon = AmITheWinner;
        initComponents();
    }

    private void initComponents() {
        this.MainText = new JLabel();
        if (this.IWon) {
            this.MainText.setText("You WIN!");
            this.MainText.setSize(500, 200);
        } else {
            this.MainText.setText("You LOOSE");
            this.MainText.setSize(600, 200);
        }
        this.MainText.setFont(new Font("Serif", Font.PLAIN, 100));
        this.MainText.setLocation(getWidth() / 2 - this.MainText.getWidth() / 2, getHeight() / 2 - this.MainText.getHeight() / 2);

        this.add(this.MainText);
    }
}
