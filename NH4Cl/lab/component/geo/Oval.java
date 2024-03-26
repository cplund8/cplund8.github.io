package lab.component.geo;

import java.awt.Graphics;

public class Oval extends GeoComponent {

	public Oval(int width, int height) {
		super(width, height);
	}
	
	public Oval(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void draw(int x, int y, int width, int height, Graphics g) {
		if (canFill()) {
			g.setColor(getFillColor());
			g.fillOval(x, y, width, height);
		}
		
		if (canStroke()) {
			g.setColor(getStrokeColor());
			g.drawOval(x, y, width, height);
		}
	}

}
