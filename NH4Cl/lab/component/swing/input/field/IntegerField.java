package lab.component.swing.input.field;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;

import lab.component.swing.Label;

public class IntegerField extends TextField {

	private int min;
	private int max;
	private Label errorLabel;
	
	public IntegerField(int width, int min, int max, int value) {
		super(width, Integer.toString(value));
		
		this.min = min;
		this.max = max;
		
		errorLabel = new Label(120, 20);
		errorLabel.setOffset(width, 0);
		errorLabel.setColor(Color.red);
		errorLabel.setFontSize(9);
		
		setValue(min);
		
		setScaleChildren(false);
		addChild(errorLabel);
		
	}
	
	public IntegerField(int width, int min, int max) {
		this(width, min, max, 0);
	}
	
	@Override
	public void setWidth(int width) {
		super.setWidth(width);
		errorLabel.setOffsetX(width);
	}
	
	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}
	
	@Override
	public Integer getValue() {
		try {
			return clamp(Integer.parseInt(getText()));
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	private int clamp(int v) {
		return Math.min(Math.max(v, min), max);
	}
	
	@Override
	public void setValue(Object obj) {
		setText(obj.toString());
	}
	
	@Override
	public boolean hasInput() {
		return errorLabel.getText().equals("") && super.hasInput();
	}
	
	private void check() {
		check(getText());
	}
	
	private void check(String text) {
		if (text.isEmpty()) {
			return;
		}
		
		int value;
		
		try {
			value = Integer.parseInt(text);
		} catch (NumberFormatException ex) {
			errorLabel.setText("<html><p>Value must be a number.</p></html>");
			return;
		}
		
		if (value > max || value < min) {
			errorLabel.setText("<html><p>Value must be between " + min + " and " + max + ".</p></html>");
			return;
		}
		
		errorLabel.setText("");
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		
		check();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		super.keyTyped(e);
		
		check(getText() + e.getKeyChar());
	}
	
	@Override
	public void focusLost(FocusEvent e) {
		super.focusLost(e);
		check();
	}

}
