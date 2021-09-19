package Components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JFrame;

public class TitlePanel extends MotionPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Elements
	Image cardBackgroundImage = null;
	
	
	//Design Variables
	Color POKEDEX_RED = new Color(204,0,0);
	Color POKEDEX_BLUE = new Color(59,76,202);
	Color TRANSPARENT = new Color(0,0,0,0);
	Color TRANSLUCENT = new Color(0,0,0,50);
	Color POKEDEX_DISPLAY_BACKGROUND = new Color(255,255,255,250);
	
	//Control Variables
	boolean isDisplayActive = false;
	
	public TitlePanel(JFrame parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}
	
	

	public void setCardBackgroundImage(Image image) {
//		System.out.println("Image set to " + image.toString());
		this.cardBackgroundImage = image;
	}
	
	public void activateDisplay() {
		isDisplayActive = true;
	}
	
	public void deactivateDisplay() {
		isDisplayActive = false;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		
		Graphics2D g2 = (Graphics2D) g;
		
		

		g2.setColor(isDisplayActive? POKEDEX_DISPLAY_BACKGROUND:TRANSLUCENT);
		g2.fillRect(0, 0, 300, 70);
		
		
	//	System.out.println(cardBackgroundImage!=null?cardBackgroundImage.toString():null);
		if(isDisplayActive) {
			g2.drawImage(cardBackgroundImage, 0, 0, 300, 70, 0, 0, 300, 70, null);
			g2.setColor(new Color(250,250,250,60));
			g2.fillRect(0, 0, 300, 70);
		}
		

		
		g2.setColor(POKEDEX_RED);
		g2.fillRect(0, 0, 45, 70);
		g2.fillRect(45, 0, 255, 55);
		
		int triangleX[] = {45,45, 65};
		int triangleY[] = {70, 52, 52};
		g2.fillPolygon(triangleX, triangleY, 3);
		
		//Horizontal Before Diagonal
		g2.setColor(POKEDEX_RED.darker());
		g2.fillRect(4, 65, 46, 5);
		g2.setColor(POKEDEX_RED.darker().darker());
		g2.fillRect(6, 68, 44, 5);
		

		
		//Horizontal After Diagonal
		g2.setColor(POKEDEX_RED.darker());
		g2.fillRect(65, 50, 231, 4);
		g2.setColor(POKEDEX_RED.darker().darker());
		g2.fillRect(65, 53, 228, 2);
		
		//RightMost Vertical
		g2.setColor(POKEDEX_RED);
		g2.fillRect(290, 55, 10, 15);
		g2.setColor(POKEDEX_RED.darker());
		g2.fillRect(290, 53, 6, 17);
		g2.setColor(POKEDEX_RED.darker().darker());
		g2.fillRect(290, 54, 2, 16);
		
		
		g2.setColor(POKEDEX_RED.darker());
		g2.setStroke(new BasicStroke(4));
		g2.drawLine(45, 68, 65, 52);
		g2.setColor(POKEDEX_RED.darker().darker());
		g2.setStroke(new BasicStroke(2));
		g2.drawLine(49, 68, 69, 52);
		

		
	}
	
}
