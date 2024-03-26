package lab.util.animation;

public abstract class LinearAnimation<E> extends Animation<E> {
	
	protected double step;
	
	public LinearAnimation(E[] targets, double step) {
		super(targets);
		
		this.step = step;
	}
	
	public double getStep() {
		return step;
	}
	
	public void setStep(double step) {
		this.step = step;
	}
	
}
