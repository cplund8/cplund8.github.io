package lab.util.animation;

import java.awt.Color;

public abstract class ColorLerpAnimation extends LerpAnimation<Color> {
	
	public ColorLerpAnimation(Color[] targets, float percent, double minDistance) {
		super(targets, percent, minDistance);
	}
	
	public ColorLerpAnimation(Color[] targets, float percent) {
		super(targets, percent);
	}
	
	public ColorLerpAnimation(Color target, float percent, double minDistance) {
		super(new Color[] { target }, percent, minDistance);
	}
	
	public ColorLerpAnimation(Color target, float percent) {
		super(new Color[] { target }, percent);
	}
	
	@Override
	public void changeValue() {
		if (Math.abs(getValue().getRed() - getCurrentTarget().getRed()) < minDistance) {
			setR(getCurrentTarget().getRed());
		} else {
			setR(lerp(getValue().getRed(), getCurrentTarget().getRed(), percent));
		}
		
		if (Math.abs(getValue().getGreen() - getCurrentTarget().getGreen()) < minDistance) {
			setG(getCurrentTarget().getGreen());
		} else {
			setG(lerp(getValue().getGreen(), getCurrentTarget().getGreen(), percent));
		}
		
		if (Math.abs(getValue().getBlue() - getCurrentTarget().getBlue()) < minDistance) {
			setB(getCurrentTarget().getBlue());
		} else {
			setB(lerp(getValue().getBlue(), getCurrentTarget().getBlue(), percent));
		}
		
		if (Math.abs(getValue().getAlpha() - getCurrentTarget().getAlpha()) < minDistance) {
			setA(getCurrentTarget().getAlpha());
		} else {
			setA(lerp(getValue().getAlpha(), getCurrentTarget().getAlpha(), percent));
		}
	}
	
	private void setR(double r) {
		setValue(new Color(clamp(r), getValue().getGreen(), getValue().getBlue(), getValue().getAlpha()));
	}
	
	private void setG(double g) {
		setValue(new Color(getValue().getRed(), clamp(g), getValue().getBlue(), getValue().getAlpha()));
	}
	
	private void setB(double b) {
		setValue(new Color(getValue().getRed(), getValue().getGreen(), clamp(b), getValue().getAlpha()));
	}
	
	private void setA(double a) {
		setValue(new Color(getValue().getRed(), getValue().getGreen(), getValue().getBlue(), clamp(a)));
	}
	
	public static int clamp(double v) {
		return (int) Math.ceil(Math.min(255, Math.max(v, 0)));
	}
	
	
}
