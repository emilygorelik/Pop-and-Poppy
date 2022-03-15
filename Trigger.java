import java.util.*;
import java.awt.*;
import java.awt.image.*;

public class Trigger
{
	private BufferedImage skin;
	private int width, height, x , y;
	private TempWall tempWall;
	private boolean activate = false;
	private int counter = 0;
	private int popOnTrigger = 0;
	private int cokeOnTrigger = 0;

	public Trigger(BufferedImage mySkin, int myWidth, int myHeight, int myX, int myY, TempWall myTW)
	{
		skin = mySkin;
		width = myWidth;
		height = myHeight;
		x = myX;
		y = myY;
		tempWall = myTW;
	}


	public void pressed(String id, int value){

		if(id.equalsIgnoreCase("pop")){
			if(value == 1){
				popOnTrigger = 1;
				System.out.println(id + " is on the button");
			}else
				popOnTrigger = 0;
		}
		else if(id.equalsIgnoreCase("coke")){
			if(value == 1)
				cokeOnTrigger = 1;
			else
				cokeOnTrigger = 0;
		}

		if(popOnTrigger == 1 || cokeOnTrigger == 1)
			hide();


		if(popOnTrigger == 0 && cokeOnTrigger == 0)
			show();


	}

	public void show(){ tempWall.show(); }
	public void hide(){ tempWall.hide(); }

	public void update()
	{

	}

	public void draw(Graphics g)
	{
		g.drawImage(skin,x,y,null);
	}

	public boolean intersects(Rectangle userRect)
	{
		if(userRect.intersects(new Rectangle(x, y, width, height)))
		{
			return true;
		}
		return false;
	}

}