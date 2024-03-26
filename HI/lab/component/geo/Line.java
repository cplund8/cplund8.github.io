package lab.component.geo;

import java.awt.Color;
import java.awt.Graphics;

import lab.component.LabComponent;

public class Line extends LabComponent {

	private Color color = Color.black;
	private int startX, startY, endX, endY;

	public Line(int startX, int startY, int endX, int endY) {
		super(0, 0);
		
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getStartX() {
		return startX;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public int getStartY() {
		return startY;
	}

	public void setStartY(int startY) {
		this.startY = startY;
	}

	public int getEndX() {
		return endX;
	}

	public void setEndX(int endX) {
		this.endX = endX;
	}

	public int getEndY() {
		return endY;
	}

	public void setEndY(int endY) {
		this.endY = endY;
	}

	@Override
	public void draw(int x, int y, int width, int height, Graphics g) {
		g.setColor(color);
		g.drawLine(startX, startY, endX, endY);

	}

}
