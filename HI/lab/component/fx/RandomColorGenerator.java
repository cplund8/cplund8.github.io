package lab.component.fx;

import java.awt.Color;

public class RandomColorGenerator {
	
	private int redStart, redEnd, greenStart, greenEnd, blueStart, blueEnd;
	
	public RandomColorGenerator(int rMin, int rMax, int gMin, int gMax, int bMin, int bMax) {
		this.redStart = rMin;
		this.redEnd = rMax;
		this.greenStart = gMin;
		this.greenEnd = gMax;
		this.blueStart = bMin;
		this.blueEnd = bMax;
	}
	
	public RandomColorGenerator(int r, int g, int b) {
		this(r, r, g, g, b, b);
	}
	
	public RandomColorGenerator(Color color) {
		this(color.getRed(), color.getGreen(), color.getBlue());
	}
	
	public RandomColorGenerator(Color color1, Color color2) {
		this(color1.getRed(), color1.getGreen(), color1.getBlue(), color2.getRed(), color2.getGreen(), color2.getBlue());
	}
	
	public int getRedStart() {
		return redStart;
	}

	public void setRedStart(int redStart) {
		this.redStart = redStart;
	}

	public int getRedEnd() {
		return redEnd;
	}

	public void setRedEnd(int redEnd) {
		this.redEnd = redEnd;
	}
	
	public int getRedRange() {
		return redStart - redEnd;
	}

	public int getGreenStart() {
		return greenStart;
	}

	public void setGreenStart(int greenStart) {
		this.greenStart = greenStart;
	}

	public int getGreenEnd() {
		return greenEnd;
	}

	public void setGreenEnd(int greenEnd) {
		this.greenEnd = greenEnd;
	}
	
	public int getGreenRange() {
		return greenEnd - greenStart;
	}

	public int getBlueStart() {
		return blueStart;
	}

	public void setBlueStart(int blueStart) {
		this.blueStart = blueStart;
	}

	public int getBlueEnd() {
		return blueEnd;
	}

	public void setBlueEnd(int blueEnd) {
		this.blueEnd = blueEnd;
	}
	
	public int getBlueRange() {
		return blueEnd - blueStart;
	}
	
	public boolean noRange() {
		return getRedRange() == 0 && getGreenRange() == 0 && getBlueRange() == 0;
	}
	
	public Color getRandomColor() {
		if (noRange()) {
			return new Color(clamp(redStart), clamp(greenStart), clamp(blueStart));
		}
		
		int r = (int) (getRedRange() * Math.random() + redStart);
		int g = (int) (getGreenRange() * Math.random() + greenStart);
		int b = (int) (getBlueRange() * Math.random() + blueStart);
		
		r = clamp(r);
		g = clamp(g);
		b = clamp(b);
		
		return new Color(r, g, b);
	}
	
	public static int clamp(int v) {
		return Math.min(255, Math.max(v, 0));
	}

	public static int clamp(double v) {
		return (int) Math.min(255, Math.max(v, 0));
	}
	
	
	

}
