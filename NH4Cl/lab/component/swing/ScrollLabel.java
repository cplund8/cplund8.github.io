package lab.component.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import lab.component.swing.input.FontStyle;

/**
 * @author James Tanner
 * @version 1.0
 * @since 1.0
 */
public class ScrollLabel extends SwingComponent {

	/**
	 * Constant for always using a horizontal scroll bar.
	 */
	public static final int HORIZONTAL_SCROLLBAR_ALWAYS = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
	/**
	 * Constant for never using a horizontal scroll bar.
	 */
	public static final int HORIZONTAL_SCROLLBAR_NEVER = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
	/**
	 * Constant for using a horizontal scroll bar if the content continues past the max width of the component.
	 */
	public static final int HORIZONTAL_SCROLLBAR_AS_NEEDED = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;

	private final JLabel label;
	private final JScrollPane scrollPane;

	/**
	 * Creates a ScrollLabel component. Used for large amounts of text that require a scroll bar.
	 * 
	 * @param width Width of the component.
	 * @param height Height of the component.
	 * @param filePath Name of the text file being read from.
	 */
	public ScrollLabel(int width, int height, String filePath) {
		super(width, height);
		label = new JLabel(readFile(filePath));
		scrollPane = new JScrollPane(label);
	}

	private static String readFile(String path) {
		BufferedReader bufferedReader;
		try {
			InputStream inputStream = ScrollLabel.class.getResourceAsStream(path);
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			bufferedReader = new BufferedReader(inputStreamReader);
			StringBuilder stringBuilder = new StringBuilder();
			String line = bufferedReader.readLine();
			while (line != null) {
				stringBuilder.append(line);
				stringBuilder.append(System.lineSeparator());
				line = bufferedReader.readLine();
			}
			return stringBuilder.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Component getJComponent() {
		return scrollPane;
	}

	/**
	 * @param text Text to display in the ScrollLabel.
	 */
	public void setText(String text) {
		label.setText(text);
	}

	/**
	 * @param policy Specifies the horizontal scroll bar policy of the ScrollLabel.
	 */
	public void setHoriztonalScrollBarPolicy(int policy) {
		scrollPane.setHorizontalScrollBarPolicy(policy);
	}

	/**
	 * @param size Specifies the size of the Font used in the ScrollLabel.
	 */
	public void setFontSize(float size) {
		label.setFont(label.getFont().deriveFont(size));
	}

	/**
	 * @return The size of the Font used in the ScrollLabel.
	 */
	public int getFontSize() {
		return label.getFont().getSize();
	}

	/**
	 * @param style Specifies the FontStyle used in the ScrollLabel.
	 */
	public void setFontStyle(FontStyle style) {
		label.setFont(label.getFont().deriveFont(style.ordinal()));
	}

	/**
	 * @return The FontStyle used in the ScrollLabel.
	 */
	public FontStyle getFontStyle() {
		return FontStyle.values()[label.getFont().getStyle()];
	}

	/**
	 * @param font Specifies the Font used in the ScrollLabel.
	 */
	public void setFont(Font font) {
		label.setFont(font);
	}

	/**
	 * @return The Font used in the ScrollLabel.
	 */
	public Font getFont() {
		return label.getFont();
	}

	/**
	 * @param color Specifies the Color used in the ScrollLabel.
	 */
	public void setColor(Color color) {
		label.setForeground(color);
	}

	/**
	 * @return The Color used in the ScrollLabel.
	 */
	public Color getColor() {
		return label.getForeground();
	}

}
