package temka.simulator;

import temka.inter.Flyable;
import temka.md5algo.Md5;
import temka.exceptions.ExceptionHandler;
import temka.aircrafts.AircraftFactory;
import temka.weather.WeatherTower;
import temka.validation.CommandLineArguments;
import java.io.*;
import java.util.*;

public class Simulator {

    private static WeatherTower weatherTower;
    private static List<Flyable> flyableList = new ArrayList<>();
    private static ExceptionHandler    exceptionHandler = new ExceptionHandler();
    private static String genRandomString() {

        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 7;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        return (generatedString);
    }

    private static Boolean hasInvCharacters(String line) {
        return (line.matches("^.*[^a-zA-Z0-9 ].*$"));
    }

    private static Boolean isBiggerThanInt(String line) {
        return (line.matches("^.*[^0-9 ].*$"));
    }

    public static void check_valid_input(String str){
        if (hasInvCharacters(str))
            exceptionHandler.errorMessage("[InvCharacters]", true);
    }

    public static void check_valid_int(String num){
        if (isBiggerThanInt(num) || Integer.parseInt(num) < 0)
            exceptionHandler.errorMessage("[InvInt]", true);
    }

    public static void main(String[] args) throws IOException
    {
        ArrayList<String> aircrafts = new ArrayList<String>();
        boolean is_md5 = false;
        String  md5fileName = new String("md5decr.md5");
        aircrafts.add("Baloon");
        aircrafts.add("Jetplane");
        aircrafts.add("Helicopter");

        Md5 md5 = new Md5(0, 100000, aircrafts);
        md5.fillNumHashes();
        md5.fillAlphaHashes();
        CommandLineArguments argumentsHandler = new CommandLineArguments(args);
        ArrayList<String> alist = new ArrayList<String>(Arrays.asList(args));


        if (argumentsHandler.isMd5()) {
            ArrayList<String>   md5tokens;
            ArrayList<String>   decryptedFile;
            Map<String, String> md5Map = md5.getHVMap();
            PrintWriter         printWriter;
            is_md5 = true;
            try (BufferedReader reader = new BufferedReader(new FileReader(alist.get(0))))
            {
                String              currentLine;
                printWriter = new PrintWriter(md5fileName, "UTF-8");
                while ((currentLine = reader.readLine()) != null) {
                    currentLine = currentLine.trim().replaceAll("\\s+", " ");
                    currentLine = (currentLine.isEmpty()) ? "" : currentLine;
                    md5tokens = new ArrayList<String>(Arrays.asList(currentLine.split(" ")));

                    for (Integer ind = 0; ind < md5tokens.size(); ind++) {
                        if (md5Map.containsKey(md5tokens.get(ind))) {
                            String HashedToken = md5Map.get(md5tokens.get(ind)).toLowerCase() + " ";
                            HashedToken = HashedToken.substring(0, 1).toUpperCase() + HashedToken.substring(1);
                            printWriter.print(HashedToken + " ");
                        } else {
                            printWriter.print(md5tokens.get(ind) + " ");
                        }
                    }
                    printWriter.println();
                }
                printWriter.close();
            } catch (IOException exception) {}
        }
        try {
            BufferedReader reader;
            if (!is_md5)
                reader = new BufferedReader(new FileReader(alist.get(0)));
            else
                reader = new BufferedReader(new FileReader(md5fileName));

            String line = reader.readLine();
            if (line != null) {
                weatherTower = new WeatherTower();
                String num = line.split(" ") [0];
                check_valid_int(num);
                int simulation = Integer.parseInt(num);
                while ((line = reader.readLine()) != null) {
                    String str = line.trim().replaceAll("\\s+", " ");
                    check_valid_input(str);
                    String [] arr = str.trim().split(" ");
                    if (arr.length  == 5 || (arr.length == 4 && is_md5)) {
                        Flyable flyable;
                        if (is_md5 && arr.length == 4) {
                            flyable = AircraftFactory.newAircraft(arr[0], genRandomString(), Integer.parseInt(arr[1]),
                                    Integer.parseInt(arr[2]), Integer.parseInt(arr[3]));
                        }
                        else
                        {
                            flyable = AircraftFactory.newAircraft(arr[0], arr[1], Integer.parseInt(arr[2]),
                                    Integer.parseInt(arr[3]), Integer.parseInt(arr[4]));
                        }
                        flyableList.add(flyable);
                    }
                    else
                    {
                        System.out.println("Invalid file format {Type Name Longitude Latitude Height}");
                    }
                }
                for (Flyable flyable : flyableList)
                {
                    flyable.registerTower(weatherTower);

                }
                for (int i = 1; i <= simulation; i++)
                {
                    String simulationWrite = "Simulation No " + i + "\n";
                    weatherTower.writeOutToFile("write", simulationWrite);
                    weatherTower.changeWeather();
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found exception: " + alist.get(0));
        }
        catch (IOException e)
        {
            System.out.println("There occured an error while reading this file: " + alist.get(0));
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("File specification error");
        }
        catch (Exception e)
        {
            System.out.println("Type of exception is: " + e);
        }
    }
}
