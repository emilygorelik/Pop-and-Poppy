import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;


public class Player
{
	private BufferedImage[] rightSkin = new BufferedImage[3];
	private BufferedImage[] leftSkin = new BufferedImage[3];
	private BufferedImage currentSkin;
		private int animCounter = 0;
		private String animState = "IDLE"; // IDLE WALK


	private int width, height, x, y;
	private int speed = 4;
	private String type;
	private boolean canMoveUp = true;
	private boolean canMoveDown = true;
	private boolean canMoveLeft = true;
	private boolean canMoveRight = true;
	private Rectangle rect;
	private int damageCooldown = 0;

	private String facing = "";

	private int animationCounter = 0;

	private ArrayList<Wall> wallList;
	private ArrayList<Obstacle> obstacleList;
	private SafeZone safeZone;
	private ArrayList<Trigger> triggerList;
	private ArrayList<TempWall> tempWallList;
	private Level observerLevel;


	public Player(String type, int x, int y, int width, int height, ArrayList<Wall> wallList, SafeZone safeZone, ArrayList<Obstacle> obstacleList, ArrayList<Trigger> triggerList, ArrayList<TempWall> tempWallList, Level observerLevel)
	{

		if(type.equalsIgnoreCase("coke")){
			this.rightSkin = ImageArrayCreator.getArray("colaStand.png","colaWalk1.png","colaWalk2.png");
			this.leftSkin = ImageArrayCreator.getArray("colaStandLeft.png","colaWalkLeft1.png","colaWalkLeft2.png");
		}
		else if(type.equalsIgnoreCase("pop")){
			this.rightSkin = ImageArrayCreator.getArray("popStand.png","popWalk1.png","popWalk2.png");
			this.leftSkin = ImageArrayCreator.getArray("popStandLeft.png","popWalkLeft1.png","popWalkLeft2.png");
		}




			this.type = type;
			this.width = width;
			this.height = height;
			this.x = x;
			this.y = y;
			this.wallList = wallList;
			this.safeZone = safeZone;
			this.obstacleList = obstacleList;
			this.triggerList = triggerList;
			this.tempWallList = tempWallList;
			this.observerLevel = observerLevel;
			currentSkin = rightSkin[0];
			facing = "right";
	}




	public void update(ArrayList<Integer> keyList)
	{
		if(type.equalsIgnoreCase("pop")){
			if(keyList.contains((Integer)KeyEvent.VK_W) && checkCollide(new Rectangle(x + 10, (y + 20) - speed, width - 20, height  - 34)) == 0 && checkCollide(new Rectangle(x + (width/2) - 12, (y + (height/3) * 2) - speed, 24, 27)) == 0){
				y-=speed;
				animState = "WALK";
			}
			else if(keyList.contains((Integer)KeyEvent.VK_S) && checkCollide(new Rectangle(x + 10, (y + 20) + speed, width - 20, height - 34)) == 0 && checkCollide(new Rectangle(x + (width/2) - 12, (y + (height/3) * 2) + speed, 24, 27)) == 0){
				y+=speed;
				animState = "WALK";

			}

			if(keyList.contains((Integer)KeyEvent.VK_A) && checkCollide(new Rectangle((x + 10) - speed, y + 20, width - 20, height - 34)) == 0 && checkCollide(new Rectangle((x + (width/2) - 12)- speed, y + (height/3) * 2, 24, 27)) == 0){
				x-=speed;
				animState = "WALK";
				facing = "left";

			}
			else if(keyList.contains((Integer)KeyEvent.VK_D) && checkCollide(new Rectangle((x + 10) + speed, y + 20, width - 20, height - 34)) == 0 && checkCollide(new Rectangle((x + (width/2) - 12) + speed, y + (height/3) * 2, 24, 27)) == 0){
				x+=speed;
				animState = "WALK";
				facing = "right";
			}
		}
		else if(type.equalsIgnoreCase("coke")){

			if(keyList.contains((Integer)KeyEvent.VK_UP) && checkCollide(new Rectangle(x , (y + 10) - speed, width, height - 24)) == 0 && checkCollide(new Rectangle(x + (width/2) - 12, (y + (height/3) * 2) - speed, 24, 27)) == 0){
				y-=speed;
				animState = "WALK";

			}else if(keyList.contains((Integer)KeyEvent.VK_DOWN) && checkCollide(new Rectangle(x, (y + 10) + speed, width, height - 24)) == 0 && checkCollide(new Rectangle(x + (width/2) - 12, (y + (height/3) * 2) + speed, 24, 27)) == 0){
				y+=speed;
				animState = "WALK";
			}

			if(keyList.contains((Integer)KeyEvent.VK_LEFT) && checkCollide(new Rectangle(x - speed, y + 10, width, height - 24)) == 0 && checkCollide(new Rectangle((x + (width/2) - 12) - speed, y + (height/3) * 2, 24, 27)) == 0){
				x-=speed;
				animState = "WALK";
				facing = "left";
			}else if(keyList.contains((Integer)KeyEvent.VK_RIGHT) && checkCollide(new Rectangle(x + speed, y + 10, width, height - 24)) == 0 && checkCollide(new Rectangle((x + (width/2) - 12) + speed, y + (height/3) * 2, 24, 27)) == 0){
				x+=speed;
				animState = "WALK";
				facing = "right";
			}
		}

		if(damageCooldown == 0){
			boolean check = checkDamage(new Rectangle(x, y, width, height - 24));
			if(check)
				damageCooldown = 30;
		}

		if(animationCounter % 4 == 0){
			animate();
			animationCounter = 0;
		}

		animState = "STAND";
		animationCounter++;

		if(damageCooldown > 0)
			damageCooldown--;

	}

	public void animate(){

			if(animState.equalsIgnoreCase("WALK")){

				if(facing.equalsIgnoreCase("right")){
					if(animCounter == 0){
						currentSkin = rightSkin[1];
						animCounter++;
					}
					else if(animCounter == 1){
						currentSkin = rightSkin[2];
						animCounter = 0;
					}
				}
				else if(facing.equalsIgnoreCase("left")){
					if(animCounter == 0){
						currentSkin = leftSkin[1];
						animCounter++;
					}
					else if(animCounter == 1){
						currentSkin = leftSkin[2];
						animCounter = 0;
					}
				}
			}
			else if(animState.equalsIgnoreCase("STAND")){
				if(facing.equalsIgnoreCase("right"))
					currentSkin = rightSkin[0];
				else if(facing.equalsIgnoreCase("left"))
					currentSkin = leftSkin[0];
			}
	}

	//1 = wall 2 = safeZone 3 = moving wall stuff 0 = nothing
	public int checkCollide(Rectangle body){
		for(Wall w : wallList){
				if(w.intersects(body)){
					return 1;
				}
		}

		for(TempWall w : tempWallList){
				if(w.intersects(body)){
					return 1;
				}
		}

		for(Trigger t : triggerList){
			if(t.intersects(body))
				t.pressed(type, 1);
			else
				t.pressed(type, 0);





		}

		if(safeZone.intersects(body))
			observerLevel.madeSafezone(type, 1);
		else
			observerLevel.madeSafezone(type, 0);


		return 0;
	}

	public boolean checkDamage(Rectangle body){

		for(Obstacle o : obstacleList){
			if(o.intersects(body)){
				observerLevel.minusLives(type);
				observerLevel.reload();
				return true;
			}
		}
		return false;
	}

	public void draw(Graphics g)
	{

		g.drawImage(currentSkin, x, y, null);
	}

	public void setX(int x){ this.x = x;}
	public void setY(int y){ this.y = y;}
	public int getX(){ return x;}
	public int getY(){ return y;}
	public Rectangle getRect(int xOffset, int yOffset){ return new Rectangle(x + xOffset,y + yOffset,width,height); }

}
