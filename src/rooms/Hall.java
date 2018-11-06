/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rooms;

import characters.Player;
import housewithoneroom.Game;
import java.util.ArrayList;

import items.iItem;
import shared.Shared;
import titles.GameStrings;

import static services.ConsoleLogger.output;
import static services.ConsoleLogger.outputLn;

/**
 *
 * @author esose
 */
public class Hall implements IRoom {
    
    private static final int id = RoomId.HALL.getId();
    private static final String name = "Hall";
    private boolean hasBeenSearched = false;
    private boolean guardianIsAngry = false;
    private boolean guardianIsDead = false;
    private int guardianHealth;
    private final int[] neighbors = {RoomId.STUDY.getId(),
        RoomId.DININGROOM.getId(), RoomId.COMPUTERROOM.getId()};
    private ArrayList<iItem> items;
    
    private final String description = RoomDescriptions.hall;
    private final String guardianAliveSearch = 
            RoomDescriptions.hallFirstSearch;
    private final String guardianDeadSearch = 
            RoomDescriptions.hallOtherSearch;

    public Player guardian;

    /**
     * Constructor for the Hall
     */
    public Hall() {
        this.items = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
//            this.items.add();
        }
        this.guardianHealth = 1;
        this.guardian = new Player("The Guardian", 100,
                "The dark form dissolves in a cloud of mist.");
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
    public ArrayList<iItem> getItems() {
        if (this.items ==  null) {
            this.items = new ArrayList<>();
            return this.items;
        }
        return this.items;
    }
    private boolean getHasBeenSearched() {
        return this.hasBeenSearched;
    }
    private void setHasBeenSearched() {
        if (!this.hasBeenSearched) {
            this.hasBeenSearched = true;
        }
    }
    private boolean getGuardianIsAngry() {
        return this.guardianIsAngry;
    }
    private void setGuardianIsAngry() {
        if (!this.guardianIsAngry) {
            this.guardianIsAngry = true;
        }
    }
    
    /******************
     * Search methods *
     ******************/
    private String search() {
        if (!this.getHasBeenSearched()) {
            this.setHasBeenSearched();
            if (!this.guardianIsDead) {
                this.setGuardianIsAngry();
                return this.guardianDeadSearch + "\n" 
                        + this.guardianAliveSearch;
            }
        }
        if (!this.guardianIsDead) {
            this.setGuardianIsAngry();
            return this.guardianAliveSearch;
        }
        ArrayList<iItem> itemsInRoom = this.getItems();
        if (itemsInRoom.isEmpty()) {
            return "There are no items to be found here.";
        }
        return this.guardianDeadSearch;
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
    @Override
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
    @Override
    public int go(String direction) {
        if (this.getGuardianIsAngry()) {
            output(RoomDescriptions.guardianIsAngryCannotLeave);
            return -2;
        }
        switch (direction) {
            case "back":
            case "backwards":
                return this.neighbors[0];
            case "left":
                return this.neighbors[1];
            case "right":
                return this.neighbors[2];
            default:
                return -1;
        }
    }

    /******************
     *    Attacking   *
     ******************/
    @Override
    public void attack() {
        String battleScript = GameStrings.NothingToAttackHereString;
        if (!this.guardianIsDead && this.guardianIsAngry) {
            Shared.attack(Game.player, this.guardian);
            if (this.guardian.getHealth() == 0) {
                this.guardianIsDead = true;
                this.guardianIsAngry = false;
                outputLn(guardian.death);
            } else {
                Shared.defend(Game.player, this.guardian);
                if (Game.player.getHealth() == 0) {
                    Game.state = false;
                    output(Game.player.death);
                }
            }
        }else {
            outputLn(battleScript);
        }
    }

}
