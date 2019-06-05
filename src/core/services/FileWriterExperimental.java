package core.services;

import java.io.*;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import characters.Player;
import housewithoneroom.Game;
import static core.services.ConsoleLogger.*;


public class FileWriterExperimental {

    private static boolean playerFileExists = false;

    private static String baseSaveFolder = "SavedGames/";
    private static String playerFolder = baseSaveFolder + "Player/";
    private static String playerFileJson = playerFolder + "SavedPlayer.json";
    private static String playerFileTxt = playerFolder + "SavedPlayer.txt";

    private static String fileName = "testFile.txt";

    public static boolean getIsPlayerFileCreated() {
        return playerFileExists;
    }

    /**
     * Creates a file based on a static string in the same class.
     * @return true if file was created, false if already exists.
     * @throws IOException if there are errors creating the file.
     */
    public static boolean createFile() throws IOException {
        boolean success = false;

        File file = new File(playerFileJson);
        file.getParentFile().mkdirs();

        if (file.createNewFile()) {
            success = true;
        }
        // as long as the exception is not thrown, the file exists
        playerFileExists = true;
        return success;
    }

    public static void writeToFile(String stringToWrite) throws IOException {
        FileWriter writer = new FileWriter(fileName, true);
        PrintWriter printer = new PrintWriter(writer);

        String[] contentArr = stringToWrite.split("\n");
        for (String c : contentArr) {
            printer.printf("%s" + "%n", c);
        }
        printer.close();
    }

    public static void writePlayerJson() throws IOException {
        FileWriter writer = new FileWriter(playerFileJson, true);
        PrintWriter printer = new PrintWriter(writer);

        Gson gson = new Gson();
        String playerJson = gson.toJson(Game.player);

        printer.printf("%s" + "%n", playerJson);
        printer.close();
    }

    /**
     * Reads the saved player object . . .
     * @return if the reading was successful
     * @throws FileNotFoundException (shouldn't happen because of if() clause)
     */
    public static boolean readPlayerJson() throws FileNotFoundException {
        boolean retVal = false;

        if (playerFileExists) {
            // Create the reader
            JsonReader jsonReader = new JsonReader(new FileReader(playerFileJson));
            Gson gson = new Gson();

            // Create player object from saved data
            Player savedPlayer = gson.fromJson(jsonReader, Player.class);

            // Assign saved data to current player
            Game.player = savedPlayer;
            output("Player assigned.");
            retVal = true;
        }

        return retVal;
    }

}
