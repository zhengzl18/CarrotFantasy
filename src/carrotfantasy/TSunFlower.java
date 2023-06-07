package carrotfantasy;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class TSunFlower extends Tower{
	private JLabel flower1;
	private JLabel flower2;
	private JLabel flame;
	private static ImageReader imgReader = new ImageReader();
	private static ImageIcon texture1 = imgReader.getImageIcon("Images\\Towers\\TSun-hd.png", 384, 620, 64, 64, 1, false);
	private static ImageIcon texture2 = imgReader.getImageIcon("Images\\Towers\\TSun-hd.png", 362, 1080, 46, 46, 1, false);
	private Monster[] monsters;

	TSunFlower(int x, int y, Monster[] m, int mn){
		this.power = 20;
		this.price = 180;
		this.upgradePrice = 260;
		this.level = 1;
		this.range = 150;
		this.cd = 1000;
		this.monsters = m;
		this.xPos = x + 40;
		this.yPos = y + 40;
		this.monsterNum = mn;
		this.attacking = false;
		this.paused = false;
		this.isInterrupted = false;
		this.setBounds(x - 360, y - 360, 800, 800);
		flower1 = new JLabel();
		flower1.setBounds(360, 360, 80, 80);
		flower1.setHorizontalAlignment(JLabel.CENTER);
		flower2 = new JLabel();
		flower2.setBounds(360, 360, 80, 80);
		flower2.setHorizontalAlignment(JLabel.CENTER);
		flower1.setIcon(texture1);
		flower2.setIcon(texture2);
		
		flame = new JLabel();
		flame.setHorizontalAlignment(JLabel.CENTER);
		flame.setBounds(0, 0, 800, 800);
		this.add(flower1, new Integer(-30001));
		this.add(flower2, new Integer(-30000));
		this.add(flame, new Integer(-29999));
		this.setVisible(true);
	}

	public void upgrade() {
		level++;
		power += 15;
		cd -= 100;
		switch(level) {
		case 2:
			price = 260;
			upgradePrice = 320;
			flower1.setIcon(imgReader.getImageIcon("Images\\Towers\\TSun-hd.png", 384, 548, 70, 70, 1, false));
			flower2.setIcon(imgReader.getImageIcon("Images\\Towers\\TSun-hd.png", 256, 1830, 52, 52, 1, false));
			break;
		case 3:
			price = 320;
			flower1.setIcon(imgReader.getImageIcon("Images\\Towers\\TSun-hd.png", 362, 1000, 78, 78, 1, false));
			flower2.setIcon(imgReader.getImageIcon("Images\\Towers\\TSun-hd.png", 450, 620, 60, 60, 1, false));
			break;
		}
		range = 200;
	}
	
	public void run() {
		while(true) {
			while(paused) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(!paused || isInterrupted) break;
			}
			if(this.isInterrupted) break;
			if(sold) {
				break;
			}
			for(int i = 0; i < monsterNum; i++) {
				if(monsters[i].reached || !monsters[i].alive || !monsters[i].born) {
					continue;
				}
				if(Math.sqrt(Math.pow(monsters[i].xPos + 50 - this.xPos, 2) + Math.pow(monsters[i].yPos + 50 - this.yPos, 2)) <= this.range){
					musicModule.play("sun");
					monsters[i].HP -= this.power;
					this.attacking = true;
				}
			}
			if(attacking) {
				try {
					switch(level) {
					case 1:
						flame.setIcon(imgReader.getImageIcon("Images\\Towers\\TSun-hd.png", 362, 770, 228, 228, 1, false));
						flame.setVisible(true);
						Thread.sleep((int)(this.cd * 1 / 12));
						flame.setIcon(imgReader.getImageIcon("Images\\Towers\\TSun-hd.png", 2, 1758, 252, 252, 1, false));
						Thread.sleep((int)(this.cd * 1 / 12));
						flame.setIcon(imgReader.getImageIcon("Images\\Towers\\TSun-hd.png", 318, 1132, 286, 286, 1, false));
						Thread.sleep((int)(this.cd * 2 / 12));
						flame.setVisible(false);
						Thread.sleep(this.cd * 8 / 12);
						this.attacking = false;
						break;
					default:
						flame.setIcon(imgReader.getImageIcon("Images\\Towers\\TSun-hd.png", 312, 1756, 284, 282, 1, false));
						flame.setVisible(true);
						Thread.sleep((int)(this.cd * 1 / 12));
						flame.setIcon(imgReader.getImageIcon("Images\\Towers\\TSun-hd.png", 2, 1132, 316, 314, 1, false));
						Thread.sleep((int)(this.cd * 1 / 12));
						flame.setIcon(imgReader.getImageIcon("Images\\Towers\\TSun-hd.png", 2, 770, 360, 358, 1, false));
						Thread.sleep((int)(this.cd * 2 / 12));
						flame.setVisible(false);
						Thread.sleep(this.cd * 8 / 12);
						this.attacking = false;
						break;
					}
				}catch(Exception e) {}
			}
		}
	}
}