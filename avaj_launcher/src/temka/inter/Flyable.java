package temka.inter;

import temka.weather.WeatherTower;

public interface Flyable
{
    public void updateConditions();

    public void registerTower(WeatherTower weatherTower);
}
