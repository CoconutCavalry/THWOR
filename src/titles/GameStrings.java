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
    private static final String title = "Game Strings";

    private static final String bigWordsThe = ""
            + " _______ _   _ _____ \n"
            + "|__   __| | | | ____|\n"
            + "   | |  | |_| | |___ \n"
            + "   | |  |  _  |  ___|\n"
            + "   | |  | | | | |___ \n"
            + "   |_|  |_| |_|_____|";

    private static final String bigWordsHouse = "" +
            " _   _  ___  _   _  ____ _____ \n" +
            "| | | |/ _ \\| | | |/ ___|  ___|\n" +
            "| |_| | | | | | | | |__ | |___ \n" +
            "|  _  | | | | | | |\\__ \\|  ___|\n" +
            "| | | | |_| | |_| |___| | |___ \n" +
            "|_| |_|\\___/ \\___/|____/|_____|";

    private static final String bigWordsWith = "" +
            " _      _ _ _____ _   _ \n" +
            "| |    | | |_   _| | | |\n" +
            "| |    | | | | | | |_| |\n" +
            "| | __ | | | | | |  _  |\n" +
            "| |/  \\| | | | | | | | |\n" +
            " \\__/\\__/|_| |_| |_| |_|  ";

    private static final String bigWordsOne = "" +
            "  ___  _   _ _____ \n" +
            " / _ \\| \\ | |  ___|\n" +
            "| | | |  \\| | |___ \n" +
            "| | | |     |  ___|\n" +
            "| |_| | |\\  | |___ \n" +
            " \\___/|_| \\_|_____|";

    private static final String bigWordsRoom = "" +
            " _____   ___   ___  _     _  \n" +
            "|  __ \\ / _ \\ / _ \\| \\   / | \n" +
            "| |__) | | | | | | |  \\_/  | \n" +
            "|     /| | | | | | | |\\_/| | \n" +
            "| |\\  \\| |_| | |_| | |   | | \n" +
            "|_|  \\_\\\\___/ \\___/|_|   |_| ";

    private static final String welcome = ""
            + "      Welcome To\n"
            + "The House With One Room";
    private static final String house = "" +
            "        _____/\\  \n" +
            "       /     \\|  \n" +
            "       |  _  ||   \n" +
            "       |_|_|_||  ";
    private static final String house2 = "" +
            "     /\\____________/\\         \n" +
            "     |    _____/\\   |   \n" +
            "     |   /     \\|   |   \n" +
            "     |   |  _  ||   |   \n" +
            "     |___|_|_|_||___|  ";

    public static String getTitle() {
        return title;
    }

    public static String getTitleInBigWords() {
        return bigWordsThe +
                "\n" + bigWordsHouse +
                "\n" +bigWordsWith +
                "\n" +bigWordsOne +
                "\n" + bigWordsRoom;
    }

    public static String getWelcome() {
        return welcome;
    }

    public static String getHouse() {
        return house;
    }
    
    
    /**********************
     * Hard-coded strings *
     **********************/
    public static final String NotInInventory = ""
            + "No item of that name is currently in your inventory.";
    public static final String InvalidNoun = "Invalid noun.";
    public static final String PerformCustomMethodsBadInput = "bad input; try again or use 'help' for help.";
    public static final String NothingToAttackHereString = "There is nothing to attack here.";
    public static final String GoInvalidDirection = "You cannot move in that direction from here.";
    public static final String DoorIsLocked = "The door is locked.";

    public static String getEOGUser() {
        return "You have ended the game.\n"
                + "Come back soon! \n"
                + "The House is waiting.";
    }
    
    public static final String HelpDialogue = "" +
            "Example Commands: \n" +
            "'attack' - engage in battle with a hostile entity sharing\n" +
            "\tyour room. The equipped items will be used to\n" +
            "\tcalculate damage done.\n" +
            "'character' - generates a report on the stats, inventory,\n" +
            "\tand equipped items of your player\n" +
            "'describe' - get current room description\n" +
            "'drop [item]' - put down an item from your inventory\n" +
            "'equip [item]' - puts an item from your inventory \n" +
            "\tinto your hand so that it can be used\n" +
            "'exit' - 'y' or 'n' to exit the game\n" +
            "'go' [direction] - move into an adjacent room\n" +
            "\texample directions: left, right, forward, \n" +
            "\tback, up, down\n" +
            "'inventory' - view the items that you have\n" +
            "\tin your inventory\n" +
            "'search' - look around in your current room for items that \n" +
            "\tyou can pick up\n" +
            "'stats' [item] - view the specified item's stats\n" +
            "'take' [item] - pick up an item that\n" +
            "\tyou find in your room\n" +
            "'unlock' - unlocks a locked door, if the necessary key is\n" +
            "\tequipped\n" +
            "'view [item]' - get details on the specified item, if it is\n" +
            "\tin your current inventory\n";

}
