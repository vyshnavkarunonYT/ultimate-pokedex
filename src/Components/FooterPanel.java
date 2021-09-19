package Components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

public class FooterPanel extends MotionPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Design Variables
	Color POKEDEX_RED = new Color(204,0,0);
	Color POKEDEX_BLUE = new Color(59,76,202);
	Color POKEDEX_YELLOW = new Color(255,222,0);
	Color TRANSPARENT = new Color(0,0,0,50);

	public FooterPanel(JFrame parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 =(Graphics2D) g;
		
		g2.setColor(POKEDEX_RED);
		g2.fillRect(0, 0, 300, 60);
		
		g2.setColor(POKEDEX_RED.darker());
		g2.fillRect(4, 0, 292, 8);
		
		g2.setColor(POKEDEX_RED.darker().darker());
		g2.fillRect(6, 0, 288, 4);
		
	}

}
