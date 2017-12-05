package stats;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Utils {
	
	public static ArrayList<Point> loadData(String filename) {
		File f = new File(filename);
		ArrayList<Point> points = null;
		try {
			points = new ArrayList<Point>();
			Scanner s = new Scanner(f);
			while (s.hasNextLine()) {
				Scanner s2 = new Scanner(s.nextLine());
				int x = s2.nextInt();
				int y = s2.nextInt();
				points.add(new Point(x,y));
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return points;

	
	}
	
	
	
	public static Point average(ArrayList<Point> points) {
		int x = 0;
		int y = 0;
		for (int i = 0; i < points.size(); i++ ) {
			x += points.get(i).getX();
			y += points.get(i).getY();
		}
		x /= points.size();
		y /= points.size();
		return new Point(x,y);
		
	}
	
	public static double dist(Point p1, Point p2) {
		return Math.sqrt((p1.getX()-p2.getX())*(p1.getX()-p2.getX())+(p1.getY()-p2.getY())*(p1.getY()-p2.getY()));
	}
}
