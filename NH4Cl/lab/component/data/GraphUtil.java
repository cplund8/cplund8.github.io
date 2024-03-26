package lab.component.data;

import java.util.List;

import lab.util.Vector2;

public class GraphUtil {
	
	public static double[] getLineOfBestFit(List<Vector2> points) {
		
		int n = points.size();
		
		double meanX = 0, meanY = 0, num = 0, denom = 0, a = 0, m;
		
		for (Vector2 v : points) {
			meanX += v.getX();
			meanY += v.getY();
		}
		
		meanX /= n;
		meanY /= n;
		
		for (Vector2 v : points) {
			a = v.getX() - meanX;
			num += a * (v.getY() - meanY);
			denom += a * a;
		}
		
		m = num / denom;
		
		return new double[] { m, meanY - m * meanX };
		
	}
	
	
}
