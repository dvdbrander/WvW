package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;

public class FrameLauncher implements Runnable {

	long oldMillis = System.currentTimeMillis();
	int frames = 0;
	long fps = 60;
	int sleepmillis = 17;
	Thread mainThread = new Thread(this);
	private MainCanvas mainCanvas;
	private boolean run = true;
	Graphics graphics = null;

	public static void main(String[] args) {
		new FrameLauncher();
	}


	public FrameLauncher() {
		System.out.println("Starting in frame");
		Frame frame = new Frame();
		frame.setVisible(true);
		Dimension size = new Dimension(640, 480);
		frame.setSize(size);
		size = frame.getSize();
		mainCanvas = new MainCanvas();
		frame.add(mainCanvas);
		frame.createBufferStrategy(2);
		graphics = frame.getBufferStrategy().getDrawGraphics();
		mainCanvas.Init(size);
		frame.pack();
		mainThread.start();

	}

	@Override
	public void run() {
		while (run) {
			if (System.currentTimeMillis() - oldMillis >= 1000) {
				long passedMillis = System.currentTimeMillis() - oldMillis;
				fps = frames / (passedMillis / 1000);
				frames = 0;
				oldMillis = System.currentTimeMillis();
				if (fps < 55) {
					sleepmillis = Math.max(1, sleepmillis - 1);
				} else if (fps > 65) {
					sleepmillis = sleepmillis + 1;
				}
				System.out.println(fps);
			}
			frames++;

			repaint(graphics);

			try {
				Thread.sleep(sleepmillis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void repaint(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(0, 0, 10, 10);
		mainCanvas.draw(g);
	}

}
