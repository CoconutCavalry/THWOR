/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rooms;

import characters.Player;
import items.Item;
import java.util.ArrayList;
import shared.CommandsObject;
import shared.GoArgs;
import shared.Shared;

/**
 *
 * @author esose
 */
public class Hall implements IRoom {
    
    private boolean hasBeenSearched = false;
    private boolean hallDoorIsLocked = true;
    private final int[] neighbors = {2};
    private ArrayList<Item> items;
    
    private final String name = "Hall";
    private final String description = RoomDescriptions.hall;
    private final String firstSearchDescription = 
            RoomDescriptions.hallFirstSearch;
    
    /**
     * Constructor for the Hall
     */
    public Hall() {
        this.items = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            this.items.add(Item.TORCH_FROM_HALL);
        }
    }

    /***********************
     * Getters and setters *
     ***********************/
    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public String getDescription() {
        return this.description;
    }
    @Override
    public ArrayList<Item> getItems() {
        if (this.items ==  null) {
            this.items = new ArrayList<>();
            return this.items;
        }
        return this.items;
    }
    public boolean getHasBeenSearched() {
        return this.hasBeenSearched;
    }
    public void setHasBeenSearched(boolean tf) {
        if (tf == true) {
            this.hasBeenSearched = true;
        }
    }
    
    /******************
     * Search methods *
     ******************/
    public String search() {
        ArrayList<Item> itemsInRoom = this.getItems();
        if (itemsInRoom.isEmpty()) {
            return "There are no items to be found here.";
        }
        if (!this.getHasBeenSearched()) {
            this.setHasBeenSearched(true);
            //return this.firstSearchDescription;
        }
        return this.firstSearchDescription;
    }

    /*************************
     * RoomInventory Methods *
     *************************/
    @Override
    public void removeItemFromItems(Item item) {
        this.items.remove(item);
    }
    @Override
    public void addItemToItems(Item item) {
        this.items.add(item);
    }

    /******************
     * Custom methods *
     ******************/
    @Override
    public CommandsObject performCustomMethods(
            String[] inputs, Player player) {
        CommandsObject commandsToReturn = new CommandsObject();
        commandsToReturn.items = player.getInventory();
        switch (inputs[0]) {
            case "s":
            case "search":
//                if (inputs.length > 1) {
//                    commandsToReturn.message = this.search(inputs);
//                } else {
//                    commandsToReturn.message = this.search();
//                }
                commandsToReturn.message = this.search();
                return commandsToReturn;
//            case "u":
//            case "unlock":
//                return tryUnlockingDoor(commandsToReturn);
            default:
                return null;
        }
    }

    
    @Override
    public GoArgs go(String direction) {
        if (direction != null) {
            switch (direction) {
                case "back":
                case "backwards":
                    return new GoArgs(this.neighbors[0]);
                default:
                    return new GoArgs();
            }
        }
        return new GoArgs();
    }
    
}
