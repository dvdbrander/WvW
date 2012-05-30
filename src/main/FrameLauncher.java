package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;

public class FrameLauncher extends Frame{
	private static final long serialVersionUID = 1L;
	Canvas mainCanvas = new Canvas();
	Graphics graphics = null;
	Dimension size = new Dimension(640, 480);
	Fps fps = new Fps();
	Runnable mainThread = new MainThread(mainCanvas);

	public static void main(String[] args) {
		new FrameLauncher();
	}


	public FrameLauncher() {
		System.out.println("Starting in frame");
		setVisible(true);
		setSize(size);
		size = getSize();
		mainCanvas.setSize(size);
		add(mainCanvas);
		mainCanvas.createBufferStrategy(2);
		pack();
		mainThread.run();

	}
}
