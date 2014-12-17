package friedman.earthquakes;

import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import com.google.gson.Gson;

import friedman.weather.Weather;
import friedman.weather.WeatherFrame;
import friedman.weather.WeatherNow;

public class Earthquakes {

	GetEarthquakes eq;

	public Earthquakes() throws IOException{
		
		//getting from api
		URL url = new URL("http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/significant_month.geojson");
		StringBuilder build = new StringBuilder();
		URLConnection connection = url.openConnection();
		InputStream in = connection.getInputStream();

		byte b[] = new byte[4096];
		int n =-1;
		while((n = in.read(b))!= -1){
			String s = new String(b ,0, n);
			build.append(s);
		}

		String json = build.toString();
		Gson gson = new Gson();
		
		eq = new GetEarthquakes();
		eq = gson.fromJson(json, GetEarthquakes.class);

		
		//getting info
		
		
		//setting up container
		Container cont = new Container(); 
		cont.setLayout(new FlowLayout());
		
		cont.add(getEQs());
	}
	
	private Component getEQs() {
		JList list = new JList();
		
		return list;
	}

	public static void main(String[] args) throws IOException{
		Earthquakes c = new Earthquakes();
		c.setVisible(true);
	}

}
