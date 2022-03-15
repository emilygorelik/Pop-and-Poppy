//5/18/2018
// have a thread 60 fps
//Create Leaderboard
//add lives?

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.awt.event.KeyEvent;


public class Main extends JFrame {


	private String GAME_STATE = "INTRO";

	private MainThread mt = null;
	private ScreenManager sm;
	private MultiKeyListener2 mk;

	public Main(){
	}

	public void start(){
		setupFrame();
		sm = new ScreenManager(this);


		mt = new MainThread(this);
		mt.start();
	}
	//1280 1024
	public void setupFrame(){
		setPreferredSize(new Dimension(1295, 1044));
		setContentPane(new DrawingPanel());
		setBackground(Color.GRAY);
		setTitle("Pop and Poppy");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mk = new MultiKeyListener2();
		addKeyListener(mk);
		pack();
	}

	public void update(){
		sm.update();
	}

	public void setGameState(String GAME_STATE){

		this.GAME_STATE = GAME_STATE;

		sm.updateScreen();
	}

	public class DrawingPanel extends JPanel {
		public void paintComponent(Graphics g){
			super.paintComponent(g);

		}
	}

	public String getGameState(){ return GAME_STATE; }
	public ArrayList<Integer> getKeys(){ return mk.getKeyList(); }
	public static void main(String[] args){ Main m = new Main(); m.start(); }
}