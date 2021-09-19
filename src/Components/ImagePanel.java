package Components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

class ImagePanel extends JPanel {

	private Image img = null;

	private int destX = 0;
	private int destY = 0;
	private int destHeight = 0;
	private int destWidth = 0;

	private boolean sourceAltered = false;
	private int sourceX = 0;
	private int sourceY = 0;
	private int sourceWidth = 0;
	private int sourceHeight = 0;
	
	
	//Style Components
	boolean addGlassLayerOnTop = false;

	public ImagePanel(String img) {
		this(new ImageIcon(img).getImage());
	}

	public ImagePanel(Image img) {
		this.img = img;
		this.destX = 0;
		this.destY = 0;
	}

	public ImagePanel(Image img, int sx, int sy, int sw, int sh) {
		this.img = img;
		this.sourceAltered = true;
		this.sourceX = sx;
		this.sourceY = sy;
		this.sourceWidth = sw;
		this.sourceHeight = sh;
	}
	
	
	public void setImage(Image img) {
		this.img = img;
	}
	
	public void setImage(Image img, int sx, int sy, int sw, int sh) {
		this.img = img;
		this.sourceX = sx;
		this.sourceY = sy;
		this.sourceWidth = sw;
		this.sourceHeight = sh;
	}
	
	public void setGlassLayerEnabled(boolean val) {
		this.addGlassLayerOnTop = val;
	}
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		try {
			if (sourceAltered) {
				g2.drawImage(img, destX, destY, destWidth == 0 ? this.getWidth() : destWidth,
						destHeight == 0 ? this.getHeight() : destHeight, sourceX, sourceY, sourceX+sourceWidth, sourceY+sourceHeight,
						null);
			} else {
				g2.drawImage(img, destX, destY, destWidth == 0 ? this.getWidth() : destWidth,
						destHeight == 0 ? this.getHeight() : destHeight, 0, 0, img.getWidth(null), img.getHeight(null),
						null);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			g2.fillRect(destX, destY, destWidth, destHeight);
		}
		
		if(addGlassLayerOnTop) {
			g2.setPaint(new Color(250,250,250,60));
			g2.fillRect(destX, destY, this.getWidth(), this.getHeight());
		}
		
	}
}
