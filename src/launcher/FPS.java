package launcher;

public class FPS {
	private long oldMillis = System.currentTimeMillis();
	private int frames = 0;
	private long fps = 60;
	private int sleepmillis = 17;
	private MainThread main;
	public FPS(MainThread main) {
		this.main = main;
	}
	
	void updateFPS(){
		if (System.currentTimeMillis() - oldMillis >= 1000) {
			long passedMillis = System.currentTimeMillis() - oldMillis;
			setFps(frames / (passedMillis / 1000));
			frames = 0;
			oldMillis = System.currentTimeMillis();
			if (getFps() < 55) {
				setSleepmillis(Math.max(1, getSleepmillis() - 1));
			} else if (getFps() > 65) {
				setSleepmillis(getSleepmillis() + 1);
			}
			System.out.println("FPS:"+getFps()+"|Ping:"+main.online.getPing()+"|ID:"+main.online.getConnection().id);
			if (main.online.getConnection().id == -1)
				main.online.getConnection().getSendMessages().getID();
		}
		frames++;
	}

	public long getFps() {
		return fps;
	}

	public void setFps(long fps) {
		this.fps = fps;
	}

	public int getSleepmillis() {
		return sleepmillis;
	}

	public void setSleepmillis(int sleepmillis) {
		this.sleepmillis = sleepmillis;
	}
}
