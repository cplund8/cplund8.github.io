package lab.component.swing.input;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

/**
 * @author James Tanner
 * @version 1.0
 * @since 1.0
 */
public class MenuComponent extends InputComponent {

	private JMenuBar menuBar;
	private Map<String, JMenu> menu;
	private Map<String, JMenuItem> menuItem;

	/**
	 * Creates a MenuComponent for a file menu.
	 * 
	 * @param width Specifies the width of the MenuComponent. Usually the width of the frame.
	 * @param height Specifies the height of the MenuComponent.
	 */
	public MenuComponent(int width, int height) {
		super(width, height);
		menuBar = new JMenuBar();
		menu = new HashMap<String, JMenu>();
		menuItem = new HashMap<String, JMenuItem>();
	}

	/**
	 * Creates a JMenu, and adds it to the MenuComponent.
	 * 
	 * @param name Name of the JMenu.
	 */
	public void addMenu(String name) {
		JMenu newMenu = new JMenu(name);
		menuBar.add(newMenu);
		menu.put(name, newMenu);
	}

	/**
	 * Creates a JMenuItem, and adds it to the specified JMenu.
	 * 
	 * @param text Text on the JMenuItem.
	 * @param menuName Name of the JMenu to add the JMenuItem to.
	 * @param e Detects clicks on the JMenuItem.
	 */
	public void addMenuItem(String text, String menuName, ActionListener e) {
		JMenuItem newMenuItem = new JMenuItem(text);
		newMenuItem.addActionListener(e);
		menu.get(menuName).add(newMenuItem);
		menuItem.put(text, newMenuItem);
	}

	/**
	 * Creates a JRadioButtonMenuItem, and adds it to the specified JMenu.
	 * 
	 * @param text Text on the JRadioButtonMenuItem.
	 * @param menuName Specifies a JMenu to add the JRadioButtonMenuItem to.
	 * @param selected Initial selected value.
	 * @param e Detects clicks on the JRadioButtonMenuItem.
	 */
	public void addRadioButtonMenuItem(String text, String menuName, boolean selected, ActionListener e) {
		JRadioButtonMenuItem newRadioButtonMenuItem = new JRadioButtonMenuItem(text);
		newRadioButtonMenuItem.addActionListener(e);
		newRadioButtonMenuItem.setSelected(selected);
		menu.get(menuName).add(newRadioButtonMenuItem);
		menuItem.put(text, newRadioButtonMenuItem);
	}

	/**
	 * @param menuItemName Specifies the JMenuItem that is changing.
	 * @param text Specifies the text on the JMenuItem.
	 */
	public void setMenuItemText(String menuItemName, String text) {
		menuItem.get(menuItemName).setText(text);
	}

	/**
	 * @param menuItemName Specifies a JMenuItem.
	 * @return The JMenuItem's text.
	 */
	public String getMenuItemText(String menuItemName) {
		return menuItem.get(menuItemName).getText();
	}

	/**
	 * @param Specifies a JRadioButtonMenuItem.
	 * @param selected Specifies whether the JRadioButtonMenuItem is selected or not.
	 */
	public void setRadioButtonSelected(String menuItemName, boolean selected) {
		menuItem.get(menuItemName).setSelected(selected);
	}

	/**
	 * @param menuItemName Specifies a JRadioButtonMenuItem.
	 * @return Returns true if the JRadioButtonMenuItem is selected.
	 */
	public boolean radioButtonSelected(String menuItemName) {
		return menuItem.get(menuItemName).isSelected();
	}

	@Override
	public Object getValue() {
		return null;
	}

	@Override
	public void setValue(Object v) {

	}

	public Component getJComponent() {
		return menuBar;
	}

}
