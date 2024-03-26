package lab.component.swing.input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class Dropdown<E> extends InputComponent implements ActionListener {

	private final JComboBox<E> dropdown;

	@SafeVarargs
	public Dropdown(int width, int height, E... args) {
		super(width, height);

		dropdown = new JComboBox<E>(args);

		dropdown.addActionListener(this);
	}

	public void setItems(List<E> items) {
		getModel().removeAllElements();

		for (E item : items) {
			getModel().addElement(item);
		}
	}
	
	public List<E> getItems() {
		List<E> list = new ArrayList<E>();
		
		for (int i = 0; i < getModel().getSize(); i++) {
			list.add(getModel().getElementAt(i));
		}
		
		return list;
	}

	public DefaultComboBoxModel<E> getModel() {
		return (DefaultComboBoxModel<E>) dropdown.getModel();
	}

	@Override
	public JComboBox<E> getJComponent() {
		return dropdown;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E getValue() {
		return (E) dropdown.getSelectedItem();
	}

	@Override
	public void setValue(Object v) {
		dropdown.setSelectedItem(v);
	}

	public void onSelectItem(E item) {

	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		onSelectItem((E) ((JComboBox<E>) e.getSource()).getSelectedItem());
	}

}
