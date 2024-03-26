package lab.util;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import lab.component.LabComponent;

public class ClickableArea extends MouseAdapter {

	private static ClickableArea currentHover = null;
	private static ClickableArea currentDrag = null;

	private int x, y, width, height;
	private final LabComponent component;
	private JPanel panel;
	private Point mousePosition = null;
	private Point clickPosition = null;
	private Point relativeClick = null;
	private boolean hasClick = false;
	private boolean hasHover = false;
	private boolean scale = false;
	private boolean enabled = true;
	private boolean initialized = false;
	private int hoverCursorIcon = Cursor.HAND_CURSOR;
	private int dragCursorIcon = Cursor.HAND_CURSOR;

	public ClickableArea(LabComponent component, int x, int y, int width, int height) {
		this.component = component;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public ClickableArea(LabComponent component) {
		this(component, 0, 0, 0, 0);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public int getHoverCursorIcon() {
		return hoverCursorIcon;
	}

	public void setHoverCursorIcon(int hoverCursorIcon) {
		this.hoverCursorIcon = hoverCursorIcon;
	}

	public int getDragCursorIcon() {
		return dragCursorIcon;
	}

	public void setDragCursorIcon(int dragCursorIcon) {
		this.dragCursorIcon = dragCursorIcon;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;

		if (!enabled) {
			clickPosition = null;
			hasClick = false;
			relativeClick = null;
		}

	}

	public Point getMousePosition() {
		return mousePosition;
	}

	public Point getClickPosition() {
		return clickPosition;
	}

	public boolean hasClick() {
		return hasClick;
	}

	public boolean hasHover() {
		return hasHover;
	}

	public Point getClickRelativeToPosition() {
		return relativeClick;
	}

	public Point getDragDelta() {
		return new Point(mousePosition.x - clickPosition.x, mousePosition.y - clickPosition.y);
	}

	public LabComponent getComponent() {
		return component;
	}

	public boolean canScale() {
		return scale;
	}

	public void setScale(boolean scale) {
		this.scale = scale;
	}

	public void initializeMouseListeners(JPanel panel) {
		if (initialized) {
			return;
		}

		panel.addMouseListener(this);
		panel.addMouseMotionListener(this);
		this.panel = panel;

		initialized = true;
	}

	private void check(int sx, int sy, int sw, int sh, boolean raw) {
		if (!enabled) {
			return;
		}

		if (currentDrag != null) {
			if (currentDrag != this) {
				return;
			}
		}

		if (clickPosition != null && !hasClick) {

			if (raw ? isInBounds2(clickPosition, sx, sy, sw, sh) : isInBounds1(clickPosition, sx, sy, sw, sh)) {
				hasClick = true;
				relativeClick = new Point(sx - clickPosition.x, sy - clickPosition.y);
			} else {
				clickPosition = null;
			}

		}

		if (mousePosition != null) {
			hasHover = raw ? isInBounds2(mousePosition, sx, sy, sw, sh) : isInBounds1(mousePosition, sx, sy, sw, sh);
		}

		if (hasHover) {
			if (currentHover != null) {
				if (currentHover != this) {
					if (currentHover.component.getZOrder() < component.getZOrder()) {
						currentHover.hasHover = false;
						currentHover = this;
					} else {
						hasHover = false;
					}
				}
			} else {
				currentHover = this;
			}
		} else {
			if (currentHover != null) {
				if (currentHover == this) {
					currentHover = null;
					setDefaultCursorIcon();
				}
			}
		}

		if (hasClick()) {
			if (currentDrag != null) {
				if (currentDrag != this) {
					if (currentDrag.component.getZOrder() < component.getZOrder()) {
						currentDrag.mouseReleased(null);
						currentDrag = this;
					} else {
						mouseReleased(null);
					}
				}
			} else {
				currentDrag = this;
			}
		} else {
			if (currentDrag != null) {
				if (currentDrag == this) {
					currentDrag = null;
					setDefaultCursorIcon();
				}
			}
		}

		if (currentDrag == this) {
			setDragCursorIcon();
		} else if (currentHover == this) {
			setHoverCursorIcon();
		} else if (currentDrag == null && currentHover == null) {
			setDefaultCursorIcon();
		}

	}

	public void check(int sx, int sy, int sw, int sh) {
		check(sx, sy, sw, sh, false);
	}

	private boolean isInBounds1(Point p, int sx, int sy, int sw, int sh) {
		double bx, by, bw, bh;

		if (sw != component.getWidth() && sh != component.getHeight() && scale) {

			bx = ((double) x / component.getWidth()) * sw + sx;
			by = ((double) y / component.getHeight()) * sh + sy;

			bw = (double) width / component.getWidth() * sw;
			bh = (double) height / component.getHeight() * sh;

		} else {
			bx = x + sx;
			by = y + sy;
			bw = width;
			bh = height;
		}

		return isInBounds2(p, bx, by, bw, bh);
	}

	private boolean isInBounds2(Point p, double sx, double sy, double sw, double sh) {
		return p.x > sx && p.x < sx + sw && p.y > sy && p.y < sy + sh;
	}

	public void checkRaw(int sx, int sy, int sw, int sh) {
		check(sx, sy, sw, sh, true);
	}

	private void setDefaultCursorIcon() {
		panel.setCursor(Cursor.getDefaultCursor());
	}

	private void setHoverCursorIcon() {
		panel.setCursor(new Cursor(hoverCursorIcon));
	}

	private void setDragCursorIcon() {
		panel.setCursor(new Cursor(dragCursorIcon));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (!hasClick) {
			clickPosition = e.getPoint();
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		hasClick = false;
		clickPosition = null;
		relativeClick = null;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (clickPosition != null) {
			mousePosition = e.getPoint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mousePosition = e.getPoint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}