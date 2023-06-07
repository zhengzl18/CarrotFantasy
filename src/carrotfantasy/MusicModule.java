package carrotfantasy;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class MusicModule {
	private static Clip BGMusic, select, towerBuild, towerSell, towerUpgrade, towerSelect, towerDeselect, crash, bottle, sun, lose, countDown,
						perfect;
	private static AudioInputStream BGMusicStream;
	private static boolean BGMPlaying = false;
	private static File selectFile, towerBuildFile, towerSellFile, towerUpgradeFile, towerSelectFile, towerDeselectFile, crashFile, bottleFile,
						sunFile, loseFile, countDownFile, perfectFile;
	
	MusicModule(){
		try {
			File BGMusicFile = new File("Music\\Main\\BGMusic.wav");
			selectFile = new File("Music\\Main\\select.wav");
			towerBuildFile = new File("Music\\Items\\TowerBuild.wav");
			towerSellFile = new File("Music\\Items\\TowerSell.wav");
			towerUpgradeFile = new File("Music\\Items\\TowerUpgrade.wav");
			towerSelectFile = new File("Music\\Items\\TowerSelect.wav");
			towerDeselectFile = new File("Music\\Items\\TowerDeselect.wav");
			crashFile = new File("Music\\Items\\Crash.wav");
			bottleFile = new File("Music\\Towers\\Bottle.wav");
			sunFile = new File("Music\\Towers\\Sun.wav");
			loseFile = new File("Music\\Items\\Lose.wav");
			countDownFile = new File("Music\\Items\\CountDown.wav");
			perfectFile = new File("Music\\Items\\Perfect.wav");
			BGMusicStream = AudioSystem.getAudioInputStream(BGMusicFile);
			BGMusic = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, BGMusicStream.getFormat()));
		} catch (UnsupportedAudioFileException e1) {
			System.out.println("The specified audio file is not supported.");
			e1.printStackTrace();
		} catch (IOException e1) {
			System.out.println("Error playing the audio file.");
			e1.printStackTrace();
		} catch (LineUnavailableException e) {
			System.out.println("Audio line for playing back is unavailable.");
			e.printStackTrace();
		}
	}

	public void play(String fileName) {
		try {
			if(fileName.equals("BGMusic") && !BGMPlaying) {
				BGMPlaying = true;
				BGMusic.open(BGMusicStream);
				BGMusic.loop(50);
			}else if(fileName.equals("select")) {
				AudioInputStream selectStream = AudioSystem.getAudioInputStream(selectFile);
				select = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, selectStream.getFormat()));
				select.open(selectStream);
				select.start();
			}else if(fileName.equals("towerBuild")) {
				AudioInputStream towerBuildStream = AudioSystem.getAudioInputStream(towerBuildFile);
				towerBuild = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, towerBuildStream.getFormat()));
				towerBuild.open(towerBuildStream);
				towerBuild.start();
			}else if(fileName.equals("towerSell")) {
				AudioInputStream towerSellStream = AudioSystem.getAudioInputStream(towerSellFile);
				towerSell = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, towerSellStream.getFormat()));
				towerSell.open(towerSellStream);
				towerSell.start();
			}else if(fileName.equals("towerUpgrade")) {
				AudioInputStream towerUpgradeStream = AudioSystem.getAudioInputStream(towerUpgradeFile);
				towerUpgrade = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, towerUpgradeStream.getFormat()));
				towerUpgrade.open(towerUpgradeStream);
				towerUpgrade.start();
			}else if(fileName.equals("crash")) {
				AudioInputStream crashStream = AudioSystem.getAudioInputStream(crashFile);
				crash = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, crashStream.getFormat()));
				crash.open(crashStream);
				crash.start();
			}else if(fileName.equals("towerSelect")) {
				AudioInputStream towerSelectStream = AudioSystem.getAudioInputStream(towerSelectFile);
				towerSelect = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, towerSelectStream.getFormat()));
				towerSelect.open(towerSelectStream);
				towerSelect.start();
			}else if(fileName.equals("towerDeselect")) {
				AudioInputStream towerDeselectStream = AudioSystem.getAudioInputStream(towerDeselectFile);
				towerDeselect = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, towerDeselectStream.getFormat()));
				towerDeselect.open(towerDeselectStream);
				towerDeselect.start();
			}else if(fileName.equals("bottle")) {
				AudioInputStream bottleStream = AudioSystem.getAudioInputStream(bottleFile);
				bottle = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, bottleStream.getFormat()));
				bottle.open(bottleStream);
				bottle.start();
			}else if(fileName.equals("sun")) {
				AudioInputStream sunStream = AudioSystem.getAudioInputStream(sunFile);
				sun = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, sunStream.getFormat()));
				sun.open(sunStream);
				sun.start();
			}else if(fileName.equals("lose")) {
				AudioInputStream loseStream = AudioSystem.getAudioInputStream(loseFile);
				lose = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, loseStream.getFormat()));
				lose.open(loseStream);
				lose.start();
			}else if(fileName.equals("countDown")) {
				AudioInputStream countDownStream = AudioSystem.getAudioInputStream(countDownFile);
				countDown = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, countDownStream.getFormat()));
				countDown.open(countDownStream);
				countDown.start();
			}else if(fileName.equals("perfect")) {
				AudioInputStream perfectStream = AudioSystem.getAudioInputStream(perfectFile);
				perfect = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, perfectStream.getFormat()));
				perfect.open(perfectStream);
				perfect.start();
			}
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}
	
	public void stop(String fileName) {
		if(fileName.equals("BGMusic")) {
			BGMusic.stop();
		}
	}
}