import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import java.io.IOException;



public class Menu extends Screen implements MouseListener {

	private BufferedImage backgroundImage, rulesImage;
	private ScreenManager sm;
	private Wall startButton;
	private Wall helpButton;
	private Wall backButton;
	private String menuState = "main"; //rules

	public Menu(ScreenManager sm){

		this.sm = sm;

		try{
			backgroundImage = ImageIO.read(getClass().getResource("resources\\Start Screen.png"));
			rulesImage = ImageIO.read(getClass().getResource("resources\\Rules.png"));
		}catch(IOException e){
			System.err.println("Failed to retrieve images for menu");
		}

		startButton = new Wall(null, 390, 110, 415, 435);
		helpButton = new Wall(null, 340, 90, 440, 615);
		backButton = new Wall(null, 190, 65, 1030, 900);


		addMouseListener(this);
	}

	public void mouseClicked(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mousePressed(MouseEvent e){


		if(startButton.intersects(new Rectangle(e.getX(), e.getY(), 2, 2)) && menuState == "main"){
			System.out.println("Start Button");
			sm.forceGameState("LEVEL_1");
		}

		if(helpButton.intersects(new Rectangle(e.getX(), e.getY(), 2, 2)) && menuState == "main"){
			System.out.println("Help Button");
			menuState = "rules";
		}

		if(backButton.intersects(new Rectangle(e.getX(), e.getY(), 2, 2)) && menuState == "rules"){
			System.out.println("Back Button");
			menuState = "main";
		}



	}





	public void paintComponent(Graphics g){
		super.paintComponent(g);

		if(backgroundImage != null && menuState == "main")
			g.drawImage(backgroundImage, 0, 0, 1280, 1024, null);

		if(rulesImage != null && menuState == "rules")
			g.drawImage(rulesImage, 0, 0, 1280, 1024, null);

		startButton.draw(g);
		helpButton.draw(g);
		backButton.draw(g);
	}
}