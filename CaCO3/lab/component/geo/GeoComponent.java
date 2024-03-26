package lab.component.geo;

import java.awt.Color;

import lab.component.LabComponent;

public abstract class GeoComponent extends LabComponent {
	
	private Color strokeColor = Color.black;
	private boolean stroke = true;
	private Color fillColor = Color.black;
	private boolean fill = true;
	
	public GeoComponent(int width, int height) {
		super(width, height);
	}
	
	public GeoComponent(int x, int y, int width, int height) {
		super(width, height);
		
		setOffsetX(x);
		setOffsetY(y);
	}
	
	public Color getStrokeColor() {
		return strokeColor;
	}

	public void setStrokeColor(Color strokeColor) {
		this.strokeColor = strokeColor;
	}

	public boolean canStroke() {
		return stroke;
	}

	public void setStroke(boolean stroke) {
		this.stroke = stroke;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
	
	public void setFill(boolean fill) {
		this.fill = fill;
	}
	
	public boolean canFill() {
		return fill;
	}
	
}
