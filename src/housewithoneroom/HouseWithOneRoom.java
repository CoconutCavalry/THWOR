/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package housewithoneroom;

import java.awt.Font;
import java.util.Scanner;

import characters.Player;
import items.Item;
import java.util.ArrayList;
import services.ConsoleLogger;
import shared.*;
import titles.GameStrings;

/**
 * Finally connected to git
 * @author esose
 */
public class HouseWithOneRoom {
    
    static Game game;
    static Scanner input = new Scanner(System.in);
    static boolean tryingToEndGame = false;
    //try to use this later on
    static Font outputFont = new Font("Courier", Font.BOLD | Font.ITALIC ,20);
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Initialize a new game in a certain room
        game = new Game(2);

        // Print welcome dialogues.
        output(game.gameStrings.getWelcome());
        output(game.gameStrings.getHouse());
        output("\n");
        
        // Initialize the player.
        game.player = initializePlayer(input);
        output("\n");
        output("Welcome, " + game.player.getName() + "\n");
        output("\n");
        
        // Show starting stats
        output(game.player.showCharacterReport() + "\n");
        output("\n");
        
        game.visitedRooms.add(game.currentRoom);
        output(game.currentRoom.getDescription() + "\n");
        
        // Herein lies the major flow of the game
        while (game.state) {
            // Instruct the user
            output("> ");
            
            // Collect and filter user commands
            String info = input.nextLine();
            String[] commands = splitAndSanitizeInput(info);
            output("\n");

            // Process user commands
            parseInput(commands);
            output("\n");
        }
    }
    
    /**
     * Created in order to be able to easily change 
     * the output method for the game.
     * @param content the content to be outputted
     */
    private static void output(String content) {
        ConsoleLogger.output(content);
    }
    
    /**
     * Gets the name and gender of the player from the user
     * @returns the player object
     */
    private static Player initializePlayer(Scanner input) {
        output("Enter name: ");
        String name = input.nextLine();
        output("Male or female? ");
        String gender = input.nextLine();
        Player newPlayer = new Player(name, gender);
        return newPlayer;
    }
    
    /**
     * Sanitizes input (toLowerCase) and splits it into separate words
     * @param input
     * @return an array of words
     */
    private static String[] splitAndSanitizeInput(String input) {
        String sanitizedInput = input.toLowerCase();
        String[] infoArray = sanitizedInput.split("\\s+");
        return infoArray;
    }

    /**
     * Takes the user input and applies the appropriate method
     * @param commands - array of inputs from the user
     */
    private static void parseInput(String[] commands) {
        String verb = commands[0];
        Game gameObj = game;
        if (tryingToEndGame) {
            switch (verb) {
                case "n":
                case "no":
                    tryingToEndGame = false;
                    output("Good.");
                    break;
                case "y":
                case "yes":
                    output(game.exitGame());
                    break;
                default:
                    output("Enter y/n to end game.");
            }
        } else {
            switch (verb) {
            case "a":
            case "attack":
                tryToAttack(gameObj.player);
                break;
            case "c":
            case "character":
                output(gameObj.player.showCharacterReport());
                break;
            case "desc":
            case "describe":
            case "description":
                output(gameObj.currentRoom.getDescription());
                break;
            case "d":
            case "drop":
                tryDroppingItem(commands[1]);
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
                tryingToEndGame = true;
                output("Are you sure you want to exit? [y/n]");
                break;
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
                output(gameObj.player.showInventory());
                break;
            case "stats":
                if (!validateNoun(commands)) {
                    output("Try including an item from your inventory \n"
                            + "after 'stats'.");
                } else {
                    showItemStats(commands[1]);
                }
                break;
            case "t":
            case "get":
            case "take":
                if (!validateNoun(commands)) {
                    output("Try including an item after 'take'.");
                } else {
                    boolean success = tryTakingItem(commands[1]);
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
            default: 
                passCommandsToCurrentRoom(commands);
            }
        }
    }

    /**
     * Passes the user input array and the current player into the room
     * to perform room-specific actions
     * @param inputs 
     */
    private static void passCommandsToCurrentRoom(String[] inputs) {
        CommandsObject resultCommands = 
                game.currentRoom.performCustomMethods(inputs, game.player);
        if (resultCommands == null) {
            output("bad input; try again or use 'help' for help");
        } else {
            output(resultCommands.message);
            game.player.setInventory(resultCommands.items);
        }
    }

    private static boolean tryTakingItem(String itemName) {
        //if (currentRoom.getItems().find)
        String name = itemName;
        ArrayList<Item> itemsInRoom = game.currentRoom.getItems();
        if (itemsInRoom == null) {
            output("You don't see any items.");
            return false;
        }
        for(Item item : itemsInRoom) {
            if (item.getName().equals(name)){
                
                // give the item to the player
                TakeArgs returnedArgs = game.player.takeItem(item);
                output(returnedArgs.message);
                
                // remove the item from the room
                if (returnedArgs.success) {
                    game.currentRoom.removeItemFromItems(item);
                    return true;
                }
                return false;
            }
        }
        output("No item of that name is available.");
        return false;
    }

    private static void tryDroppingItem(String itemName) {
        // try to drop from inventory
        String name = itemName;
        Item droppedItem = game.player.dropItem(name);
        if (droppedItem != null) {
            // print result of action
            output("You dropped the " + name + ".");
            
            // then add back to current room
            game.currentRoom.addItemToItems(droppedItem);
        } else {
            output(GameStrings.NotInInventory);
        } 
    }
    
    private static void tryGoing(String direction) {
        GoArgs newRoomArgs = game.currentRoom.go(direction);
        if (newRoomArgs.roomId > -1) {
            game.currentRoom = 
                    game.corridor._corridor.get(newRoomArgs.roomId);
            if (game.visitedRooms.contains(game.currentRoom)) {
                output("You are now in the " 
                        + game.currentRoom.getName() + ".");
            } else {
                game.visitedRooms.add(game.currentRoom);
                output(game.currentRoom.getDescription());
            }
        } else {
            output(newRoomArgs.message);
        }
    }

    private static void tryViewingItem(String itemName) {
        Item item = Shared.searchForItemInListByName(
                itemName, game.player.getInventory());
        if (item != null) {
            output(item.getDescription());
        } else {
            output(GameStrings.NotInInventory);
        }
    }
    
    public static void tryToAttack(Player player) {
        AttackArgs results = game.currentRoom.attack(player.getHealth(), 
                player.getItemsInHand());
        if (results.getHealth() == -999) {
            game.state = false;
        } else if (results.getHealth() != -1) {
            game.player.setHealth(results.getHealth());
            game.player.setItemsInHand(results.getInHand());
        }
        output(results.getMessage());
    }
    
    private static void tryEquippingItem(String itemName) {
        Item item = Shared.searchForItemInListByName(
                itemName, game.player.getInventory());
        if (item != null) {
            if (game.player.getNumberOfEmptyHands() > 0) {
                game.player.equip(item);
                output(item.getName() + " equipped.");
                return;
            }
        } else {
            output(GameStrings.NotInInventory);
        }
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

    private static void showItemStats(String command) {
        Item item = Shared.searchForItemInListByName(
                command, game.player.getInventory());
        if (item != null) {
            output(item.getStats());
        } else {
            output(GameStrings.NotInInventory);
        }
    }

    /**
     * Displays an informative dialogue about possible actions
     */
    private static void showHelpDialogue() {
        output(""
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
                + "attack, unlock, go, etc.\n");
    }

    
    
    
}
