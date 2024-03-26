package lab.component.fx;

public class RandomDoubleGenerator {
	
	private double start, end;
	
	public RandomDoubleGenerator(double start, double end) {
		this.start = start;
		this.end = end;
	}
	
	public RandomDoubleGenerator(double constant) {
		this(constant, constant);
	}
	
	public double getRange() {
		return end - start;
	}

	public double getStart() {
		return start;
	}

	public void setStart(double start) {
		this.start = start;
	}

	public double getEnd() {
		return end;
	}

	public void setEnd(double end) {
		this.end = end;
	}
	
	public boolean noRange() {
		return getRange() == 0;
	}
	
	public double getRandomDouble() {
		if (noRange()) {
			return start;
		}
		
		return Math.random() * getRange() + start;
	}

}
