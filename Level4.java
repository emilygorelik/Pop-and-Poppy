import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.imageio.*;
import java.io.*;
import java.util.ArrayList;

public class Level4 extends Level
{

	private ArrayList<Wall> wallList;
	private ArrayList<TempWall> tempWallList;
	private ArrayList<Trigger> triggerList;
	private ArrayList<Obstacle> obstacleList;
	private SafeZone sz;
	private int x,y;
	private BufferedImage Iv, Ih, Isz, Iht, Itrig, Idodge;
	private Player pop, coke;

	public Level4(Main main)
	{
		super(main, "LEVEL_4");
		try
		{
			Iv = ImageIO.read(new File("resources\\walls\\lev4V.png"));
			Ih = ImageIO.read(new File("resources\\walls\\lev4H.png"));
			Isz = ImageIO.read(new File("resources\\walls\\lev4S.png"));
			Iht = ImageIO.read(new File("resources\\walls\\lev4HT.png"));
			Itrig = ImageIO.read(new File("resources\\buttons\\button4.png"));
			Idodge = ImageIO.read(new File("resources\\obstacles\\dodge4.png"));
		}
		catch(IOException e)
		{
			System.out.println("Failed To Recvieve Images in Level 5");
		}

		createWalls();
		setBackground(new Color(239,102,97));
		pop = new Player("pop", 10, 285, 82, 80, wallList, sz, obstacleList, triggerList, tempWallList, this);
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

			//column 1
			wallList.add(new Wall(Ih,160,50,0,374));
			wallList.add(new Wall(Iv,50,150,110,224));
			tempWallList.add(new TempWall(Iht,160,50,0,874));
			triggerList.add(new Trigger(Itrig,50,50, 1010, 774, tempWallList.get(0))); //column 7

			//column 2
			wallList.add(new Wall(Ih,160,50,160,224));
			wallList.add(new Wall(Ih,160,50,160,874));

			//column 3
			wallList.add(new Wall(Ih,160,50,320,374));
			wallList.add(new Wall(Ih,160,50,320,874));
			wallList.add(new Wall(Iv,50,150,430,124));
			wallList.add(new Wall(Iv,50,150,430,274));
			wallList.add(new Wall(Iv,50,150,430,424));
			wallList.add(new Wall(Iv,50,150,430,574));
			wallList.add(new Wall(Iv,50,150,430,724));

			//column 6
			wallList.add(new Wall(Ih,160,50,800,224));
			wallList.add(new Wall(Ih,160,50,800,724));
			wallList.add(new Wall(Iv,50,150,800,274));
			wallList.add(new Wall(Iv,50,150,800,424));
			wallList.add(new Wall(Iv,50,150,800,574));
			wallList.add(new Wall(Iv,50,150,800,724));
			wallList.add(new Wall(Iv,50,150,800,874));

			//column 7
			wallList.add(new Wall(Ih,160,50,960,224));
			wallList.add(new Wall(Ih,160,50,960,874));

			//column 8
			wallList.add(new Wall(Ih,160,50,1120,724));
			wallList.add(new Wall(Iv,50,150,1120,774));
			tempWallList.add(new TempWall(Iht,160,50,1120,224));
			triggerList.add(new Trigger(Itrig,50,50, 50, 974, tempWallList.get(1))); //column 1

			//Screen Colliders
	 		wallList.add(new Wall(null, 1, 1024, 0, 129)); //left
			wallList.add(new Wall(null, 1280, 1, 0, 129)); //top
			wallList.add(new Wall(null, 1280, 1, 0, 1024)); //bottom
			wallList.add(new Wall(null, 1, 1024, 1280, 129)); //right

			obstacleList.add(new Obstacle(Idodge,365,474,1,"HORIZONTAL",365,15));
			obstacleList.add(new Obstacle(Idodge,15,624,1,"HORIZONTAL",15,365));
			obstacleList.add(new Obstacle(Idodge,365,774,1,"HORIZONTAL",365,15));
			obstacleList.add(new Obstacle(Idodge,865,324,1,"HORIZONTAL",865,1215));
			obstacleList.add(new Obstacle(Idodge,1215,474,1,"HORIZONTAL",1215,865));
			obstacleList.add(new Obstacle(Idodge,865,624,1,"HORIZONTAL",865,1215));

		sz = new SafeZone(Isz, 480, 424, 320, 300);
	}

	public void reload(){
		coke.setX(1200);
		coke.setY(940);
		pop.setX(10);
		pop.setY(285);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		//draws WAlls
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

		//draws safezone
		sz.draw(g);


		//draws players
		pop.draw(g);
		coke.draw(g);
	}
}