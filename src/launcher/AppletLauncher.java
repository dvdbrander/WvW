package launcher;

import java.applet.Applet;
import java.awt.Dimension;

import javax.swing.JPanel;

public class AppletLauncher extends Applet{
	private static final long serialVersionUID = 1L;
	Dimension size = new Dimension(640, 480);
	FPS fps = new FPS();
	Runnable mainThread = new MainThread(size);
	
	@Override
	public void init() {
		System.out.println("Starting in applet");
		setVisible(true);
		setSize(size);
		add((JPanel) mainThread);
//		mainCanvas.createBufferStrategy(2);
		mainThread.run();
		super.init();
	}
}
