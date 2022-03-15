import javax.swing.JPanel;
import java.util.HashMap;

public class ScreenManager {

 //Process for switching screens
 //main.setContentPane(new Screen());
 //main.revalidate();

 private Main main;
 private HashMap<String, Screen> screens;
 private Screen currentScreen;
 private Menu menu;
 private IntroScreen intro;
 private Level1 level_1;
 private Level2 level_2;
 private Level3 level_3;
 private Level4 level_4;
 private Level5 level_5;
 private WinScreen win_screen;
 private LoseScreen lose_screen;
 private String time;

 public ScreenManager(Main main){

  screens = new HashMap<String, Screen>();
  this.main = main;
  //main.setContentPane(currentScreen = new Level(0));
  //main.revalidate();
  loadMenu();
  loadGame();
  updateScreen();

 }

 public void update(){
		 currentScreen.update(main.getKeys());
 }

 public void loadIntro(){

 // IntroScreen intro = new IntroScreen();
  //main.setContentPane(intro);
  //main.revalidate();


 }

 public void loadMenu(){


  intro = new IntroScreen(this);
  screens.put("INTRO", intro);

  menu = new Menu(this);
  screens.put("MENU", menu);
 }

 public void loadGame(){

   //level 1
   level_1 = new Level1(main);
   screens.put("LEVEL_1", level_1);
   //level 2
   level_2 = new Level2(main);
   screens.put("LEVEL_2", level_2);
   //level 3
   level_3 = new Level3(main);
   screens.put("LEVEL_3", level_3);
   //level 4
   level_4 = new Level4(main);
   screens.put("LEVEL_4", level_4);
   //level 5
   level_5 = new Level5(main);
   screens.put("LEVEL_5", level_5);
   //Win screen
   win_screen = new WinScreen(this);
   screens.put("WIN_SCREEN", win_screen);
   //Lose Screen
   lose_screen = new LoseScreen(this);
   screens.put("LOSE_SCREEN", lose_screen);
 }

 public boolean loadScreen(String GAME_STATE){

	if(GAME_STATE.equalsIgnoreCase("INTRO")){
		 main.setContentPane(screens.get(GAME_STATE));
 		 main.revalidate();
 		 currentScreen = screens.get(GAME_STATE);
 		 return true;
	}
  	else if(GAME_STATE.equalsIgnoreCase("MENU")){

		 main.setContentPane(screens.get(GAME_STATE));
 		 main.revalidate();
 		 currentScreen = screens.get(GAME_STATE);
 		 loadGame();
 		 return true;
  	}
  	else if(GAME_STATE.equalsIgnoreCase("LEVEL_1")){

		main.setContentPane(screens.get(GAME_STATE));
		main.revalidate();
		currentScreen = screens.get(GAME_STATE);
		return true;
  	}
  	else if(GAME_STATE.equalsIgnoreCase("LEVEL_2")){

		main.setContentPane(screens.get(GAME_STATE));
		main.revalidate();
		currentScreen = screens.get(GAME_STATE);
		return true;
  	}
  	else if(GAME_STATE.equalsIgnoreCase("LEVEL_3")){

		main.setContentPane(screens.get(GAME_STATE));
		main.revalidate();
		currentScreen = screens.get(GAME_STATE);
		return true;
 	}
  	else if(GAME_STATE.equalsIgnoreCase("LEVEL_4")){

		main.setContentPane(screens.get(GAME_STATE));
		main.revalidate();
		currentScreen = screens.get(GAME_STATE);
		return true;
  	}
  	else if(GAME_STATE.equalsIgnoreCase("LEVEL_5")){

		main.setContentPane(screens.get(GAME_STATE));
		main.revalidate();
		currentScreen = screens.get(GAME_STATE);
		return true;
 	}
 	else if(GAME_STATE.equalsIgnoreCase("WIN_SCREEN")){

		main.setContentPane(screens.get(GAME_STATE));
		main.revalidate();
		currentScreen = screens.get(GAME_STATE);
		return true;
	}
 	else if(GAME_STATE.equalsIgnoreCase("LOSE_SCREEN")){

		main.setContentPane(screens.get(GAME_STATE));
		main.revalidate();
		int seconds = ((Level)currentScreen).getSeconds();
		String secondsString = "";
		if(seconds < 10)
			secondsString = "0" + seconds;
		else
			secondsString = "" + seconds;

		time = ((Level)currentScreen).getMinutes() + ":" + secondsString;
		currentScreen = screens.get(GAME_STATE);
		return true;
	}


 	return false;
 }
 public void updateScreen(){
  String GAME_STATE = main.getGameState();
  if(GAME_STATE.equalsIgnoreCase("INTRO")){

	  if(loadScreen(GAME_STATE))
	 	 System.out.println("Intro Loaded");


  }
  else if(GAME_STATE.equalsIgnoreCase("MENU")){
	  if(loadScreen(GAME_STATE)){
	  	System.out.println("Loaded The Menu");
		SoundPlayer.playMusicClip("resources\\sound\\music\\StartScreenv2.wav");
	}
  }
  else if(GAME_STATE.equalsIgnoreCase("LEVEL_1")){
	  if(loadScreen(GAME_STATE))
	  	((Level)screens.get("LEVEL_1")).setTime(0, 0);


	  	System.out.println("Loaded Level 1!");
	 	SoundPlayer.playMusicClip("resources\\sound\\music\\PCL1v2.wav");
  }
  else if(GAME_STATE.equalsIgnoreCase("LEVEL_2")){
	  if(loadScreen(GAME_STATE)){
			((Level)screens.get("LEVEL_2")).setTime(level_1.getMinutes(), level_1.getSeconds());
			((Level)screens.get("LEVEL_2")).setLives(level_1.getLives());
		SoundPlayer.playMusicClip("resources\\sound\\music\\PCL2v2.wav");
	  }
  }
  else if(GAME_STATE.equalsIgnoreCase("LEVEL_3")){
	  if(loadScreen(GAME_STATE)){
			((Level)screens.get("LEVEL_3")).setTime(level_2.getMinutes(), level_2.getSeconds());
			((Level)screens.get("LEVEL_3")).setLives(level_2.getLives());
		SoundPlayer.playMusicClip("resources\\sound\\music\\PCL3v2.wav");
	  }
  }
  else if(GAME_STATE.equalsIgnoreCase("LEVEL_4")){
	  if(loadScreen(GAME_STATE)){
			((Level)screens.get("LEVEL_4")).setTime(level_3.getMinutes(), level_3.getSeconds());
			((Level)screens.get("LEVEL_4")).setLives(level_3.getLives());
		SoundPlayer.playMusicClip("resources\\sound\\music\\PCL2v2.wav");
	  }
  }
  else if(GAME_STATE.equalsIgnoreCase("LEVEL_5")){
	  if(loadScreen(GAME_STATE)){
			((Level)screens.get("LEVEL_5")).setTime(level_4.getMinutes(), level_4.getSeconds());
			((Level)screens.get("LEVEL_5")).setLives(level_4.getLives());
		SoundPlayer.playMusicClip("resources\\sound\\music\\PCL3v2.wav");
	  }
  }
  else if(GAME_STATE.equalsIgnoreCase("WIN_SCREEN")){
	  if(loadScreen(GAME_STATE)){

				int seconds = ((Level)screens.get("LEVEL_5")).getSeconds();
		  		String secondsString = "0";
				if(seconds < 10)
					secondsString = "0" + seconds;
				else
					secondsString = "" + seconds;
			time = ((Level)screens.get("LEVEL_5")).getMinutes() + ":" + secondsString;

		  //String time = ((Level)screens.get("LEVEL_5")).getMinutes() + ":" + ((Level)screens.get("LEVEL_5")).getSeconds();
		  ((WinScreen)screens.get("WIN_SCREEN")).setTime(time);
		  SoundPlayer.stopMusic();
		  SoundPlayer.playSoundClip("resources\\sound\\victory.wav");
	  }
  }
  else if(GAME_STATE.equalsIgnoreCase("LOSE_SCREEN")){
	  if(loadScreen(GAME_STATE)){

		 //String time = ((Level)currentScreen).getMinutes() + ":" + ((Level)currentScreen).getSeconds();
		  ((LoseScreen)screens.get("LOSE_SCREEN")).setTime(time);
		  SoundPlayer.stopMusic();
		  SoundPlayer.playSoundClip("resources\\sound\\defeat.wav");
	  }
  }


 }

 public void forceGameState(String newState){
	 main.setGameState(newState);
	 updateScreen();
 }




}