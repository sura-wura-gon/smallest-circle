
public class Point {
	
	private double x;
	private double y;
	
	Point(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	Point()
	{
		this.x = 0;
		this.y = 0;
	}
	
	public double getX() {return x;}
	public double getY() {return y;}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
	
	

}
