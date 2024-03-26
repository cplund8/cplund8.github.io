package lab.util.animation;

public abstract class DoubleLinearAnimation extends LinearAnimation<Double> {
	
	public DoubleLinearAnimation(Double[] targets, double step) {
		super(targets, step);
	}
	
	public DoubleLinearAnimation(Double target, double step) {
		super(new Double[] { target }, step);
	}
	
	
	@Override
	public void changeValue() {
		if (getValue() < getCurrentTarget()) {
			setValue(Math.min(getValue() + step, getCurrentTarget()));
		} else {
			setValue(Math.max(getValue() - step, getCurrentTarget()));
		}
	}
	
	
	

}