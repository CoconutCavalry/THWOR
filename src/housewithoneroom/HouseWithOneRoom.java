/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package housewithoneroom;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Scanner;

import characters.Player;
import items.Item;
import rooms.IRoom;
import shared.CommandsObject;
import shared.GoArgs;
import shared.TakeArgs;

/**
 *
 * @author esose
 */
public class HouseWithOneRoom {
    
    static Game game;
    static Scanner input = new Scanner(System.in);
    //try to use this later on
    static Font outputFont = new Font("Courier", Font.BOLD | Font.ITALIC ,20);
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Initialize a new game
        game = new Game();

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
        
        // Today, we begin in the library.
        game.currentRoom = game.corridor._corridor.getFirst();
        game.visitedRooms.add(game.currentRoom);
        output(game.currentRoom.getDescription() + "\n");
        
        // Herein lies the major flow of the game
        while (game.state) {
            // Instruct the user
            output("> ");
            
            // Collect and filter user commands
            String info = input.nextLine();
            String[] commands = splitInput(info);
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
        System.out.print(content);
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
    
    private static String[] splitInput(String info) {
        String[] infoArray = info.split("\\s+");
        return infoArray;
    }

    private static void parseInput(String[] commands) {
        String verb = commands[0];
        switch (verb) {
            case "c":
            case "character":
                output(game.player.showCharacterReport());
                break;
            case "d":
            case "drop":
                tryDroppingItem(commands[1]);
                break;
            case "exit":
            case "x":
                output(game.exitGame());
                break;
            case "g":
            case "go":
            case "move":
                if (commands.length <= 1 ) {
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
                output(game.player.showInventory());
                break;
//            case "s":
//            case "search":
//                output(game.currentRoom.search());
//                break;
            case "t":
            case "take":
                if (commands.length <= 1 ) {
                    output("Try including an item after 'take'.");
                } else {
                    boolean success = tryTakingItem(commands[1]);
                }
                break;
            default: 
                passCommandsToCurrentRoom(commands);
        }
    }

    private static void passCommandsToCurrentRoom(String[] inputs) {
        CommandsObject commandsToPass = 
                new CommandsObject("", inputs, game.player.getInventory());
        CommandsObject resultCommands = 
                game.currentRoom.performCustomMethods(commandsToPass);
        if (resultCommands == null) {
            output("bad input; try again or use 'help' for help");
        } else {
            output(resultCommands.message);
            game.player.setInventory(resultCommands.items);
        }
    }

    private static boolean tryTakingItem(String itemName) {
        //if (currentRoom.getItemsList().find)
        if (game.currentRoom.getItemsList() == null) {
            output("You don't see any items.");
            return false;
        }
        for(Item item : game.currentRoom.getItemsList()) {
            if (item.getName().equals(itemName)){
                
                // give the item to the player
                TakeArgs returnedArgs = game.player.takeItem(item);
                output(returnedArgs.message);
                
                // remove the item from the room
                if (returnedArgs.success) {
                    game.currentRoom.removeItemFromItems(item);
                }
                return true;
            }
        }
        output("No item of that name is available.");
        return false;
    }

    private static void tryDroppingItem(String itemName) {
        // try to drop from inventory
        Item droppedItem = game.player.dropItem(itemName);
        if (droppedItem != null) {
            // print result of action
            output("You dropped the " + itemName + ".");
            
            // then add to current room
            game.currentRoom.addItemToItems(droppedItem);
        } else {
            output("No items of that name are currently in your inventory.");
        }
                
    }

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
                + "\tyou find in your room\n");
    }

    private static void tryGoing(String direction) {
        //CommandsObject commandsToSend = new CommandsObject(direction);
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

    
    
}
