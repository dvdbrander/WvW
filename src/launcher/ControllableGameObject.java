package launcher;

import java.awt.event.KeyEvent;

public class ControllableGameObject extends GameObject {


	public ControllableGameObject(MainThread main) {
		super(main);
	}

	@Override
	public void update() {
		if (main.keys[KeyEvent.VK_A]){
			x.decrease(1);
		}if (main.keys[KeyEvent.VK_D]){
			x.increase(1);
		}if (main.keys[KeyEvent.VK_W]){
			y.decrease(1);
		}if (main.keys[KeyEvent.VK_S]){
			y.increase(1);
		}
		super.update();
	}
}
