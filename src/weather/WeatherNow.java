package friedman.weather;

public class WeatherNow {
	private String name;
	private Weather[] weather;
	private Main main;
	private Sys sys;
	
	public String getName() {
		return name;
	}
	public Weather[] getWeather() {
		return weather;
	}
	public Main getMain() {
		return main;
	}
	public Sys getSys(){
		return sys;
	}
	

}
