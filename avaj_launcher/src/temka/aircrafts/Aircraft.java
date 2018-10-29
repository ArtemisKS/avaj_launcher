package temka.aircrafts;

public class Aircraft
{
        protected long id = 0L;
        protected String name;
        protected Coordinates coords;
        private static long id_Counter = 0L;

        protected Aircraft(String name, Coordinates coordinates)
        {
            this.id = nextId();
            this.name = name;
            this.coords = coordinates;
        }

        protected Aircraft()
        {
            return;
        }
        private long nextId()
        {
            return (++this.id_Counter);
        }
}
