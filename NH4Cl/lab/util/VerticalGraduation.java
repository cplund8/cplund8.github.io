package lab.util;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;

/**
 * @author Benjamin Walls
 * @version 1.0
 * @since 1.0
 */
public class VerticalGraduation extends Graduation implements Drawable {

	/**
	 * Creates a VerticalGraduation for use with components.
	 * 
	 * @param start
	 *            The minimum graduation mark.
	 * @param end
	 *            The maximum graduation mark.
	 * @param lineIntervals
	 *            Specifies the interval at which to draw graduation lines.
	 * @param subLineIntervals
	 *            Specifies the subinterval at which to draw graduation lines.
	 */
	public VerticalGraduation(double start, double end, double lineIntervals, double subLineIntervals) {
		super(start, end, lineIntervals, subLineIntervals);
	}

	/**
	 * Creates a VerticalGraduation for use with components. Starts at zero.
	 * 
	 * @param end
	 *            The maximum graduation mark.
	 * @param lineIntervals
	 *            Specifies the interval at which to draw graduation lines.
	 * @param subLineIntervals
	 *            Specifies the subinterval at which to draw graduation lines.
	 */
	public VerticalGraduation(double end, double lineIntervals, double subLineIntervals) {
		super(0, end, lineIntervals, subLineIntervals);
	}

	/**
	 * Creates a VerticalGraduation from an existing Graduation object.
	 * 
	 * @param g
	 *            A Graduation object.
	 */
	public VerticalGraduation(Graduation g) {
		super(g);
	}

	@Override
	public void draw(int x, int y, int w, int h, Graphics g) {
		g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, this.fontSize));
		FontMetrics metrics = g.getFontMetrics();
		float textHeight = metrics.getLineMetrics("0", g).getHeight();
		Point bottom, top, p;
		double height, range;
		bottom = edge.get(0);
		top = edge.get(edge.size() - 1);
		height = bottom.y - top.y; // top will have a lower y value than bottom
									// (confusing)
		range = end - start;
		range = round(range);
		g.setColor(color);
		for (double i = 0; round(i) <= range; i += subLineIntervals) {
			p = findLinePosition(edge, (int) ((i / range * height) + top.y));
			p.y += offset;
			if (modulus(round(i), round(lineIntervals)) == 0.0) {
				g.drawLine(p.x, p.y, (int) (p.x - lineLength), p.y);
				if (showLabels) {
					String s = SigFig.sigfigalize(round(range - i + start), sigfigs, minPowerForScientificNotation)
							+ (i == range ? suffix : "");
					if (removePointZero)
						s = s.replace(".0", "");
					g.drawString(s, p.x + 5 + textOffset, (int) (p.y + (textHeight / 2)));
				}
			} else
				g.drawLine(p.x, p.y, (int) (p.x - subLineLength), p.y);
		}
	}

	@Override
	public int getBottomTick() {
		return edge.get(0).y + offset;
	}

	/**
	 * Finds an x-value for a given y value on a line.
	 * 
	 * @param edge List of points on the line.
	 * @param y Specifies a y value.
	 * @return The new point.
	 */
	public static Point findLinePosition(List<Point> edge, int y) {
		Point p1 = null, p2 = null;
		for (int i = 1; i < edge.size(); i++) {
			p1 = edge.get(i);
			p2 = edge.get(i - 1);
			if ((y <= p1.getY() && y >= p2.getY()) || (y >= p1.getY() && y <= p2.getY()))
				break;
		}
		// x = my + b
		// x - my = b
		double m, b;
		m = (p1.getX() - p2.getX()) / (p1.getY() - p2.getY());
		b = p1.getX() - m * p1.getY();
		return new Point((int) (y * m + b), y);
	}

}
