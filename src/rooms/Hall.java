/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rooms;

import characters.Player;
import items.Item;
import java.util.ArrayList;
import shared.AttackArgs;
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
    private boolean guardianIsAngry = false;
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
    private boolean getGuardianIsAngry() {
        return this.guardianIsAngry;
    }
    private void setGuardianIsAngry(boolean tf) {
        this.guardianIsAngry = tf;
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
        if (this.getGuardianIsAngry()) {
            return new GoArgs("The shadowy form laughs an eerie, haunting \n"
                    + "laugh that sends chills down your spine. \n"
                    + "'You have angered the Guardian,' it says.\n"
                    + "'You cannot leave.'");
        }
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

    /******************
     *    Attacking   *
     ******************/
    @Override
    public AttackArgs attack(int health, Item[] inHand) {
        return new AttackArgs();
    }
    private String tryToAttack(Player player) {
        this.setGuardianIsAngry(true);
        return "Yaas die fool";
        // DO NOT DIE
    }
    
}
