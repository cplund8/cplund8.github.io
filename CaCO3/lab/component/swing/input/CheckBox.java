package lab.component.swing.input;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;

public class CheckBox extends InputComponent implements ItemListener {

	private final JCheckBox checkBox;
	
	public CheckBox(int width, int height, String text) {
		super(width, height);
		
		checkBox = new JCheckBox();
		checkBox.setText(text);
		checkBox.setBackground(Color.white);
		
		checkBox.addItemListener(this);
		
	}
	
	public CheckBox(int width, int height) {
		this(width, height, "");
	}
	
	public boolean isSelected() {
		return checkBox.isSelected();
	}
	
	public void setSelected(boolean selected) {
		checkBox.setSelected(selected);
	}

	@Override
	public JCheckBox getJComponent() {
		return checkBox;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			onSelect();
		} else {
			onDeselect();
		}
	}
	
	public void onSelect() {
		
	}
	
	public void onDeselect() {
		
	}
	
	@Override
	public Boolean getValue() {
		return checkBox.isSelected();
	}

	@Override
	public void setValue(Object v) {
		checkBox.setSelected((Boolean) v);
	}
	
}
