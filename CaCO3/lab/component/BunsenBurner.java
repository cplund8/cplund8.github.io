package lab.component;

import java.awt.Color;
import java.awt.Graphics;

import lab.component.fx.Flame;

/**
 * @author Benjamin Walls
 * @version 1.0
 * @since 1.0
 */
public class BunsenBurner extends LabComponent {

	private final Flame flame;
	
	/**
	 * Creates a BunsenBurner LabComponent with the given parameters.
	 * 
	 * @param width Specifies the width of the BunsenBurner.
	 * @param height Specifies the height of the BunsenBurner.
	 */
	public BunsenBurner(int width, int height) {
		super(width, height);
		
		flame = new Flame(width, height / 7, width / 2, height / 20);
		
		flame.setIntensity(150);
		flame.setNoiseIncrement(20);
		
		setOffsetX(30);
		setOffsetY(30);
		
		addChild(flame);
	}
	
	@Override
	public void setWidth(int width) {
		super.setWidth(width);
		flame.setWidth(width);
	}
	
	@Override
	public void setHeight(int height) {
		super.setHeight(height);
		flame.setHeight(height / 7);
	}
	
	/**
	 * @return The Flame object used by the BunsenBurner.
	 */
	public Flame getFlame() {
		return flame;
	}

	@Override
	public void draw(int x, int y, int width, int height, Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(x, y + height / 7 - 1, width, height - height / 7 + 1);
		g.setColor(Color.darkGray);
		g.fillRect(x - width / 5, y + height - height / 10, width + width / 5 * 2, height / 10);
	}

}
