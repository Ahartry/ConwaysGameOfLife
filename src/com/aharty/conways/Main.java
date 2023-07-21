package com.aharty.conways;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import java.awt.BorderLayout;
import java.awt.Color;

public class Main {
	
	//dimensions for the grid
	static final int X = 80;
	static final int Y = 40;
	static int buttonSize = 80;
	static int coeffecient = 20;
	
	//stuff for the stop/start
	static Boolean startBool = true;
	static Timer timer = new Timer(150, new nextListener());
	
	 //stuff for the keybind
	static Boolean brushBool = false;
	static Action brushAction;
	static Action startAction;
	static Action nextAction;
	static Action clearAction;
	static Action saveAction;
	static Action revertAction;
	static Action exitAction;
	
	//general components
	static JFrame frame = new JFrame();
	static JPanel top = new JPanel();
	static JPanel panel = new JPanel(new GridLayout(Y, X));
	static JButton next = new JButton("Next");
	static JButton clear = new JButton("Clear");
	static JButton save = new JButton("Save");
	static JButton revert = new JButton("Revert");
	static JButton start = new JButton("Start");
	static JButton brush = new JButton("Brush");
	public static Square[][] squaresOLD = new Square[Y][X];
	public static Square[][] squaresNEW = new Square[Y][X];
	public static Square[][] squaresSAVE = new Square[Y][X];

	public static void main (String[] args) {
		
		//sets up button size for various sized windows
		buttonSize = ((X * coeffecient) / 7);
		
		//sets up key action
		brushAction = new brushAction();
		nextAction = new nextAction();
		clearAction = new clearAction();
		saveAction = new saveAction();
		revertAction = new revertAction();
		startAction = new startAction();
		exitAction = new exitAction();
		
		//sets up the actions
		panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('b'), "brushAction");
		panel.getActionMap().put("brushAction", brushAction);
		
		panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('n'), "nextAction");
		panel.getActionMap().put("nextAction", nextAction);
		
		panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('c'), "clearAction");
		panel.getActionMap().put("clearAction", clearAction);
		
		panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('s'), "saveAction");
		panel.getActionMap().put("saveAction", saveAction);

		panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('r'), "revertAction");
		panel.getActionMap().put("revertAction", revertAction);
		
		panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"), "startAction");
		panel.getActionMap().put("startAction", startAction);
		
		panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('e'), "exitAction");
		panel.getActionMap().put("exitAction", exitAction);
		
		//sets up the buttons
		next.addActionListener(new nextListener());
		next.setPreferredSize(new Dimension(buttonSize, 40));
		next.setFocusable(false);
		
		clear.addActionListener(new clearListener());
		clear.setPreferredSize(new Dimension(buttonSize, 40));
		clear.setFocusable(false);
		
		save.addActionListener(new saveListener());
		save.setPreferredSize(new Dimension(buttonSize, 40));
		save.setFocusable(false);
		
		revert.addActionListener(new revertListener());
		revert.setPreferredSize(new Dimension(buttonSize, 40));
		revert.setFocusable(false);
		revert.setEnabled(false);
		
		start.addActionListener(new startListener());
		start.setPreferredSize(new Dimension(buttonSize, 40));
		start.setFocusable(false);
		
		brush.addActionListener(new brushListener());
		brush.setPreferredSize(new Dimension(buttonSize, 40));
		brush.setFocusable(false);
		
		//sets up top panel with buttons
		top.setPreferredSize(new Dimension((X * coeffecient), 50));
		top.add(next, BorderLayout.CENTER);
		top.add(clear, BorderLayout.CENTER);
		top.add(save, BorderLayout.CENTER);
		top.add(revert, BorderLayout.CENTER);
		top.add(start, BorderLayout.CENTER);
		top.add(brush, BorderLayout.CENTER);
		
		panel.setPreferredSize(new Dimension((X * coeffecient), (Y * coeffecient)));
		panel.setBackground(new Color(100, 100, 100));
		
		//creates the new grid and adds to panel. Also initializes the old grid and save grid
		for (int i = 0; i < Y; i++) {
			for (int j = 0; j < X; j++) {
				squaresNEW[i][j] = new Square();
				panel.add(squaresNEW[i][j]);
				
				squaresOLD[i][j] = new Square();
				
				squaresSAVE[i][j] = new Square();
			}
		}
		
		//sets up the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(X * coeffecient, (Y * coeffecient + 50));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.add(top, BorderLayout.NORTH);
		frame.add(panel, BorderLayout.CENTER);
		frame.setTitle("Conway's/Aidan's game of life");
		frame.setResizable(false);
	}
	//key bindings
	public static class brushAction extends AbstractAction{


		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			brushBool = !brushBool;
			
			if (brushBool) {
				brush.setForeground(new Color(0, 150, 0));
			} else {
				brush.setForeground(Color.BLACK);
			}
		}		
	}
	
	public static class exitAction extends AbstractAction{


		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}		
	}
	
	public static class nextAction extends AbstractAction{


		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			next();
		}		
	}
	
	public static class clearAction extends AbstractAction{


		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < Y; i++) {
				for (int j = 0; j < X; j++) {
					squaresNEW[i][j].on = false;
					
					squaresNEW[i][j].update();
				}
			}
		}		
	}
	
	public static class saveAction extends AbstractAction{


		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			revert.setEnabled(true);
			
			for (int i = 0; i < Y; i++) {
				for (int j = 0; j < X; j++) {
					squaresSAVE[i][j].on = squaresNEW[i][j].on;
				}
			}
		}
			
	}
	
	public static class revertAction extends AbstractAction{


		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			revert.setEnabled(true);
			
			for (int i = 0; i < Y; i++) {
				for (int j = 0; j < X; j++) {
					squaresNEW[i][j].on = squaresSAVE[i][j].on;
					
					squaresNEW[i][j].update();
				}
			}
		}
			
	}
	
	public static class startAction extends AbstractAction{


		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			startBool = !startBool;
			
			
			if (startBool) {
				start.setText("Start");
				
				timer.stop();
				
			}else {
				start.setText("Stop");
				
				timer.start();
			}
		}
			
	}
	
	//button listeners
	public static class nextListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			next();
		}
		
	}
	//clears the sim0
	public static class clearListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			for (int i = 0; i < Y; i++) {
				for (int j = 0; j < X; j++) {
					squaresNEW[i][j].on = false;
					
					squaresNEW[i][j].update();
				}
			}
		}
		
	}
	//saves the sim state
	public static class startListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			startBool = !startBool;
			
			
			if (startBool) {
				start.setText("Start");
				
				timer.stop();
				
			}else {
				start.setText("Stop");
				
				timer.start();
			}
		}
		
	}
	//saves the sim state
	public static class saveListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			revert.setEnabled(true);
			
			for (int i = 0; i < Y; i++) {
				for (int j = 0; j < X; j++) {
					squaresSAVE[i][j].on = squaresNEW[i][j].on;
				}
			}
		}
		
	}
	//reverts the sim to the save
	public static class revertListener implements ActionListener{


		@Override
		public void actionPerformed(ActionEvent e) {
			
			for (int i = 0; i < Y; i++) {
				for (int j = 0; j < X; j++) {
					squaresNEW[i][j].on = squaresSAVE[i][j].on;
					
					squaresNEW[i][j].update();
				}
			}
		}
		
	}
	//activates brush mode
	public static class brushListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			brushBool = !brushBool;
			if (brushBool) {
				brush.setForeground(new Color(0, 150, 0));
			} else {
				brush.setForeground(Color.BLACK);
			}

			
		}
		
	}
	//this method has the code to go to the next generation
	public static void next() {
		//sets new array to old
		for (int i = 0; i < Y; i++) {
			for (int j = 0; j < X; j++) {
				squaresOLD[i][j].on = squaresNEW[i][j].on;
			}
		}
		
		//goes through all of them
		for (int i = 0; i < Y; i++) {
			for (int j = 0; j < X; j++) {
				
				//checks if the 8 nearby ones are on. Catch statement because edges would check outside range
				int counter = 0;
				try {
					if (squaresOLD[i-1][j-1].on) {
						counter++;
					} 
				}catch (Exception ex){
				
				}
				try {
					if (squaresOLD[i-1][j].on) {
						counter++;
					} 
				}catch (Exception ex){
					
				}
				try {
					if (squaresOLD[i-1][j+1].on) {
						counter++;
					} 
				}catch (Exception ex){
					
				}
				try {
					if (squaresOLD[i][j+1].on) {
						counter++;
					} 
				}catch (Exception ex){
					
				}
				try {
					if (squaresOLD[i][j-1].on) {
						counter++;
					} 
				}catch (Exception ex){
					
				}
				try {
					if (squaresOLD[i+1][j+1].on) {
						counter++;
					} 
				}catch (Exception ex){
					
				}
				try {
					if (squaresOLD[i+1][j].on) {
						counter++;
					}
				}catch (Exception ex){

				}
				try {
					if (squaresOLD[i+1][j-1].on) {
						counter++;
					} 
					
				}catch (Exception ex){
					
				}
				
				//checks if current square is on
				if (squaresOLD[i][j].on) {
					if (counter < 2) {
						squaresNEW[i][j].on = false;
					} else if (counter > 3) {
						squaresNEW[i][j].on = false;
					} else {
						squaresNEW[i][j].on = true;
					} 
				}
				else {
					if (counter == 3) {
						squaresNEW[i][j].on = true;
					}
					else {
						squaresNEW[i][j].on = false;
					}
				}
				squaresNEW[i][j].update();
				
			}
		}
	}

}
	

