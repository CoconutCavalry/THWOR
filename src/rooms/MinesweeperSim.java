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
import static shared.Shared.validateNoun;

/**
 *
 * @author esose
 */
public class MinesweeperSim implements IRoom {
    
    private static final int id = RoomId.MINESWEEPERSIM.getId();
    private static final String name = "Computer Room";
    private boolean hasBeenSearched = false;
    private boolean computerIsLocked = true;
    private boolean lightIsOn = false;
    private final int[] neighbors = {RoomId.HALL.getId()};
    private ArrayList<Item> items;
    
    private static final String description = RoomDescriptions.msSim;
    private static final String firstSearch = RoomDescriptions.msSimFirstSearch;
    private static final String lightOnSearch = RoomDescriptions.msSimOtherSearch;
    private static final String searchNoLight = 
            RoomDescriptions.msSimOtherSearchNoLight;

    /**
     * Constructor for the Minesweeper Simulation room
     */
    public MinesweeperSim() {
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
        if (!this.hasBeenSearched) {
            if (tf) {
               this.hasBeenSearched = true; 
            }
        }
    }
    public boolean getComputerIsLocked() {
        return this.hasBeenSearched;
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
        if (!this.hasBeenSearched) {
            setHasBeenSearched(true);
            //output(firstSearch);
            return firstSearch;
        }
        if (this.lightIsOn) {
            //output(lightOnSearch);
            return lightOnSearch;
        } else {
            //output(description + "\n" + firstSearch);
            return firstSearch;
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
    public CommandsObject performCustomMethods(String[] inputs, Player player) {
        CommandsObject commandsToReturn = new CommandsObject();
        commandsToReturn.items = player.getInventory();
        switch (inputs[0]) {
            case "flip":
            case "turn":
            case "toggle":
                commandsToReturn.message = tryFlippingLightswitch(inputs);
                return commandsToReturn;
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
    public AttackArgs attack(int health, Item[] inHand) {
        return new AttackArgs();
    }

    
    
    
    /************************
     *  CUSTOM TRY METHODS  *
     ************************/
    private String tryFlippingLightswitch(String[] inputs) {
        if (validateNoun(inputs)) {
            String noun = inputs[1];
            switch(noun) {
                case "light":
                case "lightswitch":
                    boolean light = this.toggleLights();
                    if (light) {
                        return "Bright bars of light in the ceiling "
                                + "flicker on.";
                    }
                    return "The lights shut off, plunging you back "
                            + "into the dark.";
                default:
                    
            }
        }
        return "Try including an object to interact with after the verb.";
            
    }
    
}
