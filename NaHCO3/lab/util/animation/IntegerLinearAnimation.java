package lab.util.animation;

public abstract class IntegerLinearAnimation extends LinearAnimation<Integer> {

	public IntegerLinearAnimation(Integer[] targets, int step) {
		super(targets, step);
	}
	
	public IntegerLinearAnimation(Integer target, int step) {
		super(new Integer[] { target }, step);
	}
	
	@Override
	public void changeValue() {
		if (getValue() < getCurrentTarget()) {
			setValue((int) Math.min(getValue() + step, getCurrentTarget()));
		} else {
			setValue((int) Math.max(getValue() - step, getCurrentTarget()));
		}
	}
	
}
