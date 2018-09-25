/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rooms;

import shared.Shared;
import items.Item;
import java.util.ArrayList;
import java.util.Arrays;
import shared.CommandsObject;
import shared.GoArgs;
import titles.Excerpts;

/**
 *
 * @author esose
 */
public class Study implements IRoom {
    
    private boolean _hasBeenSearched = false;
    private boolean _hallDoorIsLocked = true;
    private final int[] _neighbors = {0,2};
    public ArrayList<Item> items;
    private final String _description = RoomDescriptions.study;
    private final String _firstSearchDescription = 
            RoomDescriptions.studyFirstSearch;
    
    public Study() {
        this.items = new ArrayList<>();
        this.items.add(Item.KEY);
    }
    
    @Override
    public int getId() {
        return 1;
    }

    @Override
    public String getName() {
        return "Study";
    }

    @Override
    public String getDescription() {
        return _description;
    }

    @Override
    public boolean hasBeenSearched() {
        return this._hasBeenSearched;
    }

    @Override
    public boolean areItemsAlwaysVisble() {
        return false;
    }

    @Override
    public int[] getNeighbors() {
        return this._neighbors;
    }

    @Override
    public ArrayList<Item> getItemsList() {
        if (!this._hasBeenSearched) {
            return null;
        }
        return this.items;
    }

    @Override
    public String getItemsInRoom() {
        String stringOfItems = "";
        stringOfItems = Shared.getItemsInListToString(this.items);
        return stringOfItems;
    }

    private String search(String[] inputs) {
        if (this.items.isEmpty()) {
            return "There are no items to be found here.";
        }
        
        if (!this._hasBeenSearched) {
            this._hasBeenSearched = true;
            return this._firstSearchDescription + this.getItemsInRoom();
        }
        
        return this.defaultSearchDescription + this.getItemsInRoom();
    }
    
    @Override
    public void removeItemFromItems(Item item) {
        this.items.remove(item);
    }
    
    @Override
    public void addItemToItems(Item item) {
        this.items.add(item);
    }
    
    @Override
    public CommandsObject performCustomMethods(CommandsObject commands) {
        CommandsObject commandsToReturn = new CommandsObject();
        switch (commands.inputs[0]) {
            
            case "s":
            case "search":
                commandsToReturn.message = this.search(commands.inputs);
                commandsToReturn.items = commands.items;
                return commandsToReturn;
            case "u":
            case "unlock":
                return tryUnlockingDoor(commands);
            default:
                return null;
        }
    }
    
    @Override
    public GoArgs go(String direction) {
        if (direction != null) {
            switch (direction) {
                case "ahead":
                case "forward":
                case "straight":
                    return tryMovingIntoHall();
                case "back":
                case "backwards":
                    return new GoArgs(this._neighbors[0]);
                default:
                    return new GoArgs();
            }
        }
        return new GoArgs();
    }

    private GoArgs tryMovingIntoHall() {
        if (!this._hallDoorIsLocked) {
            return new GoArgs(this._neighbors[1]);
        } else {
            return new GoArgs("The door is locked.");
        }
    }

    private CommandsObject tryUnlockingDoor(CommandsObject commands) {
        //check inventory for black key
        for (Item item : commands.items) {
            // if the key is in the player's inventory
            if (item.getName().equals(Item.KEY.getName())) {
                // use the key and drop it
                commands.items.remove(item);
                // add an action message
                commands.message = "You unlock the door.";
                return commands;
            }
        }
        commands.message = "You do not have the right key.";
        return commands;
    }

    
}


