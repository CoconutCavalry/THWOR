/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package housewithoneroom;

import characters.Player;
import characters.SimpleMonster;
import items.iItem;
import java.util.ArrayList;
import static services.ConsoleLogger.*;
import shared.*;
import services.*;
import titles.GameStrings;

/**
 * Finally connected to git
 * @author esose
 */
public class Start {

    public static boolean admin = false;
//    public static boolean admin = true;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Initialize a new game
        Game.NewGame();

        ///////
        //output("XXXXX XXXXXXX XX XXXXX XXXXXX XXX \nNewLineXXX XXXXX XX XXX XX XXXXXXX XXXX\nNewLineXXX XX X XXXXX XXXXXX XXX\nNewLineXX XXX XXXXXXX X XXXXX XXXXX XXXXXX XX XXXXX\nNewLine XXXXXXX X");
        ///////

        // Print welcome dialogues.
//        output(GameStrings.getTitleInBigWords());
//        outputLn();
        output(GameStrings.getHouse());
        output(GameStrings.getWelcome());
        outputLn();

        // Initialize the player.
        initializePlayer();

        if (admin) {
            // Allow to start in a particular room
            int roomNum = getStartingRoom();
            if (roomNum >= Game.house.getCorridor().size()) {
                roomNum = 0;
            }
            Game.currentRoom = Game.house.getCorridor().get(roomNum);
            output("You are starting in the "
                    + Game.currentRoom.getName() + ".");
            outputLn();
        } else {
            Game.currentRoom = Game.house.getCorridor().getFirst();
        }

        // Show starting stats
        output(Game.player.showCharacterReport());
        outputLn();

        Game.visitedRooms.add(Game.currentRoom);
        output(Game.currentRoom.getDescription());
        outputLn();

        
        // Herein lies the major flow of the game
        while (Game.state) {
            // Instruct the user
            outputForInput("> ");
            
            // Collect and filter user commands
            String info = IOService.getNextLine();
            String[] commands = splitAndSanitizeInput(info);
            outputLn();

            // Process user commands
            parseInput(commands);
            outputLn();
        }
    }
    
    /**
     * Gets the name and gender of the player from the user
     * and creates the player
     */
    private static void initializePlayer() {
        //in order to make startup easier for myself:
        if (admin) {
            Game.player = new Player("CoconutCavalry", "male");
            outputLn("Welcome, " + Game.player.getName() + ".\n");
        } else {
            outputForInput("Enter name: ");
            String name = IOService.getNextLine();
            outputForInput("Male or female? ");
            String gender = IOService.getNextLine();
            Game.player = new Player(name, gender);
            outputLn("\nWelcome, " + Game.player.getPronouns()[0] + " " + Game.player.getName() + ".\n");
        }
    }
    /**
     * Gets the starting room number from the user
     * @return the player object
     */
    private static int getStartingRoom () {
        int retVal = -1;
        String in;
        while (retVal < 0) {
            output("Enter starting room number: ");
            in = IOService.getNextLine();
            try {
                retVal = Integer.parseInt(in);
            } catch (Exception e) {
                output("You must enter an integer.\n");
            }
        }
        
        return retVal;
    }
    
    /**
     * Sanitizes input (toLowerCase) and splits it into separate words
     * @param input the user input that needs to be split and sanitized
     * @return an array of words
     */
    private static String[] splitAndSanitizeInput(String input) {
        String sanitizedInput = input.toLowerCase();
//        String[] infoArray = sanitizedInput.split("\\s+");
        return sanitizedInput.split("\\s+");
    }

    /**
     * Takes the user input and applies the appropriate method
     * @param commands - array of inputs from the user
     */
    private static void parseInput(String[] commands) {
        String verb = commands[0];
        switch (verb) {
            //////////
            // ROOM //
            //////////
            case "a":
            case "attack":
                tryToAttack();
                break;
            case "c":
            case "character":
                output(Game.player.showCharacterReport());
                break;
            //////////
            // ROOM //
            //////////
            case "desc":
            case "describe":
            case "description":
                output(Game.currentRoom.getDescription());
                break;
            case "d":
            case "drop":
                if (!validateNoun(commands)) {
                    output("Try including an item after 'drop'.");
                } else {
                    tryDroppingItem(commands[1]);
                }
                break;
            case "e":
            case "equip":
                if (!validateNoun(commands)) {
                    output("Try including an item after 'equip'.");
                } else {
                    tryEquippingItem(commands[1]);
                }
                break;
            case "exit":
            case "x":
                Game.exitGame();
                break;
            //////////?????????????
            // ROOM //?????????????
            //////////?????????????
            case "g":
            case "go":
            case "move":
                if (!validateNoun(commands)) {
                    output("Try including a direction after 'go'.");
                } else {
                    tryGoing(commands[1]);
                }
                break;
            case "h":
            case "help":
                showHelpDialogue();
                break;
            case "i":
            case "inventory":
                output(Game.player.showInventory());
                break;
            case "pocket":
                if (!validateNoun(commands)) {
                    output("Try including an equipped item after 'pocket'.");
                } else {
                    tryPocketingItem(commands[1]);
                }
                break;
            //////////
            // ROOM //
            //////////
//            case "ra":
//                Game.currentRoom.roomActions();
//                break;
//            case "stats":
//                if (!validateNoun(commands)) {
//                    output("Try including an item from your inventory \n"
//                            + "after 'stats'.");
//                } else {
//                    showItemStats(commands[1]);
//                }
//                break;
            case "t":
            case "get":
            case "take":
                if (!validateNoun(commands)) {
                    output("Try including an item after 'take'.");
                } else {
                    tryTakingItem(commands[1]);
                }
                break;
            case "view":
            case "v":
                if (!validateNoun(commands)) {
                    output("Try adding an item name from your inventory \n"
                            + "after 'view'.");
                } else {
                    tryViewingItem(commands[1]);
                }
                break;
            //////////
            // ROOM //
            //////////
            default:
                passCommandsToCurrentRoom(commands);
        }
    }

    /**
     * Passes the user input array and the current player into the room
     * to perform room-specific actions
     * @param inputs the commands
     */
    private static void passCommandsToCurrentRoom(String[] inputs) {
        Game.currentRoom.performCustomMethods(inputs);
    }

    private static void tryTakingItem(String itemName) {
        ArrayList<iItem> itemsInRoom = Game.currentRoom.getItems();
        if (itemsInRoom == null) {
            output("You don't see any items.");
        } else {
            for(iItem item : itemsInRoom) {
                if (item.getName().equals(itemName)){

                    // remove the item from the room if taken by player
                    if (Game.player.takeItem(item)) {
                        Game.currentRoom.removeItemFromItems(item);
                        return;
                    }
                    return;
                }
            }
        }
        output("No item of that name is available.");
    }

    private static void tryDroppingItem(String itemName) {
        // try to drop from inventory
        iItem droppedItem = Game.player.dropItem(itemName);
        if (droppedItem != null) {
            // print result of action
            output("You dropped the " + itemName + ".");
            
            // then add back to current room
            Game.currentRoom.addItemToItems(droppedItem);
        } else {
            output(GameStrings.NotInInventory);
        }
    }
    
    private static void tryGoing(String direction) {
        if (Game.currentRoom.getMonster() != null) {
            SimpleMonster monster = Game.currentRoom.getMonster();
            if (!monster.isDead()) {
                output("The " + monster.getName() + " blocks your path.\n" +
                        "You cannot leave while the " + monster.getName() + " is alive.");
                return;
            }
        }
        int roomId = Game.currentRoom.go(direction);
        if (roomId > -1) {
            Game.currentRoom =
                    Game.house.getCorridor().get(roomId);
            if (Game.visitedRooms.contains(Game.currentRoom)) {
                output("You are now in the " 
                        + Game.currentRoom.getName() + ".");
            } else {
                Game.visitedRooms.add(Game.currentRoom);
                output(Game.currentRoom.getDescription());
            }

        } else if (roomId == -1) {
            output(GameStrings.GoInvalidDirection);
        } else if (roomId == -99) {
            // roomId -99 is endGameWin
            // Do nothing; necessary actions have already taken place.
            // Here, it just needs to fall through back to the main loop so that it can exit.
        }
    }

    private static void tryViewingItem(String itemName) {
        iItem item = Shared.searchForItemInListByName(
                itemName, Game.player.getInventory());
        if (item == null) {
            item = Shared.searchForItemInListByName(itemName, Game.player.getItemsInHands());
        }
        if (item != null) {
            output(item.getDescription());
        } else {
            output(GameStrings.NotInInventory);
        }
    }
    
    private static void tryToAttack() {
        Game.currentRoom.attack();
    }
    
    private static void tryEquippingItem(String itemName) {
        iItem item = Shared.searchForItemInListByName(
                itemName, Game.player.getInventory());
        if (item != null) {
            output(Game.player.equip(item));
        } else {
            output(GameStrings.NotInInventory);
        }
    }

    private static void tryPocketingItem(String itemName) {
        Game.player.pocket(itemName);
    }


    
    
    
    private static boolean validateNoun(String[] inputs) {
        if (inputs.length > 1) {
            if (inputs[1] != null) {
                return true;
            }
            output(GameStrings.InvalidNoun);
            return false;
        }
        return false;
    }

    // TODO: this method needs some work before implementing
//    private static void showItemStats(String command) {
//        iItem item = Shared.searchForItemInListByName(
//                command, Game.player.getInventory());
//        if (item != null) {
//            output(item.getStats());
//        } else {
//            output(GameStrings.NotInInventory);
//        }
//    }

    /**
     * Displays an informative dialogue about possible actions
     */
    private static void showHelpDialogue() {
        output(GameStrings.HelpDialogue);
    }

    
    
    
}
