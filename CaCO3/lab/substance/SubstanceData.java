package lab.substance;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import lab.component.container.ContentState;
import lab.substance.Substance;

/**
 * @author Dan Cline
 * @version 1.0
 * @since 1.0
 */
public class SubstanceData {
	private static ArrayList<Substance> substances = new ArrayList<>();

	/**
	 * Allows the user to import a custom .csv file of substances.
	 * 
	 * @param fileName Specifies the name of a .csv file to read from.
	 * @return An array list of the substances as read from the specified file.
	 */
	public static List<Substance> importSubstances(String fileName) {
		return readFile(fileName);
	}

	/**
	 * Imports substances from the default .csv file: "/SubstanceData.csv".
	 * 
	 * @return An array list of substances read from the default file.
	 */
	public static List<Substance> importSubstances() {
		return readFile("/SubstanceData.csv");
	}

	private static ArrayList<Substance> readFile(String fileName) {
		String csv = fileName;
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		substances = new ArrayList<Substance>();
		
		try {

			InputStream is = SubstanceData.class.getResourceAsStream(csv);
			InputStreamReader isr = new InputStreamReader(is);
			br = new BufferedReader(isr);

			while ((line = br.readLine()) != null) {
				Substance tempSubstance = new Substance();
				// use comma as separator
				String[] substance = line.split(cvsSplitBy);
				tempSubstance.setName(substance[0]);
				if (substance[1] == "SOLID") {
					tempSubstance.setState(ContentState.SOLID);
				} else if (substance[1] == "LIQUID") {
					tempSubstance.setState(ContentState.LIQUID);
				} else if (substance[1] == "GAS") {
					tempSubstance.setState(ContentState.GAS);
				}
				tempSubstance.setMeltingPoint(Double.parseDouble(substance[2]));
				tempSubstance.setBoilingPoint(Double.parseDouble(substance[3]));
				tempSubstance.setHeatOfVaporization(Double.parseDouble(substance[4]));
				tempSubstance.setHeatOfFusion(Double.parseDouble(substance[5]));
				tempSubstance.setMolarMass(Double.parseDouble(substance[6]));
				tempSubstance.setFormula(substance[7]);
				substances.add(tempSubstance);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return substances;
	}

}
