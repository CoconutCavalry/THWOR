package adventures;

import java.util.Arrays;

import titles.Excerpts;
import services.IOService;
import static services.ConsoleLogger.output;

public class LibraryBookshelfAdventure {

    private static final String[] bookTitlesInLibrary = {""
            + "The Tragic Youth of Ambrose Fogarty: \n"
            + "\tYoung Master Fogarty, by KM",
            "\nThe Tragic Youth of Ambrose Fogarty: \n"
                    + "\tFogarty, Esquire, by KM",
            "\nThe Tragic Youth of Ambrose Fogarty: \n"
                    + "\tIn Her Majesty's Service, by KM",
            "\nThe Perilous Adventures of Jon Legenn, by KM",
            "\nMcEver, by KM",
            "\nThe Complete Clocktower Books, by KM and FM"
    };


    public static void browseLibrary() {
        output(showBookTitles());

        String action = IOService.getNextLine();
        String[] actionsArray = action.split(" ");

        while (!actionsArray[0].equals("leave")) {
            switch (actionsArray[0]) {
                case "read":
                    output(readBook(actionsArray));
                    break;
                default:
                    output("invalid");
            }

            actionsArray = IOService.getNextLine().split(" ");
        }

    }

    private static String showBookTitles() {
        return Arrays.toString(bookTitlesInLibrary);
    }

    private static String readBook(String[] commands) {
        if (commands.length > 1) {
            switch (commands[1]) {
                case "m":
                    return Excerpts.mcEver;
                case "l":
                    return Excerpts.legenn;
                case "f":
                    return Excerpts.fogarty;
                case "c":
                    return Excerpts.clocktower;
                default:
                    return "Try 'm', 'l', 'f', or 'c'.";
            }
        }
        return "Try including a title after 'read'.";
    }

}
