package lab.component;

import lab.util.Graduation;

/**
 * @author Benjamin Walls
 * @version 1.0
 * @since 1.0
 */
public abstract class GraduatedComponent extends MeasurableComponent {
	
	private Graduation graduation = null;
	
	/**
	 * Creates a GraduatedComponent with the given parameters.
	 * 
	 * @param width Specifies the width of the component.
	 * @param height Specifies the height of the component.
	 */
	public GraduatedComponent(int width, int height) {
		super(width, height);
	}

	/**
	 * @return The Graduation object used by the component.
	 */
	public Graduation getGraduation() {
		return graduation;
	}

	/**
	 * @param graduation Sets a new Graduation object to be used by the component.
	 */
	public void setGraduation(Graduation graduation) {
		this.graduation = graduation;
	}

}
