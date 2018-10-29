package temka.aircrafts;

import temka.inter.Flyable;
import temka.weather.WeatherTower;
import java.util.Random;

public class Baloon extends Aircraft implements Flyable
{
		private WeatherTower weatherTower;

		Baloon(String name, Coordinates coordinates)
		{
			super(name, coordinates);
		}

		public void     updateConditions()
		{
			String weatherTemp = weatherTower.getWeather(this.coords);
			String writerToFile = "";
			String writerToFileUnregister = "";
			String temp = "Baloon#" + this.name + "(" + this.id + ") :";

			switch (weatherTemp) {
				case "SUN":
					coords = new Coordinates((coords.getLongitude() + 2), coords.getLatitude(), (coords.getHeight() + 4));
					writerToFile = temp + " Let's enjoy the moment and have passionate sex under the sparking sunlight.\n";
					break;
				case "SNOW":
					coords = new Coordinates(coords.getLongitude(), coords.getLatitude(), (coords.getHeight() - 15));
					writerToFile = temp + " I like snow, especially when flying in the baloon. Let's make a snowman and have a threesome. \n";
					break;
				case "RAIN":
					coords = new Coordinates(coords.getLongitude(), coords.getLatitude(), (coords.getHeight() - 5));
					writerToFile = temp + " It's raining men, hallelujah! It's raining men, hey, hey!\n";
					break;
				case "FOG":
					coords = new Coordinates(coords.getLongitude(), coords.getLatitude(), (coords.getHeight() - 3));
					writerToFile = temp + " Damn this cockney weather, if I wanted it I'd have moved to London. \n";
					break;
			}
			weatherTower.writeOutToFile("write", writerToFile);

			if (this.coords.getHeight() < 1)
			{
				writerToFileUnregister =  "Baloon#" + this.name + "(" + this.id + ") has unregistered from weather tower.\n";
				weatherTower.writeOutToFile("write", writerToFileUnregister);
				weatherTower.unregister(this);
			}
		}
	public void     registerTower(WeatherTower weatherTower)
	{
		this.weatherTower = weatherTower;
		weatherTower.register(this);
		String writeTo = "Baloon#" + this.name + "(" + this.id + ") has registered to weather tower.\n";
		weatherTower.writeOutToFile("write", writeTo);

	}
}
