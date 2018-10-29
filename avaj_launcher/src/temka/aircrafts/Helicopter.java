package temka.aircrafts;

import temka.inter.Flyable;
import temka.weather.WeatherTower;
import temka.tower.Tower;

public class Helicopter extends Aircraft implements Flyable
{
	private WeatherTower weatherTower;

	Helicopter(String name, Coordinates coordinates)
	{
		super(name, coordinates);
	}

	public void     updateConditions()
	{
		String temp = "Helicopter#" + this.name + "(" + this.id + ") :";
		String writerToFile = "";
		String writerToFileUnregister = "";
		String weatherTemp = weatherTower.getWeather(this.coords);

		switch (weatherTemp) {
			case "SUN":
				coords = new Coordinates((coords.getLongitude() + 10), coords.getLatitude(), coords.getHeight() + 2);
				writerToFile = temp + " What does the name Sunny mean? We are americans. Our names don't mean shit. \n";
				break;
			case "SNOW":
				coords = new Coordinates(coords.getLongitude(), coords.getLatitude(), (coords.getHeight() - 12));
				writerToFile =  temp + " You know what they call snow in France? Royale with snow. \n";
				break;
			case "RAIN":
				coords = new Coordinates((coords.getLongitude() + 5), coords.getLatitude(), coords.getHeight());
				writerToFile =  temp + " I f***ing hate rain. \n";
				break;
			case "FOG":
				coords = new Coordinates((coords.getLongitude() + 1), coords.getLatitude(), coords.getHeight());
				writerToFile = temp + " Foggy, foggy, little froggy, don't come here, or even near! \n";
				break;
		}

		weatherTower.writeOutToFile("write", writerToFile);

		if (this.coords.getHeight() < 1)
		{
			writerToFileUnregister = "Helicopter#" + this.name + "(" + this.id + ") has unregistered from weather tower.\n";
			weatherTower.writeOutToFile("write", writerToFileUnregister);
			weatherTower.unregister(this);
		}
	}
	public void     registerTower(WeatherTower weatherTower)
	{
		weatherTower.register(this);
		String writeTo = "Helicopter#" + this.name + "(" + this.id + ") has registered to weather tower.\n";
		this.weatherTower = weatherTower;
		weatherTower.writeOutToFile("write", writeTo);

	}
}
