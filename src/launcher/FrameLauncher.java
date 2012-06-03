package launcher;

import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JPanel;

public class FrameLauncher extends Frame{
	private static final long serialVersionUID = 1L;
	Dimension size = new Dimension(640, 480);
	FPS fps = new FPS();
	Runnable mainThread = new MainThread(size);

	public static void main(String[] args) {
		new FrameLauncher();
	}


	public FrameLauncher() {
		System.out.println("Starting in frame");
		setVisible(true);
		setSize(size);
		add((JPanel) mainThread);
//		mainCanvas.createBufferStrategy(2);
		mainThread.run();

	}
}
