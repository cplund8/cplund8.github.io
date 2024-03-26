package lab.component.swing.input.slider;

import lab.component.swing.Label;
import lab.util.SigFig;

public class LabeledDoubleSlider extends DoubleSlider {

	private final Label label;
	private int sigfigs;
	
	public LabeledDoubleSlider(int size, double min, double max, double increment, int sigfigs, int orientation) {
		super(size, min, max, increment, orientation);
		
		this.sigfigs = sigfigs;
		
		setLayout(FREE_FORM);
		
		label = new Label(size / 4, 20);

		if (orientation == Slider.HORIZONTAL) {
			label.setOffset(size, 0);
		} else {
			label.setOffset(20, 0);
		}
		
		addChild(label);
		
	}
	
	public void setSigFigs(int sigfigs) {
		this.sigfigs = sigfigs;
	}
	
	public int getSigFigs() {
		return sigfigs;
	}
	
	public Label getLabel() {
		return label;
	}
	
	public void onChange() {
		
	}
	
	@Override
	public void update() {
		label.setText(SigFig.sigfigalize(getValue(), sigfigs));
	}

}
