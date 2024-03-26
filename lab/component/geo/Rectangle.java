package lab.component.geo;

import java.awt.Graphics;

public class Rectangle extends GeoComponent {

	public Rectangle(int width, int height) {
		super(width, height);
	}
	
	public Rectangle(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void draw(int x, int y, int width, int height, Graphics g) {
		if (canFill()) {
			g.setColor(getFillColor());
			g.fillRect(x, y, width, height);
		}
		
		if (canStroke()) {
			g.setColor(getStrokeColor());
			g.drawRect(x, y, width, height);
		}
	}

}
