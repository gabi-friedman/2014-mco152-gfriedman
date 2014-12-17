package friedman.weather;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;

public class CurrentWeather {
	URL url;
	URLConnection con;
	InputStream in;
	byte[] b;
	int n;
	String json;
	Gson gson;
	WeatherNow now;

	public CurrentWeather() throws IOException{
		url = new URL("http://api.openweathermap.org/data/2.5/weather?q=Brooklyn&units=imperial");
		con = url.openConnection();
		in = con.getInputStream();
		b = new byte[4096];
		n = -1;
		now = gson.fromJson(json, WeatherNow.class);
		
		StringBuilder build = new StringBuilder();
		while((n = in.read(b)) != -1){
			String s = new String(b, 0, n);
			build.append(s);
		}

		json = build.toString();

		gson = new Gson();

	}
	
	//Main group
	public double getTemp(){
		return now.getMain().getTemp();
	}
	public double getTempMax(){
		return now.getMain().getTemp_max();
	}
	public double getTempMin(){
		return now.getMain().getTemp_min();
	}
	public double getHumidity(){
		return now.getMain().getHumidity();
	}
	public double getPressure(){
		return now.getMain().getPressure();
	}
	
	
	

	/*public static void main(String[] args) throws IOException { //MURLException extends IO
		URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=Brooklyn&units=imperial");

		URLConnection con = url.openConnection();

		InputStream in = con.getInputStream();
		//doesnt care abt string int double... cares abt byte

		byte[] b = new byte[4096];
		int n = -1;
		StringBuilder build = new StringBuilder();
		while((n = in.read(b)) != -1){
			//while there is stuff to read
			//n is the amt of bytes returned
			//read from the InputStream in and then stic everything into the byte[] b
			String s = new String(b, 0, n);
			build.append(s);
			//create a string from the bytes

			//System.out.print(s);


		}
		String json = build.toString();

		Gson gson = new Gson();
		//giv it a string from JSON and tell it to turn the string into a class

		WeatherNow now = gson.fromJson(json, WeatherNow.class);

		System.out.println(now.getMain().getTemp());
	}*/

}
