package lab.util.animation;

public abstract class LerpAnimation<E> extends Animation<E> {
	
	protected float percent;
	protected double minDistance;
	
	public LerpAnimation(E[] targets, float percent, double minDistance) {
		super(targets);
		
		this.percent = percent;
		this.minDistance = minDistance;
	}
	
	public LerpAnimation(E[] targets, float percent) {
		this(targets, percent, 0.001); 
	}
	
	public float getPercent() {
		return percent;
	}

	public void setPercent(float percent) {
		this.percent = percent;
	}

	public double getMinDistance() {
		return minDistance;
	}

	public void setMinDistance(double minDistance) {
		this.minDistance = minDistance;
	}
	
	public static double lerp(double v1, double v2, float f) {
		return (v2 - v1) * f + v1;
	}
	
}
