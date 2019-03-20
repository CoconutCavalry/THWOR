/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rooms;

import housewithoneroom.Game;
import items.HallKey;
import items.Message;
import items.iItem;
import items.iKey;
import rooms.doors.Door;
import shared.Shared;

import java.util.ArrayList;

import titles.GameStrings;

import static services.ConsoleLogger.output;

/**
 *
 * @author esose
 */
public class Study extends RoomFull {

//    private final Door[] doors = {
//            new Door(RoomId.HALL.getId(), "\nOn your left, beside the desk, is a dark-colored door.",
//                    new String[]{"left"}, new HallKey(), true),
//            new Door(RoomId.LIBRARY.getId(), "\nBehind you is the door to the library.",
//                    new String[]{"back", "backward", "backwards"})};
    
    private static final int id = RoomId.STUDY.getId();
    private static final String name = "Study";
    private boolean hasBeenSearched = false;
    private boolean deskHasBeenSearched = false;
    private boolean fireplaceHasBeenSearched = false;
    private boolean hallDoorIsLocked = true;
    private final int[] neighbors = {
        RoomId.LIBRARY.getId(), RoomId.HALL.getId()};
    
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
    public Study() {}
    
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
        return description;
    }

    private boolean getHasBeenSearched() {
        return this.hasBeenSearched;
    }
    private void setHasBeenSearched() {
        if (!this.hasBeenSearched) {
            this.hasBeenSearched = true;
            //starts as false but can never go back
        }
    }
    private boolean getDeskHasBeenSearched() {
        return this.deskHasBeenSearched;
    }
    private void setDeskHasBeenSearched() {
        if (!this.deskHasBeenSearched) {
            this.deskHasBeenSearched = true;
            this.addItemToItems(new HallKey());
        }
    }
    private boolean getFireplaceHasBeenSearched() {
        return this.fireplaceHasBeenSearched;
    }
    private void setFireplaceHasBeenSearched() {
        if (!this.fireplaceHasBeenSearched) {
            this.fireplaceHasBeenSearched = true;
            this.addItemToItems(new Message());
        }
    }

    /******************
     * Search methods *
     ******************/
    private String search() {
        ArrayList<iItem> itemsInRoom = this.getItems();
        if (itemsInRoom.isEmpty()) {
            return "There are no items to be found here.";
        }
        if (!this.getHasBeenSearched()) {
            this.setHasBeenSearched();
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
            this.setDeskHasBeenSearched();
            return this.deskFirstSearchDescription;
        }
        return this.deskOtherSearchDescription;
    }
    private String searchFireplace() {
        if (!this.getFireplaceHasBeenSearched()) {
            this.setFireplaceHasBeenSearched();
            return this.fireplaceFirstSearchDescription;
        }
        return this.fireplaceOtherSearchDescription;
    }
    
    /******************
     * Custom methods *
     ******************/
     
    public void performCustomMethods(String[] inputs) {
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
                tryUnlockingDoor(inputs);
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
            case "left":
                return tryMovingIntoHall();
            case "back":
            case "backward":
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

    /**
     * unlocks the door to the hall if the player has the right key equipped
     */
    private void tryUnlockingDoor(String[] inputs) {
        // Validate input
        if (inputs.length == 1) {
            output("What would you like to unlock?");
        } else if (inputs.length == 2) {
            if (inputs[1].equals("door")) {
                output("Which door would you like to unlock?");
            } else {
                output(GameStrings.PerformCustomMethodsBadInput);
            }
        } else if (inputs.length == 3) {
            if (inputs[1].equals("left")) {
                if (inputs[2].equals("door")) {
                    unlockHallDoor();
                } else {
                    output("try: 'unlock left door'");
                }
            } else {
                output(GameStrings.PerformCustomMethodsBadInput);
            }
        }
        else {
            output(GameStrings.PerformCustomMethodsBadInput);
        }

    }

    private void unlockHallDoor() {
        //check hands for black key
        if (Game.player.getRHand() != null && Game.player.getRHand() instanceof iKey) {
            iKey key = (iKey)Game.player.getRHand();
            if (key.unlocks() == neighbors[1]) {
                Game.player.setRHand(null);
                output("You use the black key to unlock the door on the left.");
                this.hallDoorIsLocked = false;
            }
        } else if (Game.player.getLHand() != null && Game.player.getLHand() instanceof iKey) {
            iKey key = (iKey)Game.player.getLHand();
            if (key.unlocks() == neighbors[1]) {
                Game.player.setLHand(null);
                output("You use the black key to unlock the door on the left.");
                this.hallDoorIsLocked = false;
            }
        } else {
            output("You do not have the right key equipped.");
        }
    }

}


