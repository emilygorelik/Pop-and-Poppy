import java.awt.Graphics;
import javax.swing.Timer;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.Font;
import javax.imageio.ImageIO;
import java.io.IOException;


public class Level extends Screen implements MouseListener{

	private BufferedImage logo, lifeCounterImage, exitImage;
	private String time;
	private int count = 0;
	private int secondTime = 0;
	private int minuteTime = 0;
	private boolean intermediateZero = false;
	private int timeOffset = 45;
	private Font timeFont = null;
	private Font liveFont = null;
	private int popInZoneCounter = 0;
	private int cokeInZoneCounter = 0;
	private int totalLives = 3;
	private int cokeLives = 10;
	private Main main;
	private String screenID = "";
	private Rectangle exitButton;
	private Player pop, coke;
	private int[] popLoc = new int[2];
	private int[] cokeLoc= new int[2];


	public Level(Main main, String screenID){

		this.main = main;
		this.screenID = screenID;


		try{
			logo = ImageIO.read(getClass().getResource("resources//logo1.png"));
			lifeCounterImage = ImageIO.read(getClass().getResource("resources//lifeCounter.png"));
			exitImage = ImageIO.read(getClass().getResource("resources//exit sign.png"));
		}catch(IOException e){
			e.printStackTrace();
		}

		exitButton = new Rectangle(960, 0, exitImage.getWidth(), exitImage.getHeight());


		time = "" + secondTime;
		timeFont = new Font("Sans Serif", Font.BOLD, 75);
		liveFont = new Font("Sans Serif", Font.BOLD, 40);


		secondTime = 0;
		minuteTime = 0;
		addMouseListener(this);

	}
	public void givePlayers(Player pop, Player coke){
		this.pop = pop;
		this.coke = coke;
		popLoc[0] = pop.getX();
		popLoc[1] = pop.getY();
		cokeLoc[0] = coke.getX();
		cokeLoc[1] = coke.getY();

		System.out.println("Pop loc: x: " + popLoc[0] + " y: " + popLoc[1]);
	}

	public void update(){
		count++;
		if(count == 59){
			secondTime++;

			if(secondTime == 60){
				minuteTime++;

				secondTime = 0;
				timeOffset = 85;
			}

			if(secondTime < 10)
				intermediateZero = true;
			else
				intermediateZero = false;


			if(minuteTime > 0){

				if(intermediateZero)
					time = minuteTime + ":" + 0 + "" + secondTime;
				else
					time = minuteTime + ":" + secondTime;
			}
			else
				time = "" + secondTime;



			count = 0;
		}


	}


	public void mouseClicked(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mousePressed(MouseEvent e){


		if(exitButton.intersects(new Rectangle(e.getX(), e.getY(), 2, 2))){
			System.out.println("Exit Button");
			main.setGameState("MENU");
		}



	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);

		drawLeftFrame(g);
		drawRightFrame(g);
		drawBottomFrame(g);
		drawTime(g);
		drawLives(g);
		drawExitButton(g);
		g.drawImage(logo, 5, 0, logo.getWidth(), logo.getHeight(), null);


	}

	public void drawTime(Graphics g){
		g.setFont(timeFont);
		g.drawString(time, 1280/2-timeOffset, 75);
	}

	public void drawLives(Graphics g){

		g.setFont(liveFont);
		g.drawImage(lifeCounterImage, logo.getWidth() + 10, 0, null);
		g.drawString("" + totalLives, logo.getWidth() + 65, 110);
	//	g.drawString("" + popLives, logo.getWidth() + 50, 110);
		//g.drawString("" + cokeLives, logo.getWidth() + 132, 110);
	}

	public void drawExitButton(Graphics g){

		g.drawImage(exitImage, 960, 0, null);
	}

	public void drawLeftFrame(Graphics g){
		g.fillRect(0, 0, 5, logo.getHeight());
		g.fillRect(logo.getWidth() + 5, 0, 5, logo.getHeight());
	}

	public void drawRightFrame(Graphics g){
		g.fillRect(1275, 0, 5, logo.getHeight());
		g.fillRect(1280-logo.getWidth(), 0, 5, logo.getHeight());
	}

	public void drawBottomFrame(Graphics g){
		g.fillRect(0, logo.getHeight(), 1280, 5);
	}

	public void madeSafezone(String id, int value){

		if(id.equalsIgnoreCase("pop")){
			if(value == 1)
				popInZoneCounter = 1;
			else
				popInZoneCounter = 0;

		}
		else if(id.equalsIgnoreCase("coke")){
			if(value == 1)
				cokeInZoneCounter = 1;
			else
				cokeInZoneCounter = 0;

		}

		if(popInZoneCounter + cokeInZoneCounter == 2){

			if(screenID.equalsIgnoreCase("LEVEL_1"))
				main.setGameState("LEVEL_2");
			else if(screenID.equalsIgnoreCase("LEVEL_2"))
				main.setGameState("LEVEL_3");
			else if(screenID.equalsIgnoreCase("LEVEL_3"))
				main.setGameState("LEVEL_4");
			else if(screenID.equalsIgnoreCase("LEVEL_4"))
				main.setGameState("LEVEL_5");
			else if(screenID.equalsIgnoreCase("LEVEL_5"))
				main.setGameState("WIN_SCREEN");
		}
	}

	public void minusLives(String id){

		if(id.equalsIgnoreCase("pop"))
			totalLives--;
		else if(id.equalsIgnoreCase("coke"))
			totalLives--;

		if(totalLives <= 0){
			main.setGameState("LOSE_SCREEN");
			System.out.println("Lose");
		}

	}

	public void setLives(int totalLives){
		//this.popLives = popLives;
		//this.cokeLives = cokeLives;
		this.totalLives = totalLives;
	}

	public int getLives(){ return totalLives; }
	//public int getPopLives(){return popLives; }
	//public int getCokeLives(){ return cokeLives; }

	public void setTime(int minuteTime, int secondTime){
		this.minuteTime = minuteTime;
		this.secondTime = secondTime;
	}

	public void reload(){
		pop.setX(popLoc[0]);
		pop.setY(popLoc[1]);
		coke.setX(cokeLoc[0]);
		coke.setY(cokeLoc[1]);
	}

	public int getMinutes(){ return minuteTime; }
	public int getSeconds(){ return secondTime; }
	public String getTime(){ return time; }

}