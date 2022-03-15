import java.io.*;
import javax.sound.sampled.*;

public class Musicv2
{
 static Clip bitsPlayed = null;
 static Clip musicPlayed = null;

 public static void playMusic(String playedMusic)
 {
  if(musicPlayed != null && musicPlayed.isRunning() )
   musicPlayed.stop();

  try{
   AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(playedMusic).getAbsoluteFile());
   musicPlayed = AudioSystem.getClip();
   musicPlayed.open(audioInputStream);
   musicPlayed.loop(Clip.LOOP_CONTINUOUSLY);
  }
  catch(Exception ex){}

 }

 //"G:\\Java Projects\\FighterArena\\" + clipName + ".wav"
 public static void playBits(String playedBits)
 {
  if(bitsPlayed != null && bitsPlayed.isRunning())
  bitsPlayed.stop();

  try{
   AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(playedBits).getAbsoluteFile());
   bitsPlayed = AudioSystem.getClip();
   bitsPlayed.open(audioInputStream);
   bitsPlayed.start();
  }
  catch(Exception ex){
   ex.printStackTrace();
  }

 }
}