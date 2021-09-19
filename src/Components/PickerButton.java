package Components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class PickerButton extends JButton {
	
	String type = "";
	boolean isActive = false;
	String name = "";
	
	public PickerButton(String name, String type, boolean isActive) {
		this.setBackground(getColor(type));
		this.type = type;
		this.name = name;
		this.isActive = isActive;
	}
	
	
	public void setColorWithType(String type) {
		this.type = type;
		this.setBackground(getColor(type));
	}
	
	
	public void setActivated(boolean isActive) {
		this.isActive = isActive;
		this.setBackground(getColor(this.type));
	}
	
	
	public Color getColor(String type) {
		Color color = Color.gray;
		
		
		type = type.toLowerCase();
//		if(type.equals("fire")) {
//			//Darkish Orange
//			color = Color.decode("#DD571C");
//		} if(type.equals("grass")) {
//			color = new Color(86,125,70);
//		} else if(type.equals("water")) {
//			color = Color.decode("#2832c2");
//		}	
//		
		if( this.isActive) {
			return color.darker();
		}
		
		return color.brighter();
	}	
}
