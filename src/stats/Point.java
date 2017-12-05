package stats;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Point {
	
	private static final int size = 10;
	
	private int x,y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(Point p) {
		x = p.x;
		y = p.y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void draw(Graphics g, int screenHeight, Color c) {
		g.setColor(c);
		g.fillOval(x-size/2, screenHeight-(y+size/2), size, size);
	}
	
	public Rectangle getBounds(int screenHeight) {
		return new Rectangle(x-size/2,screenHeight-(y+size/2),size,size);
	}
	
	public String toString() {
		return ""+x + " " + y;
	}
	
	public boolean equals(Point p) {
		return x==p.getX()&&y==p.getY();
	}

}
