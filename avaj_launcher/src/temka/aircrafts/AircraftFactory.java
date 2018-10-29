package temka.aircrafts;

import temka.inter.Flyable;

public class AircraftFactory
{
    public static Flyable  newAircraft(String type, String name, int longitude, int latitude, int height)
    {
        Coordinates coords = new Coordinates(longitude, latitude, height);
        Helicopter  helic;
        JetPlane    jetPlane;
        Baloon      baloon;

        switch (type)
        {
            case "JetPlane":
                jetPlane = new JetPlane(name, coords);
                return jetPlane ;
            case "Jetplane":
                jetPlane = new JetPlane(name, coords);
                return jetPlane ;
            case "Helicopter":
                helic = new Helicopter(name, coords);
                return helic ;
            case "Baloon":
                baloon = new Baloon(name, coords);
                return baloon ;
            default:
                return null;
        }
    }
}
