package carrotfantasy;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class GameOverPanel extends JLayeredPane{
	private JLabel bg, trophy, text;
	private JLabel[] waveNums;
	private JButton tryAgain, chooseLevel;
	private static ImageReader imgReader = new ImageReader();

	GameOverPanel(JButton re, JButton cl){
		super();
		this.setBounds(130, 70, 700, 500);
		this.setVisible(false);
		bg = new JLabel();
		trophy = new JLabel();
		text = new JLabel();
		waveNums = new JLabel[2];
		waveNums[0] = new JLabel();
		waveNums[1] = new JLabel();
		tryAgain = re;
		chooseLevel = cl;
		this.add(bg, new Integer(0));
		this.add(trophy, new Integer(1));
		this.add(text, new Integer(1));
		this.add(waveNums[0], new Integer(1));
		this.add(waveNums[1], new Integer(1));
		this.add(tryAgain, new Integer(1));
		this.add(chooseLevel, new Integer(1));
	}

	public void set(int outcome, int wave, int heart) {
		if(outcome == 0) {
			bg.setBounds(2, 12, 696, 476);
			bg.setIcon(imgReader.getImageIcon("Images\\scene\\gameover0-hd.png", 2, 2, 696, 476, 1, false));
			text.setBounds(100, 208, 412, 50);
			text.setIcon(imgReader.getImageIcon("Images\\scene\\gameover0-hd.png", 700, 400, 412, 50, 1, false));
			waveNums[0].setBounds(284, 213, 44, 40);
			waveNums[1].setBounds(328, 213, 44, 40);
			waveNums[0].setIcon(YellowNum.nums[wave / 10]);
			waveNums[1].setIcon(YellowNum.nums[wave % 10]);
			tryAgain.setBounds(225, 395, 220, 72);
			tryAgain.setIcon(imgReader.getImageIcon("Images\\scene\\gameover-hd.png", 20, 2, 72, 220, 1, true));
			tryAgain.setVisible(true);
			chooseLevel.setBounds(445, 400, 236, 72);
			chooseLevel.setVisible(true);
		}else if(outcome == 1) {
			bg.setBounds(12, 52, 676, 396);
			bg.setIcon(imgReader.getImageIcon("Images\\scene\\gameover0-hd.png", 700, 2, 676, 396, 1, false));
			if(heart >= 8) {
				trophy.setBounds(275, 24, 128, 160);
				trophy.setIcon(imgReader.getImageIcon("Images\\scene\\gameover0-hd.png", 1378, 182, 128, 160, 1, false));
			}else if(4 <= heart && heart <= 7) {
				trophy.setBounds(276, 13, 126, 174);
				trophy.setIcon(imgReader.getImageIcon("Images\\scene\\gameover0-hd.png", 1378, 2, 126, 174, 1, false));
			}else if(heart <= 3) {		
				trophy.setBounds(280, 25, 118, 162);
				trophy.setIcon(imgReader.getImageIcon("Images\\scene\\gameover0-hd.png", 1378, 345, 118, 162, 1, false));
			}
			text.setBounds(120, 212, 412, 50);
			text.setIcon(imgReader.getImageIcon("Images\\scene\\gameover0-hd.png", 700, 400, 412, 50, 1, false));
			waveNums[0].setBounds(296, 215, 44, 40);
			waveNums[1].setBounds(340, 215, 44, 40);
			waveNums[0].setIcon(YellowNum.nums[wave / 10]);
			waveNums[1].setIcon(YellowNum.nums[wave % 10]);
			chooseLevel.setBounds(350, 400, 236, 72);
			chooseLevel.setVisible(true);
		}
		this.setVisible(true);
	}
}