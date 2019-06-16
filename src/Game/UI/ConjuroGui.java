package UI;

import ConjuroNet.ConjuroComms;
import Lib.Consts;

import javax.swing.*;
import java.awt.*;

public class ConjuroGui extends JFrame implements Consts {

    private IPanel ScreenPanel;

    public ConjuroGui() {
        this.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.ScreenPanel = new StartPanel(SCREEN_WIDTH, SCREEN_HEIGHT); //new StartPanel(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setTitle("Conjuro");
        this.setResizable(false);
        this.getContentPane().setLayout(null);
        initComponents();
        this.getContentPane().add(this.ScreenPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        Thread PanelChanger = new Thread(() -> {
            while (Thread.currentThread().isAlive()) {
                if (ScreenPanel.getClass() == StartPanel.class) {
                    if (((StartPanel) ScreenPanel).getHost()) {
                        remove(ScreenPanel);
                        ScreenPanel = new HostPanel(getWidth(), getHeight());
                        add(ScreenPanel);
                        repaint();
                    } else if (((StartPanel) ScreenPanel).getJoin()) {
                        remove(ScreenPanel);
                        ScreenPanel = new JoinPanel(getWidth(), getHeight());
                        add(ScreenPanel);
                        repaint();
                    }
                } else if (ScreenPanel.getClass() == JoinPanel.class) {
                    if (((JoinPanel)ScreenPanel).getTextReady()) {
                        String Ip = ((JoinPanel)ScreenPanel).getIp();
                        ConjuroComms Client = new ConjuroComms();
                        Client.conectarAJuego(Ip);
                    }
                } else if (ScreenPanel.getClass() == HostPanel.class) {
                    ConjuroComms host = new ConjuroComms();
                    host.iniciarJuegoNuevo();
                    host.getServer().getClients();
                }
            }
        });
        PanelChanger.start();
    }

    public void initComponents() {

    }

    public static void main(String[] args) {
        ConjuroGui a = new ConjuroGui();
    }
}
