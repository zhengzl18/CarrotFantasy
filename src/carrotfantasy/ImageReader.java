package carrotfantasy;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageReader {
	ImageIcon getImageIcon(String file, int x, int y, int width, int height, double ratio, boolean rotate){
		File imageFile=new File(file);
		BufferedImage img;
		ImageIcon imageicon = new ImageIcon();
		try {
			img = ImageIO.read(imageFile);
			BufferedImage outImg=img.getSubimage(x, y, width, height);
			if(rotate) outImg = rotate(outImg, -90.0);
			imageicon = new ImageIcon(outImg);
			imageicon = new ImageIcon(imageicon.getImage().getScaledInstance((int)(imageicon.getIconWidth() * ratio), (int)(imageicon.getIconHeight() * ratio), Image.SCALE_FAST));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imageicon;
	}
	
	ImageIcon getImageIcon(String file, int x, int y, int width, int height, double ratio, double degrees){
		File imageFile=new File(file);
		BufferedImage img;
		ImageIcon imageicon = new ImageIcon();
		try {
			img = ImageIO.read(imageFile);
			BufferedImage outImg=img.getSubimage(x, y, width, height);
			outImg = rotate(outImg, degrees);
			imageicon = new ImageIcon(outImg);
			imageicon = new ImageIcon(imageicon.getImage().getScaledInstance((int)(imageicon.getIconWidth() * ratio), (int)(imageicon.getIconHeight() * ratio), Image.SCALE_FAST));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imageicon;
	}
	
	public BufferedImage rotate(BufferedImage image, Double degrees) {
		double radians = Math.toRadians(degrees);
		double sin = Math.abs(Math.sin(radians));
		double cos = Math.abs(Math.cos(radians));
	    int newWidth = (int) Math.round(image.getWidth() * cos + image.getHeight() * sin);
	    int newHeight = (int) Math.round(image.getWidth() * sin + image.getHeight() * cos);
		
		BufferedImage rotate = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = rotate.createGraphics();
		int x = (newWidth - image.getWidth()) / 2;
		int y = (newHeight - image.getHeight()) / 2;
		AffineTransform at = new AffineTransform();
		at.setToRotation(radians, x + (image.getWidth() / 2), y + (image.getHeight() / 2));
		at.translate(x, y);
		g2d.setTransform(at);
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();
		return rotate;
	}
}