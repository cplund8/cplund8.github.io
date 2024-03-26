package lab.util;

/**
 * @author Benjamin Walls
 * @version 1.0
 * @since 1.0
 */
public class Vector2 {

	private double x, y;
	
	/**
	 * Creates a two dimensional vector with the given a and y values.
	 * 
	 * @param x Specifies the x value of the vector.
	 * @param y Specifies the y value of the vector.
	 */
	public Vector2(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return The x value of the vector.
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * @return The y value of the vector.
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * Sets the x value of the vector.
	 * 
	 * @param x The x value of the vector.
	 */
	public void setX(double x) {
		this.x = x;
	}
	
	/**
	 * Sets the y value of the vector.
	 * 
	 * @param y The y value of the vector.
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * Sets the vector equal to another vector.
	 * 
	 * @param other The other vector.
	 */
	public void set(Vector2 other) {
		x = other.x;
		y = other.y;
	}
	
	/**
	 * Sets the x and y values of the vector.
	 * 
	 * @param x The x value of the vector.
	 * @param y The y value of the vector.
	 */
	public void set(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Adds the given components to the vector.
	 * 
	 * @param x The x value to be added to the vector's x value.
	 * @param y The y value to be added to the vector's y value.
	 * @return The resulting vector after the addition.
	 */
	public Vector2 add(double x, double y) {
		return new Vector2(this.x + x, this.y + y);
	}
	
	/**
	 * Adds another vector to the vector.
	 * 
	 * @param other The other vector.
	 * @return The resulting vector after the addition.
	 */
	public Vector2 add(Vector2 other) {
		return add(other.getX(), other.getY());
	}
	
	/**
	 * Subtracts the given components from the vector.
	 * 
	 * @param x The x value to be subtracted from the vector's x value.
	 * @param y The y value to be subtracted from the vector's y value.
	 * @return The resulting vector after the subtraction.
	 */
	public Vector2 subtract(double x, double y) {
		return add(-x, -y);
	}
	
	/**
	 * Subtracts a given vector from the vector.
	 * 
	 * @param other A vector to subtract from the vector.
	 * @return The resulting vector after the subtraction.
	 */
	public Vector2 subtract(Vector2 other) {
		return subtract(other.getX(), other.getY());
	}
	
	/**
	 * Multiplies the vector by the given value.
	 * 
	 * @param m The value to multiply the vector by.
	 * @return The resulting vector after multiplication.
	 */
	public Vector2 multiply(double m) {
		return new Vector2(x * m, y * m);
	}
	
	/**
	 * Divides the vector by the given value.
	 * 
	 * @param d The value to divide the vector by.
	 * @return The resulting vector after division.
	 */
	public Vector2 divide(double d) {
		return multiply(1 / d);
	}
	
	/**
	 * @return Whether or not the x and y values of the vector are zero.
	 */
	public boolean isZero() {
		return x == 0 && y == 0;
	}
	
	/**
	 * Rotates the vector by the given number of degrees.
	 * 
	 * @param deg Specifies the number of degrees to rotate the vector by.
	 * @return The resulting vector after rotation.
	 */
	public Vector2 rotate(double deg) {
		deg = Math.toRadians(deg);
		double s = Math.sin(deg);
		double c = Math.cos(deg);
		return new Vector2(x * c - y * s, x * s + y * c);
	}
	
	public double angleBetween(Vector2 other) {
		return Math.atan2(y - other.getY(), x - other.getX()) * 180.0 / Math.PI;
	}
	
	public double angle() {
		return Math.atan2(y, x) * 180.0 / Math.PI;
	}
	
	public double distanceSqrt(Vector2 other) {
		double dx = x - other.getX(), dy = y = other.getY();
		
		return dx * dx + dy * dy;
	}
	
	public double distance(Vector2 other) {
		return Math.sqrt(distanceSqrt(other));
	}
	
	public double length() {
		return Math.sqrt(x * x + y * y);
	}
	
	public double lengthSqrt() {
		return x * x + y * y;
	}
	
	public Vector2 normalize(double length) {
		double l = fastInverseSqrt(x * x + y * y);
		return new Vector2((x * l) * length, (y * l) * length);
	}
	
	public Vector2 normalize() {
		return normalize(1.0);
	}
	
	public Vector2 extend(double amount) {
		return normalize(length() + amount);
	}
	
	public double dot(Vector2 other) {
		return x * other.x + y * other.y;
	}
	
	public double cross(Vector2 other) {
		return x * other.y - y * other.x;
	}
	
	@Override
	public String toString() {
		return x + "," + y;
	}
	
	public Vector2 clone() {
		return new Vector2(x, y);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Vector2)) {
			return false;
		}
		Vector2 other = (Vector2) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x)) {
			return false;
		}
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y)) {
			return false;
		}
		return true;
	}

	public static Vector2 parse(String str) {
		String[] nums = str.split(",");
		
		double x = Double.parseDouble(nums[0]);
		double y = Double.parseDouble(nums[1]);
		
		return new Vector2(x, y);
		
	}
	
	private static double fastInverseSqrt(double x) {
	    double xhalf = 0.5d * x;
	    long i = Double.doubleToLongBits(x);
	    i = 0x5fe6ec85e7de30daL - (i >> 1);
	    x = Double.longBitsToDouble(i);
	    x *= (1.5d - xhalf * x * x);
	    return x;
	}
}
