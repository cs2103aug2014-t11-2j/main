package gui;

import java.util.Random;
import java.util.Vector;

import org.eclipse.swt.graphics.Image;

public class imageGetter {
	private static Vector<Image> imageRepo = new Vector<Image>(); 

	private static void loadimage() {
		imageRepo.add(SWTResourceManager.getImage(MainController.class, "/resource/image/wallpaper1.jpg"));
		imageRepo.add(SWTResourceManager.getImage(MainController.class, "/resource/image/wallpaper.jpg"));
		imageRepo.add(SWTResourceManager.getImage(MainController.class, "/resource/image/cloud.jpg"));
		imageRepo.add(SWTResourceManager.getImage(MainController.class, "/resource/image/forest.jpg"));
		imageRepo.add(SWTResourceManager.getImage(MainController.class, "/resource/image/highway.jpg"));
		imageRepo.add(SWTResourceManager.getImage(MainController.class, "/resource/image/nebula.jpg"));
		imageRepo.add(SWTResourceManager.getImage(MainController.class, "/resource/image/sunset.jpg"));
	}
	
	protected static Image imageGen() {
		imageRepo.clear();
		loadimage();
		Random gen = new Random();
		return imageRepo.get(gen.nextInt(imageRepo.size()));
	}
}
