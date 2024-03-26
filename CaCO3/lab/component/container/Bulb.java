package lab.component.container;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import lab.util.VerticalGraduation;

public class Bulb extends Container {

	private static final int VERTICES = 31;
	private static final double OPENING_WIDTH = 0.025;
	private static final double OPENING_HEIGHT = 0.025;
	
	public static final double[] POLY1_X = new double[VERTICES + 4];
	public static final double[] POLY1_Y = new double[VERTICES + 4];
	public static final double[] POLY2_X = new double[VERTICES + 4];
	public static final double[] POLY2_Y = new double[VERTICES + 4];
	
	static {
		double angle, x, y;
		
		for (int i = 0; i < VERTICES; i++) {
			angle = (double) i / VERTICES * Math.PI + (Math.PI / 1.96);
			
			x = Math.cos(angle) * 0.5;
			y = Math.sin(angle) * (0.5 - OPENING_HEIGHT);
			
			POLY1_X[i] = -x + 0.5;
			POLY1_Y[i] = -y + (0.5 - OPENING_HEIGHT) + 2 * OPENING_HEIGHT;
			
			POLY2_X[i] = x + 0.5;
			POLY2_Y[i] = -y + (0.5 - OPENING_HEIGHT) + 2 * OPENING_HEIGHT;
		}
		
		POLY1_X[VERTICES] = 0.5;
		POLY1_Y[VERTICES] = 1;
		
		POLY1_X[VERTICES + 1] = 1;
		POLY1_Y[VERTICES + 1] = 1;
		
		POLY1_X[VERTICES + 2] = 1;
		POLY1_Y[VERTICES + 2] = 0;
		
		POLY1_X[VERTICES + 3] = 0.5 + OPENING_WIDTH / 2.0;
		POLY1_Y[VERTICES + 3] = 0;
		
		
		POLY2_X[VERTICES] = 0.5;
		POLY2_Y[VERTICES] = 1;
		
		POLY2_X[VERTICES + 1] = 0;
		POLY2_Y[VERTICES + 1] = 1;
		
		POLY2_X[VERTICES + 2] = 0;
		POLY2_Y[VERTICES + 2] = 0;
		
		POLY2_X[VERTICES + 3] = 0.5 - OPENING_WIDTH / 2.0;
		POLY2_Y[VERTICES + 3] = 0;
	}
	
	private final int[] polyX1 = new int[VERTICES + 4];
	private final int[] polyY1 = new int[VERTICES + 4];
	private final int[] polyX2 = new int[VERTICES + 4];
	private final int[] polyY2 = new int[VERTICES + 4];
	
	private final List<Point> graduationEdge = new ArrayList<Point>(VERTICES);
	
	public Bulb(int width, int height) {
		super(width, height);
		
		setOffsetX(30);
		setOffsetY(30);
		
		setGraduation(new VerticalGraduation(0, 100, 10, 2));
		
		setShowGraduation(false);
		
	}
	
	@Override
	public void draw(int x, int y, int width, int height, Graphics g) {
		graduationEdge.clear();
		
		for (int i = 0; i < VERTICES + 4; i++) {
			polyX1[i] = (int) (POLY1_X[i] * width + x);
			polyY1[i] = (int) (POLY1_Y[i] * height + y);
			polyX2[i] = (int) (POLY2_X[i] * width + x);
			polyY2[i] = (int) (POLY2_Y[i] * height + y);
			
			if (i < VERTICES) { 
				graduationEdge.add(new Point(polyX1[VERTICES - i - 1], polyY1[VERTICES - i - 1]));
			}
		}
		
		getGraduation().setEdge(graduationEdge);
		
		g.setColor(getColor());
		g.fillRect(x, y, width, height);
		
		if (!isEmpty()) {
			drawContent(x, y, width, height - 1, g);
		}
		
		g.setColor(Color.white);
		g.fillPolygon(polyX1, polyY1, VERTICES + 4);
		g.fillPolygon(polyX2, polyY2, VERTICES + 4);
		
		g.setColor(Color.black);
		
		if (canShowGraduation()) {
			getGraduation().draw(g, width, height);
		}
		
		for (int i = 1; i < VERTICES + 1; i++) {
			g.drawLine(polyX1[i], polyY1[i], polyX1[i - 1], polyY1[i - 1]);
			g.drawLine(polyX2[i], polyY2[i], polyX2[i - 1], polyY2[i - 1]);
		}
		
		g.drawLine(polyX1[0], polyY1[0], polyX1[VERTICES + 3], polyY1[VERTICES + 3]);
		g.drawLine(polyX2[0], polyY2[0], polyX2[VERTICES + 3], polyY2[VERTICES + 3]);
		
		drawLabel(x, y, width, height, g);

	}

}
