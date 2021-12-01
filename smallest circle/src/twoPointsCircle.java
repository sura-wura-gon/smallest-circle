import java.util.*;
import java.io.*;

public class twoPointsCircle {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		FileReader pointFile = new FileReader("Circles_100000.txt");
		Scanner read = new Scanner(pointFile);
		
		int n = read.nextInt();
		
		ArrayList<Point> Points = new ArrayList<Point>();
		ArrayList<possibleTwoPoints> possiblePoints = new ArrayList<possibleTwoPoints>();
		ArrayList<Point> possibleCentre = new ArrayList<Point>();
		ArrayList<Double> possibleDiameter = new ArrayList<Double>();
		long startTime;
		long finishTime;
		long overallTime;
		
		while(read.hasNextInt())
		{
			Point p = new Point(read.nextInt(), read.nextInt());
			Points.add(p);
		}
		
		startTime = System.nanoTime();
		
		for(int i = 0; i<n; i++)
		{
			for(int j = i+1; j<n; j++)
			{
				double xc = (Points.get(i).getX() + Points.get(j).getX())/2;
				double yc = (Points.get(i).getY() + Points.get(j).getY())/2;
				double rc2 = (Math.pow(Points.get(i).getX() - Points.get(j).getX(), 2) + Math.pow(Points.get(i).getY() - Points.get(j).getY(), 2))/4;
				
				if(loop3(Points, xc,yc,rc2))
				{
					Point centre = new Point(xc, yc);
					possibleCentre.add(centre);
					possibleDiameter.add(rc2);
					possibleTwoPoints pp = new possibleTwoPoints(Points.get(i), Points.get(j));
					possiblePoints.add(pp);
				}
				
			}
		}
		
		finishTime = System.nanoTime();
		overallTime = finishTime - startTime;
		
		minimum(possibleDiameter, possiblePoints, possibleCentre);
		System.out.println("the overall execution time of the loops were: " + overallTime*0.000000001 + " seconds");
		
		pointFile.close();

	}
	
	public static boolean loop3(ArrayList<Point> Points, double xc, double yc, double rc2)
	{
		for(int k =0; k<Points.size(); k++)
		{
			double x = Math.pow(Points.get(k).getX() - xc, 2);
			double y = Math.pow(Points.get(k).getY() - yc, 2);
			if(x+y > rc2)
			{
				return false;
			}
		}
		return true;
	}
	
	public static void minimum(ArrayList<Double> diameter, ArrayList<possibleTwoPoints> possiblePoints, ArrayList<Point> possibleCentre)
	{
		double min = diameter.get(0);
		possibleTwoPoints pp = possiblePoints.get(0);
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
		System.out.println("the points are: A: " + pp.getP1().getX() + "," + pp.getP1().getY() + " B: " + pp.getP2().getX() + "," + pp.getP2().getY());
	}

}
