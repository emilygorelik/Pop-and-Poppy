import java.util.ArrayList;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class MultiKeyListener2 implements KeyListener {

	private ArrayList<Integer> keyList;

	public MultiKeyListener2(){
		keyList = new ArrayList<Integer>();
	}

	public void keyPressed(KeyEvent e){

		if(e.getKeyCode() == KeyEvent.VK_W && !keyList.contains((Integer)KeyEvent.VK_W))
			keyList.add((Integer)KeyEvent.VK_W);

		if(e.getKeyCode() == KeyEvent.VK_S && !keyList.contains((Integer)KeyEvent.VK_S))
			keyList.add((Integer)KeyEvent.VK_S);

		if(e.getKeyCode() == KeyEvent.VK_A && !keyList.contains((Integer)KeyEvent.VK_A))
			keyList.add((Integer)KeyEvent.VK_A);

		if(e.getKeyCode() == KeyEvent.VK_D && !keyList.contains((Integer)KeyEvent.VK_D))
			keyList.add((Integer)KeyEvent.VK_D);

		if(e.getKeyCode() == KeyEvent.VK_UP && !keyList.contains((Integer)KeyEvent.VK_UP))
			keyList.add((Integer)KeyEvent.VK_UP);

		if(e.getKeyCode() == KeyEvent.VK_DOWN && !keyList.contains((Integer)KeyEvent.VK_DOWN))
			keyList.add((Integer)KeyEvent.VK_DOWN);

		if(e.getKeyCode() == KeyEvent.VK_LEFT && !keyList.contains((Integer)KeyEvent.VK_LEFT))
			keyList.add((Integer)KeyEvent.VK_LEFT);

		if(e.getKeyCode() == KeyEvent.VK_RIGHT && !keyList.contains((Integer)KeyEvent.VK_RIGHT))
			keyList.add((Integer)KeyEvent.VK_RIGHT);


		//System.out.println("FIRE");
	}

	public void keyReleased(KeyEvent e){

		if(e.getKeyCode() == KeyEvent.VK_W && keyList.contains((Integer)KeyEvent.VK_W))
			keyList.remove((Integer)KeyEvent.VK_W);

		if(e.getKeyCode() == KeyEvent.VK_S && keyList.contains((Integer)KeyEvent.VK_S))
			keyList.remove((Integer)KeyEvent.VK_S);

		if(e.getKeyCode() == KeyEvent.VK_A && keyList.contains((Integer)KeyEvent.VK_A))
			keyList.remove((Integer)KeyEvent.VK_A);

		if(e.getKeyCode() == KeyEvent.VK_D && keyList.contains((Integer)KeyEvent.VK_D))
			keyList.remove((Integer)KeyEvent.VK_D);


		if(e.getKeyCode() == KeyEvent.VK_UP && keyList.contains((Integer)KeyEvent.VK_UP))
			keyList.remove((Integer)KeyEvent.VK_UP);

		if(e.getKeyCode() == KeyEvent.VK_DOWN && keyList.contains((Integer)KeyEvent.VK_DOWN))
			keyList.remove((Integer)KeyEvent.VK_DOWN);

		if(e.getKeyCode() == KeyEvent.VK_LEFT && keyList.contains((Integer)KeyEvent.VK_LEFT))
			keyList.remove((Integer)KeyEvent.VK_LEFT);

		if(e.getKeyCode() == KeyEvent.VK_RIGHT && keyList.contains((Integer)KeyEvent.VK_RIGHT))
			keyList.remove((Integer)KeyEvent.VK_RIGHT);

			//System.out.println("Release");
	}

	public void keyTyped(KeyEvent e){}

	public ArrayList<Integer> getKeyList(){ return keyList; }








}