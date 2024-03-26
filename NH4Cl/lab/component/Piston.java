package lab.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import lab.util.ClickableArea;
import lab.util.VerticalGraduation;

/**
 * @author Benjamin Walls
 * @version 1.0
 * @since 1.0
 */
public class Piston extends GraduatedComponent {

	private Color gasColor = Color.red;
	private double transparencyModifier = 1.0;
	private ClickableArea dragArea = new ClickableArea(this);
	private boolean canDrag = true;

	/**
	 * @param width The width of the Piston
	 * @param height The height of the Piston
	 */
	public Piston(int width, int height) {
		super(width, height);

		setGraduation(new VerticalGraduation(0, 25, 5, 1));
		getGraduation().setSuffix("mL");
		// getGraduation().setColor(Color.white);

		setOffsetX(30);
		setOffsetY(30);

		setValue(0);

		dragArea.setScale(false);
	}

	
	/**
	 * @return The color of the gas contained by the Piston.
	 */
	public Color getGasColor() {
		return gasColor;
	}

	
	/**
	 * Use to change the color of the gas in the Piston.
	 * 
	 * @param gasColor The color of the gas contained by the Piston.
	 */
	public void setGasColor(Color gasColor) {
		this.gasColor = gasColor;
	}

	/**
	 * @return The transparency of the gas, number between 0.0 and 1.0
	 */
	public double getTransparencyModifier() {
		return transparencyModifier;
	}

	/**
	 * @param transparencyModifier A number between 0.0 and 1.0, determines how transparent the Piston's gas is.
	 */
	public void setTransparencyModifier(double transparencyModifier) {
		this.transparencyModifier = transparencyModifier;
	}
	
	
	/**
	 * @return Whether the piston's volume can be changed or not via dragging.
	 */
	public boolean canDrag() {
		return canDrag;
	}
	
	/**
	 * Disables or enables the drag feature of the piston, allowing the user to change the volume or not.
	 * 
	 * @param canDrag Whether or not the piston's volume can be changed by the user.
	 */
	public void setCanDrag(boolean canDrag) {
		this.canDrag = canDrag;
	}

	@Override
	public void draw(int x, int y, int width, int height, Graphics g) {
		List<Point> edge = new ArrayList<Point>();
		edge.add(new Point(x + width, y + height));
		edge.add(new Point(x + width, y));
		getGraduation().setEdge(edge);

		double range = getGraduation().getEnd() - getGraduation().getStart();

		double pistonY = (range - getValue()) / range * height;
		double pistonHeight = getGraduation().getSubLineIntervals() / range * height;

		g.setColor(Color.lightGray);
		g.fillRect(x, y, width, height);


		if (gasColor != null) {
			double transparency = pistonY / height * transparencyModifier * 255;
	
			transparency = Math.max(transparency, 0);
			transparency = Math.min(transparency, 255);
	
			g.setColor(new Color(gasColor.getRed(), gasColor.getGreen(), gasColor.getBlue(), (int) (transparency)));
			g.fillRect(x, (int) (y + pistonY), width + 1, (int) (height - pistonY) + 1);
		}
		
		g.setColor(Color.darkGray);
		g.fillRect(x - 5, y - (int) pistonHeight, 5, height + (int) pistonHeight);
		g.fillRect(x - 5, y + height, width + 10, 5);
		g.fillRect(x + width, y - (int) pistonHeight, 5, height + (int) pistonHeight);

		g.setColor(Color.darkGray);
		g.fillRect(x, (int) (pistonY + y - pistonHeight), width + 1, (int) pistonHeight + 1);

		if (getGraduation() != null) {
			getGraduation().draw(g, width, height);
		}

		if (canDrag) {
			dragArea.checkRaw(x, (int) (pistonY - pistonHeight) + y, width, (int) pistonHeight);
	
			if (dragArea.hasClick()) {
				setValue(range - (double) (dragArea.getMousePosition().y - y) / height * range);
				onDragged();
			}
		}

		if (getValue() > range) {
			setValue(range);
		}

		if (getValue() < 0) {
			setValue(0);
		}

	}
	
	
	/**
	 * Override this to run code when the piston's volume is changed by the user.
	 */
	public void onDragged() {
		
	}

	@Override
	public void initJPanel(JPanel panel) {
		dragArea.initializeMouseListeners(panel);
	}

}
