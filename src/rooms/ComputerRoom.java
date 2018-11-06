/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rooms;

import java.util.ArrayList;

import items.iItem;
import titles.GameStrings;

import static services.ConsoleLogger.output;
import static shared.Shared.validateNoun;

/**
 *
 * @author esose
 */
public class ComputerRoom implements IRoom {
    
    private static final int id = RoomId.COMPUTERROOM.getId();
    private static final String name = "Computer Room";
    private boolean hasBeenSearched = false;
    private boolean computerIsLocked = true;
    private boolean lightIsOn = false;
    private final int[] neighbors = {RoomId.HALL.getId()};
    private ArrayList<iItem> items;
    
    private static final String description = RoomDescriptions.compRoom;
    private static final String firstSearchNoLight = 
            RoomDescriptions.compSearchNoLight;
    private static final String firstSearchWithLight = 
            RoomDescriptions.compFirstSearchWithLight;
    private static final String otherSearchWithLight = 
            RoomDescriptions.compOtherSearchWithLight;

    /**
     * Constructor for the Minesweeper Simulation room
     */
    ComputerRoom() {
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
        if (!this.getLightIsOn()) {
            return description;
        }
        return firstSearchWithLight;
    }
    private boolean getHasBeenSearched() {
        return this.hasBeenSearched;
    }
    private void setHasBeenSearched() {
        if (!this.hasBeenSearched) {
            this.hasBeenSearched = true;
        }
    }
    public boolean getComputerIsLocked() {
        return this.computerIsLocked;
    }
    public void setComputerIsLocked(boolean tf) {
        //possibly additional code in here later.
        this.computerIsLocked = tf;
    }
    private boolean getLightIsOn() {
        return this.lightIsOn;
    }
    private boolean toggleLights() {
        //possibly additional code in here later.
        if (this.lightIsOn) {
            this.lightIsOn = false;
            return false;
        } else {
            this.lightIsOn = true;
            return true;
        }
    }
    
    @Override
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
        if (this.lightIsOn) {
            if (!this.getHasBeenSearched()) {
                setHasBeenSearched();
                return firstSearchWithLight;
            }
            return otherSearchWithLight;
        } else {
//            if (!this.getHasBeenSearched()) {
//                setHasBeenSearched(true);
//                return firstSearchNoLight;
//            }
            return firstSearchNoLight;
        }
    }
    
    /*************************
     * RoomInventory Methods *
     *************************/
    @Override
    public void removeItemFromItems(iItem item) {
        this.items.remove(item);
    }
    @Override
    public void addItemToItems(iItem item) {
        this.items.add(item);
    }

    /******************
     * Custom methods *
     ******************/
    //flip lightswitch/turn light(s) on/etc.
    @Override
    public void performCustomMethods(String[] inputs) {
        switch (inputs[0]) {
            case "flip":
            case "turn":
            case "toggle":
                output(tryFlippingLightswitch(inputs));
                break;
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
    @Override
    public int go(String direction) {
        switch (direction) {
            case "back":
            case "backward":
            case "backwards":
                return this.neighbors[0];
            default:
                return -1;
        }
    }

    /******************
     *    Attacking   *
     ******************/
    @Override
    public void attack() {
        output(GameStrings.NothingToAttackHereString);
    }

    
    
    
    /************************
     *  CUSTOM TRY METHODS  *
     ************************/
    private String tryFlippingLightswitch(String[] inputs) {
        if (validateNoun(inputs)) {
            String noun = inputs[1];
            switch(noun) {
                case "light":
                case "lights":
                case "lightswitch":
                case "switch":
                    boolean light = this.toggleLights();
                    if (light) {
                        return "Bright bars of light in the ceiling "
                                + "flicker on.";
                    }
                    return "The lights shut off, plunging you back "
                            + "into the dark.";
                default:
                    return "Try including the title of the object you wish \n"
                            + "to interact with.";
            }
        }
        return "Try including an object to interact with after the verb.";
            
    }
    
}
