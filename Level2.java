import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.imageio.*;
import java.io.*;
import java.util.ArrayList;

public class Level2 extends Level
{

	private ArrayList<Wall> wallList;
	private ArrayList<TempWall> tempWallList;
	private ArrayList<Trigger> triggerList;
	private ArrayList<Obstacle> obstacleList;
	private SafeZone sz;
	private int x,y;
	private BufferedImage Iv, Ih, Isz, Ivt, Itrig, Idodge;
	private Player pop, coke;

	public Level2(Main main)
	{
		super(main, "LEVEL_2");
		try
		{
			Iv = ImageIO.read(new File("resources\\walls\\lev2V.png"));
			Ih = ImageIO.read(new File("resources\\walls\\lev2H.png"));
			Isz = ImageIO.read(new File("resources\\walls\\lev2S.png"));
			Ivt = ImageIO.read(new File("resources\\walls\\lev2VT.png"));
			Itrig = ImageIO.read(new File("resources\\buttons\\button2.png"));
			Idodge = ImageIO.read(new File("resources\\obstacles\\dodge2.png"));
		}
		catch(IOException e)
		{
			System.out.println("Failed To Recieve Images in Level 2");
		}

		createWalls();
		setBackground(new Color(139,239,127));
		pop = new Player("pop", 10, 135, 82, 80, wallList, sz, obstacleList, triggerList, tempWallList, this);
		coke =  new Player("coke", 1200, 940, 41, 80, wallList, sz, obstacleList, triggerList, tempWallList, this);
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

			//row1
			wallList.add(new Wall(Ih,160,50,0,224));
			wallList.add(new Wall(Ih,160,50,160,224));
			wallList.add(new Wall(Ih,160,50,320,224));
			wallList.add(new Wall(Ih,160,50,480,224));
			wallList.add(new Wall(Ih,160,50,640,224));
			wallList.add(new Wall(Ih,160,50,800,224));
			wallList.add(new Wall(Ih,160,50,960,224));
			tempWallList.add(new TempWall(Ivt,50,150,640,124));
			triggerList.add(new Trigger(Itrig,50,50, 800, 949, tempWallList.get(0))); //row6
			//row2
			wallList.add(new Wall(Ih,160,50,160,374));
			wallList.add(new Wall(Ih,160,50,320,374));
			wallList.add(new Wall(Ih,160,50,480,374));
			wallList.add(new Wall(Ih,160,50,640,374));
			wallList.add(new Wall(Ih,160,50,800,374));
			wallList.add(new Wall(Ih,160,50,960,374));
			wallList.add(new Wall(Ih,160,50,1120,374));
			tempWallList.add(new TempWall(Ivt,50,150,640,274));
			triggerList.add(new Trigger(Itrig,50,50, 800, 799, tempWallList.get(1))); //row5
			//row5
			wallList.add(new Wall(Ih,160,50,0,724));
			wallList.add(new Wall(Ih,160,50,160,724));
			wallList.add(new Wall(Ih,160,50,320,724));
			wallList.add(new Wall(Ih,160,50,480,724));
			wallList.add(new Wall(Ih,160,50,640,724));
			wallList.add(new Wall(Ih,160,50,800,724));
			wallList.add(new Wall(Ih,160,50,960,724));
			tempWallList.add(new TempWall(Ivt,50,150,640,774));
			triggerList.add(new Trigger(Itrig,50,50, 800, 299, tempWallList.get(2))); //row2
			//row6
			wallList.add(new Wall(Ih,160,50,160,874));
			wallList.add(new Wall(Ih,160,50,320,874));
			wallList.add(new Wall(Ih,160,50,480,874));
			wallList.add(new Wall(Ih,160,50,640,874));
			wallList.add(new Wall(Ih,160,50,800,874));
			wallList.add(new Wall(Ih,160,50,960,874));
			wallList.add(new Wall(Ih,160,50,1120,874));
			tempWallList.add(new TempWall(Ivt,50,150,640,924));
			triggerList.add(new Trigger(Itrig,50,50, 800, 149, tempWallList.get(3))); //row1
			//column2
			wallList.add(new Wall(Iv,50,150,160,424));
			//column7
			wallList.add(new Wall(Iv,50,150,1070,574));

			//Screen Colliders
	 		wallList.add(new Wall(null, 1, 1024, 0, 129)); //left
			wallList.add(new Wall(null, 1280, 1, 0, 129)); //top
			wallList.add(new Wall(null, 1280, 1, 0, 1024)); //bottom
			wallList.add(new Wall(null, 1, 1024, 1280, 129)); //right

			obstacleList.add(new Obstacle(Idodge, 320, 649, 2, "VERTICAL", 649, 449));
			obstacleList.add(new Obstacle(Idodge, 960, 449, 2, "VERTICAL", 449, 649));

		sz = new SafeZone(Isz, 480, 424, 320, 300);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		for (Wall w : wallList)
			w.draw(g);
		for (TempWall tw : tempWallList)
			tw.draw(g);
		for (Trigger tr : triggerList)
			tr.draw(g);
		for(Obstacle o : obstacleList)
			o.draw(g);

		//draws safezone
		sz.draw(g);


		//draws players
		pop.draw(g);
		coke.draw(g);
	}
}