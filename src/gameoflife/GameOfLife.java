package friedman.gameoflife;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOfLife extends JFrame{

	private final int ROWS = 40;
	private final int COLS = 40;
	private JButton cells[][];
	private JButton cellsNextGen[][];
	private ArrayList<Integer> greensToUpdate;
	private ArrayList<Integer> blacksToUpdate;
	
	public GameOfLife(){
		this.setSize(800, 600);
		setTitle("Current Weather");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		cells = new JButton[COLS][ROWS];
		cellsNextGen = new JButton[COLS][ROWS];
		greensToUpdate = new ArrayList<Integer>();
		blacksToUpdate = new ArrayList<Integer>();
		
		BorderLayout container = new BorderLayout();
		
		
		
		setLayout(container);
		add( getNextGenBtn(), BorderLayout.WEST);
		add( getGame(), BorderLayout.CENTER);
			
	}
	
	public JComponent getNextGenBtn(){
		
		JButton nextGenBtn = new JButton("Next Gen");
		
		ActionListener listener = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				nextGeneration();
			}
		};
		
		nextGenBtn.addActionListener(listener);
		
		return nextGenBtn;
	}
	
	public JComponent getGame(){
		JPanel game = new JPanel();
		GridLayout mngr = new GridLayout(ROWS,COLS);
		game.setLayout(mngr);
		
		ActionListener listener = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				JButton button = (JButton) event.getSource();
				if(button.getBackground() == Color.BLACK){
					button.setBackground(Color.GREEN);
				}
				else if(button.getBackground() == Color.GREEN){
					button.setBackground(Color.BLACK);
				}
			}
		};
		
		Random random = new Random();
		
		for(int i = 0; i < COLS; i++){
			for(int j = 0; j < ROWS; j++){
				final JButton button = new JButton();
				cells[i][j] = button;
				game.add(button);
				button.addActionListener(listener);
				
				int numAlive = random.nextInt(100);
				if( numAlive < 30 ){
					button.setBackground(Color.GREEN);
				}
				else{
					button.setBackground(Color.BLACK);
				}
			}
			
		}
		
		return game;
	}
	
	public int getAliveNeighbors(int i, int j){
		int numAlive = 0;
		try{
			if(	isAlive(i-1, j-1) ){ numAlive++; }
			if(	isAlive(i-1, j  ) ){ numAlive++; }
			if(	isAlive(i-1, j+1) ){ numAlive++; }
			if(	isAlive(i  , j+1) ){ numAlive++; }
			if(	isAlive(i  , j-1) ){ numAlive++; }
			if(	isAlive(i+1, j-1) ){ numAlive++; }
			if(	isAlive(i+1, j  ) ){ numAlive++; }
			if(	isAlive(i+1, j+1) ){ numAlive++; }
		}catch(ArrayIndexOutOfBoundsException e){
			
		}
		
		
		return numAlive;
	}

	private boolean isAlive(int i, int j) {
		return cells[i][j].getBackground() == Color.GREEN;
	}
	
	public void nextGeneration(){
		for(int i = 0; i < COLS; i++){
			for(int j = 0; j < ROWS; j++){
				int neighbors = getAliveNeighbors(i,j);
				switch (neighbors) {
				case 0:
				case 1:
				case 4:
				case 5:
				case 6:
				case 7:
				case 8:
					blacksToUpdate.add(i);
					blacksToUpdate.add(j);
					break;
				case 2:
				case 3:
					greensToUpdate.add(i);
					greensToUpdate.add(j);
					break;
				}
			}
		}
		
		for(int i = 0; i < blacksToUpdate.size(); i++){
			cells[blacksToUpdate.get(0)][blacksToUpdate.get(1)].setBackground(Color.BLACK);
			blacksToUpdate.remove(0);
			blacksToUpdate.remove(1);
		}
		for(int i = 0; i < greensToUpdate.size(); i++){
			cells[greensToUpdate.get(0)][greensToUpdate.get(1)].setBackground(Color.BLACK);
			greensToUpdate.remove(0);
			greensToUpdate.remove(1);
		}
		
	}
	
	public static void main(String[] args){
		GameOfLife game = new GameOfLife();
		game.setVisible(true);
	}
	
	
}
