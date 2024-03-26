package lab.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public abstract class Graduation implements Drawable {

	protected double start;
	protected double end;
	protected double lineIntervals;
	protected double subLineIntervals;
	protected int offset = 0;
	protected int fontSize = 10;
	protected int textOffset = 5;
	protected String suffix = "";
	protected List<Point> edge = new ArrayList<Point>();
	protected boolean showLabels = true;
	protected Color color = Color.black;
	protected int lineLength = 10;
	protected int subLineLength = 5;
	protected boolean removePointZero = true;
	protected int sigfigs = 3;
	protected int minPowerForScientificNotation = 3;
	
	public Graduation(double start, double end, double lineIntervals, double subLineIntervals) {
		this.start = start;
		this.end = end;
		this.lineIntervals = lineIntervals;
		this.subLineIntervals = subLineIntervals;
	}
	
	public Graduation(Graduation g) {
		this.start = g.start;
		this.end = g.end;
		this.lineIntervals = g.lineIntervals;
		this.subLineIntervals = g.subLineIntervals;
		this.offset = g.offset;
		this.fontSize = g.fontSize;
		this.suffix = g.suffix;
		this.showLabels = g.showLabels;
		this.color = g.color;
	}
	
	public void setStart(double start) {
		this.start = start;
	}

	public void setEnd(double end) {
		this.end = end;
	}

	public void setLineIntervals(double lineIntervals) {
		this.lineIntervals = lineIntervals;
	}

	public void setSubLineIntervals(double subLineIntervals) {
		this.subLineIntervals = subLineIntervals;
	}

	public double getStart() {
		return start;
	}

	public double getEnd() {
		return end;
	}

	public double getLineIntervals() {
		return lineIntervals;
	}

	public double getSubLineIntervals() {
		return subLineIntervals;
	}
	
	public int getOffset() {
		return offset;
	}
	
	public int getFontSize() {
		return fontSize;
	}
	
	public void setOffset(int offset) {
		this.offset = offset;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
	
	public void setEdge(List<Point> edge) {
		this.edge = edge;
	}
	
	public List<Point> getEdge() {
		return edge;
	}
	
	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	public boolean isShowingLabels() {
		return showLabels;
	}
	
	public void setShowLabels(boolean showLabels) {
		this.showLabels = showLabels;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getTickLength() {
		return lineLength;
	}

	public void setTickLength(int tickLength) {
		this.lineLength = tickLength;
	}

	public int getSubTickLength() {
		return subLineLength;
	}

	public void setSubTickLength(int subTickLength) {
		this.subLineLength = subTickLength;
	}

	public int getTextOffset() {
		return textOffset;
	}

	public void setTextOffset(int textOffset) {
		this.textOffset = textOffset;
	}
	
	public void setRemovePointZero(boolean removePointZero) {
		this.removePointZero = removePointZero;
	}
	
	public boolean canRemovePointZero() {
		return removePointZero;
	}
	
	public int getSigfigs() {
		return sigfigs;
	}

	public void setSigfigs(int sigfigs) {
		this.sigfigs = sigfigs;
	}

	public int getMinPowerForScientificNotation() {
		return minPowerForScientificNotation;
	}

	public void setMinPowerForScientificNotation(int minPowerForScientificNotation) {
		this.minPowerForScientificNotation = minPowerForScientificNotation;
	}

	public void draw(Graphics g, int width, int height) {
		draw(0, 0, width, height, g);
	}
	
	
	public abstract int getBottomTick();
	
	protected static double round(double t) {
		if (Math.abs(t) < 1E-5) {
			return 0;
		}
		
		return Double.parseDouble(SigFig.sigfigalize(t, 4));
	}
	
	// custom modulus method for decimals
	protected static int modulus(double d1, double d2) {
		int n = (int) Math.log10(Math.min(d1, d2));
		
		d1 *= Math.pow(10, n);
		d2 *= Math.pow(10, n);
		
		return (int) (d1 % d2);
	}

}
