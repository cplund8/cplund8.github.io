package lab.component.swing;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import lab.component.swing.input.FontStyle;

public class Label extends SwingComponent {

	private final JLabel label;
	private boolean wrap = false;
	
	public Label(int width, int height, String text) {
		super(width, height);
		
		label = new JLabel(text);
		
	}
	
	public Label(int width, int height) {
		this(width, height, "");
	}
	
	@Override
	public JLabel getJComponent() {
		return label;
	}
	
	public void setText(String text) {
		if (wrap) {
			label.setText("<html>" + text + "</html>");
		} else {
			label.setText(text);
		}
	}
	
	public String getText() {
		return label.getText().replaceFirst("<html>", "").replace("</html>", "");
	}
	
	public int getTextWidth() {
		return label.getFontMetrics(label.getFont()).stringWidth(label.getText());
	}
	
	public void setFontSize(float size) {
		label.setFont(label.getFont().deriveFont(size));
	}
	
	public int getFontSize() {
		return label.getFont().getSize();
	}
	
	public void setFontStyle(FontStyle style) {
		label.setFont(label.getFont().deriveFont(style.ordinal()));
	}
	
	public FontStyle getFontStyle() {
		return FontStyle.values()[label.getFont().getStyle()];
	}
	
	public void setFont(Font font) {
		label.setFont(font);
	}
	
	public Font getFont() {
		return label.getFont();
	}
	
	public void setColor(Color color) {
		label.setForeground(color);
	}
	
	public Color getColor() {
		return label.getForeground();
	}
	
	public void setWrap(boolean wrap) {
		this.wrap = wrap;
		setText(label.getText());
	}
	
	public boolean canWrap() {
		return wrap;
	}
	
}
