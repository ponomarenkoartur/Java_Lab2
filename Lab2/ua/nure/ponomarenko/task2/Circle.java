package ua.nure.ponomarenko.task2;

public class Circle {

	// Demo
	public static void main(String[] args) {
		Circle circle1 = new Circle(-100, -100, 10);
		Circle circle2 = new Circle(1, 1, 5);
		Circle circle3 = new Circle(5, -5, 5);
		double pointX = 5;
		double pointY = 5;

		System.out.println("=====");
		System.out.println("Circle1:");
		circle1.printInfo();

		System.out.println("=====");
		circle1.move(100, 100);
		System.out.println("Circle1 is moved");
		System.out.println("Circle1:");
		circle1.printInfo();

		System.out.println("=====");
		boolean isPointInsideCircle1 = circle1.contains(pointX, pointY);
		System.out.println("Point (" + pointX + ", " + pointY +
			") is inside circle1: " + isPointInsideCircle1);

		System.out.println("=====");
		boolean isCircle2InsideCircle1 = circle1.contains(circle2);
		System.out.println("Circle2:");
		circle2.printInfo();
		System.out.println("Circle2 is inside circle1: " + isCircle2InsideCircle1);

		System.out.println("=====");
		boolean isCircle3InsideCircle1 = circle1.contains(circle3);
		System.out.println("Circle3:");
		circle3.printInfo();
		System.out.println("Circle3 is inside circle1: " + isCircle3InsideCircle1);
	}

	// Properties
	private double x;
	private double y;
	private double r;

	// Getters
	public double getX() {
		return x;
	}

	public double getY() {
		return x;
	}

	public double getR() {
		return x;
	}

	// Setters
	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setR(double r) {
		this.r = r;
	}

	// Initialization

	public Circle(double x, double y, double r) {
		this.x = x;
		this.y = y;
		this.r = r;
	}

	// Public methods

	public void move(double dx, double dy) {
		setX(x + dx);
		setY(y + dy);
	}

	public boolean contains(double x, double y) {
		double distanceBetweentanceBetweenCenterAndPoint =
			Geometry.distanceBetweenPoints(this.x, this.y, x, y);
		return distanceBetweentanceBetweenCenterAndPoint <= r;
	}

	public boolean contains(Circle c) {
		if (c == null) {
			return false;
		}
		double distanceBetweenCenters = Geometry.distanceBetweenPoints(this.x, this.y, c.x, c.y);
		return distanceBetweenCenters + c.r <= this.r;
	}

	public void printInfo() {
		System.out.println("X: " + x);
		System.out.println("Y: " + y);
		System.out.println("R: " + r);
	}

	public static class Geometry {
		public static double distanceBetweenPoints(double x1, double y1, double x2, double y2) {
			return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
		}
	}
}

