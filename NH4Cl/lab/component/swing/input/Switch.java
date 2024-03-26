package lab.component.swing.input;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Switch extends InputComponent implements ChangeListener {

	private final JCheckBox switchField;
	
	public Switch(int width, int height) {
		
		super(width, height);
		
		switchField = new JCheckBox(new ImageIcon("src/resources/offSwitch.png", "Off"));
		switchField.setSelectedIcon(new ImageIcon("src/resources/onSwitch.png", "On"));
		
		switchField.addChangeListener(this);
	}

	public void onAction() {
		
	}

	public void offAction() {
		
	}
	
	@Override
	public JCheckBox getJComponent() {
		return switchField;
	}

	
	@Override
	public void stateChanged(ChangeEvent e) {
		
		JCheckBox source = (JCheckBox) e.getSource();
		
		if (source.isSelected()) {
			
			onAction();
			
		} else {
			
			offAction();
			
		}
	}
	
	@Override
	public Object getValue() {
		return switchField.isSelected();
	}
	
	@Override
	public void setValue(Object v) {
		switchField.setSelected((Boolean) v);
	}

	
	
}
