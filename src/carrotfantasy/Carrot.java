package carrotfantasy;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Carrot extends JPanel implements Runnable{
	private int HP;
	private JLabel carrot, heart;
	private static ImageReader imgReader = new ImageReader();
	private ImageIcon[] carrotTexes;
	private ImageIcon[] heartTexes;
	private boolean paused;
	private boolean isInterrupted;

	Carrot(int x, int y, int hy){
		HP = 10;
		this.setBounds(x, y, 200, 128);
		carrot = new JLabel();
		carrot.setBounds(0, 0, 80, 128);
		heart = new JLabel();
		heart.setBounds(80, hy, 74, 38);
		this.isInterrupted = false;
		this.carrotTexes = new ImageIcon[10];
		this.heartTexes = new ImageIcon[10];
		this.paused = false;
		this.setLayout(null);
		this.add(carrot);
		this.add(heart);
		this.setVisible(true);
		this.setOpaque(false);
	}

	public void run() {
		carrotTexes[0] = imgReader.getImageIcon("Images\\Items\\Items01-hd.png", 1374, 2, 75, 126, 1, false);
		carrotTexes[1] = imgReader.getImageIcon("Images\\Items\\Items01-hd.png", 1297, 2, 77, 126, 1, false);
		carrotTexes[2] = imgReader.getImageIcon("Images\\Items\\Items01-hd.png", 1220, 2, 77, 126, 1, false);
		heartTexes[0] = imgReader.getImageIcon("Images\\Items\\Items02-hd.png", 930, 42, 74, 38, 1, false);
		carrot.setIcon(carrotTexes[0]);
		heart.setIcon(heartTexes[0]);
		int i = 0;
		while(true) {
			while(paused) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(!paused || this.isInterrupted) break;
			}
			if(this.isInterrupted) {
				break;
			}
			if(HP == 9) {
				if(carrotTexes[3] == null) {
					carrotTexes[3] = imgReader.getImageIcon("Images\\Items\\Items01-hd.png", 904, 2, 77, 126, 1, false);
					carrotTexes[4] = imgReader.getImageIcon("Images\\Items\\Items01-hd.png", 983, 2, 77, 126, 1, false);
				}
				carrot.setIcon(carrotTexes[3]);
				if(heartTexes[1] == null) heartTexes[1] = imgReader.getImageIcon("Images\\Items\\Items02-hd.png", 1458, 2, 74, 38, 1, false);
				heart.setIcon(heartTexes[1]);
			}else if(HP == 8) {
				if(heartTexes[2] == null) heartTexes[2] = imgReader.getImageIcon("Images\\Items\\Items02-hd.png", 866, 880, 38, 74, 1, true);
				heart.setIcon(heartTexes[2]);
			}else if(HP == 7) {
				if(carrotTexes[5] == null) {
					carrotTexes[5] = imgReader.getImageIcon("Images\\Items\\Items01-hd.png", 1062, 2, 77, 126, 1, false);
					carrotTexes[6] = imgReader.getImageIcon("Images\\Items\\Items01-hd.png", 1141, 2, 77, 126, 1, false);
				}
				carrot.setIcon(carrotTexes[5]);
				if(heartTexes[3] == null) heartTexes[3] = imgReader.getImageIcon("Images\\Items\\Items02-hd.png", 788, 960, 74, 38, 1, false);
				heart.setIcon(heartTexes[3]);
			}else if(HP == 6) {
				if(heartTexes[4] == null) heartTexes[4] = imgReader.getImageIcon("Images\\Items\\Items02-hd.png", 1038, 964, 74, 38, 1, false);
				heart.setIcon(heartTexes[4]);
			}else if(HP == 5) {
				if(carrotTexes[7] == null) {
					carrot.setBounds(-12, 25, 90, 128);
					carrotTexes[7] = imgReader.getImageIcon("Images\\Items\\Items01-hd.png", 1451, 2, 66, 96, 1, true);
				}
				carrot.setIcon(carrotTexes[7]);
				if(heartTexes[5] == null) heartTexes[5] = imgReader.getImageIcon("Images\\Items\\Items02-hd.png", 1152, 578, 74, 38, 1, false);
				heart.setIcon(heartTexes[5]);
			}else if(HP == 4) {
				if(heartTexes[6] == null) heartTexes[6] = imgReader.getImageIcon("Images\\Items\\Items02-hd.png", 1238, 645, 74, 38, 1, false);
				heart.setIcon(heartTexes[6]);
			}else if(HP == 3) {
				if(carrotTexes[8] == null) {
					carrot.setBounds(-12, 25, 90, 128);
					carrotTexes[8] = imgReader.getImageIcon("Images\\Items\\Items01-hd.png", 1519, 2, 66, 96, 1, true);
				}
				carrot.setIcon(carrotTexes[8]);
				if(heartTexes[7] == null) heartTexes[7] = imgReader.getImageIcon("Images\\Items\\Items02-hd.png", 1304, 965, 74, 38, 1, false);
				heart.setIcon(heartTexes[7]);
			}else if(HP == 2) {
				if(heartTexes[8] == null) heartTexes[8] = imgReader.getImageIcon("Images\\Items\\Items02-hd.png", 1380, 967, 74, 38, 1, false);
				heart.setIcon(heartTexes[8]);
			}else if(HP == 1) {
				if(carrotTexes[9] == null) {
					carrot.setBounds(-12, 35, 90, 128);
					carrotTexes[9] = imgReader.getImageIcon("Images\\Items\\Items01-hd.png", 1587, 2, 46, 96, 1, true);
				}
				carrot.setIcon(carrotTexes[9]);
				if(heartTexes[9] == null) heartTexes[9] = imgReader.getImageIcon("Images\\Items\\Items02-hd.png", 1416, 526, 74, 38, 1, false);
				heart.setIcon(heartTexes[9]);
			}else if(HP == 0) {
				carrot.setIcon(null);
				heart.setIcon(null);
			}
			try {
				Thread.sleep(100);
			}catch(Exception e) {}
		}
		
	}

	public void hurt(int h) {
		HP -= h;
	}

	public void renew() {
		this.isInterrupted = false;
		this.paused = false;
		carrot.setBounds(0, 0, 80, 128);
		carrot.setIcon(carrotTexes[0]);
		heart.setIcon(heartTexes[0]);
		HP = 10;
	}

	public int getHP() {
		return HP;
	}

	public void pause() {
		paused = true;
	}

	public void myResume() {
		paused = false;
	}
	
	public void interrupt() {
		isInterrupted = true;
	}
}