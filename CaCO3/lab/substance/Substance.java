package lab.substance;

import java.awt.Color;

import lab.component.data.GraphDataSet;
import lab.component.container.ContentState;
import lab.component.swing.Label;

public class Substance {
	
	private boolean isReactant;
	private Color color;
	private ContentState state;
	private double amount, heatOfVaporization, heatOfFusion, boilingPoint, meltingPoint, boilPressure, molarMass;
	private GraphDataSet set;
	private Label connectedLabel;
	private String name, units, formula;
	
	public Substance() {
		color = Color.WHITE;
		state = ContentState.SOLID;
		set = null;
		connectedLabel =null;
	}

	public Substance(String name, double amount, boolean isReactant) {
		this.name = name;
		this.amount = amount;
		this.isReactant = isReactant;
	}

	public Substance(String name, double amount, boolean isReactant, Color color) {
		this(name, amount, isReactant);
		this.color = color;
	}

	public Substance(String name, double amount, boolean isReactant, String units) {
		this.name = name;
		this.amount = amount;
		this.isReactant = isReactant;
		this.units = units;
	}

	public void setBoilingConditions(double hov, double bpt, double bpressure) {
		this.heatOfVaporization = hov;
		this.boilingPoint = bpt;
		this.boilPressure = bpressure;
	}

	public boolean isReactant() {
		return isReactant;
	}

	public void setReactant(boolean isReactant) {
		this.isReactant = isReactant;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GraphDataSet getSet() {
		return set;
	}

	public void setSet(GraphDataSet dataSet) {
		set = new GraphDataSet(dataSet.getName(), dataSet.isConnectPoints(), dataSet.isShowName());
	}

	public Label getConnectedLabel() {
		return connectedLabel;
	}

	public void setConnectedLabel(Label connectedLabel) {
		this.connectedLabel = connectedLabel;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public ContentState getState() {
		return state;
	}

	public void setState(ContentState state) {
		this.state = state;
	}

	public double getHeatOfVaporization() {
		return heatOfVaporization;
	}

	public void setHeatOfVaporization(double heatOfVaporization) {
		this.heatOfVaporization = heatOfVaporization;
	}

	public double getBoilingPoint() {
		return boilingPoint;
	}

	public void setBoilingPoint(double boilingPoint) {
		this.boilingPoint = boilingPoint;
	}

	public double getBoilPressure() {
		return boilPressure;
	}

	public void setBoilPressure(double boilPressure) {
		this.boilPressure = boilPressure;
	}

	public double getMolarMass() {
		return molarMass;
	}

	public void setMolarMass(double molarMass) {
		this.molarMass = molarMass;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public double getMeltingPoint() {
		return meltingPoint;
	}

	public void setMeltingPoint(double meltingPoint) {
		this.meltingPoint = meltingPoint;
	}

	public double getHeatOfFusion() {
		return heatOfFusion;
	}

	public void setHeatOfFusion(double heatOfFusion) {
		this.heatOfFusion = heatOfFusion;
	}

}
