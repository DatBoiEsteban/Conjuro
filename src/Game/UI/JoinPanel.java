package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class JoinPanel extends IPanel {

    private JTextField TextField;
    private JLabel MainText;
    private JButton ConnectButton;
    private String Ip;
    private Boolean TextReady = false;

    public JoinPanel(int Width, int Height) {
        this.setSize(Width, Height);
        this.setBackground(new Color(100, 100, 100));
        this.setLayout(null);
        initComponents();
    }

    private void initComponents() {
        this.MainText = new JLabel("Conjuro");
        this.MainText.setFont(new Font("Serif", Font.PLAIN, 100));
        this.MainText.setBounds(getWidth()/2 - 350 /2,(getHeight() / 5 - 60)* 2 , 350, 120);

        this.TextField = new JTextField();
        this.TextField.setBounds(this.getWidth()/3, this.getHeight()/2, this.getWidth()/3, 30);
        this.TextField.setToolTipText("Ip address to connect");

        this.ConnectButton = new JButton("Connect!");
        this.ConnectButton.setBounds(getWidth()/3, this.getHeight() * 2 / 3, this.getWidth() / 3, 30);
        this.ConnectButton.addActionListener(this::getIp);

        this.add(this.MainText);
        this.add(this.TextField);
        this.add(this.ConnectButton);

    }

    private void getIp(ActionEvent e) {
        this.Ip = this.TextField.getText();
        this.TextReady = true;
    }

    public String getIp() {
        return this.Ip;
    }

    public Boolean getTextReady() {
        return this.TextReady;
    }
}
