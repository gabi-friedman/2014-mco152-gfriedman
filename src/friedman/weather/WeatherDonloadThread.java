package friedman.weather;

import java.awt.Container;
import java.awt.FlowLayout;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;

public class WeatherDonloadThread extends Thread{
	
	@Override
	public void run(){
		try{
			URL url = new URL(" http://api.openweathermap.org/data/2.5/weather?q=sydney&units=imperial");
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
			
			WeatherNow wn = gson.fromJson(json, WeatherNow.class);
		} catch (IOException e){
			e.printStackTrace();
		}
	}
}
