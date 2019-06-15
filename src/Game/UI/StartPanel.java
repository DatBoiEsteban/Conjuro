package UI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPanel extends IPanel {

    private JLabel MainText;
    private JButton HostButton;
    private JButton JoinButton;
    private Boolean Host = false;
    private Boolean Join = false;

    public StartPanel(int Width, int Height) {
        this.setSize(Width, Height);
        this.setBackground(new Color(100, 100, 100));
        this.setLayout(null);
        initComponents();
    }

    private void initComponents() {
        this.MainText = new JLabel("Conjuro");
        this.MainText.setFont(new Font("Serif", Font.PLAIN, 100));
        this.MainText.setBounds(getWidth()/2 - 350 /2,(getHeight() / 5 - 60)* 2 , 350, 120);

        this.HostButton = new JButton("Host");
        this.HostButton.setFont(new Font("Serif", Font.PLAIN, 20));
        this.HostButton.setBounds(getWidth()/2 - 50, ((getHeight() /5) * 3), 100, 50);
        this.HostButton.addActionListener(this::selectHost);

        this.JoinButton = new JButton("Join");
        this.JoinButton.setFont(new Font("Serif", Font.PLAIN, 20));
        this.JoinButton.setBounds(getWidth()/2 - 50, ((getHeight() /5) * 4), 100, 50);
        this.JoinButton.addActionListener(this::selectJoin);

        this.add(this.MainText);
        this.add(this.HostButton);
        this.add(this.JoinButton);
    }

    public void selectHost(ActionEvent e){
        this.Host = true;
    }

    public void selectJoin(ActionEvent e) {
        this.Join = true;
    }

    public Boolean getHost() {
        return this.Host;
    }

    public Boolean getJoin() {
        return this.Join;
    }
}
