package lab.component.swing.input.list;

import lab.component.swing.input.field.TextField;

public class MutableStringList extends SingleFieldMutableList<String> {

	public MutableStringList(int width, int height) {
		super(width, height);
	}

	@Override
	public TextField createEntryField() {
		return new TextField(0);
	}

}
