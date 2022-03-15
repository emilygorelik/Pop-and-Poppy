import java.util.*;
import java.awt.*;
import java.awt.image.*;

public class Obstacle
{
	private BufferedImage skin;
	private int width, height;
	private int x = 0;
	private int y = 0;
	private int speed = 4;
	private String direction = ""; //0 is up and down, 1 is left and right
	private int to = 0; //direction were moving towards
	private int start;
	private boolean right = false;
	private boolean left = false;
	private boolean up = false;
	private boolean down = false;

	private int animMovementCounter = 0;
	private int updateMultiplier = 4;

	public Obstacle(BufferedImage skin, int x, int y, int updateMultiplier, String direction, int start, int to)
	{
		this.skin = skin;
		this.x = x;
		this.y = y;
		this.updateMultiplier = updateMultiplier;
		this.width = 50;
		this.height = 50;
		this.direction = direction;
		this.to = to;
		this.start = start;

		if(direction.equalsIgnoreCase("HORIZONTAL")){
			if(start < to){
				right = true;
				left = false;
			}else if(start > to){
				right = false;
				left = true;
			}
		}
		else if(direction.equalsIgnoreCase("VERTICAL")){
			if(start < to){
				up = false;
				down = true;
			}else if(start > to){
				up = true;
				down = false;
			}
		}
	}

	public void update()
	{
		if(animMovementCounter % updateMultiplier == 0){

			if(direction.equalsIgnoreCase("HORIZONTAL")){
				if(right && !left){

					x+=speed;

					if(start < to){
						if(x >= to){
							right = false;
							left = true;
						}
					}
					else if(start > to){
						if(x >= start){
							right = false;
							left = true;
						}
					}
				}
				else if(left && !right){

					x-=speed;

					if(start < to){
						if(x <= start){
							right = true;
							left = false;
						}
					}
					else if(start > to){
						if(x <= to){
							right = true;
							left = false;
						}
					}
				}
			}
			else if(direction.equalsIgnoreCase("VERTICAL")){
				if(up && !down){

					y-=speed;

					if(start < to){
						if(y <= start){
							up = false;
							down = true;
						}
					}else if(start > to){
						if(y <= to){
							up = false;
							down = true;
						}
					}
				}
				else if(down && !up){

					y+=speed;

					if(start < to){
						if(y >= to){
							up = true;
							down = false;
						}
					}else if(start > to){
						if(y >= start){
							up = true;
							down = false;
						}
					}
				}
			}

			animMovementCounter = 0;
		}
		//System.out.println("X: " + x + " Y: " + y);

		animMovementCounter++;
	}

	public void draw(Graphics g)
	{
		g.drawImage(skin, x, y, null);
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