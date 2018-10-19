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
import titles.GameStrings;

/**
 *
 * @author esose
 */
public class DiningRoom implements IRoom {

    private static final int id = RoomId.DININGROOM.getId();
    private boolean hasBeenSearched = false;
    private final int[] neighbors = {RoomId.HALL.getId()};
    public ArrayList<Item> items;
    private static final String name = "Dining Room";
    private final String description = RoomDescriptions.dining;
    private final String firstSearchDescription = 
            RoomDescriptions.diningFirstSearch;
    
    /**
     * Constructor for the Library
     */
    public DiningRoom() {
        this.items = new ArrayList<>();
    }
    
    /***********************
     * Getters and setters *
     ***********************/
    @Override
    public int getId() {
        return id;
    }
    @Override
    public String getName() {
        return name;
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
    
    /******************
     * Search methods *
     ******************/
    public String search() {
        ArrayList<Item> itemsInRoom = this.getItems();
        if (itemsInRoom.isEmpty()) {
            return "There are no items to be found here.";
        }
        if (!this.hasBeenSearched) {
            this.hasBeenSearched = true;
            return Shared.appendDescriptionToItemsString(
                    this.firstSearchDescription, itemsInRoom);
        }
        
        return Shared.appendDescriptionToItemsString(
                    RoomDescriptions.defaultSearchDescription, itemsInRoom);
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
        commandsToReturn.player = player;
        switch (inputs[0]) {
            case "s":
            case "search":
                commandsToReturn.message = this.search();
                return commandsToReturn;
            default:
                return null;
        }
    }

    /*****************
     *    MOVEMENT   *
     *****************/
    @Override
    public GoArgs go(String direction) {
        if (direction != null) {
            switch (direction) {
                case "back":
                    return new GoArgs(this.neighbors[0]);
                default:
                    return new GoArgs();
            }
        }
        return new GoArgs();
    }

    @Override
    public String attack() {
        return GameStrings.NothingToAttackHereString;
    }
    
}
