package graphics;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import stats.KMeans;
import stats.Point;
import stats.Utils;

public class Window extends JPanel implements ActionListener, MouseListener {
	
	KMeans kmeans;
	
	JMenuBar menuBar = new JMenuBar();
	
	JMenu file = new JMenu("File");
	
	JMenuItem load = new JMenuItem("Load");
	
	
	JMenu run = new JMenu("Run");
	JMenuItem run2 = new JMenuItem("Run");
	JMenuItem nextStep = new JMenuItem("Next Step");
	JMenuItem reset = new JMenuItem("Reset");
	
	
	JMenu k = new JMenu("K");
	ButtonGroup group = new ButtonGroup();
	JRadioButtonMenuItem k1 = new JRadioButtonMenuItem("1");
	JRadioButtonMenuItem k2 = new JRadioButtonMenuItem("2");
	JRadioButtonMenuItem k3 = new JRadioButtonMenuItem("3");
	JRadioButtonMenuItem k4 = new JRadioButtonMenuItem("4");
	JRadioButtonMenuItem k5 = new JRadioButtonMenuItem("5");
	JRadioButtonMenuItem k6 = new JRadioButtonMenuItem("6");
	JRadioButtonMenuItem k7 = new JRadioButtonMenuItem("7");
	JRadioButtonMenuItem k8 = new JRadioButtonMenuItem("8");
	JRadioButtonMenuItem k9 = new JRadioButtonMenuItem("9");


	
	final JFileChooser fc = new JFileChooser();
	
	JFrame frame = new JFrame("KMeans Clustering Demo");


	public Window() {
		//super("KMeans");
		
		kmeans = new KMeans(2,new ArrayList<Point>());
		
		load.addActionListener(this);
		load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,ActionEvent.ALT_MASK));
		file.add(load);
		
		
		run2.addActionListener(this);
		run2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.ALT_MASK));
		nextStep.addActionListener(this);
		nextStep.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.ALT_MASK));
		reset.addActionListener(this);
		reset.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.CTRL_MASK));
		run.add(run2);
		run.add(nextStep);
		run.add(reset);
		
		k1.addActionListener(this);
		k2.addActionListener(this);
		k3.addActionListener(this);
		k4.addActionListener(this);
		k5.addActionListener(this);
		k6.addActionListener(this);
		k7.addActionListener(this);
		k8.addActionListener(this);
		k9.addActionListener(this);
		
		k1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,ActionEvent.ALT_MASK));
		k2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2,ActionEvent.ALT_MASK));
		k3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3,ActionEvent.ALT_MASK));
		k4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4,ActionEvent.ALT_MASK));
		k5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5,ActionEvent.ALT_MASK));
		k6.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_6,ActionEvent.ALT_MASK));
		k7.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_7,ActionEvent.ALT_MASK));
		k8.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_8,ActionEvent.ALT_MASK));
		k9.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_9,ActionEvent.ALT_MASK));
		
		group.add(k1);
		group.add(k2);
		group.add(k3);
		group.add(k4);
		group.add(k5);
		group.add(k6);
		group.add(k7);
		group.add(k8);
		group.add(k9);
		
		k2.setSelected(true);
		
		k.add(k1);
		k.add(k2);
		k.add(k3);
		k.add(k4);
		k.add(k5);
		k.add(k6);
		k.add(k7);
		k.add(k8);
		k.add(k9);
		
		menuBar.add(file);
		menuBar.add(run);
		menuBar.add(k);
		frame.getContentPane().add(this);
		frame.setJMenuBar(menuBar);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addMouseListener(this);
		frame.pack();
		frame.setResizable(false);
		frame.setSize(1000, 1000);
		frame.setVisible(true);
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image i = createImage(getWidth(),getHeight());
		Graphics ig = i.getGraphics();
		kmeans.draw(ig, getHeight());
		g.drawImage(i, 0, 0, null);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == load ) {
			int returnVal = fc.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				String s = fc.getSelectedFile().getPath();
				kmeans.setData(Utils.loadData(s));
			}
			
		}
		if (e.getSource() == reset) {
			kmeans.reset();
		}
		if (e.getSource() == run2) {
			kmeans.run();
		}
		if (e.getSource() == nextStep) {
			kmeans.nextStep();
		}
		
		if (e.getSource() == k1) {
			kmeans.setK(1);
		}
		if (e.getSource() == k2) {
			kmeans.setK(2);
		}
		if (e.getSource() == k3) {
			kmeans.setK(3);
		}
		if (e.getSource() == k4) {
			kmeans.setK(4);
		}
		if (e.getSource() == k5) {
			kmeans.setK(5);
		}
		if (e.getSource() == k6) {
			kmeans.setK(6);
		}
		if (e.getSource() == k7) {
			kmeans.setK(7);
		}
		if (e.getSource() == k8) {
			kmeans.setK(8);
		}
		if (e.getSource() == k9) {
			kmeans.setK(9);
		}

		frame.repaint();
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (SwingUtilities.isLeftMouseButton(e)) {
			kmeans.setCenter(e.getX(), e.getY(), getHeight());
		}
		if (SwingUtilities.isRightMouseButton(e)) {
			kmeans.removeCenter(e.getX(), e.getY(), getHeight());
		}
		kmeans.printCenters();
		frame.repaint();
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
