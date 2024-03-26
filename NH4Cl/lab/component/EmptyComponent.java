package lab.component;

import java.awt.Graphics;

/**
 * @author Benjamin Walls
 * @version 1.0
 * @since 1.0
 */
public class EmptyComponent extends LabComponent {

	/**
	 * Creates an empty LabComponent object with the given parameters.
	 * Good for serving as a container.
	 * 
	 * @param width Specifies the width of the component.
	 * @param height Specifies the height of the component.
	 */
	public EmptyComponent(int width, int height) {
		super(width, height);
	}

	@Override
	public void draw(int x, int y, int width, int height, Graphics g) { }

}
