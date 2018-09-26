/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rooms;

import characters.Player;
import shared.Shared;
import items.Item;
import java.util.ArrayList;
import shared.CommandsObject;
import shared.GoArgs;

/**
 *
 * @author esose
 */
public class Study implements IRoom {
    
    private boolean hasBeenSearched = false;
    private boolean deskHasBeenSearched = false;
    private boolean fireplaceHasBeenSearched = false;
    private boolean hallDoorIsLocked = true;
    private final int[] neighbors = {0,2};
    private ArrayList<Item> items;
    
    private final String description = RoomDescriptions.study;
    private final String firstSearchDescription = 
            RoomDescriptions.studyFirstSearch;
    private final String deskFirstSearchDescription = 
            RoomDescriptions.studyDeskFirstSearch;
    private final String deskOtherSearchDescription = 
            RoomDescriptions.studyDeskOtherSearch;
    private final String fireplaceFirstSearchDescription = 
            RoomDescriptions.studyFireplaceFirstSearch;
    private final String fireplaceOtherSearchDescription = 
            RoomDescriptions.studyFireplaceOtherSearch;
    
    /**
     * Constructor for the Study
     */
    public Study() {
        this.items = new ArrayList<>();
    }
    
    /***********************
     * Getters and setters *
     ***********************/
    public int getId() {
        return 1;
    }
    @Override
    public String getName() {
        return "Study";
    }
    @Override
    public String getDescription() {
        return description;
    }
    public boolean getHasBeenSearched() {
        return this.hasBeenSearched;
    }
    public void setHasBeenSearched(boolean tf) {
        if (tf == true) {
            this.hasBeenSearched = tf; 
            //starts as false but can never go back
        }
    }
    public boolean getDeskHasBeenSearched() {
        return this.deskHasBeenSearched;
    }
    public void setDeskHasBeenSearched(boolean tf) {
        if (tf == true) {
            this.deskHasBeenSearched = tf;
            this.addItemToItems(Item.BLACK_KEY_TO_HALL_FROM_STUDY);
        }
    }
    private boolean getFireplaceHasBeenSearched() {
        return this.fireplaceHasBeenSearched;
    }
    private void setFireplaceHasBeenSearched(boolean tf) {
        if (tf == true) {
            this.fireplaceHasBeenSearched = tf;
            this.addItemToItems(Item.MESSAGE_FROM_FIREPLACE_IN_STUDY);
        }
    }
    
    @Override
    public ArrayList<Item> getItems() {
        if (this.items ==  null) {
            this.items = new ArrayList<>();
            return this.items;
        }
        return this.items;
    }

    /******************
     * Search methods *
     ******************/
    private String search() {
        ArrayList<Item> itemsInRoom = this.getItems();
        if (itemsInRoom.isEmpty()) {
            return "There are no items to be found here.";
        }
        if (!this.getHasBeenSearched()) {
            this.setHasBeenSearched(true);
            return Shared.appendDescriptionToItemsString(
                    this.firstSearchDescription, itemsInRoom);
        }
        return Shared.appendDescriptionToItemsString(
                    IRoom.defaultSearchDescription, itemsInRoom);
    }
    private String search(String[] inputs) {
        if (inputs[1] == null) {
            return "Bad input; try again.";
        }
        switch (inputs[1]) {
            case "desk":
                return this.searchDesk();
            case "fireplace":
            case "fp":
                return this.searchFireplace();
            default:
                return "Bad input; try again.";
        }
    }
    private String searchDesk() {
        if (!this.getDeskHasBeenSearched()) {
            this.setDeskHasBeenSearched(true);
            return this.deskFirstSearchDescription;
        }
        return this.deskOtherSearchDescription;
    }
    private String searchFireplace() {
        if (!this.getFireplaceHasBeenSearched()) {
            this.setFireplaceHasBeenSearched(true);
            return this.fireplaceFirstSearchDescription;
        }
        return this.fireplaceOtherSearchDescription;
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
                if (inputs.length > 1) {
                    commandsToReturn.message = this.search(inputs);
                } else {
                    commandsToReturn.message = this.search();
                }
                return commandsToReturn;
            case "u":
            case "unlock":
                return tryUnlockingDoor(commandsToReturn);
            default:
                return null;
        }
    }
    
    @Override
    public GoArgs go(String direction) {
        if (direction != null) {
            switch (direction) {
                case "left":
                    return tryMovingIntoHall();
                case "back":
                case "backwards":
                    return new GoArgs(this.neighbors[0]);
                default:
                    return new GoArgs();
            }
        }
        return new GoArgs();
    }

    private GoArgs tryMovingIntoHall() {
        if (!this.hallDoorIsLocked) {
            return new GoArgs(this.neighbors[1]);
        } else {
            return new GoArgs("The door is locked.");
        }
    }

    private CommandsObject tryUnlockingDoor(CommandsObject commands) {
        //check inventory for black key
        for (Item item : commands.items) {
            // if the key is in the player's inventory
            if (item.getName().equals(Item.BLACK_KEY_TO_HALL_FROM_STUDY.getName())) {
                // use the key and drop it
                this.hallDoorIsLocked = false;
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


