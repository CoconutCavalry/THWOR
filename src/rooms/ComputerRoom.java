/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rooms;

import items.Item;
import java.util.ArrayList;

import shared.GoArgs;
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
    private ArrayList<Item> items;
    
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
    public ComputerRoom() {
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
    public boolean getHasBeenSearched() {
        return this.hasBeenSearched;
    }
    public void setHasBeenSearched(boolean tf) {
        if (!this.hasBeenSearched) {
            if (tf) {
               this.hasBeenSearched = true; 
            }
        }
    }
    public boolean getComputerIsLocked() {
        return this.computerIsLocked;
    }
    public void setComputerIsLocked(boolean tf) {
        //possibly additional code in here later.
        this.computerIsLocked = tf;
    }
    public boolean getLightIsOn() {
        return this.lightIsOn;
    }
    public boolean toggleLights() {
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
        if (this.lightIsOn) {
            if (!this.getHasBeenSearched()) {
                setHasBeenSearched(true);
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
    public GoArgs go(String direction) {
        if (direction != null) {
            switch (direction) {
                case "back":
                case "backward":
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
    public String attack() {
        return GameStrings.NothingToAttackHereString;
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
