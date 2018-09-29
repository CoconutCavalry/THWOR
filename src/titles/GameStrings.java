/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package titles;

/**
 *
 * @author esose
 */
public class GameStrings {
    private final String title = "Game Strings";
    private final String titleInBigWords = ""
            + " _______ _   _ _____ \n"
            + "|__   __| | | | ____|\n"
            + "   | |  | |_| | |___ \n"
            + "   | |  |  _  |  ___|\n"
            + "   | |  | | | | |___ \n"
            + "   |_|  |_| |_|_____|\n";
    private final String welcome = ""
            + "      Welcome To\n"
            + "The House With One Room\n";
    private final String house = ""
            + "        _____/\\   \n"
            + "       /     \\|   \n"
            + "       |  _  ||    \n"
            + "       |_|_|_||    \n";

    public String getTitle() {
        return title;
    }

    public String getTitleInBigWords() {
        return titleInBigWords;
    }

    public String getWelcome() {
        return welcome;
    }
    
    public String getHouse() {
        return house;
    }
    
    
    /**********************
     * Hard-coded strings *
     **********************/
    public static String NotInInventory = ""
            + "No item of that name is currently in your inventory.";
    public static String InvalidNoun = "Invalid noun.";
    
}
