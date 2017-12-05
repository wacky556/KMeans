package stats;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class KMeans {
	static final Color[] colors = {Color.red, Color.blue, Color.green, Color.PINK, Color.ORANGE, Color.CYAN, Color.magenta, Color.YELLOW};
	int k;
	ArrayList<Point> data;
	ArrayList<Point>[] clusters;
	int timeStep;
	Point[] centers;
	
	public KMeans(int k, ArrayList<Point> data) {
		this.k = k;
		this.data = new ArrayList<Point>(data);
		clusters = new ArrayList[k];
		for (int i = 0; i < k; i++) {
			clusters[i] = new ArrayList<Point>();
		}
		timeStep = 0;
		centers = new Point[k];
	}
	
	
	public void setK(int k) {
		this.k = k;
		clusters = new ArrayList[k];
		for (int i = 0; i < k; i++) {
			clusters[i] = new ArrayList<Point>();
		}
		Point[] oldCenters = centers;
		centers = new Point[k];
		for (int i = 0; i < Math.min(oldCenters.length,centers.length); i++) {
			centers[i] = oldCenters[i];
		}
		timeStep = 0;
	}
	
	public void nextStep() {
		Random rand = new Random();
		if (timeStep == 0) {
			HashSet<Point> chosen = new HashSet<Point>();
			for (int i = 0 ; i < k; i++) {
				if (centers[i] != null) {
					chosen.add(centers[i]);
					continue;
				}
				int index;
				do {
					index = rand.nextInt(data.size());
				} while (chosen.contains(data.get(index)));
				
				chosen.add(data.get(index));
				centers[i] = data.get(index);
				
				
			}
		}
		for (int i = 0; i < k; i++) {
			clusters[i] = new ArrayList<Point>();
		}

			
			for (int i = 0; i < data.size(); i++) {
				double minDist = Utils.dist(data.get(i), centers[0]);
				int minIndex = 0;
				for (int j = 1; j < k; j++) {
					double dist = Utils.dist(data.get(i), centers[j]);
					if (dist < minDist) {
						minDist = dist;
						minIndex = j;
					}
				}
				clusters[minIndex].add(data.get(i));
			}
			for (int i = 0; i < k; i++) {
				if (clusters[i].size() > 0)
					centers[i] = Utils.average(clusters[i]);
				else 
					centers[i] = data.get(rand.nextInt(data.size()));
			}
		timeStep++;

			
		
	}
	
	public void run() {
		if (timeStep == 0) nextStep();
		double totalDist;
		do {
		Point[] oldCenters = centers;
		nextStep();
		totalDist = 0;
		for (int i = 0; i < k; i++) {
			totalDist += Utils.dist(centers[i], oldCenters[i]);
		}
		} while (totalDist > 0.05);
	}
	
	public void draw(Graphics g, int screenHeight) {
		if (timeStep == 0) {
			for (int i = 0; i < data.size(); i++) {
				data.get(i).draw(g, screenHeight, Color.black);
			}
		}
		else {
			for (int i = 0; i < k; i++) {
				for (Point p:clusters[i]) {
					p.draw(g, screenHeight, colors[i]);
				}
			}
		}
		for (int i = 0; i < k; i++) {
			if (centers[i] != null) {
				centers[i].draw(g, screenHeight, colors[i]);
				g.drawString("*", centers[i].getX()-4, screenHeight-(centers[i].getY()+4));
			}
		}
	}
	
	
	public void setCenter(int x, int y, int screenHeight) {
		for (Point p:data) {
			if (p.getBounds(screenHeight).contains(new java.awt.Point(x, y))) {
				for (int j = 0; j < k; j++) {
					if (centers[j] == null) continue;
					if (centers[j].equals(p)) return;
				}
				for (int j = 0; j < k; j++) {
					if (centers[j] == p) break;
					if (centers[j] == null) {
						centers[j] = p;
						return;
					}
				}
			}
		}
	}
	
	public void removeCenter(int x, int y,int screenHeight) {
		for (int i = 0; i < k; i++) {
			if (centers[i] == null) continue;
			if (centers[i].getBounds(screenHeight).contains(x, y)) {
				centers[i] = null;
				return;
			}
		}
	}
	
	public boolean beginning() {
		return timeStep==0;
	}
	public void setData(ArrayList<Point> data) {
		this.data = new ArrayList<Point>(data);
	}
	
	public int getK() {
		return k;
	}
	
	public void reset() {
		for (int i = 0; i < k; i++) {
			clusters[i] = new ArrayList<Point>();
			centers[i] = null;
		}
		timeStep = 0;
	}
	
	public void printCenters() {
		for (int i = 0; i < k; i++) {
			if (centers[i] == null) continue;
			System.out.println(centers[i]);
		}
	}

}
