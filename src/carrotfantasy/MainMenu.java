package carrotfantasy;

import java.awt.event.*;
import javax.swing.*;

public class MainMenu extends JFrame implements ActionListener{
	private JLabel bg;
	private JLabel title;
	private JLayeredPane layeredPane;
	private JPanel contentPane;
	private JButton btStart;
	private JButton[] btDifficulty;
	private ImageReader imgReader;
	private static MusicModule musicModule = new MusicModule();
	private static GamePanel gp;

	public static void main(String[] args) {
		MainMenu mainMenu = new MainMenu();
		mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	MainMenu(){
		this.setTitle("Carrot Fantasy");
		this.setSize(975, 640);
		this.setResizable(false);
		imgReader = new ImageReader();
		init();
	}
	
	void init(){
		musicModule.play("BGMusic");
		bg = new JLabel(imgReader.getImageIcon("Images\\scene\\mainscene1-hd.png", 2, 2, 640, 960, 1, true));
		bg.setSize(960, 640);
		
		layeredPane = this.getLayeredPane();
		layeredPane.add(bg, new Integer(-30010));
		
		title = new JLabel(imgReader.getImageIcon("Images\\scene\\mainscene1-hd.png", 644, 2, 534, 298, 1, false));
		title.setBounds(213, 281, 534, 298);

		btStart = new JButton(imgReader.getImageIcon("Images\\scene\\mainscene1-hd.png", 1180, 300, 122, 296, 1, true));
		btStart.setBounds(332, 53, 296, 122);
		btStart.setBorder(null);
		btStart.setContentAreaFilled(false);
		btStart.addActionListener(this);
		
		contentPane = (JPanel)this.getContentPane();
		contentPane.setOpaque(false);
		contentPane.setLayout(null);
		contentPane.add(btStart);
		contentPane.add(title);
		this.setVisible(true);
		
	}
	
	void selectDifficultyPage() {
		btDifficulty = new JButton[3];
		for(int i = 0; i < 3; i++) {
			btDifficulty[i] = new JButton(imgReader.getImageIcon("Images\\scene\\difficulty_bt_" + String.valueOf(i+1) + ".png", 0, 0, 296, 122, 1, false));
			btDifficulty[i].setBounds(332 + (i-1) * 300, 53, 296, 122);
			btDifficulty[i].setBorder(null);
			btDifficulty[i].setContentAreaFilled(false);
			btDifficulty[i].addActionListener(this);
			contentPane.add(btDifficulty[i]);
			contentPane.setVisible(false);
			contentPane.setVisible(true);
		}
		
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == (Object)btStart) {
			musicModule.play("select");
			btStart.setIcon(imgReader.getImageIcon("Images\\scene\\mainscene1-hd.png", 1180, 2, 122, 296, 1, true));
			btStart.setVisible(false);
			selectDifficultyPage();
		}else if(obj == (Object)btDifficulty[0]) {
			musicModule.play("select");
			try {
				gp= new GamePanel(0, this);
				new Thread(gp).start();
				Thread.sleep(1);
				gp.setVisible(true);
			} catch (InterruptedException exc) {}	
			this.dispose();
		}else if(obj == (Object)btDifficulty[1]) {
			musicModule.play("select");
			try {
				gp= new GamePanel(1, this);
				new Thread(gp).start();
				Thread.sleep(1);
				gp.setVisible(true);
			} catch (InterruptedException exc) {}	
			this.dispose();
		}else if(obj == (Object)btDifficulty[2]) {
			musicModule.play("select");
			try {
				gp= new GamePanel(2, this);
				new Thread(gp).start();
				Thread.sleep(1);
				gp.setVisible(true);
			} catch (InterruptedException exc) {}	
			this.dispose();
		}
	}
}