import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.*;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.imageio.*;
import java.io.*;
import java.util.ArrayList;

public class Level3 extends Level
{

	private ArrayList<Wall> wallList;
	private ArrayList<TempWall> tempWallList;
	private ArrayList<Trigger> triggerList;
	private ArrayList<Obstacle> obstacleList;
	private SafeZone sz1;
	private int x,y;
	private BufferedImage Iv, Ivt, Ih, Iht1, Isz, Itrig, Idodge;
	private Player pop, coke;

	public Level3(Main main)
	{
		super(main, "LEVEL_3");

		try
		{
			Iv = ImageIO.read(new File("resources\\walls\\lev3V.png"));
			Ih = ImageIO.read(new File("resources\\walls\\lev3H.png"));
			Isz = ImageIO.read(new File("resources\\walls\\lev3S.png"));
			Ivt = ImageIO.read(new File("resources\\walls\\lev3VT.png"));
			Itrig = ImageIO.read(new File("resources\\buttons\\button3.png"));
			Idodge = ImageIO.read(new File("resources\\obstacles\\dodge3.png"));
		}
		catch(IOException e)
		{
			System.out.println("couldnt find images for Level3");
		}

		createWalls();
		setBackground(new Color(173,213,219));
		pop = new Player("pop", 10, 134, 82, 80, wallList, sz1, obstacleList, triggerList, tempWallList, this);
		coke =  new Player("coke", 1229, 134, 41, 80, wallList, sz1, obstacleList, triggerList, tempWallList, this);
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
		tempWallList = new ArrayList<TempWall>();
		triggerList = new ArrayList<Trigger>();
		obstacleList = new ArrayList<Obstacle>();
			//column 1 & 2
			wallList.add(new Wall(Ih,160,50,0,224));
			wallList.add(new Wall(Ih,160,50,160,374));
			wallList.add(new Wall(Ih,160,50,0,524));
			wallList.add(new Wall(Ih,160,50,160,674));
			wallList.add(new Wall(Iv,50,150,160,724));
			wallList.add(new Wall(Iv,50,150,320,124));
			wallList.add(new Wall(Iv,50,150,320,274));
			wallList.add(new Wall(Iv,50,150,320,424));
			wallList.add(new Wall(Iv,50,150,320,574));
			wallList.add(new Wall(Iv,50,150,320,874));
			tempWallList.add(new TempWall(Ivt,50,150,320,724));
			triggerList.add(new Trigger(Itrig,50,50, 1000, 900, tempWallList.get(0))); //col 7&8

			//column3
			wallList.add(new Wall(Ih,160,50,370,674));
			wallList.add(new Wall(Iv,50,150,480,724));
			//column 7 & 8
			wallList.add(new Wall(Ih,160,50,1120,224));
			wallList.add(new Wall(Ih,160,50,960,374));
			wallList.add(new Wall(Ih,160,50,1120,524));
			wallList.add(new Wall(Ih,160,50,960,674));
			wallList.add(new Wall(Iv,50,150,1070,724));
			wallList.add(new Wall(Iv,50,150,910,124));
			wallList.add(new Wall(Iv,50,150,910,274));
			wallList.add(new Wall(Iv,50,150,910,424));
			wallList.add(new Wall(Iv,50,150,910,574));
			wallList.add(new Wall(Iv,50,150,910,874));
			tempWallList.add(new TempWall(Ivt,50,150,910,724));
			triggerList.add(new Trigger(Itrig,50,50, 400, 900, tempWallList.get(1))); //col 1&2
			//column5
			wallList.add(new Wall(Ih,160,50,750,674));
			wallList.add(new Wall(Iv,50,150,750,724));

			//Screen Colliders
			wallList.add(new Wall(null, 1, 1024, 0, 129)); //left
			wallList.add(new Wall(null, 1280, 1, 0, 129)); //top
			wallList.add(new Wall(null, 1280, 1, 0, 1024)); //bottom
			wallList.add(new Wall(null, 1, 1024, 1280, 129)); //right

			obstacleList.add(new Obstacle(Idodge, 370, 499, 1, "HORIZONTAL", 370, 860));
			obstacleList.add(new Obstacle(Idodge, 860, 574, 1, "HORIZONTAL", 860, 370));
			obstacleList.add(new Obstacle(Idodge, 860, 899, 1, "HORIZONTAL", 860, 370));

		sz1 = new SafeZone(Isz, 480, 124, 320, 300);

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
			//System.out.println("Drawing Triggers");
		}

		for(Obstacle o : obstacleList)
			o.draw(g);

		sz1.draw(g);

		pop.draw(g);
		coke.draw(g);
	}
}