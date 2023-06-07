package carrotfantasy;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class MonsterThread extends Thread{
	private Monster[] monsters;
	private JLabel[] waveNums;
	private JLabel[] moneyNums;
	private JButton bottle;
	private JButton sunFlower;
	private JButton upgrade;
	private boolean paused, countDown0, countDown1, countDown2, countDown3;
	private int mode;
	private int monsterNum;
	private int wave;
	private int currentWave;
	public int money;
	private Carrot carrot;
	private GameOverPanel gameOverPane;
	private Tower[] towers;
	private JLabel countDown;
	private static MusicModule musicModule = new MusicModule();
	private static ImageReader imgReader = new ImageReader();
	private static ImageIcon bottleTexture1 = imgReader.getImageIcon("Images\\Towers\\TBottle-hd.png", 55, 2, 76, 78, 0.8, true);
	private static ImageIcon bottleTexture2 = imgReader.getImageIcon("Images\\Towers\\TBottle-hd.png", 55, 80, 76, 78, 0.8, true);
	private static ImageIcon sunFlowerTexture1 = imgReader.getImageIcon("Images\\Towers\\TSun-hd.png", 442, 1000, 76, 78, 0.8, true);
	private static ImageIcon sunFlowerTexture2 = imgReader.getImageIcon("Images\\Towers\\TSun-hd.png", 522, 1000, 76, 78, 0.8, false);
	private static ImageIcon upgrade_180 = imgReader.getImageIcon("Images\\Items\\Items02-hd.png", 994, 808, 78, 76, 0.8, true);
	private static ImageIcon upgrade_260 = imgReader.getImageIcon("Images\\Items\\Items02-hd.png", 906, 886, 78, 76, 0.8, true);
	private static ImageIcon upgrade_320 = imgReader.getImageIcon("Images\\Items\\Items02-hd.png", 924, 730, 78, 76, 0.8, true);
	private static ImageIcon countDown_1 = imgReader.getImageIcon("Images\\Items\\Items02-hd.png", 658, 688, 116, 74, 1, true);
	private static ImageIcon countDown_2 = imgReader.getImageIcon("Images\\Items\\Items02-hd.png", 688, 570, 96, 116, 1, false);
	private static ImageIcon countDown_3 = imgReader.getImageIcon("Images\\Items\\Items02-hd.png", 540, 680, 116, 98, 1, true);
	private static ImageIcon go = imgReader.getImageIcon("Images\\Items\\Items02-hd.png", 1280, 42, 200, 200, 1, false);

	MonsterThread(Monster[] mon, int m, JLabel[] wn, JLabel[] mn, JButton b, JButton sun, Carrot c, GameOverPanel gop, Tower[] ts, JButton up,
					JLabel cd){
		monsters = mon;
		money = 250 + m * 250;
		paused = false;
		waveNums = wn;
		moneyNums = mn;
		mode = m;
		wave = 5 * mode + 10;
		monsterNum = mon.length - (2 - mode) * 5 - 10 + currentWave / 2;
		currentWave = 1;
		bottle = b;
		sunFlower = sun;
		carrot = c;
		gameOverPane = gop;
		towers = ts;
		upgrade = up;
		countDown0 = false;
		countDown1 = false;
		countDown2 = false;
		countDown3 = false;
		countDown = cd;
	}
	
	public void run() {
		int[] dir = new int[monsterNum];
		for(int i = 0; i < monsterNum; i++) {
			dir[i] = 0;
		}
		waveNums[0].setIcon(YellowNum.nums[currentWave / 10]);
		waveNums[1].setIcon(YellowNum.nums[currentWave % 10]);
		waveNums[2].setIcon(WhiteNum.nums[wave / 10]);
		waveNums[3].setIcon(WhiteNum.nums[wave % 10]);
		if(money >= 100) {
			bottle.setIcon(bottleTexture1);
		}
		else {
			bottle.setIcon(bottleTexture2);
		}
		if(money >= 180) {
			sunFlower.setIcon(sunFlowerTexture1);
		}
		else {
			sunFlower.setIcon(sunFlowerTexture2);
		}
		int count = 0;
		int switchCount = 0;
		long currentTime;
		long deltaTime;
		long lastTime = System.currentTimeMillis();
		long duration = 0;
		moneyNums[0].setIcon(WhiteNum.nums[money / 1000]);
		moneyNums[1].setIcon(WhiteNum.nums[(money / 100) % 10]);
		moneyNums[2].setIcon(WhiteNum.nums[(money / 10) % 10]);
		moneyNums[3].setIcon(WhiteNum.nums[money % 10]);
		while(true) {
			while(paused) {
				lastTime = System.currentTimeMillis();
				if(!paused  || this.isInterrupted()) break;
			}
			if(this.isInterrupted()) break;
			currentTime = System.currentTimeMillis();
			deltaTime = currentTime - lastTime;
			duration += deltaTime;
			switchCount++; 
			if(Math.abs(duration - 7000) < 13 && !countDown3) {
				musicModule.play("countDown");
				countDown.setVisible(true);
				countDown.setIcon(countDown_3);
				countDown3 = true;
			}
			if(Math.abs(duration - 8000) < 20 && !countDown2) {
				musicModule.play("countDown");
				countDown.setIcon(countDown_2);
				countDown2 = true;
			}
			if(Math.abs(duration - 9000) < 20 && !countDown1) {
				musicModule.play("countDown");
				countDown.setIcon(countDown_1);
				countDown1 = true;
			}
			if(Math.abs(duration - 10000) < 20 && !countDown0) {
				musicModule.play("countDown");
				countDown.setIcon(go);
				countDown1 = true;
			}
			if(Math.abs(duration - 10500) < 20 && !countDown0) {
				countDown0 = true;
				countDown.setVisible(false);
			}
			for(int i = 0; i < monsterNum; i++) {
				if(monsters[i].reached || !monsters[i].alive) {
					continue;
				}
				
				if(i < (duration - 9000) / 1000) {
					monsters[i].setVisible(true);
					monsters[i].born = true;
					if(mode == 0) {
						switch(dir[i]) {
						case 0:
							monsters[i].yPos += (int)(deltaTime * Monster.speed);
							if(monsters[i].yPos >= 330) dir[i]++;
							break;
						case 1:
							monsters[i].xPos += (int)(deltaTime * Monster.speed);
							if(monsters[i].xPos >= 300) dir[i]++;
							break;
						case 2:
							monsters[i].yPos -= (int)(deltaTime * Monster.speed);
							if(monsters[i].yPos <= 250) dir[i]++;
							break;
						case 3:
							monsters[i].xPos += (int)(deltaTime * Monster.speed);
							if(monsters[i].xPos >= 545) dir[i]++;
							break;
						case 4:
							monsters[i].yPos += (int)(deltaTime * Monster.speed);
							if(monsters[i].yPos >= 330) dir[i]++;
							break;
						case 5:
							monsters[i].xPos += (int)(deltaTime * Monster.speed);
							if(monsters[i].xPos >= 785) dir[i]++;
							break;
						case 6:
							monsters[i].yPos -= (int)(deltaTime * Monster.speed);
							if(monsters[i].yPos <= 110) monsters[i].reached = true;
							break;
						}
					}else if(mode == 1) {
						switch(dir[i]) {
						case 0:
							monsters[i].xPos += (int)(deltaTime * Monster.speed);
							if(monsters[i].xPos >= 725) dir[i]++;
							break;
						case 1:
							monsters[i].yPos += (int)(deltaTime * Monster.speed);
							if(monsters[i].yPos >= 260) dir[i]++;
							break;
						case 2:
							monsters[i].xPos -= (int)(deltaTime * Monster.speed);
							if(monsters[i].xPos <= 166) dir[i]++;
							break;
						case 3:
							monsters[i].yPos += (int)(deltaTime * Monster.speed);
							if(monsters[i].yPos >= 420) dir[i]++;
							break;
						case 4:
							monsters[i].xPos += (int)(deltaTime * Monster.speed);
							if(monsters[i].xPos >= 680) monsters[i].reached = true;
							break;
						}
					}else if(mode == 2) {
						switch(dir[i]) {
						case 0:
							monsters[i].xPos += (int)(deltaTime * Monster.speed);
							if(monsters[i].xPos >= 160) dir[i]++;
							break;
						case 1:
							monsters[i].yPos += (int)(deltaTime * Monster.speed);
							if(monsters[i].yPos >= 420) dir[i]++;
							break;
						case 2:
							monsters[i].xPos += (int)(deltaTime * Monster.speed);
							if(monsters[i].xPos >= 320) dir[i]++;
							break;
						case 3:
							monsters[i].yPos -= (int)(deltaTime * Monster.speed);
							if(monsters[i].yPos <= 260) dir[i]++;
							break;
						case 4:
							monsters[i].xPos += (int)(deltaTime * Monster.speed);
							if(monsters[i].xPos >= 560) dir[i]++;
							break;
						case 5:
							monsters[i].yPos += (int)(deltaTime * Monster.speed);
							if(monsters[i].yPos >= 420) dir[i]++;
							break;
						case 6:
							monsters[i].xPos += (int)(deltaTime * Monster.speed);
							if(monsters[i].xPos >= 800) dir[i]++;
							break;
						case 7:
							monsters[i].yPos -= (int)(deltaTime * Monster.speed);
							if(monsters[i].yPos <= 110) monsters[i].reached = true;
							break;
						}
						
					}
					
					monsters[i].setText("HP:" + Integer.toString(monsters[i].HP));
					monsters[i].setBounds(monsters[i].xPos, monsters[i].yPos, 100, 110);
					
					if(switchCount % 8 == 0) monsters[i].switchType();
					if(monsters[i].HP <= 0) {
						count++;
						monsters[i].alive = false;
						monsters[i].setVisible(false);
						money += monsters[i].money;
						moneyNums[0].setIcon(WhiteNum.nums[money / 1000]);
						moneyNums[1].setIcon(WhiteNum.nums[(money / 100) % 10]);
						moneyNums[2].setIcon(WhiteNum.nums[(money / 10) % 10]);
						moneyNums[3].setIcon(WhiteNum.nums[money % 10]);
						if(money >= 320) {
							if(upgrade.getName() != null) {
								if(upgrade.getName().equals("_upgrade_320")) {
									upgrade.setIcon(upgrade_320);
									upgrade.setName(null);
								}
							}
						}else if(money >= 260) {
							if(upgrade.getName() != null) {
								if(upgrade.getName().equals("_upgrade_260")) {
									upgrade.setIcon(upgrade_260);
									upgrade.setName(null);
								}
							}
						}else if(money >= 180) {
							sunFlower.setIcon(sunFlowerTexture1);
							if(upgrade.getName() != null) {
								if(upgrade.getName().equals("_upgrade_180")) {
									upgrade.setIcon(upgrade_180);
									upgrade.setName(null);
								}
							}
						}else if(money >= 100) {
							bottle.setIcon(bottleTexture1);
						}
						
					}
					if(monsters[i].reached) {
						musicModule.play("crash");
						count++;
						monsters[i].setVisible(false);
						carrot.hurt(monsters[i].power);
						if(carrot.getHP() <= 0) {
							musicModule.play("lose");
							for(int j = 0; j < towers.length; j++) {
								if(towers[j] != null) towers[j].gameOver();
							}
							this.interrupt();
							gameOverPane.set(0, currentWave, 0);
							break;
						}
					}
				}
			}
			if(this.isInterrupted()) break;
			lastTime = currentTime;
			try {
				sleep(20);
			}catch(Exception e) {}
			if(count == monsterNum) {
				if(++currentWave > wave) {
					musicModule.play("perfect");
					for(int j = 0; j < towers.length; j++) {
						if(towers[j] != null) towers[j].gameOver();
					}
					gameOverPane.set(1, wave, carrot.getHP());
					break;
				}else {
					waveNums[0].setIcon(YellowNum.nums[currentWave / 10]);
					waveNums[1].setIcon(YellowNum.nums[currentWave % 10]);
					for(int i = 0; i < monsterNum; i++) {
						monsters[i].renew(currentWave);
						dir[i] = 0;
					}
					count = 0;
					switchCount = 0;
					duration = 5000;
				}
			}
		}
	}

	public void pause() {
		paused = true;
	}

	public void myResume() {
		paused = false;
	}
}