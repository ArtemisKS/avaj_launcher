package temka.weather;

import temka.aircrafts.Coordinates;
import temka.tower.Tower;

public class WeatherTower extends Tower
{
    public String getWeather(Coordinates coordinates)
    {
        return (WeatherProvider.getProvider().getCurrentweather(coordinates));
    }

    public void changeWeather()
    {
        this.conditionsChanged();
    }
}
