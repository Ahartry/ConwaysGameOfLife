package com.aharty.conways;

import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

import java.awt.event.MouseListener;
import javax.swing.*;

public class Square extends JLabel implements MouseListener{
	private static final long serialVersionUID = 1L;
	
	//variables needed for each square
	Boolean on = false;
	ImageIcon onImage = new ImageIcon(getClass().getClassLoader().getResource("on.png"));
	ImageIcon offImage = new ImageIcon(getClass().getClassLoader().getResource("off.png"));
	Border border = BorderFactory.createLineBorder(new Color(100, 100, 100), 1);

	//sets up the square
	public Square() {
		setIcon(offImage);
		addMouseListener(this);
		setBorder(border);
	}
	
	//refreshes the icon based off of the on bool
	public void update() {
		if (on) {
			setIcon(onImage);
		}else {
			setIcon(offImage);
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if (Main.brushBool) {
			on = true;
			
			if (on) {
				setIcon(onImage);
			}
			else {
				setIcon(offImage);
			}
			}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	//when pressed, the label will switch on state
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (!Main.brushBool) {
		on = !on;
		
		if (on) {
			setIcon(onImage);
		}
		else {
			setIcon(offImage);
		}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
