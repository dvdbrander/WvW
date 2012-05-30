package main;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

public class AppletLauncher extends Applet implements Runnable{

	private static final long serialVersionUID = 1L;
	MainCanvas mainCanvas;
	private boolean run = true;
	long oldMillis = System.currentTimeMillis();
	int frames = 0;
	long fps = 60;
	int sleepmillis = 17;
	private Image doubleBufferImage;
	private Graphics doubleBufferGraphics;
	Thread mainThread = new Thread(this);

	public AppletLauncher(){
		System.out.println("Starting in applet");

	}
	
	@Override
	public void init() {
		Dimension size = new Dimension(640,480);
		setSize(size);
		mainCanvas = new MainCanvas();
		add(mainCanvas);
		mainCanvas.Init(size);
		mainThread.start();
		super.init();
	}

	@Override
	public void run(){
		while (run){
			if (System.currentTimeMillis() - oldMillis >= 1000){
				long passedMillis = System.currentTimeMillis() - oldMillis;
				fps = frames / (passedMillis / 1000);
				frames = 0;
				oldMillis = System.currentTimeMillis();
				if (fps < 55){
					sleepmillis = Math.max(1, sleepmillis - 1);
				}else if (fps > 65){
						sleepmillis = sleepmillis + 1;
				}
				System.out.println(fps);
			}
			frames ++;
			
			repaint();

			try
			{
				Thread.sleep(sleepmillis);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		destroy();
	}
	
	@Override
	public void update(Graphics g)
	{
		if (doubleBufferImage == null){
			doubleBufferImage = createImage(this.getWidth(), this.getHeight());
		}
		doubleBufferGraphics = doubleBufferImage.getGraphics();
		doubleBufferGraphics.setColor(getBackground());
		doubleBufferGraphics.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		doubleBufferGraphics.setColor(getForeground());
		paint(doubleBufferGraphics);
		
		g.drawImage(doubleBufferImage, 0, 0, this);
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(0, 0, 10, 10);
		mainCanvas.draw(g);
		super.paint(g);
	}
}
