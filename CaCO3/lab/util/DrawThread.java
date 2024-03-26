package lab.util;


import javax.swing.JPanel;

public class DrawThread extends Thread {
	
	private final JPanel frame;
	private final int updateInterval;
	private final long delay;
	
	public DrawThread(JPanel frame, int updateInterval, long delay) {
		this.frame = frame;
		this.updateInterval = updateInterval;
		this.delay = delay;
		
		setDaemon(true);
		
		start();
	}
	
	public DrawThread(JPanel frame, int updateInterval) {
		this(frame, updateInterval, 10L);
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		while (true) {
			frame.repaint();
			try {
				Thread.sleep((long) (1000.0 / updateInterval));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


}
