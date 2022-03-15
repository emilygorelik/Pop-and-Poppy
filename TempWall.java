import java.util.*;
import java.awt.*;
import java.awt.image.*;

public class TempWall
{
	private BufferedImage skin;
	private int width, height, x , y;

	private boolean hide = false;


	public TempWall(BufferedImage mySkin, int myWidth, int myHeight, int myX, int myY)
	{
		skin = mySkin;
		width = myWidth;
		height = myHeight;
		x = myX;
		y = myY;
	}

	public boolean intersects(Rectangle userRect)
	{

		if(!hide){
			if(userRect.intersects(new Rectangle(x, y, width, height)))
			{
				return true;
			}
			return false;
		}
		else
			return false;
	}


	public void update()
	{

	}

	public String getStatus(){

		if(hide)
			return "hidden";
		else
			return "showing";

	}

	public void draw(Graphics g)
	{
		if(!hide)
			g.drawImage(skin,x,y,null);

		//System.out.println("Temp Wall Draw");
	}


	public void hide(){ hide = true; }
	public void show(){ hide = false; }

}