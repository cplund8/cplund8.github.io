package lab.component.data;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import lab.util.Vector2;

public class GraphDataSet {
	
	private String name;
	private boolean connectPoints;
	private boolean showName;
	private Color color;
	private List<Vector2> points;
	private int nameOffsetX;
	private int nameOffsetY;
	
 	public GraphDataSet(String name, boolean connectPoints, boolean showName) {
		this(name, connectPoints, showName, Color.red);
	}
 	
 	public GraphDataSet(String name, boolean connectPoints, boolean showName, Color color) {
		this(name, connectPoints, showName, color, new ArrayList<Vector2>());
	}
 	
 	public GraphDataSet(String name, boolean connectPoints, boolean showName, List<Vector2> points) {
		this(name, connectPoints, showName, Color.red, points);
	}
 	
 	public GraphDataSet(String name, boolean connectPoints, boolean showName, Color color, List<Vector2> points) {
		this.name = name;
		this.connectPoints = connectPoints;
		this.showName = showName;
		this.color = color;
		this.points = points;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isConnectPoints() {
		return connectPoints;
	}

	public void setConnectPoints(boolean connectPoints) {
		this.connectPoints = connectPoints;
	}

	public boolean isShowName() {
		return showName;
	}

	public void setShowName(boolean showName) {
		this.showName = showName;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public List<Vector2> getPoints() {
		return points;
	}

	public void setPoints(List<Vector2> points) {
		this.points = points;
	}

	public int getNameOffsetX() {
		return nameOffsetX;
	}

	public void setNameOffsetX(int nameOffsetX) {
		this.nameOffsetX = nameOffsetX;
	}

	public int getNameOffsetY() {
		return nameOffsetY;
	}

	public void setNameOffsetY(int nameOffsetY) {
		this.nameOffsetY = nameOffsetY;
	}

	public void addPoint(Vector2 point) {
		points.add(point);
	}
	
	public void addPoint(double x, double y) {
		points.add(new Vector2(x, y));
	}
	
	public void removePoint(Vector2 point) {
		points.remove(point);
	}
	
	public void clearPoints() {
		points.clear();
	}
	
	public int size() {
		return points.size();
	}


	

}
