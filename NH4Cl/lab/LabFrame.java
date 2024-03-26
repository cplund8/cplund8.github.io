package lab;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import lab.component.EmptyComponent;
import lab.component.LabComponent;
import lab.util.DrawThread;
import lab.util.animation.Animator;

/**
 * @author Benjamin Walls
 * @version 1.0
 * @since 1.0
 */
public abstract class LabFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private DrawCanvas canvas;
	private final Animator animator = new Animator();
	private boolean started = false;
	private final LabComponent root;
	
	/**
	 * Creates a new LabFrame window with the given parameters.
	 * 
	 * @param name Specifies the name of the JFrame.
	 * @param width Specifies the width of the JFrame.
	 * @param height Specifies the height of the JFrame.
	 * @param startOpen Specifies whether the frame is automatically opened after instantiation.
	 */
	public LabFrame(String name, int width, int height, boolean startOpen) {
		setSize(width, height);
		setTitle(name);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
				
		root = new EmptyComponent(width, height);
		
		add(canvas = new DrawCanvas());
		
		setVisible(startOpen);
	}
	
	/**
	 * Creates a JFrame window with the given parameters that is visible when the class that creates it begins running.
	 * 
	 * @param name Specifies the name of the JFrame.
	 * @param width Specifies the width of the JFrame.
	 * @param height Specifies the height of the JFrame.
	 */
	public LabFrame(String name, int width, int height) {
		this(name, width, height, true);
	}
	
	/**
	 * Starts drawing on the JFrame.
	 * 
	 * @param fps The rate at which the frame redraws itself (frames per second).
	 */
	public void start(int fps) {
		canvas.maxFPS = fps;
		new DrawThread(canvas, fps);
		started = true;
	}
	
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		
		if (visible) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					canvas.repaint();
				}
			});
		}
	}
	
	/**
	 * Gets the JPanel which the frame draws on.
	 * For adding Swing components to the frame.
	 * 
	 * @return The JPanel which the frame draws on.
	 */
	public JPanel getDrawCanvas() {
		return canvas;
	}
	
	/**
	 * Adds a LabComponent object to the JFrame.
	 * 
	 * @param components The LabComponents to add to the JFrame.
	 */
	public void addComponent(LabComponent...components) {
		root.addChild(components);
	}
	
	/**
	 * Removes a LabComponent object from the JFrame
	 * 
	 * @param components The LabComponents to remove from the JFrame.
	 */
	public void removeComponent(LabComponent...components) {
		root.removeChild(components);
	}
	
	/**
	 * @return The root LabComponent, parent of all LabComponent's in the frame.
	 */
	public LabComponent getRoot() {
		return root;
	}
	
	/**
	 * @return An animator used by the LabFrame, can be used to animate components.
	 */
	public Animator getAnimator() {
		return animator;
	}
	
	private void draw(Graphics g, boolean overMaxFPS) {
		if (!started) return;
		
		if (!overMaxFPS) {
			animator.animate();
			update();
		}
		
		root.draw(g, 0, 0, canvas.getWidth(), canvas.getHeight(), canvas, overMaxFPS);
	}
	
	/**
	 * The update method runs every frame at the specified FPS.
	 */
	public abstract void update();
	
	private class DrawCanvas extends JPanel {
		
		private static final long serialVersionUID = 6333028362943156947L;

		int maxFPS = 1000;
		private long lastCall = -1;
		private long deltaTime = 0;
		private boolean overFPS = false;
		
		public DrawCanvas() {
			setLayout(null);
			
		}
		
		@Override
		public void paintComponent(Graphics g) {
			
			if (lastCall == -1) {
				lastCall = System.nanoTime();
				return;
			}
			
			if (overFPS) {
				deltaTime += System.nanoTime() - lastCall;
			} else {
				deltaTime = System.nanoTime() - lastCall;
			}
			
			double fps = Math.floor(1.0 / (deltaTime / 1.0E9));
			

			lastCall = System.nanoTime();
			
			overFPS = fps > maxFPS + 5;
			
			
			setBackground(Color.white);
			super.paintComponent(g);
			draw(g, overFPS);
			
			
			
		}
		
		
	}
	
}
