/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rooms;

import java.util.ArrayList;

import items.iItem;
import shared.Shared;
import titles.GameStrings;

import static core.services.ConsoleLogger.output;

/**
 *
 * @author esose
 */
public class DiningRoom extends RoomBase {

    private static final int id = RoomId.DININGROOM.getId();
    private final int[] neighbors = {RoomId.HALL.getId(), RoomId.KITCHEN.getId()};
    private static final String name = "Dining Room";
    private final String description = RoomDescriptions.dining;
    private ArrayList<iItem> items;

    private boolean hasBeenSearched = false;

    /**
     * Constructor for the DiningRoom
     */
    DiningRoom() {
        this.items = new ArrayList<>();
    }
    
    /***********************
     * Getters and setters *
     ***********************/
      
    public int getId() {
        return id;
    }
      
    public String getName() {
        return name;
    }
      
    public String getDescription() {
        return this.description;
    }
      
    public ArrayList<iItem> getItems() {
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
        ArrayList<iItem> itemsInRoom = this.getItems();
        if (itemsInRoom.isEmpty()) {
            return "There are no items to be found here.";
        }
        if (!this.hasBeenSearched) {
            this.hasBeenSearched = true;
        }

        return Shared.appendDescriptionToItemsString(
                    RoomDescriptions.defaultSearchDescription, itemsInRoom);
    }
    
    /*************************
     * RoomInventory Methods *
     *************************/
      
    public void removeItemFromItems(iItem item) {
        this.items.remove(item);
    }
      
    public void addItemToItems(iItem item) {
        this.items.add(item);
    }

    /******************
     * Custom methods *
     ******************/
      
    public void performCustomMethods(
            String[] inputs) {
        switch (inputs[0]) {
            case "s":
            case "search":
                output(this.search());
                break;
            default:
                output(GameStrings.PerformCustomMethodsBadInput);
        }
    }

    /*****************
     *    MOVEMENT   *
     *****************/
      
    public int go(String direction) {
        switch (direction) {
            case "back":
                return this.neighbors[0];
            case "forward":
            case "ahead":
            case "forwards":
                return this.neighbors[1];
            default:
                return -1;
        }
    }

    /******************
     *      Other     *
     ******************/


}
