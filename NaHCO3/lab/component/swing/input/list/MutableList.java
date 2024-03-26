package lab.component.swing.input.list;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;

import lab.component.swing.input.Button;
import lab.component.swing.input.InputComponent;

public abstract class MutableList<E> extends InputComponent {

	private static final JPanel dummyComponent = new JPanel();

	static {
		dummyComponent.setOpaque(false);
	}
	
	private final MultiSelectionList<E> itemList;
	private final Button addButton;
	private final JPopupMenu deleteMenu = new JPopupMenu("Delete");
	
	public MutableList(int width, int height) {
		super(width, height);

		itemList = new MultiSelectionList<E>(width, height - 40);
		itemList.getJList().addKeyListener(new ListKeyListener());
		itemList.getJList().addMouseListener(new ListMouseListener());
		
		addButton = new Button(60, 20, "Add") {
			@Override
			public void doSomething() {
				E e = getEntry();
				itemList.add(e);
				clearEntry();
				addButton.setEnabled(false);
				
				onAddValue(e);
				
				JScrollBar scrollBar = itemList.getJComponent().getVerticalScrollBar();
				scrollBar.setValue(scrollBar.getMaximum());
				
			}
		};
		
		addButton.setEnabled(false);

		
		addChild(itemList, addButton);
		
		
		JMenuItem deleteMenuItem = new JMenuItem("Delete");
		
		deleteMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				for (int i : itemList.getSelectedIndices()) {
					onRemoveValue(itemList.getItemAt(i));
					
					itemList.remove(i);
				}
			}
		});
		
		deleteMenu.add(deleteMenuItem);
	}

	public List<E> getItems() {
		return itemList.getItems();
	}
	
	public int size() {
		return itemList.size();
	}

	public void setItems(List<E> items) {
		itemList.setItems(items);
	}

	public boolean contains(E elem) {
		return itemList.contains(elem);
	}

	public E getItemAt(int index) {
		return itemList.getItemAt(index);
	}

	public void setItemAt(E element, int index) {
		itemList.setItemAt(element, index);
	}

	public void add(E element) {
		itemList.add(element);
	}

	public boolean remove(E e) {
		return itemList.remove(e);
	}

	public E set(int index, E element) {
		return itemList.set(index, element);
	}

	public void add(int index, E element) {
		itemList.add(index, element);
	}

	public E remove(int index) {
		return itemList.remove(index);
	}

	public void clear() {
		itemList.clear();
	}

	public int[] getSelectedIndices() {
		return itemList.getSelectedIndices();
	}
	
	public List<E> getSelectedValues() {
		return itemList.getValue();
	}

	@Override
	public List<E> getValue() {
		return itemList.getItems();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setValue(Object v) {
		itemList.setItems((List<E>) v);
	}

	public Button getAddButton() {
		return addButton;
	}

	public void onAddValue(E e) {
		
	}
	
	public void onRemoveValue(E e) {
		
	}
	
	public abstract E getEntry();
	public abstract void clearEntry();
	public abstract boolean entryHasFocus();
	
	@Override
	public void update() {
		addButton.setEnabled(getEntry() != null);
	}
	
	@Override
	public Component getJComponent() {
		return dummyComponent;
	}
	
	@Override
	public boolean hasFocus() {
		return itemList.hasFocus() || addButton.hasFocus() || entryHasFocus();
	}
	
	protected class EntryFieldKeyListener extends KeyAdapter {
		public EntryFieldKeyListener() {
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER && getAddButton().isEnabled()) {
				getAddButton().doSomething();
			}
		}
	}
	
	private class ListKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent ev) {
			if (ev.getKeyCode() == KeyEvent.VK_DELETE) {
				for (E e : itemList.getSelectedValues()) {
					onRemoveValue(e);
					
					itemList.remove(e);
				}
			}
		}
	}
	
	private class ListMouseListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			if (e.isPopupTrigger()) {
				int row = itemList.getJList().locationToIndex(e.getPoint());
				
				int[] selected = Arrays.copyOf(itemList.getSelectedIndices(), itemList.getSelectedIndices().length + 1);
				selected[selected.length - 1] = row;
				
				itemList.setSelectedIndices(selected);
				
				deleteMenu.show(e.getComponent(), e.getX(), e.getY());
			}
		}
		
	}

}
