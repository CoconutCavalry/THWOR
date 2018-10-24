/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rooms;

import housewithoneroom.Game;
import shared.Shared;
import items.Item;
import java.util.ArrayList;

import titles.GameStrings;

import static services.ConsoleLogger.output;

/**
 *
 * @author esose
 */
public class Study implements IRoom {
    
    private static final int id = RoomId.STUDY.getId();
    private static final String name = "Study";
    private boolean hasBeenSearched = false;
    private boolean deskHasBeenSearched = false;
    private boolean fireplaceHasBeenSearched = false;
    private boolean hallDoorIsLocked = true;
    private final int[] neighbors = {
        RoomId.LIBRARY.getId(), RoomId.HALL.getId()};
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
                    RoomDescriptions.defaultSearchDescription, itemsInRoom);
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
    public void performCustomMethods(
            String[] inputs) {
        switch (inputs[0]) {
            case "s":
            case "search":
                if (inputs.length > 1) {
                    output(this.search(inputs));
                } else {
                    output(this.search());
                }
                break;
            case "u":
            case "unlock":
                tryUnlockingDoor();
                break;
            default:
                output(GameStrings.PerformCustomMethodsBadInput);
        }
    }
    
    /*****************
     *    MOVEMENT   *
     *****************/
    @Override
    public int go(String direction) {
        switch (direction) {
            case "left":
                return tryMovingIntoHall();
            case "back":
            case "backwards":
                return this.neighbors[0];
            default:
                return -1;
        }
    }

    private int tryMovingIntoHall() {
        if (!this.hallDoorIsLocked) {
            return this.neighbors[1];
        } else {
            output(GameStrings.DoorIsLocked);
            return -2;
        }
    }

    private void tryUnlockingDoor() {
        //check hands for black key
        Item[] items = Game.player.getItemsInHands();
        for (int i = 0; i < items.length; i++) {
            Item key = items[i];
            if (key == Item.BLACK_KEY_TO_HALL_FROM_STUDY) {
                // use the key and drop it
                this.hallDoorIsLocked = false;
                items[i] = null;
                Game.player.setItemsInHand(items);
                // add an action message
                output("You use the black key to unlock the door.");
                return;
            }
        }
        output("You do not have the right key equipped.");
    }
    
    /******************
     *    Attacking   *
     ******************/
    @Override
    public String attack() {
        return GameStrings.NothingToAttackHereString;
    }

    
}


