package lab.component.container;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import lab.component.GraduatedComponent;

public abstract class Container extends GraduatedComponent {

	private Color contentColor = Color.green;
	private ContentState contentState = ContentState.LIQUID;
	private Color color = new Color(230, 230, 230);
	private String label = null;
	private int labelOffsetX;
	private int labelOffsetY;
	private int labelSize = 10;
	private boolean showGraduation = true;
	
	public Container(int width, int height) {
		super(width, height);
	}

	public Color getContentColor() {
		return contentColor;
	}

	public void setContentColor(Color contentColor) {
		this.contentColor = contentColor;
	}

	public ContentState getContentState() {
		return contentState;
	}

	public void setContentState(ContentState contentState) {
		this.contentState = contentState;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getLabelOffsetX() {
		return labelOffsetX;
	}

	public void setLabelOffsetX(int labelOffsetX) {
		this.labelOffsetX = labelOffsetX;
	}

	public int getLabelOffsetY() {
		return labelOffsetY;
	}

	public void setLabelOffsetY(int labelOffsetY) {
		this.labelOffsetY = labelOffsetY;
	}
	
	public void setLabelOffset(int labelOffsetX, int labelOffsetY) {
		setLabelOffsetX(labelOffsetX);
		setLabelOffsetY(labelOffsetY);
	}

	public int getLabelSize() {
		return labelSize;
	}

	public void setLabelSize(int labelSize) {
		this.labelSize = labelSize;
	}
	
	public boolean canShowGraduation() {
		return showGraduation;
	}

	public void setShowGraduation(boolean showGraduation) {
		this.showGraduation = showGraduation;
	}
	
	@Override
	public double getValue() {
		if (contentState == ContentState.LIQUID) {
			double range = getGraduation().getEnd() - getGraduation().getStart();
			return super.getValue() * range;
		} else {
			return super.getValue();
		}
	}

	@Override
	public void setValue(double value) {
		if (contentState == ContentState.LIQUID) {
			double range = getGraduation().getEnd() - getGraduation().getStart();
			super.setValue(value / range);
		} else {
			super.setValue(value);
		}
	}
	
	public boolean isEmpty() {
		return getValue() == 0.0;
	}
	
	public void drawContent(int x, int y, int width, int height, Graphics g) {
		if (contentState == ContentState.SOLID) {
			
			Random rand = new Random((long) getValue() * 1000);	
			double scaling = (double) width / getWidth();
			
			int[] polyX = new int[(int) getValue()];
			int[] polyY = new int[(int) getValue()];
			
			for (int i = 0; i < (int) getValue(); i++) {
				
				Point p = new Point((int) (-getValue() * (rand.nextDouble() * 0.1 + 0.9) * scaling), 0);
				
				p = rotate(p, (double) i / (int) getValue() * 190.0);
				
				polyX[i] = p.x + x + width / 2;
				polyX[i] = polyX[i] > width + x ? width + x : polyX[i] < x ? x : polyX[i];
				polyY[i] = p.y + y + height;
				polyY[i] = polyY[i] > height + y ? height + y : polyY[i] < y ? y : polyY[i];
				
			}
			
			g.setColor(contentColor);
			g.fillPolygon(polyX, polyY, polyX.length);
			g.setColor(Color.lightGray);
			g.drawPolygon(polyX, polyY, polyX.length);
			
		} else if (contentState == ContentState.LIQUID) {
			
			g.setColor(contentColor);
			g.fillRect(x, getGraduation().getBottomTick() - (int) (super.getValue() * height), width, (int) (super.getValue() * height));	
			
			
		} else if (contentState == ContentState.GAS) {
		
			g.setColor(new Color(contentColor.getRed(), contentColor.getGreen(), contentColor.getBlue(), Math.max(Math.min(255, (int) getValue()), 0)));
			g.fillRect(x, y, width, height);
			
		}
	}
	
	public void drawLabel(int x, int y, int w, int h, Graphics g) {
		if (label == "" || label == null) return;
		
		int sx = (int) ((double) labelOffsetX / getWidth() * w);
		int sy = (int) ((double) labelOffsetY / getHeight() * h);
		
		x += sx;
		y += sy;
		
		g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, labelSize));
		g.setColor(Color.black);
		
		float sh = g.getFontMetrics().getLineMetrics("0", g).getHeight();
		
		int posY = 0;
		
		for (String str : label.split("\n")) {	
			g.drawString(str, x, y + posY);
			posY += sh;
		}
	}
	
	public static Point rotate(Point p, double angle) {
		angle = angle / 180.0 * Math.PI;
		double s = Math.sin(angle);
		double c = Math.cos(angle);
		return new Point((int) (p.x * c - p.y * s), (int) (p.x * s + p.y * c));
	}
	
}
