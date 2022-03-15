import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;
import java.util.ArrayList;

public class IntroScreen extends Screen{

	private ScreenManager sm;

	private BufferedImage[] anim;
	private BufferedImage currentImage;

	private int animTimer = 0;
	private int animCounter = 0;

	public IntroScreen(ScreenManager sm){

		this.sm = sm;

		anim = ImageArrayCreator.getIntroArray("anim4.png","anim3.png","anim2.png","anim1.png","base.png");

		currentImage = anim[animCounter];

	}

	public void update(ArrayList<Integer> keyList){

		if(animTimer == 30){
			animTimer = 0;

			if(animCounter == 4)
				stop();

			animCounter+=1;
		}

		animTimer++;
		//System.out.println(animCounter);
	}

	public void stop(){
		sm.forceGameState("MENU");
	}



	public void paintComponent(Graphics g){
		super.paintComponent(g);

		g.drawImage(anim[animCounter], 0, 0, null);


	}




}