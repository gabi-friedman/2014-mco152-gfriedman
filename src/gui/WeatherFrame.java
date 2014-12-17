package friedman.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class WeatherFrame extends JFrame{
	
	public WeatherFrame(){
		this.setSize(800, 600);
		setTitle("Current Weather");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLocationRelativeTo(null);
		
		Container container = getContentPane(); //this holds other componants
		
		setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		//setLayout(new FlowLayout());
		container.add(new JLabel("Hello World"));
		container.add(new JLabel("This Class Rocks!"));
		container.add(new JLabel("Hello World"));
		
		/*
		BorderLayout Layout = new BorderLayout();
		container.setLayout(Layout);
		
		container.add(new JLabel("Hello World NORTH"), BorderLayout.NORTH);
		
		//add labels to container and containers to the north
		Container northContainer = new Container();
		northContainer.setLayout(new FlowLayout());
		northContainer.add(new JLabel("Hello World"));
		northContainer.add(new JLabel("Hello More World"));
		
		
		container.add(northContainer, BorderLayout.NORTH);
		container.add(new JLabel("Hello World SOUTH"), BorderLayout.SOUTH);
		container.add(new JLabel("Hello World EAST"), BorderLayout.EAST);
		container.add(new JLabel("Hello World WEST"), BorderLayout.WEST);
		
		//alternate way to add a JLable
		JLabel label = new JLabel("Hello World CENTER");
		label.setBackground(Color.PINK);
		label.setOpaque(true);
		container.add(label, BorderLayout.CENTER);
		*/
		
	}
	
	
	public static void main(String[] args){
		WeatherFrame frame = new WeatherFrame();
		frame.setVisible(true);
	}
	

}
