import java.util.*;
import java.awt.*;
import java.awt.image.*;

public class SafeZone
{
	BufferedImage skin;
	int width, height;
	int x, y;
	public SafeZone(BufferedImage skin, int x, int y, int width, int height)
	{
		this.skin = skin;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
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
		g.drawImage(skin, x, y, null);
	}
}