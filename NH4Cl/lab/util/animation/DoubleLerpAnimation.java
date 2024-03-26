package lab.util.animation;

public abstract class DoubleLerpAnimation extends LerpAnimation<Double> {
	
	public DoubleLerpAnimation(Double[] targets, float percent, double minDistance) {
		super(targets, percent, minDistance);
	}
	
	public DoubleLerpAnimation(Double[] targets, float percent) {
		super(targets, percent);
	}
	
	public DoubleLerpAnimation(Double target, float percent, double minDistance) {
		super(new Double[] { target }, percent, minDistance);
	}
	
	public DoubleLerpAnimation(Double target, float percent) {
		super(new Double[] { target }, percent);
	}
	
	@Override
	public void changeValue() {
		if (Math.abs(getValue() - getCurrentTarget()) < minDistance) {
			setValue(getCurrentTarget());
		} else {
			setValue(lerp(getValue(), getCurrentTarget(), percent));
		}
	}


}
