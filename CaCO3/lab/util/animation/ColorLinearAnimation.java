package lab.util.animation;

import java.awt.Color;

public abstract class ColorLinearAnimation extends LinearAnimation<Color> {

	public ColorLinearAnimation(Color[] targets, double step) {
		super(targets, step);
	}
	
	public ColorLinearAnimation(Color target, double step) {
		super(new Color[] { target }, step);
	}
	
	@Override
	public void changeValue() {
		if (getValue().getRed() < getCurrentTarget().getRed()) {
			setR(Math.min(getValue().getRed() + step, getCurrentTarget().getRed()));
		} else {
			setR(Math.max(getValue().getRed() - step, getCurrentTarget().getRed()));
		}
		
		if (getValue().getGreen() < getCurrentTarget().getGreen()) {
			setG(Math.min(getValue().getGreen() + step, getCurrentTarget().getGreen()));
		} else {
			setG(Math.max(getValue().getGreen() - step, getCurrentTarget().getGreen()));
		}
		
		if (getValue().getBlue() < getCurrentTarget().getBlue()) {
			setB(Math.min(getValue().getBlue() + step, getCurrentTarget().getBlue()));
		} else {
			setB(Math.max(getValue().getBlue() - step, getCurrentTarget().getBlue()));
		}
		
		if (getValue().getAlpha() < getCurrentTarget().getAlpha()) {
			setA(Math.min(getValue().getAlpha() + step, getCurrentTarget().getAlpha()));
		} else {
			setA(Math.max(getValue().getAlpha() - step, getCurrentTarget().getAlpha()));
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
		return (int) Math.min(255, Math.max(v, 0));
	}
	
	

}
