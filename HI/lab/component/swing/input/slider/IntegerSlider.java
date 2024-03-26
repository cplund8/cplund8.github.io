package lab.component.swing.input.slider;

import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.JLabel;

public class IntegerSlider extends Slider {

	private int min;
	private int max;

	public IntegerSlider(int size, int min, int max, int orientation) {
		super(size, orientation);

		slider.setValue(0);
		
		this.min = min;
		this.max = max;

	}

	public void setValue(int v) {
		v = Math.max(min, Math.min(max, v));
		slider.setValue(v);
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public void setMax(int max) {
		this.max = max;
	}

	@Override
	public Integer getValue() {
		return slider.getValue();
	}

	public void setValue(Object v) {
		slider.setValue((Integer) v);
	}
	
	@SuppressWarnings("unchecked")
	public void addValueLabel(int value, String label) {
		Dictionary<Integer, JLabel> labels = slider.getLabelTable();

		if (labels == null) {
			labels = new Hashtable<Integer, JLabel>();
		}

		labels.put(value, new JLabel(label));

		slider.setLabelTable(labels);

		slider.setPaintLabels(true);
	}

}
