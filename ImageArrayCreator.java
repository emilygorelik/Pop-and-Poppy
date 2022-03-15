import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageArrayCreator {

	private static BufferedImage[] array;

	public static BufferedImage[] getArray(String stand, String walk1, String walk2){

		array = new BufferedImage[3];
		BufferedImage standImage, walkImage1, walkImage2;

		try{

			standImage = ImageIO.read(new File("resources\\character\\" + stand));
			walkImage1 = ImageIO.read(new File("resources\\character\\" + walk1));
			walkImage2 = ImageIO.read(new File("resources\\character\\" + walk2));

			array[0] = standImage;
			array[1] = walkImage1;
			array[2] = walkImage2;
		}
		catch(IOException e)
		{
			System.out.println("couldnt find images for Level1");
		}


		return array;
	}

	public static BufferedImage[] getIntroArray(String four, String three, String two, String one, String base){

		array = new BufferedImage[5];
		BufferedImage fourImage, threeImage, twoImage, oneImage, baseImage;

		try{

			fourImage = ImageIO.read(new File("resources\\intro\\" + four));
			threeImage = ImageIO.read(new File("resources\\intro\\" + three));
			twoImage = ImageIO.read(new File("resources\\intro\\" + two));
			oneImage = ImageIO.read(new File("resources\\intro\\" + one));
			baseImage = ImageIO.read(new File("resources\\intro\\" + base));

			array[0] = oneImage;
			array[1] = twoImage;
			array[2] = threeImage;
			array[3] = fourImage;
			array[4] = baseImage;

		}
		catch(IOException e)
		{
			System.out.println("couldnt find images for Intro");
		}


		return array;
	}





}