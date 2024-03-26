package lab.component.swing.input.slider;

import lab.component.swing.Label;

public class LabeledIntegerSlider extends IntegerSlider {

	private final Label label;

	public LabeledIntegerSlider(int size, int min, int max, int orientation) {
		super(size, min, max, orientation);

		setLayout(FREE_FORM);

		label = new Label(size / 4, 20);

		if (orientation == Slider.HORIZONTAL) {
			label.setOffset(size, 0);
		} else {
			label.setOffset(20, 0);
		}

		addChild(label);

	}

	public Label getLabel() {
		return label;
	}

	public void onChange() {

	}

	@Override
	public void update() {
		label.setText(getValue().toString());
	}

}
