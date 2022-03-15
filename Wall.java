import java.util.*;
import java.awt.*;
import java.awt.image.*;

public class Wall
{
	private BufferedImage skin;
	private int width, height, x , y;

	public Wall(BufferedImage mySkin, int myWidth, int myHeight, int myX, int myY)
	{
		skin = mySkin;
		width = myWidth;
		height = myHeight;
		x = myX;
		y = myY;

	}

	public boolean intersects(Rectangle userRect)
	{
		if(userRect.intersects(new Rectangle(x, y, width, height)))
		{
			return true;
		}
		return false;
	}

	public void update()
	{

	}

	public void draw(Graphics g)
	{
		g.setColor(Color.RED);

		if(skin != null)
			g.drawImage(skin,x,y,null);

			//g.drawRect(x,y, width, height);
	}

}