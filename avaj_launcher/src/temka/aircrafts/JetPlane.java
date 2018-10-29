package temka.aircrafts;

import temka.inter.Flyable;
import temka.weather.WeatherTower;

public class JetPlane extends Aircraft implements Flyable
{
	private WeatherTower weatherTower;

	JetPlane(String name, Coordinates coordinates)
	{
		super(name, coordinates);
	}

	public void     updateConditions()
	{
		String temp = "JetPlane#" + this.name + "(" + this.id + ") :";
		String writerToFile = "";
		String writerToFileUnregister = "";
		String weatherTemp = weatherTower.getWeather(this.coords);


		switch (weatherTemp) {
			case "SUN":
				coords = new Coordinates(coords.getLongitude(), (coords.getLatitude() + 10), (coords.getHeight() + 2));
				writerToFile = temp + " Why doesn't the Sun go to college? Because it has a million degrees. \n";
				break;
			case "SNOW":
				coords = new Coordinates(coords.getLongitude(), coords.getLatitude(), (coords.getHeight() - 7));
				writerToFile = temp + " How does a snowman get to work? By icicle. \n";
				break;
			case "RAIN":
				coords = new Coordinates(coords.getLongitude(), (coords.getLatitude() + 5), coords.getHeight());
				writerToFile = temp + " What do you call a bear caught in the rain? A drizzly bear. \n";
				break;
			case "FOG":
				coords = new Coordinates(coords.getLongitude(), (coords.getLatitude() + 1), coords.getHeight());
				writerToFile = temp + " What type of cloud is so lazy, because it will never get up? Fog! \n";
				break;
		}

		weatherTower.writeOutToFile("write", writerToFile);

		if (this.coords.getHeight() < 1)
		{
			writerToFileUnregister = "JetPlane#" + this.name + "(" + this.id + ") has unregistered from weather tower.\n";
			weatherTower.writeOutToFile("write", writerToFileUnregister);
			weatherTower.unregister(this);
		}
	}
	public void     registerTower(WeatherTower weatherTower) {

		weatherTower.register(this);
		String writeTo = "JetPlane#" + this.name + "(" + this.id + ") has registered to weather tower.\n";
		this.weatherTower = weatherTower;
		weatherTower.writeOutToFile("write", writeTo);

	}
}
