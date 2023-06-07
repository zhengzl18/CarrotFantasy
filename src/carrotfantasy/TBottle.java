package carrotfantasy;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.*;

public class TBottle extends Tower{
	private JButton bottle1;
	private JButton bottle2;
	private JButton cell;
	private static ImageReader imgReader = new ImageReader();
	private static ImageIcon texture1 = imgReader.getImageIcon("Images\\Towers\\TBottle-hd.png", 2, 264, 60, 60, 1, false);
	private static ImageIcon texture2 = imgReader.getImageIcon("Images\\Towers\\TBottle-hd.png", 15, 462, 56, 26, 1, true);
	private Monster[] monsters;
	private boolean ready;

	TBottle(int x, int y, Monster[] m, int mn, JButton c){
		this.power = 20;
		this.price = 100;
		this.upgradePrice = 180;
		this.level = 1;
		this.range = 200;
		this.cd = 300;
		this.monsters = m;
		this.xPos = x + 40;
		this.yPos = y + 40;
		this.monsterNum = mn;
		this.ready = true;
		this.cell = c;
		this.isInterrupted = false;
		this.setBounds(x - 360, y - 360, 800, 800);
		bottle1 = new JButton();
		bottle1.setBounds(360, 360, 80, 80);
		bottle1.setIcon(texture1);
		bottle1.setBorder(null);
		bottle1.setFocusPainted(false);
		bottle1.setContentAreaFilled(false);
		bottle2 = new JButton();
		bottle2.setBorder(null);
		bottle2.setFocusPainted(false);
		bottle2.setContentAreaFilled(false);
		bottle2.setBounds(360, 360, 80, 80);
		bottle2.setIcon(texture2);
		bottle2.addActionListener(new BottleActionListener(cell));
		this.add(bottle1, new Integer(-30001));
		this.add(bottle2, new Integer(-30000));
		this.setVisible(true);
	}

	public void upgrade() {
		level++;
		power += 10;
		cd -= 50;
		price += 80;
		upgradePrice += 80;
		range += 80;
		switch(level) {
		case 2:
			bottle2.setIcon(imgReader.getImageIcon("Images\\Towers\\TBottle-hd.png", 2, 356, 64, 36, 1, true));
			break;
		case 3:
			bottle2.setIcon(imgReader.getImageIcon("Images\\Towers\\TBottle-hd.png", 8, 132, 46, 70, 1, -180));
			break;
		}
	}

	public void run() {
		long lastTime = System.currentTimeMillis();
		while(true) {
			while(paused) {
				lastTime = System.currentTimeMillis();
				if(!paused  || isInterrupted) break;
			}
			if(this.isInterrupted) break;
			if(sold) {
				break;
			}
			if(System.currentTimeMillis() - lastTime > this.cd) {
				lastTime = System.currentTimeMillis();
				this.ready = true; 
			}
			for(int i = 0; i < monsterNum; i++) {
				if(monsters[i].reached || !monsters[i].alive || !monsters[i].born) {
					continue;
				}
				if(Math.sqrt(Math.pow(monsters[i].xPos + 50 - this.xPos, 2) + Math.pow(monsters[i].yPos + 55 - this.yPos, 2)) <= this.range){
					int deltaY = monsters[i].yPos + 55 - this.yPos;
					int deltaX = monsters[i].xPos + 50 - this.xPos;
					double orient = Math.toDegrees(Math.atan((double)deltaY / deltaX));
					if(deltaX < 0) orient += 180;
					if(deltaX == 0 && deltaY > 0) orient = 90;
					else if(deltaX == 0 && deltaY < 0) orient = -90;
					else if(deltaY == 0 && deltaX > 0) orient = 0; 
					else if(deltaY == 0 && deltaX < 0) orient = 180; 
					
					switch(level) {
					case 1:
						bottle2.setIcon(imgReader.getImageIcon("Images\\Towers\\TBottle-hd.png", 15, 462, 56, 26, 1, orient));
						break;
					case 2:
						bottle2.setIcon(imgReader.getImageIcon("Images\\Towers\\TBottle-hd.png", 2, 356, 64, 36, 1, orient));
						break;
					case 3:
						bottle2.setIcon(imgReader.getImageIcon("Images\\Towers\\TBottle-hd.png", 8, 132, 46, 70, 1, orient - 90));
						break;
					}
					if(this.ready) {
						musicModule.play("bottle");
						Bullet bullet = new Bullet(400 + deltaX, 400 + deltaY, orient);
						bullet.setIcon(imgReader.getImageIcon("Images\\Towers\\TBottle-hd.png", 2, 2, 50, 26, 1, orient));
						this.add(bullet);
						new Thread(bullet).start();
						this.ready = false;
						try {
							Thread.sleep(100);
						}catch(Exception e) {}
						monsters[i].HP -= this.power;
					}
					break;
				}
			}
			try {
				Thread.sleep(20);
			}catch(Exception e) {}
		}
	}
}

class Bullet extends JLabel implements Runnable{
	private int goalX, goalY;
	private double xPos, yPos;
	private double speed;
	private double cos, sin;

	Bullet(int gx, int gy, double or){
		cos = Math.cos(Math.toRadians(or));
		sin = Math.sin(Math.toRadians(or));
		xPos = 375 + 45 * cos;
		yPos = 375 + 45 * sin;
		goalX = gx;
		goalY = gy;
		speed = 1;
	}

	public void run() {
		long currentTime, deltaTime;
		this.setBounds((int)xPos, (int)yPos, 50, 50);
		long lastTime = System.currentTimeMillis();
		while(true) {
			currentTime = System.currentTimeMillis();
			deltaTime = currentTime - lastTime;
			xPos += deltaTime * speed * cos;
			yPos += deltaTime * speed * sin;
			if((goalX < 400 && xPos + 25 < goalX && goalY < 400 && yPos + 25 < goalY) ||
				(goalX > 400 && xPos + 25 > goalX && goalY < 400 && yPos + 25 < goalY) ||
				(goalX < 400 && xPos + 25 < goalX && goalY > 400 && yPos + 25 > goalY) ||
				(goalX > 400 && xPos + 25 > goalX && goalY > 400 && yPos + 25 > goalY) ||
				(goalX == 400 && goalY < 400 && yPos + 25 < goalY) ||
				(goalX == 400 && goalY > 400 && yPos + 25 > goalY) ||
				(goalY == 400 && goalX < 400 && xPos + 25 < goalX) ||
				(goalY == 400 && goalX > 400 && xPos + 25 > goalX)){
				this.setVisible(false);
				break;
			}
			this.setBounds((int)xPos, (int)yPos, 50, 50);
			lastTime = currentTime;
			try {
				Thread.sleep(2);
			}catch(Exception e) {}
		}
	}
}

class BottleActionListener implements ActionListener{
	private JButton cell;

	BottleActionListener(JButton c){
		cell = c;
	}

	public void actionPerformed(ActionEvent e) {
		cell.doClick();
	}
}