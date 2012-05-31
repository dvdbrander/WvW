package main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Frame;


public class FrameLauncher extends Frame{
	private static final long serialVersionUID = 1L;
	Canvas mainCanvas = new Canvas();
	Dimension size = new Dimension(640, 480);
	FPS fps = new FPS();
	Runnable mainThread = new MainThread(mainCanvas,size);

	public static void main(String[] args) {
		new FrameLauncher();
	}


	public FrameLauncher() {
		System.out.println("Starting in frame");
		setVisible(true);
		setSize(size);
		mainCanvas.createBufferStrategy(2);
		mainCanvas.setSize(size);
		add(mainCanvas);
		mainThread.run();

	}
}
