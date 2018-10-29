package temka.weather;

import temka.aircrafts.Coordinates;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class WeatherProvider {


	Random  randomGenerator = new Random();


	private static WeatherProvider WeatherProv = null;
	private static  String[]        weather         = {"SUN", "SNOW", "RAIN", "FOG"};
	private         Integer         algorithm       = 0;

	private WeatherProvider() {}

	public static WeatherProvider getProvider()
	{
		if (WeatherProv == null)
			WeatherProv = new WeatherProvider();
		return WeatherProv;
	}

	private String getRandomweather(Coordinates coords_) {
		algorithm = coords_.getLongitude() * coords_.getLatitude() * coords_.getHeight() + randomGenerator.nextInt(500);
		if (algorithm < 0)
			algorithm *= -1;
		return (weather[algorithm % weather.length]);
	}

	public String getCurrentweather(Coordinates coords_) {
		return (getRandomweather(coords_));
	}
}
