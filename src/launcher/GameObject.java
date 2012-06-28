package launcher;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import util.IntegerObject;

public class GameObject {// TODO: Add player class
	public IntegerObject x = new IntegerObject(0);
	public IntegerObject y = new IntegerObject(0);
	protected BufferedImage img = null;
	protected MainThread main;
	protected Boolean sync = false;
	protected ArrayList<Object> toSync = null;
	public Integer id = -1;
	
	public long lastupdated = 0;

	public GameObject(MainThread main) {
		this.main = main;
	}

	public void init() {

	}

	public void update() {

	}

	public void paint(Graphics g) {
		if (img != null) {
			g.drawImage(img, x.get(), y.get(), main);
			g.drawString("ID: " + id + "|" + lastupdated, x.get() + 64, y.get() + 32);
		}
	}

	public Boolean getSync() {
		return sync;
	}

	public void setSync(Boolean sync) {
		this.sync = sync;
	}

	public ArrayList<Object> getToSync() {
		return toSync;
	}

	public void setToSync(ArrayList<Object> toSync) {
		this.toSync = toSync;
	}
}
