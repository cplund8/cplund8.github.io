package lab.component.swing.input.field;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;

import lab.component.swing.Label;
import lab.util.SigFig;

public class DoubleField extends TextField {

	private double min;
	private double max;
	private Label errorLabel;
	private int sigfigs;
	private int scientificNotationMinPower;
	
	public DoubleField(int width, double min, double max, int sigfigs, int scientificNotationMinPower, double value) {
		super(width, Double.toString(value));
		
		this.min = min;
		this.max = max;
		this.sigfigs = sigfigs;
		this.scientificNotationMinPower = scientificNotationMinPower;
		
		errorLabel = new Label(120, 20);
		errorLabel.setOffset(width, 0);
		errorLabel.setColor(Color.red);
		errorLabel.setFontSize(9);
		
		setValue(min);
		
		setScaleChildren(false);
		addChild(errorLabel);
		
	}
	
	public DoubleField(int width, double min, double max, int sigfigs, int scientificNotationMinPower) {
		this(width, min, max, sigfigs, scientificNotationMinPower, 0);
	}
	
	public DoubleField(int width, double min, double max, int sigfigs) {
		this(width, min, max, sigfigs, 1);
	}
	
	@Override
	public void setWidth(int width) {
		super.setWidth(width);
		errorLabel.setOffsetX(width);
	}
	
	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}
	
	public int getSigFigs() {
		return sigfigs;
	}
	
	public void setSigFigs(int sigfigs) {
		this.sigfigs = sigfigs;
	}
	
	public int getScientificNotationMinPower() {
		return scientificNotationMinPower;
	}

	public void setScientificNotationMinPower(int scientificNotationMinPower) {
		this.scientificNotationMinPower = scientificNotationMinPower;
	}

	@Override
	public Double getValue() {
		try {
			return clamp(Double.parseDouble(getText()));
		} catch (NumberFormatException e) {
			return 0.0;
		}
	}
	
	private double clamp(double v) {
		return Math.min(Math.max(v, min), max);
	}
	
	@Override
	public void setValue(Object obj) {
		if (sigfigs > 0) {
			setText(SigFig.sigfigalize((Double) obj, sigfigs, scientificNotationMinPower));
		} else {
			setText(obj.toString());
		}
	}
	
	@Override
	public boolean hasInput() {
		return errorLabel.getText().equals("") && super.hasInput();
	}
	
	private void check(boolean updateFormat) {
		check(getText(), updateFormat);
	}
	
	private void check(String text, boolean updateFormat) {
		if (text.isEmpty()) {
			return;
		}
		
		double value;
		
		try {
			value = Double.parseDouble(text);
		} catch (NumberFormatException ex) {
			errorLabel.setText("<html><p>Value must be a number.</p></html>");
			return;
		}
		
		if (value > max || value < min) {
			errorLabel.setText("<html><p>Value must be between " + min + " and " + max + ".</p></html>");
			return;
		}
		
		errorLabel.setText("");
		
		if (sigfigs > 0 && updateFormat) {
			setText(SigFig.sigfigalize(value, sigfigs, scientificNotationMinPower));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		
		check(true);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		super.keyTyped(e);
		
		check(getText() + e.getKeyChar(), false);
	}

	@Override
	public void focusLost(FocusEvent e) {
		super.focusLost(e);
		check(true);
	}

}
