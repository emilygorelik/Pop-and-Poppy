//Nicholas DeRissio -- Project Toxic
//MainThread is the "game loop" thread
//1/23/18


public class MainThread extends Thread {

	private int MAX_FPS = 60;
	private Main driver;

	public MainThread(Main driver){
		this.driver = driver;
	}

	@SuppressWarnings("static-access")
	public void run(){

		long startTime;
		long elapsedTime;
		long waitTime;
		long targetTime = 1000/MAX_FPS;
		int frameCount = 0;
		long averageFPS;
		long totalTime = 0;
		boolean running = true;

		while(running){

			startTime = System.nanoTime();
			//-----------<CODE BELOW>----------------


			driver.update();
		//	driver.checkCollision();
			driver.repaint(); //redraw


			//-----------^CODE ABOVE^----------------

			elapsedTime = (System.nanoTime() - startTime) / 1000000;
			waitTime = targetTime - elapsedTime;

			try{
				if(waitTime > 0){
					this.sleep(waitTime);
				}
			}catch(Exception e){ e.printStackTrace(); }

			totalTime += System.nanoTime() - startTime;
			frameCount++;

			if(frameCount == MAX_FPS){
				averageFPS = 1000/((totalTime/frameCount)/1000000);
				frameCount = 0;
				totalTime = 0;
				System.out.println("CURRENT_FPS: " + averageFPS);
			}
		}
	}
}