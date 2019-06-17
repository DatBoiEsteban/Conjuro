package UI;

import javax.swing.*;
import java.awt.*;

public class HostPanel extends IPanel {

    private JLabel Text;

    public HostPanel(int Width, int Height) {
        this.setBounds(0, 0, Width, Height);
        this.setBackground(new Color(100, 100, 100));
        this.setLayout(null);
        initComponents();

    }

    public void initComponents() {
        this.Text = new JLabel("Esperando A Jugador");
        this.Text.setFont(new Font("Serif", Font.PLAIN, 35));
        this.Text.setBounds(getWidth()/2 - 350 /2,(getHeight() / 5 - 60)* 2 , 350, 120);

        this.add(this.Text);
    }
}
