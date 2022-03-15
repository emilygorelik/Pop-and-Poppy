import java.awt.Graphics;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class LoseScreen extends Screen implements MouseListener{

	private BufferedImage loseImage;
	private Font timeFont;
	private String time = "";
	private Wall playAgainButton;
	ScreenManager sm;


	public LoseScreen(ScreenManager sm){

		this.sm = sm;

		try{

			loseImage = ImageIO.read(new File("resources\\lose.png"));
		}catch(IOException e){
			System.out.println("Failed to load images for WinScreen");
		}

		timeFont = new Font("Sans Serif", Font.BOLD, 75);
		playAgainButton = new Wall(null, 255, 50, 980, 890);

		addMouseListener(this);
	}

	public void update(){


	}

	public void mouseClicked(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mousePressed(MouseEvent e){


		if(playAgainButton.intersects(new Rectangle(e.getX(), e.getY(), 2, 2))){
			System.out.println("Play Again Button Button");
			sm.forceGameState("MENU");
		}





	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);

		g.setFont(timeFont);
		g.setColor(Color.RED);

		g.drawImage(loseImage, 0, 0, null);
		g.drawString("Your Time: ", 300, 900);
		g.drawString(" " + time, 700, 900);

		playAgainButton.draw(g);
	}

	public void setTime(String time){ this.time = time; }

}