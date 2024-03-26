package lab.component;

import java.awt.Color;
import java.awt.Graphics;

import lab.util.Vector2;

public class Tube extends LabComponent {

	/**
	 * The default color of tubes, a nice gray color.
	 */
	public static final Color DEFAULT_COLOR = new Color(230, 230, 230);
	
	private static int diameter = 10;
	
	/**
	 * @param diameter Diameter of all tubes created 
	 */
	public static void setDiameter(int diameter) {
		Tube.diameter = diameter;
	}
	
	public static Tube straight(int x, int y, double angle, int length) {
		return new Tube(x, y, angle, 
				0, 0, 
				diameter, 0, 
				diameter, length, 
				0, length
		);
	}
	
	public static Tube angle90(int x, int y, double angle, int length) {
		return new Tube(x, y, angle, 
				0, 0, 
				0, length,
				diameter, length,
				diameter, diameter, 
				length, diameter,
				length, 0
		);
	}
	
	private final int[] xs, ys;
	private Color color = DEFAULT_COLOR;
	
	private Tube(int x, int y, double angle, int...xy) {
		super(diameter, diameter);
		
		setOffset(x, y);
		
		xs = new int[xy.length / 2];
		ys = new int[xy.length / 2];
		
		Vector2 v;
		
		for (int i = 0; i < xy.length / 2; i++) {
			
			v = new Vector2(xy[i * 2], xy[i * 2 + 1]).rotate(angle);
			
			xs[i] = (int) v.getX();
			ys[i] = (int) v.getY();
			
		}
		
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}

	@Override
	public void draw(int x, int y, int width, int height, Graphics g) {
		
		int[] xsScaled = scaleAndShift(xs, (double) width / getWidth(), x);
		int[] ysScaled = scaleAndShift(ys, (double) height / getHeight(), y);
		
		g.setColor(color);
		g.fillPolygon(xsScaled, ysScaled, xs.length);
		
		g.setColor(Color.black);
		g.drawPolygon(xsScaled, ysScaled, xs.length);
		
	}
	
	private static int[] scaleAndShift(int[] arr, double m, int c) {
		int[] newArr = new int[arr.length];
		
		for (int i = 0; i < arr.length; i++) {
			newArr[i] = (int) (arr[i] * m + c);
		}
		
		return newArr;
	}

}
