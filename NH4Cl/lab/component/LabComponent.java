package lab.component;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JPanel;

import lab.util.Drawable;

/**
 * @author Benjamin Walls
 * @version 1.0
 * @since 1.0
 */
public abstract class LabComponent implements Drawable {

	/**
	 * Constant representing the free form layout, where LabComponent's are
	 * positioned solely by their offsets.
	 */
	public static final byte FREE_FORM = 0;
	/**
	 * Constant representing the paragraph layout, where LabComponent's are
	 * positioned from left to right as if in a paragraph.
	 */
	public static final byte PARAGRAPH = 1;

	private String identifier = "";
	private int width, height;
	private int offsetX = 0;
	private int offsetY = 0;
	private int zOrder = 0;
	private boolean visible = true;
	private int layout = PARAGRAPH;
	private boolean scaleChildren = true;
	private final List<LabComponent> children = new ArrayList<LabComponent>();
	private LabComponent parent = null;
	private LabComponent root = null;
	private boolean needsChildSort = false;
	private int lastDrawX = -1;
	private int lastDrawY = -1;
	private int lastDrawWidth = -1;
	private int lastDrawHeight = -1;
	private int scaleReferenceWidth;
	private int scaleReferenceHeight;
	private boolean jPanelInitCalled = false;
	private boolean showBounds = false;

	/**
	 * Creates a LabComponent object with the given parameters.
	 * 
	 * @param width
	 *            Specifies the width of the LabComponent.
	 * @param height
	 *            Specifies the height of the LabComponent.
	 */
	public LabComponent(int width, int height) {
		this.width = width;
		this.height = height;
		scaleReferenceWidth = width;
		scaleReferenceHeight = height;
	}

	private void updateRoot() {
		root = getRoot(this);
		for (LabComponent c : children)
			c.updateRoot();
	}

	/**
	 * Inserts child LabComponent's at a specified index in the children array.
	 * Index determines z-order.
	 * 
	 * @param index
	 *            Index to insert the children.
	 * @param components
	 *            LabComponent's to insert as children.
	 */
	public void insertChild(int index, LabComponent... components) {
		for (LabComponent component : components) {
			component.parent = this;
			component.updateRoot();
			children.add(index, component);
			needsChildSort = true;
		}
	}

	/**
	 * Adds children to this LabComponent, allowing them to be drawn within this
	 * LabComponents bounds.
	 * 
	 * @param components
	 *            LabComponent's to add as children
	 */
	public void addChild(LabComponent... components) {
		for (LabComponent component : components) {
			component.parent = this;
			component.updateRoot();
			children.add(component);
			needsChildSort = true;
		}
	}

	/**
	 * Removes child LabComponent's.
	 * 
	 * @param components
	 *            LabComponent's to remove as children.
	 */
	public void removeChild(LabComponent... components) {
		for (LabComponent component : components) {
			children.remove(component);
			component.parent = null;
			component.updateRoot();

			needsChildSort = true;
		}
	}

	/**
	 * Remove child LabComponent's by index.
	 * 
	 * @param index
	 *            Indices of children to remove from this LabComponent.
	 */
	public void removeChild(int... index) {
		for (int i : index) {
			removeChild(getChild(i));
		}
	}

	/**
	 * @return List contains all children of this LabComponent.
	 */
	public List<LabComponent> getChildren() {
		return new ArrayList<LabComponent>(children);
	}

	/**
	 * @param index
	 *            Index of child contained by this LabComponent.
	 * @return Child LabComponen at specified index.
	 */
	public LabComponent getChild(int index) {
		return children.get(index);
	}

	/**
	 * @return LabComponent parent of this LabComponent.
	 */
	public LabComponent getParent() {
		return parent;
	}

	/**
	 * @return Root LabComponent, or the only LabComponent in the LabComponent
	 *         hierarchy without a parent.
	 */
	public LabComponent getRoot() {
		return root;
	}

	/**
	 * @return String given to this LabComponent for unique identification.
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * @param identifier
	 *            Set the unique String identifier for this LabComponent.
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * Stretches the LabComponent to a new width while maintaining aspect ratio.
	 * 
	 * @param width
	 *            New width of the LabComponent.
	 */
	public void stretchToNewWidth(int width) {
		this.width = width;
	}

	/**
	 * Stretches the LabComponent to a new height while maintaining aspect
	 * ratio.
	 * 
	 * @param width
	 *            New height of the LabComponent.
	 */
	public void stretchToNewHeight(int height) {
		this.height = height;
	}

	/**
	 * Sets the width of the LabComponent.
	 * 
	 * @param width
	 *            Specifies the width of the LabComponent.
	 */
	public void setWidth(int width) {
		this.width = width;
		this.scaleReferenceWidth = width;
	}

	/**
	 * Sets the height of the LabComponent.
	 * 
	 * @param height
	 *            Specifies the height of the LabComponent.
	 */
	public void setHeight(int height) {
		this.height = height;
		this.scaleReferenceHeight = height;
	}

	/**
	 * @return The width of the LabComponent.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return The height of the LabComponent.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return The X offset of the LabComponent.
	 */
	public int getOffsetX() {
		return offsetX;
	}

	/**
	 * Specifies the X offset of the LabComponent. If the layout is free form,
	 * then this is the absolute X coordinate at which this LabComponent is
	 * drawn at.
	 * 
	 * @param offsetX
	 *            The X offset of the LabComponent.
	 */
	public void setOffsetX(int offsetX) {
		this.offsetX = offsetX;
	}

	/**
	 * @return The Y offset of the LabComponent.
	 */
	public int getOffsetY() {
		return offsetY;
	}

	/**
	 * Specifies the Y offset of the LabComponent. If the layout is free form,
	 * then this is the absolute Y coordinate at which this LabComponent is
	 * drawn at.
	 * 
	 * @param offsetY
	 *            The Y offset of the LabComponent.
	 */
	public void setOffsetY(int offsetY) {
		this.offsetY = offsetY;
	}

	/**
	 * Sets the offset in both dimensions.
	 * 
	 * @param offsetX
	 *            Specifies the X offset of the LabComponent.
	 * @param offsetY
	 *            Specifies the Y offset of the LabComponent.
	 */
	public void setOffset(int offsetX, int offsetY) {
		setOffsetX(offsetX);
		setOffsetY(offsetY);
	}

	/**
	 * The z-order determine the order at which LabComponent's are drawn in.
	 * 
	 * @return The z-order of this LabComponent.
	 */
	public int getZOrder() {
		return zOrder;
	}

	/**
	 * The z-order determine the order at which LabComponent's are drawn in.
	 * 
	 * @param zOrder
	 *            The new z-order of this LabComponent.
	 */
	public void setZOrder(int zOrder) {
		this.zOrder = zOrder;
		if (parent != null)
			parent.needsChildSort = true;
	}

	/**
	 * @return Whether or not the LabComponent is visible.
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * Sets a LabComponent and its children visible.
	 * 
	 * @param visible
	 *            Specifies the visibility of the LabComponent and its children.
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;

		for (LabComponent child : children) {
			child.setVisible(visible);
		}
	}

	/**
	 * @return Either FREE_FORM or PARAGRAPH, the layout this LabComponent draws
	 *         its children in.
	 */
	public int getLayout() {
		return layout;
	}

	/**
	 * Change the layout this LabComponent draws its children in.
	 * 
	 * @param layout
	 *            Either FREE_FORM or PARAGRAPH.
	 */
	public void setLayout(int layout) {
		this.layout = layout;
	}

	/**
	 * @return Whether or not this LabComponent stretches its children to
	 *         maintain initial aspect ratios.
	 */
	public boolean canScaleChildren() {
		return scaleChildren;
	}

	/**
	 * @param scaleChildren
	 *            Whether or not this LabComponent stretches its children to
	 *            maintain initial aspect ratios.
	 */
	public void setScaleChildren(boolean scaleChildren) {
		this.scaleChildren = scaleChildren;
	}

	/**
	 * @return The absolute X coordinate on screen at which this LabComponent
	 *         was last drawn at.
	 */
	public int getLastDrawX() {
		return lastDrawX;
	}

	/**
	 * @return The absolute Y coordinate on screen at which this LabComponent
	 *         was last drawn at.
	 */
	public int getLastDrawY() {
		return lastDrawY;
	}

	/**
	 * @return The absolute width on screen at which this LabComponent was last
	 *         drawn at.
	 */
	public int getLastDrawWidth() {
		return lastDrawWidth;
	}

	/**
	 * @return The absolute height on screen at which this LabComponent was last
	 *         drawn at.
	 */
	public int getLastDrawHeight() {
		return lastDrawHeight;
	}

	/**
	 * @return Ratio between initial width and current width, used to
	 *         horizontally stretch LabComponent's and children.
	 */
	public double getWidthScaleRatio() {
		return (double) width / scaleReferenceWidth;
	}

	/**
	 * @return Ratio between initial height and current height, used to
	 *         vertically stretch LabComponent's and children.
	 */
	public double getHeightScaleRatio() {
		return (double) height / scaleReferenceHeight;
	}

	/**
	 * @param showBounds
	 *            Whether or not to draw a black rectangle to represent this
	 *            LabComponents bounds.
	 */
	public void setShowBounds(boolean showBounds) {
		this.showBounds = showBounds;
	}

	/**
	 * @return Whether or not this LabComponent is drawing a black rectangle to
	 *         represent its bounds.
	 */
	public boolean canShowBounds() {
		return showBounds;
	}

	/**
	 * Tests whether the LabComponent is covering a point on the screen or not.
	 * 
	 * @param x
	 *            X coordinate of the test point.
	 * @param y
	 *            Y coordinate of the test point.
	 * @param z
	 *            Z coordinate of the test point.
	 * @return True if this LabComponent is covering the specified point,
	 *         otherwise false.
	 */
	public boolean isPointCovered(int x, int y, int z) {
		for (LabComponent c : children) {
			if (c.contains(x, y)) {
				if (c.getZOrder() > z) {
					return true;
				}
			}
		}

		return false;
	}

	private void sortChildren() {
		Collections.sort(children, new Comparator<LabComponent>() {
			@Override
			public int compare(LabComponent c1, LabComponent c2) {
				return c1.getZOrder() - c2.getZOrder();
			}
		});
	}

	/**
	 * Tests whether the specified point is contained by the LabComponent's
	 * bounds.
	 * 
	 * @param x
	 *            X coordinate of the test point.
	 * @param y
	 *            Y coordinate of the test point.
	 * 
	 * @return True if this LabComponent is containing the specified point,
	 *         otherwise false.
	 */
	public boolean contains(int x, int y) {
		return x >= lastDrawX && x <= lastDrawX + lastDrawWidth && y >= lastDrawY && y <= lastDrawY + lastDrawHeight;
	}

	/**
	 * Update method that runs at specified FPS of the LabFrame containing this
	 * LabComponent.
	 */
	public void update() { // guarenteed to run at constant fps

	}

	/**
	 * Allows LabComponent's to access the JPanel of the LabFrame they have been
	 * added to.
	 * 
	 * @param panel
	 *            JPanel of the LabFrame this LabComponent has been added to.
	 */
	public void initJPanel(JPanel panel) {

	}

	public void draw(Graphics g, int px, int py, int w, int h, JPanel jPanel, boolean overMaxFPS) {
		if (!jPanelInitCalled) {
			initJPanel(jPanel);
			jPanelInitCalled = true;
		}

		if (!overMaxFPS) {
			update();
		}

		if (needsChildSort) {
			sortChildren();
			needsChildSort = false;
		}

		if (showBounds) {
			g.setColor(Color.black);
			g.drawRect(px, py, w, h);
		}

		if (children.size() > 0) {
			if (layout == PARAGRAPH) {
				drawParagraph(g, px, py, w, h, jPanel, overMaxFPS);
			} else if (layout == FREE_FORM) {
				drawFreeForm(g, px, py, w, h, jPanel, overMaxFPS);
			}
		} else {
			draw(px, py, w, h, g);
		}

		lastDrawX = px;
		lastDrawY = py;
		lastDrawWidth = w;
		lastDrawHeight = h;

	}

	private void drawParagraph(Graphics g, int px, int py, int w, int h, JPanel panel, boolean overMaxFPS) {
		int maxHeight = Integer.MIN_VALUE;
		int x = 0, y = 0, swidth, sheight, sx, sy;

		boolean thisDrawn = false;

		for (LabComponent c : children) {

			if (c.getZOrder() >= 0 && !thisDrawn) {
				thisDrawn = true;
				draw(px, py, w, h, g);
			}

			if (x + c.getWidth() + c.getOffsetX() > (scaleChildren ? width : w)) {
				if (maxHeight == Integer.MIN_VALUE) {
					maxHeight = 0;
				}

				y += maxHeight + c.getOffsetY();
				maxHeight = Integer.MIN_VALUE;
				x = 0;
			}

			if (scaleChildren) {
				swidth = (int) ((double) c.getWidth() / width * w * getWidthScaleRatio());
				sheight = (int) ((double) c.getHeight() / height * h * getHeightScaleRatio());
				sx = (int) ((double) (x + c.getOffsetX()) / width * w) + px;
				sy = (int) ((double) (y + c.getOffsetY()) / height * h) + py;
			} else {
				swidth = c.getWidth();
				sheight = c.getHeight();
				sx = x + c.getOffsetX() + px;
				sy = y + c.getOffsetY() + py;
			}

			if (c.isVisible()) {
				c.draw(g, sx, sy, swidth, sheight, panel, overMaxFPS);
			}

			if (!overMaxFPS) {
				c.update();
			}

			x += c.getWidth() + c.getOffsetX();

			if (c.getHeight() + c.getOffsetY() > maxHeight) {
				maxHeight = c.getHeight() + c.getOffsetY();
			}

		}

		if (!thisDrawn) {
			draw(px, py, w, h, g);
		}
	}

	private void drawFreeForm(Graphics g, int px, int py, int w, int h, JPanel panel, boolean overMaxFPS) {
		int swidth, sheight, sx, sy;

		boolean thisDrawn = false;

		for (LabComponent c : children) {

			if (c.getZOrder() >= 0 && !thisDrawn) {
				thisDrawn = true;
				draw(px, py, w, h, g);
			}

			if (scaleChildren) {
				swidth = (int) ((double) c.getWidth() / width * w * getWidthScaleRatio());
				sheight = (int) ((double) c.getHeight() / height * h * getHeightScaleRatio());
				sx = (int) ((double) (c.getOffsetX()) / width * w) + px;
				sy = (int) ((double) (c.getOffsetY()) / height * h) + py;
			} else {
				swidth = c.getWidth();
				sheight = c.getHeight();
				sx = c.getOffsetX() + px;
				sy = c.getOffsetY() + py;
			}

			if (c.isVisible()) {
				c.draw(g, sx, sy, swidth, sheight, panel, overMaxFPS);
			}

			if (!overMaxFPS) {
				c.update();
			}

		}

		if (!thisDrawn) {
			draw(px, py, w, h, g);
		}
	}

	/**
	 * Little utility method to draw strings centered horizontally at a point.
	 * 
	 * @param g
	 *            Graphics object to draw the String on.
	 * @param str
	 *            String to drawn.
	 * @param x
	 *            X coordinate of where to draw the String.
	 * @param y
	 *            Y coordinate of where to draw the String.
	 */
	public static void drawCenteredString(Graphics g, String str, int x, int y) {
		FontMetrics metrics = g.getFontMetrics();
		int width = metrics.stringWidth(str);

		g.drawString(str, x - width / 2, y);

	}

	private static LabComponent getRoot(LabComponent p) {
		if (p.root != null) {
			return p.root;
		}

		if (p.parent != null) {
			return getRoot(p.parent);
		} else {
			return p;
		}
	}

}
