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
    private Game game;
    private boolean restarted;

    public ConjuroGui(Game pGame,ConjuroComms pConnection) {
    	restarted=false;
    	game=pGame;
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
            	//repaint();
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
                       
                        repaint();
                    }else if (game.isDecrypted()){
                    	((GamePanel) ScreenPanel).removeCards();
                    }
                    if(this.game.getRound()==2&&!restarted){
                    	this.game.GameReset();

                    	((GamePanel) ScreenPanel).removeAll();
                    	 ((GamePanel) ScreenPanel).add( ((GamePanel) ScreenPanel).ToDecrypt);
                    	 ((GamePanel) ScreenPanel).add( ((GamePanel) ScreenPanel).ElapsedTime);
                    	 ((GamePanel) ScreenPanel).printPlayerCards();
                    	restarted = true;
                    	//add your elements
                    	revalidate();
                    	repaint();
                    }
                    if(this.game.getRound()==3){
                    	remove(ScreenPanel);
                    	
                    	ScreenPanel =  new WinnerPanel(getWidth(), getHeight(),game.getPlayer().getWins(),game.getPlayer().getKey());
                    	add(ScreenPanel);
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
    	EncriptionReader reader = new EncriptionReader("keys.txt");
    	ConjuroComms comms = new ConjuroComms();
    	Game game = new Game(comms,reader.getRandomKey());
        ConjuroGui  GUI = new ConjuroGui(game,comms);
    }
}
