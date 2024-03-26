package lab.component.swing.input.slider;

import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.JLabel;

public class DoubleSlider extends Slider {
	
	private double min;
	private double max;
	private double increment;

	public DoubleSlider(int size, double min, double max, double increment, int orientation) {
		super(size, orientation);

		this.min = min;
		this.max = max;
		this.increment = increment;
		
		slider.setMinimum(0);
		setMaxSliderValue();
		slider.setValue(0);
		
	}

	public void setValue(Double v) {
		v = Math.max(min, Math.min(max, v));
		v -= min;
		slider.setValue((int) (v / increment));
	}
	
	public double getMin() {
		return min;
	}

	public double getMax() {
		return max;
	}

	public double getIncrement() {
		return increment;
	}

	public void setIncrement(double increment) {
		this.increment = increment;
		setMaxSliderValue();
	}

	public void setMin(double min) {
		this.min = min;
		setMaxSliderValue();
	}

	public void setMax(double max) {
		this.max = max;
		setMaxSliderValue();
	}
	
	private void setMaxSliderValue() {
		slider.setMaximum((int) ((max - min) / increment));
	}

	@Override
	public Double getValue() {
		return slider.getValue() * increment + min;
	}

	@Override
	public void setValue(Object v) {
		setValue((Double) v);
	}

	@SuppressWarnings("unchecked")
	public void addValueLabel(double value, String label) {
		Dictionary<Integer, JLabel> labels = slider.getLabelTable();

		if (labels == null) {
			labels = new Hashtable<Integer, JLabel>();
		}

		labels.put(new Integer((int) ((value - min) / increment)), new JLabel(label));

		slider.setLabelTable(labels);

		slider.setPaintLabels(true);
	}

}
