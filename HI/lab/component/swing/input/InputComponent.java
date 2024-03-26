package lab.component.swing.input;

import lab.component.swing.SwingComponent;

public abstract class InputComponent extends SwingComponent {

	public InputComponent(int width, int height) {
		super(width, height);
	}

	public abstract Object getValue();
	public abstract void setValue(Object v);

}
