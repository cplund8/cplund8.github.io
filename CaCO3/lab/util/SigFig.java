package lab.util;

public class SigFig {
	
	/*
	public static void main(String[] args) {
		System.out.println(sigfigalize(2, 4, 1));

	}
	*/
	
	
	public static String sigfigalize(double num, int sigfigs) {
		return sigfigalize(num, sigfigs, 1);
	}
	
	public static String sigfigalize(double num, int sigfigs, int scientificNotationStart) {
		
		if (num == 0) {
			return "0";
		}
		
		if (sigfigs <= -1) {
			return Double.toString(num);
		}
		
		boolean isNegative = num < 0;
		
		num = Math.abs(num);
		
		sigfigs--;

		String strNum = Double.toString(num);
		double power = Math.pow(10, sigfigs);
		
		if (strNum.contains("E")) {
			String powerStr = "E" + strNum.split("E")[1];
			
			strNum = strNum.replaceAll(powerStr, "");
			
			double d = Double.parseDouble(strNum);
			
			d *= power;
			
			d = Math.round(d);
			
			d /= power;
			
			return Double.toString(d) + powerStr;
			
		}
		
		int places;
		if (num > scientificNotationStart) {
			places = (int) Math.log10(num);
		} else {
			places = (int) Math.floor(Math.log10(num));
		}
		
		num /= Math.pow(10, places);
		num *= power;
		
		num = Math.round(num);
		
		num /= power;
		
		
		
		if (Math.abs(places - 0) <= scientificNotationStart) {
			
			strNum = Float.toString((float) (num * Math.pow(10, places)));
			
			int figs = getSigFigs(num * Math.pow(10, places));
			
			if (figs < sigfigs + 1) {
				String zeros = "";
				
				for (int i = 0; i <= sigfigs - figs; i++) {
					zeros += "0";
				}
				
				strNum += zeros;
			} else if (figs > sigfigs + 1 && strNum.endsWith(".0")) {
				strNum = strNum.substring(0, strNum.length() - 2);
			}
			
			return (isNegative ? "-" : "") + strNum;
			
		} else {
			
			int figs = getSigFigs(num);
			
			strNum = Float.toString((float) (num));
			
			if (figs < sigfigs + 1) {
				String zeros = "";
				
				for (int i = 0; i <= sigfigs - figs; i++) {
					zeros += "0";
				}
				
				strNum += zeros;
				
			} else if (figs > sigfigs + 1 && strNum.endsWith(".0")) {
				strNum = strNum.substring(0, strNum.length() - 2);
			}
				
			strNum += "E" + places;
				
			
			return (isNegative ? "-" : "") + strNum;
		}
		
	}
	
	private static int getSigFigs(double num) {
		String strNum = Float.toString((float) num).replace("-", "").replace(".", "");
		
		if (strNum.startsWith("0")) {
			strNum = strNum.replaceFirst("0", "");
		}
		
		return strNum.length();
	}
	

}
