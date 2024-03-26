package lab.component.swing.input.slider;

import java.awt.Color;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import lab.component.swing.input.InputComponent;

public abstract class Slider extends InputComponent implements ChangeListener {

	public static final int HORIZONTAL = 0;
	public static final int VERTICAL = 1;

	protected final JSlider slider;
	
	public Slider(int size, int orientation) {
		super(orientation == HORIZONTAL ? size : 20, orientation == VERTICAL ? size : 20);
		
		slider = new JSlider();
		slider.setOrientation(orientation);
		slider.setBackground(Color.white);
		slider.addChangeListener(this);
		
	}
	
	public int getOrientation() {
		return slider.getOrientation();
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		onChange();
	}

	public void onChange() {
		
	}
	
	@Override
	public JSlider getJComponent() {
		return slider;
	}

}
