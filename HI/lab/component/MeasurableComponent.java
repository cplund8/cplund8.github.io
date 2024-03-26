package lab.component;

/**
 * @author Benjamin Walls
 * @version 1.0
 * @since 1.0
 */
public abstract class MeasurableComponent extends LabComponent {
	
	private double value = 0.0D;
	
	/**
	 * @param width Width of the LabComponent.
	 * @param height Height of the LabComponent.
	 */
	public MeasurableComponent(int width, int height) {
		super(width, height);
	}
	
	/**
	 * @return Returns the value associated with this MeasurableComponent.
	 */
	public double getValue() {
		return value;
	}
	
	/**
	 * @param value The new value of this MeasurableComponent.
	 */
	public void setValue(double value) {
		this.value = value;
	}
	
}
