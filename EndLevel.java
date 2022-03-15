import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class EndLevel extends Screen {

	private int secondTime = 0;
	private int minuteTime = 0;

	Scanner scan = null;



	public EndLevel(){
		scan = new Scanner(System.in);

		try{
			scan = new Scanner(new FileReader("ldrbrd.txt"));
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}


	}

	public void leaderBoard(){


		while(scan.hasNext()){

			String i = scan.next();

			if(i.length() == 4){


			}


		}

	}


	public void setTime(int minuteTime, int secondTime){
		this.minuteTime = minuteTime;
		this.secondTime = secondTime;
	}
}