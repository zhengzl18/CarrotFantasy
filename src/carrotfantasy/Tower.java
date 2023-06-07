package carrotfantasy;

import javax.swing.JLayeredPane;

public abstract class Tower extends JLayeredPane implements Runnable{
	protected int power;
	protected int range;
	protected int price, upgradePrice;
	protected int level;
	protected int cd;
	protected int xPos, yPos;
	protected int monsterNum;
	protected boolean attacking;
	protected boolean paused, sold, isInterrupted;
	protected static MusicModule musicModule = new MusicModule();

	public abstract void run();

	public void pause() {
		paused = true;
	}

	public void myResume() {
		paused = false;
	}

	public void sell() {
		sold = true;
	}

	public void interrupt() {
		this.setVisible(false);
		isInterrupted = true;
	}

	public void gameOver() {
		isInterrupted = true;
	}

	public int getLevel() {
		return this.level;
	}
	
	public abstract void upgrade();
}