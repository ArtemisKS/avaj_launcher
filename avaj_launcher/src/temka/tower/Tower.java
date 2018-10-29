package temka.tower;

import temka.inter.Flyable;
import java.util.ArrayList;
import java.io.*;
import java.util.*;

public class Tower {

    private ArrayList<Flyable> observers = new ArrayList<>();
    private ArrayList<Flyable> obs_to_delete = new ArrayList<>();
    private File                file;
    private FileWriter          writer;

    public void register(Flyable flyable)
    {
        if (observers.contains(flyable))
            return ;
        try {
            observers.add(flyable);
        } catch (Exception e) {
            System.out.println("Aircraft registration failed!!");
        }
    }

    public void unregister(Flyable flyable)
    {
        if (obs_to_delete.contains(flyable))
            return ;
        try {
            obs_to_delete.add(flyable);
        } catch (Exception e) {
            System.out.println("Aircraft unregistration failed!!");
        }
    }

    public void conditionsChanged()
    {
        if (this.observers.size() < 1) {
                writeOutToFile("write", "\n\nAll of the aircrafts have landed!");
                writeOutToFile("destroy", "");
                System.exit(0);
            }
        for (Flyable flyable : observers)
        {
            flyable.updateConditions();
        }
        observers.removeAll(obs_to_delete);
        obs_to_delete.clear();
    }

    public void writeOutToFile(String _state, String _text) {
        try {
            this.file = new File("simulation.txt");
            this.writer = new FileWriter(file, true);
            this.file.createNewFile();

            switch (_state)
            {
                case "write":
                    try {
                        writer.write(_text);
                        writer.flush();
                    } catch (Exception exception) { System.out.println("Error occured, could not write info to file");}
                    break;
                case "destroy" :
                    try {
                        writer.close();
                    } catch (Exception exception) { System.out.println("Error occured, could not close output stream");}
                     break ;
            }
        }catch (Exception e)
        {}
    }
}
