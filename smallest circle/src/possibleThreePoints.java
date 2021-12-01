
public class possibleThreePoints {
	
	private Point p1;
	private Point p2;
	private Point p3;
	
	public possibleThreePoints()
	{
		p1 = new Point();
		p2 = new Point();
		p3 = new Point();
	}
	
	public possibleThreePoints(Point p1, Point p2, Point p3)
	{
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
	}
	
	public Point getP1() {return p1;}
	public Point getP2() {return p2;}
	public Point getP3() {return p3;}

	@Override
	public String toString() {
		return "possibleThreePoints [p1=" + p1 + ", p2=" + p2 + ", p3=" + p3 + "]";
	}

}
