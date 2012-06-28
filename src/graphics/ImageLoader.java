package graphics;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

import launcher.MainThread;

public class ImageLoader {
	private MainThread	main;
	HashMap<String, Image> images = new HashMap<String, Image>();
	HashMap<String, BufferedImage> bufferedimages = new HashMap<String, BufferedImage>();

	public ImageLoader(MainThread main)
	{
		this.main = main;
	}

	public Image loadImage(String name)
	{
		if (images.containsKey(name))return images.get(name);
		URL url = this.getClass().getResource("/res/" + name);
		if (url == null) { throw new NullPointerException("Could not load "
				+ "/res/" + name); }
		Image img = main.getToolkit().getImage(url);
		images.put(name, img);
		System.out.println(name + " loaded");
		return img;
	}

	public BufferedImage loadBufferedImage(String name)
	{
		if (bufferedimages.containsKey(name))return bufferedimages.get(name);
		URL url = this.getClass().getResource("/res/" + name);
		if (url == null) { throw new NullPointerException("Could not load "
				+ "/res/" + name); }
		BufferedImage img = null;
		try
		{
			img = ImageIO.read(url);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		bufferedimages.put(name, img);
		System.out.println(name + " loaded");
		return img;
	}
}
