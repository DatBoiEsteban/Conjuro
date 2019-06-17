package UI;

import ConjuroNet.ConjuroComms;
import Game.Game;
import Game.Player;
import Lib.Consts;
import Lib.EncriptionReader;
import Lib.Logger;

import javax.swing.*;
import java.awt.*;

public class ConjuroGui extends JFrame implements Consts {

    private IPanel ScreenPanel;
    private ConjuroComms connection;


    public ConjuroGui(Game game,ConjuroComms pConnection) {
    	connection=pConnection;
        this.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.ScreenPanel = new StartPanel(SCREEN_WIDTH, SCREEN_HEIGHT); //new StartPanel(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setTitle("Conjuro");
        this.setResizable(false);
        this.getContentPane().setLayout(null);
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
                        this.connection.conectarAJuego(Ip);
                        if (this.connection.isConnected()) {
                            remove(ScreenPanel);
                            ScreenPanel = new GamePanel(getWidth(), getHeight(), game,this.connection.getClient());
                            add(ScreenPanel);
                            repaint();
                        }
                    }
                } else if (ScreenPanel.getClass() == HostPanel.class) {
                    if (!this.connection.isHosting()) {
                        this.connection.iniciarJuegoNuevo();
                    } else {
                        if (this.connection.getServer().getClient() != null) {
                            remove(ScreenPanel);
                            ScreenPanel = new GamePanel(getWidth(), getHeight(),game, this.connection.getServer());
                            add(ScreenPanel);
                            repaint();
                        }
                    }
                } else if (ScreenPanel.getClass() == GamePanel.class) {
                    if (this.connection.getOtherPlayerCards() != null) {
                        ((GamePanel) ScreenPanel).setOtherPlayerCards(this.connection.getOtherPlayerCards());
                        this.connection.clearOtherPlayerCards();
                        repaint();
                    }
                }
                try {
                    Thread.sleep(THREAD_SLEEP_TIME);
                } catch (Exception e) {
                    Logger.Log(e.getMessage());
                }
            }
        });
        PanelChanger.start();
    }

    public static void main(String[] args) {
    	ConjuroComms comms = new ConjuroComms();
        EncriptionReader reader = new EncriptionReader("keys.txt");
    	Game game = new Game(comms, reader.getRandomKey());

        ConjuroGui a = new ConjuroGui(game,comms);
    }
}
