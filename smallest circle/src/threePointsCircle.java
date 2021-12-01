import java.util.*;
import java.io.*;

public class threePointsCircle {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		FileReader pointFile = new FileReader("Circles_100000.txt");
		Scanner read = new Scanner(pointFile);
		
		int n = read.nextInt();
		
		ArrayList<Point> Points = new ArrayList<Point>();
		ArrayList<possibleThreePoints> possiblePoints = new ArrayList<possibleThreePoints>();
		ArrayList<Point> possibleCentre = new ArrayList<Point>();
		ArrayList<Double> possibleDiameter = new ArrayList<Double>();
		long startTime;
		long finishTime;
		long overallTime;
		double xc = 0;
		double yc = 0;
		double rc2 = 0;
		
		while(read.hasNextInt())
		{
			Point p = new Point(read.nextInt(), read.nextInt());
			Points.add(p);
		}
		
		startTime = System.nanoTime();
		
		for(int i = 0; i<n; i++) //point 1
		{
			for(int j= i+1; j<n; j++) //point 2
			{
				for(int k = j+1; k<n; k++) //point 3
				{
					double x1 = Points.get(i).getX();
					double x2 = Points.get(j).getX();
					double x3 = Points.get(k).getX();
					double y1 = Points.get(i).getY();
					double y2 = Points.get(j).getY();
					double y3 = Points.get(k).getY();
					
					double A = (x1*(y2-y3)) - y1*(x2-x3) + ((x2*y3) - (x3*y2));
					double B = ((Math.pow(x1, 2)+Math.pow(y1, 2))*(y3-y2)) + ((Math.pow(x2, 2)+ Math.pow(y2, 2))*(y1-y3)) + ((Math.pow(x3, 2) + Math.pow(y3, 2))*(y2-y1));
					double C = ((Math.pow(x1, 2)+Math.pow(y1, 2))*(x2-x3)) + ((Math.pow(x2, 2)+ Math.pow(y2, 2))*(x3-x1)) + ((Math.pow(x3, 2) + Math.pow(y3, 2))*(x1-x2));
					double D = ((Math.pow(x1, 2)+Math.pow(y1, 2))*((x3*y2)-(x2*y3))) + ((Math.pow(x2, 2)+ Math.pow(y2, 2))*((x1*y3)-(x3*y1))) +  ((Math.pow(x3, 2) + Math.pow(y3, 2))*((x2*y1)-(x1*y2)));                        
					
					xc = -(B/(2*A));
					yc = -(C/(2*A));
					rc2 = (Math.pow(B, 2) + Math.pow(C, 2) - (4*A*D)) / (4 * Math.pow(A, 2)); //Rc^2
					
					if(loop4(Points, xc, yc, rc2))
					{
						possibleThreePoints ppp = new possibleThreePoints(Points.get(i), Points.get(j), Points.get(k));
						Point centre = new Point(xc, yc);
						possiblePoints.add(ppp);
						possibleDiameter.add(rc2);
						possibleCentre.add(centre);
						
					}
					
				}
			}
		}
		
		finishTime = System.nanoTime();
		overallTime = finishTime - startTime;
		
		minimum(possibleDiameter, possiblePoints, possibleCentre);
		System.out.println("the overall execution time of the loops were: " + overallTime*0.000000001 + " seconds");
		
		
		pointFile.close();

	}
	
	public static boolean loop4(ArrayList<Point> Points, double xc, double yc, double rc2)
	{
		if(Double.isNaN(rc2))
		{
			return false;
		}
		for(int l = 0; l<Points.size(); l++)
		{
			double lx = Points.get(l).getX();
			double ly = Points.get(l).getY();
			if(Math.round(Math.pow(lx-xc, 2) + Math.pow(ly-yc, 2)) > Math.round(rc2))
			{
				return false;
			}
		}
		return true;
	}
	
	public static void minimum(ArrayList<Double> diameter, ArrayList<possibleThreePoints> possiblePoints, ArrayList<Point> possibleCentre)
	{
		double min = diameter.get(0);
		possibleThreePoints pp = possiblePoints.get(0);
		Point centre = possibleCentre.get(0);
		for(int i = 0; i<diameter.size(); i++)
		{
			if(min > diameter.get(i))
			{
				min = diameter.get(i);
				pp = possiblePoints.get(i);
				centre = possibleCentre.get(i);
				
			}
		}
		
		System.out.println("the minimum radius is: " + Math.sqrt(min));
		System.out.println("the circle centre is: " + centre.getX() + ", " + centre.getY());
		System.out.println("the circle area is: " + Math.PI*min);
		System.out.println("A: " + pp.getP1().getX() + "," + pp.getP1().getY() + " B: " + pp.getP2().getX() + "," + pp.getP2().getY() + " C: " + pp.getP3().getX() + "," + pp.getP3().getY());

	}

}
