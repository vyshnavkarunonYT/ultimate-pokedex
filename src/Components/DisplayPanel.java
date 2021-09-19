package Components;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

import Accessories.Pokemon;

public class DisplayPanel extends MotionPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Data Variables
	Pokemon pokemon;
	
	//Design Variables
	Color POKEDEX_RED = new Color(204,0,0);
	Color POKEDEX_BLUE = new Color(59,76,202);
	Color POKEDEX_YELLOW = new Color(255,222,0);
	Color TRANSLUCENT = new Color(0,0,0,50);
	Color TRANSPARENT = new Color(0,0,0,0);
	Color POKEDEX_DISPLAY_BACKGROUND = new Color(255,255,255,250);
	
	
	
	//Control Variable
	boolean isDisplayActive = false;
	


	public DisplayPanel(JFrame parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	
	}

	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(isDisplayActive ? POKEDEX_DISPLAY_BACKGROUND:TRANSLUCENT);
		g2.fillRect(0, 0, 300, 370);
		
		g2.setColor(POKEDEX_RED);
		g2.fillRect(0, 0, 10, 370);
		g2.fillRect(290, 0, 10, 370);
		
		g2.setColor(POKEDEX_RED.darker());
		g2.fillRect(4, 0, 6, 370);
		g2.fillRect(290, 0, 6, 370);
		
		g2.setColor(POKEDEX_RED.darker().darker());
		g2.fillRect(8, 0, 2, 370);
		g2.fillRect(290, 0, 2, 370);
		
		//Displaying pokemon data
//		if(isDisplayActive) {
//			g2.setColor(Color.black);
//			g2.drawString("Loading...", 150, 125);
//		}

		
		
		
	}
	
	//Setters 
	public void activateDisplay(){
		isDisplayActive = true;
	}
	
	public void deactivateDisplay() {
		isDisplayActive = false;
	}
}
