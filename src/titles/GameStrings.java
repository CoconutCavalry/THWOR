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
            + "   |_|  |_| |_|_____|";
    private final String welcome = ""
            + "      Welcome To\n"
            + "The House With One Room";
    private final String house = ""
            + "        _____/\\   \n"
            + "       /     \\|   \n"
            + "       |  _  ||    \n"
            + "       |_|_|_||    ";

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

    public String getEOGUser() {
        return "You have ended the game.\n"
                + "Come back soon! \n"
                + "The House is waiting.";
    }
    
    public static String HelpDialogue = ""
            + "Commands: \n"
            + "drop [item] - put down an item from your\n"
            + "\tinventory\n"
            + "exit - exits the game\n"
            + "inventory - view the items that you have\n"
            + "\tin your inventory\n"
            + "search - look around in your current\n"
            + "\troom for items that \n"
            + "\tyou can pick up\n"
            + "take [item] - pick up an item that\n"
            + "\tyou find in your room\n"
            + "attack, unlock, go, etc.\n";
    
}
