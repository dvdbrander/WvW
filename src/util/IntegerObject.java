package util;

public class IntegerObject {
	private Integer value = 0;

	public IntegerObject() {
	}

	public IntegerObject(Integer value) {
		this.value = value;
	}

	public Integer get() {
		return value;
	}

	public void set(Integer value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value.toString();
	}

	public void decrease(int i) {
		value -= i;
	}

	public void increase(int i) {
		value += i;
	}

}
