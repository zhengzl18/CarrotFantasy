package carrotfantasy;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Monster extends JLabel{
	public int HP;
	public int power;
	public int money;
	public static double speed;
	public int xPos, yPos;
	public boolean born;
	public boolean reached;
	public boolean alive;
	private int mode;
	private int type;
	private static ImageReader imgReader = new ImageReader();
	private static ImageIcon texture1;
	private static ImageIcon texture2;

	Monster(int m){
		super();
		mode = m;
		HP = 100 + 50 * mode;
		speed = 0.1 + 0.05 * mode;
		power = 1;
		switch(mode) {
		case 0:
			money = 10;
			xPos = 65;
			yPos = 110;
			if(texture1 == null) {
				texture1 = imgReader.getImageIcon("Images\\Theme1\\Items\\Monsters01-hd.png", 176, 430, 100, 69, 1, false);
				texture2 = imgReader.getImageIcon("Images\\Theme1\\Items\\Monsters01-hd.png", 286, 373, 94, 88, 1, false);
			}
			break;
		case 1:
			money = 10;
			xPos = 10;
			yPos = 100;
			if(texture1 == null) {
				texture1 = imgReader.getImageIcon("Images\\Theme2\\Items\\Monsters01-hd.png", 105, 496, 75, 65, 1, false);
				texture2 = imgReader.getImageIcon("Images\\Theme2\\Items\\Monsters01-hd.png", 221, 486, 65, 75, 1, true);
			}
			break;
		case 2:
			money = 15;
			xPos = 10;
			yPos = 175;
			if(texture1 == null) {
				texture1 = imgReader.getImageIcon("Images\\Theme3\\Items\\Monsters01-hd.png", 295, 62, 90, 80, 1, false);
				texture2 = imgReader.getImageIcon("Images\\Theme3\\Items\\Monsters01-hd.png", 388, 62, 95, 80, 1, false);
			}
			break;
		}
		
		born = false;
		reached = false;
		alive = true;
		type = 0;
		this.setVerticalTextPosition(JLabel.TOP);
		this.setHorizontalTextPosition(JLabel.CENTER);
		this.setBounds(xPos, yPos, 100, 110);
		this.setFont(new Font("Times New Roman", Font.BOLD, 15));
		this.setText("HP:" + Integer.toString(HP));
		this.setIcon(texture1);
		this.setVisible(false);
	}

	public void switchType() {
		if(type % 2 == 0) {
			this.setIcon(texture2);
			type++;
		}else {
			this.setIcon(texture1);
			type++;
		}
		
	}

	public void renew(int currentWave) {
		HP = 100 + 25 * mode + 25 * (mode + 1) * (currentWave - 1);
		switch(mode) {
		case 0:
			money = 10;
			xPos = 65;
			yPos = 110;
			if(texture1 == null) {
				texture1 = imgReader.getImageIcon("Images\\Theme1\\Items\\Monsters01-hd.png", 176, 430, 100, 69, 1, false);
				texture2 = imgReader.getImageIcon("Images\\Theme1\\Items\\Monsters01-hd.png", 286, 373, 94, 88, 1, false);
			}
			break;
		case 1:
			money = 10;
			xPos = 10;
			yPos = 100;
			if(texture1 == null) {
				texture1 = imgReader.getImageIcon("Images\\Theme2\\Items\\Monsters01-hd.png", 105, 496, 75, 65, 1, false);
				texture2 = imgReader.getImageIcon("Images\\Theme2\\Items\\Monsters01-hd.png", 221, 486, 65, 75, 1, true);
			}
			break;
		case 2:
			money = 15;
			xPos = 10;
			yPos = 175;
			if(texture1 == null) {
				texture1 = imgReader.getImageIcon("Images\\Theme3\\Items\\Monsters01-hd.png", 295, 62, 90, 80, 1, false);
				texture2 = imgReader.getImageIcon("Images\\Theme3\\Items\\Monsters01-hd.png", 388, 62, 95, 80, 1, false);
			}
			break;
		}
		born = false;
		reached = false;
		alive = true;
		type = 0;
		this.setVisible(false);
		this.setText("HP:" + Integer.toString(HP));
		this.setBounds(xPos, yPos, 100, 110);
	}

	public static void setTexNull() {
		texture1 = null;
		texture2 = null;
	}
}