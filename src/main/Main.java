package main;

import graphics.Window;

public class Main {
	public static void main(String[] args) {
		Window w = new Window();
		while (true) {
			//w.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
