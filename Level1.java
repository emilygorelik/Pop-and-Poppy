import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.imageio.*;
import java.io.*;
import java.util.ArrayList;

public class Level1 extends Level
{

	private ArrayList<Wall> wallList;
	private ArrayList<TempWall> tempWallList;
	private ArrayList<Trigger> triggerList;
	private ArrayList<Obstacle> obstacleList;
	private SafeZone sz;
	private int x,y;
	private BufferedImage Iv, Ih, Isz;//, Ivt, Itrig, angryGumImage, angryBanana;
	private Player pop, coke;

	public Level1(Main main)
	{
		super(main, "LEVEL_1");

		try
		{
			Iv = ImageIO.read(new File("resources\\walls\\lev1V.png"));
			Ih = ImageIO.read(new File("resources\\walls\\lev1H.png"));
			Isz = ImageIO.read(new File("resources\\walls\\lev1S.png"));
		}
		catch(IOException e)
		{
			System.out.println("Failed To Recvieve Images in Level 2");
		}

		createWalls();
		setBackground(new Color(255,204,255));
		pop = new Player("pop", 10, 285, 65, 80, wallList, sz, obstacleList, triggerList, tempWallList, this);
		coke =  new Player("coke", 10, 435, 41, 80, wallList, sz, obstacleList, triggerList, tempWallList, this);
		super.givePlayers(pop, coke);
	}

	public void update(ArrayList<Integer> keyList){
		super.update();


		pop.update(keyList);
		coke.update(keyList);

		for(Obstacle o : obstacleList)
			o.update();
	}

	public void createWalls(){
		wallList = new ArrayList<Wall>();
		obstacleList = new ArrayList<Obstacle>();
		tempWallList = new ArrayList<TempWall>();
		triggerList = new ArrayList<Trigger>();

			//row 1
			wallList.add(new Wall(Ih,160,50,0,374));
			wallList.add(new Wall(Iv,50,150,110,274));
			wallList.add(new Wall(Iv,50,150,110,424));
			wallList.add(new Wall(Iv,50,150,110,574));
			wallList.add(new Wall(Iv,50,150,110,724));

			//row 2
			wallList.add(new Wall(Iv,50,150,270,124));
			wallList.add(new Wall(Iv,50,150,270,274));
			wallList.add(new Wall(Iv,50,150,270,424));
			wallList.add(new Wall(Iv,50,150,270,574));
			wallList.add(new Wall(Ih,160,50,160,824));

			//row 3
			wallList.add(new Wall(Ih,160,50,320,824));
			wallList.add(new Wall(Iv,50,150,430,274));
			wallList.add(new Wall(Iv,50,150,430,424));
			wallList.add(new Wall(Iv,50,150,430,574));
			wallList.add(new Wall(Iv,50,150,430,724));

			//row 4
			wallList.add(new Wall(Ih,160,50,480,274));
			wallList.add(new Wall(Iv,50,150,590,424));
			wallList.add(new Wall(Iv,50,150,590,574));
			wallList.add(new Wall(Iv,50,150,590,724));
			wallList.add(new Wall(Iv,50,150,590,874));

			//row 5
			wallList.add(new Wall(Ih,160,50,640,274));
			wallList.add(new Wall(Iv,50,150,750,274));
			wallList.add(new Wall(Iv,50,150,750,424));
			wallList.add(new Wall(Iv,50,150,750,574));
			wallList.add(new Wall(Iv,50,150,750,724));

			//row 6
			wallList.add(new Wall(Iv,50,150,910,124));
			wallList.add(new Wall(Iv,50,150,910,274));
			wallList.add(new Wall(Iv,50,150,910,424));
			wallList.add(new Wall(Iv,50,150,910,574));
			wallList.add(new Wall(Ih,160,50,800,824));

			//row 7
			wallList.add(new Wall(Ih,160,50,960,824));
			wallList.add(new Wall(Iv,50,150,1070,274));
			wallList.add(new Wall(Iv,50,150,1070,424));
			wallList.add(new Wall(Iv,50,150,1070,574));
			wallList.add(new Wall(Iv,50,150,1070,724));

			//Screen Colliders
	 		wallList.add(new Wall(null, 1, 1024, 0, 129)); //left
			wallList.add(new Wall(null, 1280, 1, 0, 129)); //top
			wallList.add(new Wall(null, 1280, 1, 0, 1024)); //bottom
			wallList.add(new Wall(null, 1, 1024, 1280, 129)); //right

			//obstacleList.add(new Obstacle(angryGumImage, 320, 649, 1, "VERTICAL", 649, 449));
			//obstacleList.add(new Obstacle(angryBanana, 960, 449, 1, "VERTICAL", 449, 649));

		sz = new SafeZone(Isz, 960, 124, 320, 300);
	}


	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		//draws WAlls
		for (Wall w : wallList)
		{
			w.draw(g);
		}

		//draws safezone
		sz.draw(g);


		//draws players
		pop.draw(g);
		coke.draw(g);
	}
}