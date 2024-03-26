package lab.component.container;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import lab.util.VerticalGraduation;

public class GraduatedCylinder extends Container {

	public GraduatedCylinder(int width, int height) {
		super(width, height);

		setGraduation(new VerticalGraduation(0, 25, 5, 1));

		setOffsetX(30);
		setOffsetY(30);
	}

	@Override
	public void draw(int x, int y, int width, int height, Graphics g) {

		List<Point> edge = new ArrayList<Point>();
		edge.add(new Point(x + width, y + height));
		edge.add(new Point(x + width, y));
		getGraduation().setEdge(edge);

		g.setColor(getColor());
		g.fillRect(x, y, width, height);

		g.setColor(Color.gray);

		g.drawLine(x, y, x, y + height);
		g.drawLine(x + width, y, x + width, y + height);
		g.drawLine(x, y + height, x + width, y + height);

		if (!isEmpty()) {
			drawContent(x, y, width, height, g);
		}

		if (canShowGraduation()) {
			getGraduation().draw(g, width, height);
		}

		drawLabel(x, y, width, height, g);

	}

}
