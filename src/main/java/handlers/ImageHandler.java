package handlers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import animations.Animation;
import layers.BackgroundLayer;

public class ImageHandler {
	
	private static ImageHandler instance = new ImageHandler();
	private static HashMap<String, BufferedImage> imageLoader;
	private static HashMap<String, BufferedImage[]> animationLoader;
	
	private ImageHandler(){
		imageLoader = new HashMap<String, BufferedImage>();
		animationLoader = new HashMap<String, BufferedImage[]>();
	}
	
	public static ImageHandler getInstance(){
		return instance;
	}
	
	public void loadImage(String imageKey, String imageURL){
		if(!imageLoader.containsKey(imageKey)){
			BufferedImage image;
			try {
				image = ImageIO.read(ImageHandler.class.getResourceAsStream(imageURL));
				imageLoader.put(imageKey, image);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
	}
	
	public BufferedImage getImage(String imageKey){
		return imageLoader.get(imageKey);
	}
	
	public void loadAnimation(String animationKey, String animationURL, int numberFrames, int frameWidth, int frameHeight){
		if(!animationLoader.containsKey(animationKey)){
			BufferedImage image;
			try {
				image = ImageIO.read(ImageHandler.class.getResourceAsStream(animationURL));
				BufferedImage[] frames = new BufferedImage[numberFrames];
				int startX;
		        for (int i = 0; i < numberFrames; i++) {
		        	startX = 0 + (i * frameWidth);
		            frames[i] = image.getSubimage(startX, 0, frameWidth, frameHeight);
		        }
		        animationLoader.put(animationKey, frames);
			} catch (IOException e){
				
			}
		}
	}
	
	public BufferedImage[] getAnimation(String animationKey){
		return animationLoader.get(animationKey);
	}
	

}
