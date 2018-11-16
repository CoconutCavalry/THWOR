/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rooms;

import java.util.ArrayList;

import items.iItem;
import services.IOService;
import shared.Shared;
import titles.GameStrings;

import static services.ConsoleLogger.output;

/**
 *
 * @author esose
 */
public class DiningRoom implements iRoom {

    private static final int id = RoomId.DININGROOM.getId();
    private boolean hasBeenSearched = false;
    private final int[] neighbors = {RoomId.HALL.getId()};
    public ArrayList<iItem> items;
    private static final String name = "Dining Room";
    private final String description = RoomDescriptions.dining;
    private final String firstSearchDescription =
            RoomDescriptions.diningFirstSearch;

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
            return Shared.appendDescriptionToItemsString(
                    this.firstSearchDescription, itemsInRoom);
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
            default:
                return -1;
        }
    }

      
    public void attack() {
        output(GameStrings.NothingToAttackHereString);
    }


    /******************
     *      Other     *
     ******************/
      
    public void roomActions() {
        String input = IOService.getNextLine();
        output("You entered " + input);
    }


}
