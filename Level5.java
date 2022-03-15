import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.imageio.*;
import java.io.*;
import java.util.ArrayList;

public class Level5 extends Level {


	private ArrayList<Wall> wallList = null;
	private ArrayList<Obstacle> obstacleList;
	private ArrayList<TempWall> tempWallList;
	private ArrayList<Trigger> triggerList;
	private SafeZone sz;
	private int x,y;
	private BufferedImage Iv, Ih, Isz, Ivt, Iht, Ic, Itrig, Idodge, Idodge2;
	private Player pop, coke;


	public Level5(Main main)
	{
		super(main, "LEVEL_5");
		try
		{
			Iv = ImageIO.read(new File("resources\\walls\\lev5V.png"));
			Ih = ImageIO.read(new File("resources\\walls\\lev5H.png"));
			Isz = ImageIO.read(new File("resources\\walls\\lev5S.png"));
			Iht = ImageIO.read(new File("resources\\walls\\lev5HT.png"));
			Ivt = ImageIO.read(new File("resources\\walls\\lev5VT.png"));
			Ic = ImageIO.read(new File("resources\\walls\\lev5C.png"));
			Itrig = ImageIO.read(new File("resources\\buttons\\button5.png"));
			Idodge = ImageIO.read(new File("resources\\obstacles\\dodge5.png"));
			Idodge2 = ImageIO.read(new File("resources\\obstacles\\dodge5.1.png"));
		}
		catch(IOException e)
		{
			System.out.println("Failed to retirive images for Level 4");
		}


		createWalls();
		setBackground(new Color(255,204,153));
		pop = new Player("pop", 10, 135, 82, 80, wallList, sz, obstacleList, triggerList, tempWallList, this);
		coke =  new Player("coke", 10, 940, 41, 80, wallList, sz, obstacleList, triggerList, tempWallList, this);
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

			//column 1
			wallList.add(new Wall(Ih,160,50,0,524));
			wallList.add(new Wall(Iv,50,150,160,274));
			wallList.add(new Wall(Iv,50,150,160,424));
			wallList.add(new Wall(Iv,50,150,160,574));
			wallList.add(new Wall(Iv,50,150,160,724));

			//column 3
			wallList.add(new Wall(Ic,50,50,320,249));
			wallList.add(new Wall(Ic,50,50,320,549));
			wallList.add(new Wall(Ic,50,50,320,849));
			wallList.add(new Wall(Ic,50,50,480,399));
			wallList.add(new Wall(Ic,50,50,480,699));

			//column 4
			wallList.add(new Wall(Iv,50,150,640,124));
			wallList.add(new Wall(Iv,50,150,640,274));
			tempWallList.add(new TempWall(Ivt,50,150,640,424));
			triggerList.add(new Trigger(Itrig,50,50,715,549,tempWallList.get(0)));
			tempWallList.add(new TempWall(Ivt,50,150,640,574));
			triggerList.add(new Trigger(Itrig,50,50,160,174,tempWallList.get(1)));

			wallList.add(new Wall(Iv,50,150,640,724));
			wallList.add(new Wall(Iv,50,150,640,874));
			//column 5
			wallList.add(new Wall(Iv,50,150,800,274));
			wallList.add(new Wall(Iv,50,150,800,424));
			wallList.add(new Wall(Iv,50,150,800,574));
			wallList.add(new Wall(Iv,50,150,800,724));
			wallList.add(new Wall(Ih,160,50,800,224));
			wallList.add(new Wall(Ih,160,50,960,224));
			tempWallList.add(new TempWall(Iht,160,50,1120,224));
			triggerList.add(new Trigger(Itrig,50,50,715,924,tempWallList.get(2)));
			wallList.add(new Wall(Ih,160,50,800,874));
			wallList.add(new Wall(Ih,160,50,960,874));
			tempWallList.add(new TempWall(Iht,160,50,1120,874));
			triggerList.add(new Trigger(Itrig,50,50,875,299,tempWallList.get(3)));

			//column 6
			wallList.add(new Wall(Ih,160,50,960,374));
			wallList.add(new Wall(Ih,160,50,1120,374));
			wallList.add(new Wall(Ih,160,50,960,724));
			wallList.add(new Wall(Ih,160,50,1120,724));

			//Screen Colliders
			wallList.add(new Wall(null, 1, 1024, 0, 129)); //left
			wallList.add(new Wall(null, 1280, 1, 0, 129)); //top
			wallList.add(new Wall(null, 1280, 1, 0, 1024)); //bottom
			wallList.add(new Wall(null, 1, 1024, 1280, 129)); //right

			//Top 2
			obstacleList.add(new Obstacle(Idodge2, 235, 139, 1, "VERTICAL", 139, 959));
			obstacleList.add(new Obstacle(Idodge2, 395, 474, 1, "VERTICAL", 139, 959));
			obstacleList.add(new Obstacle(Idodge2, 555, 139, 1, "VERTICAL", 139, 959));

			//Bottom 2
			obstacleList.add(new Obstacle(Idodge, 235, 959, 1, "VERTICAL", 959, 139));
			obstacleList.add(new Obstacle(Idodge, 395, 642, 1, "VERTICAL", 959, 139));
			obstacleList.add(new Obstacle(Idodge, 555, 959, 1, "VERTICAL", 959, 139));

		sz = new SafeZone(Isz, 960, 424, 320, 300);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for (Wall w : wallList)
		{
			w.draw(g);
		}
		for (TempWall tw : tempWallList)
		{
			tw.draw(g);
		}
		for (Trigger tr : triggerList)
		{
			tr.draw(g);
		}
		for(Obstacle o : obstacleList)
			o.draw(g);

		sz.draw(g);

		pop.draw(g);
		coke.draw(g);
	}
}