import java.io.*;
import javax.sound.sampled.*;

public class SoundPlayer
{
	static Clip effectClip = null;
	static Clip musicClip = null;

	public static void playMusicClip(String clipName)
	{
		if(musicClip != null && musicClip.isRunning())
			musicClip.stop();

		try{
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(clipName).getAbsoluteFile());
			musicClip = AudioSystem.getClip();
			musicClip.open(audioInputStream);
			musicClip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}

	}

	//"G:\\Java Projects\\FighterArena\\" + clipName + ".wav"
	public static void playSoundClip(String clipName)
	{
		//if(effectClip != null && effectClip.isRunning())
			//effectClip.stop();

		try{
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(clipName).getAbsoluteFile());
			effectClip = AudioSystem.getClip();
			effectClip.open(audioInputStream);
			effectClip.start();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}

	}

	public static void stopMusic(){
		if(musicClip != null && musicClip.isRunning())
			musicClip.stop();

	}
}