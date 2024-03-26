package lab.component.sensor;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.io.IOException;

import javax.swing.SwingConstants;

import lab.component.LabComponent;
import lab.component.MeasurableComponent;
import lab.component.geo.Rectangle;
import lab.component.swing.Label;
import lab.util.SigFig;

public class PressureGauge extends MeasurableComponent {

	static {
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,
					PressureGauge.class.getResourceAsStream("/DSEG14Classic-Regular.ttf")));
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	private final Label titleLabel;
	private final Label gaugeLabel;
	private int sigfigs;
	private int minPowerForScientificNotation;
	private String units;
	
	public PressureGauge(int width, int height, String title, String units, int sigfigs) {
		this(width, height, title, units, sigfigs, 1);
	}

	public PressureGauge(int width, int height, String title, String units, int sigfigs, int minPowerForScientificNotation) {
		super(width, height);

		this.units = units;
		this.minPowerForScientificNotation = minPowerForScientificNotation;

		setLayout(LabComponent.FREE_FORM);

		this.sigfigs = sigfigs;

		gaugeLabel = new Label((int) (width / 1.6), height / 6);
		gaugeLabel.setOffset((width - gaugeLabel.getWidth()) / 2, 2 * (height - gaugeLabel.getHeight()) / 3);
		gaugeLabel.setShowBounds(true);
		Rectangle gaugeBorder = new Rectangle(gaugeLabel.getWidth() + 10, gaugeLabel.getHeight() + 10);
		gaugeLabel.addChild(gaugeBorder);
		gaugeBorder.setFillColor(Color.white);
		gaugeBorder.setOffset(-5, -3);
		gaugeLabel.getJComponent().setVerticalAlignment(SwingConstants.CENTER);
		gaugeLabel.getJComponent().setHorizontalAlignment(SwingConstants.CENTER);
		gaugeLabel.setWrap(true);
		gaugeLabel.setColor(Color.black);

		gaugeLabel.setFont(new Font("DSEG14 Classic", Font.BOLD, 13));

		titleLabel = new Label(width / 2, height / 6, "<center>" + title + "</center>");
		titleLabel.setOffset((width - titleLabel.getWidth()) / 2, 5 * (width - titleLabel.getWidth()) / 12);
		titleLabel.setShowBounds(true);
		Rectangle titleBorder = new Rectangle(titleLabel.getWidth() + 5, titleLabel.getHeight() + 5);
		titleLabel.addChild(titleBorder);
		titleBorder.setFillColor(Color.white);
		titleBorder.setOffset(-3, -1);
		titleLabel.getJComponent().setVerticalAlignment(SwingConstants.CENTER);
		titleLabel.getJComponent().setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setWrap(true);
		titleLabel.setFont(new Font("DSEG14 Classic", Font.BOLD, 10));
		titleLabel.setColor(Color.BLACK);

		addChild(titleLabel);
		addChild(gaugeLabel);

	}

	public int getSigfigs() {
		return sigfigs;
	}

	public void setSigfigs(int sigfigs) {
		this.sigfigs = sigfigs;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getTitleText() {
		return titleLabel.getText().replace("<center>", "").replaceAll("</center>", "");
	}

	public void setTitleText(String title) {
		titleLabel.setText("<center>" + title + "</center>");
	}

	public int getMinPowerForScientificNotation() {
		return minPowerForScientificNotation;
	}

	public void setMinPowerForScientificNotation(int minPowerForScientificNotation) {
		this.minPowerForScientificNotation = minPowerForScientificNotation;
	}

	public Label getGaugeLabel() {
		return gaugeLabel;
	}

	public Label getTitleLabel() {
		return titleLabel;
	}

	@Override
	public void setValue(double value) {
		super.setValue(value);

		gaugeLabel.setText("<html><center>" + SigFig.sigfigalize(value, sigfigs, minPowerForScientificNotation).replace(".", " . ") + "<br>" + units + "</center></html>");
	}

	@Override
	public void draw(int x, int y, int width, int height, Graphics g) {
		g.setColor(Color.black);
		g.fillOval(x, y, width, height);

		int maxValue = 200;
		int maxRuns = 10;

		for (int i = 0; i < maxRuns; i++) {
			g.setColor(new Color((maxValue / maxRuns) * i, (maxValue / maxRuns) * i, (maxValue / maxRuns) * i));
			g.fillOval(x + i, y + i, width - 2 * i, height - 2 * i);
		}
		
		g.setColor(new Color(84, 140, 229));
		g.fillOval(x + maxRuns, y + maxRuns, width - 2 * maxRuns, height - 2 * maxRuns);
		
	}

}
