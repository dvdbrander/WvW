package main;

import java.applet.Applet;
import java.awt.Canvas;
import java.awt.Dimension;

public class AppletLauncher extends Applet{
	private static final long serialVersionUID = 1L;
	Canvas mainCanvas = new Canvas();
	Dimension size = new Dimension(640, 480);
	FPS fps = new FPS();
	Runnable mainThread = new MainThread(mainCanvas);
	
	@Override
	public void init() {
		System.out.println("Starting in applet");
		setSize(size);
		mainCanvas.setSize(size);
		add(mainCanvas);
		mainCanvas.createBufferStrategy(2);
		mainThread.run();
		super.init();
	}
}
