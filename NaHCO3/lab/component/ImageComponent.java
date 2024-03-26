package lab.component;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * @author James Tanner
 * @version 1.0
 * @since 1.0
 */
public class ImageComponent extends LabComponent {

	private final BufferedImage img;

	/**
	 * Creates an ImageComponent with custom width and height values.
	 * 
	 * @param width Specifies the width of the image.
	 * @param height Specifies the height of the image.
	 * @param fileName Specifies the name of the image resource file.
	 */
	public ImageComponent(int width, int height, String fileName) {
		super(width, height);
		img = readImage(fileName);
	}

	/**
	 * Creates an ImageComponent with the width and height of the image file.
	 * 
	 * @param fileName Specifies the name of the image resource file.
	 */
	public ImageComponent(String fileName) {
		super(0, 0);
		img = readImage(fileName);
		if (img != null) {
			setWidth(img.getWidth());
			setHeight(img.getHeight());
		}
	}
	
	private static BufferedImage readImage(String fileName) {
		InputStream is = null;
		try {
			is = new BufferedInputStream(ImageComponent.class.getResourceAsStream(fileName));
			return ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * @return A BufferedImage associated with this image component.
	 */
	public BufferedImage getImage() {
		return img;
	}

	@Override
	public void draw(int x, int y, int width, int height, Graphics g) {
		g.drawImage(img, x, y, width, height, null);
	}

}
