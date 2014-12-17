package friedman.weather;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.google.gson.Gson;
import com.sun.prism.Image;

public class WeatherFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Weather ws[];
	JComponent comp[];

	public WeatherFrame() throws IOException{
		wn = new WeatherNow();
		this.setSize(800, 600);
		setTitle("Current Weather");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		Container container = new Container();
		container.setLayout(new FlowLayout );

		container.add(getCurTemp());
		container.add(getPlc());
		container.add(getMinMax());
		
		ws = new Weather[wn.getWeather().length];
		ws = wn.getWeather();

		for(int i = 0;i < ws.length; i++){
			JPanel x = getSingleWeather(i);
			container.add(x);			
		}	
		
		//container.add(getAllWeather());
		

		WeatherDonloadThread thread = new WeatherDonloadThread();
		thread.start();


	}

	public JPanel nextLine(){
		JPanel temp = new JPanel();
		return temp;
	}
	
	public JPanel getCurTemp(){
		JPanel temp = new JPanel();
		BoxLayout mngr = new BoxLayout(temp, BoxLayout.LINE_AXIS);
		temp.setLayout(mngr);
		
		temp.add(new JLabel(String.valueOf(wn.getMain().getTemp())));
		
		return temp;
	}
	
	public JPanel getPlc(){
		JPanel plc = new JPanel();
		BoxLayout mngr = new BoxLayout(plc, BoxLayout.LINE_AXIS);
		plc.setLayout(mngr);
		
		plc.add( new JLabel(String.valueOf(wn.getName())));
		
		return plc;
	}
	
	public JPanel getMinMax(){
		JPanel minmax = new JPanel();
		FlowLayout mngr = new FlowLayout();
		minmax.setLayout(mngr);
		
		
		minmax.add( new JLabel(String.valueOf("\nMin: "+wn.getMain().getTemp_min())));
		minmax.add( new JLabel(String.valueOf("\nMax: "+wn.getMain().getTemp_max())));		
		
		return minmax;
	}
	
	public JPanel getAllWeather() throws IOException{
		JPanel allW = new JPanel();
		FlowLayout mngr = new FlowLayout();
		allW.setLayout(mngr);
		
		ws = new Weather[wn.getWeather().length];
		ws = wn.getWeather();

		for(int i = 0;i < ws.length; i++){
			JPanel x = getSingleWeather(i);
			allW.add(x);			
		}	
				
		return allW;
	}
	
	
	public JPanel getSingleWeather(int indexNum) throws IOException{
		JPanel weather = new JPanel();
		FlowLayout mngr = new FlowLayout();
		weather.setLayout(mngr);		

		BufferedImage myPicture = ImageIO.read(new URL ("http://openweathermap.org/img/w/"+ws[indexNum].getIcon()+".png"));

		
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		weather.add(picLabel);	
		
		String main = String.valueOf("Main: " + ws[indexNum].getMain()+"\n");
		weather.add(new JLabel(main));
		
		String description = String.valueOf("Desc: " + ws[indexNum].getDescription()+"\n");
		weather.add(new JLabel(description));

		return weather;
	}


	public static void main(String[] args) throws IOException{
		WeatherFrame c = new WeatherFrame();
		c.setVisible(true);
	}


}
