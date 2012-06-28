package launcher;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JPanel;

public class FrameLauncher extends Frame {
	private static final long serialVersionUID = 1L;
	Dimension size = new Dimension(640, 480);
	Runnable mainThread = new MainThread(size);

	public static void main(String[] args) {
		new FrameLauncher();

	}

	public FrameLauncher() {
		System.out.println("Starting in frame");
		setVisible(true);
		setSize(size);
		add((JPanel) mainThread);
		// mainCanvas.createBufferStrategy(2);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		mainThread.run();
	}

}
