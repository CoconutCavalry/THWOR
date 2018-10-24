/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rooms;

import housewithoneroom.Game;
import items.Item;
import java.util.ArrayList;

import shared.DiceRoller;
import titles.GameStrings;

import static services.ConsoleLogger.output;

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
    private ArrayList<Item> items;
    
    private final String description = RoomDescriptions.hall;
    private final String guardianAliveSearch = 
            RoomDescriptions.hallFirstSearch;
    private final String guardianDeadSearch = 
            RoomDescriptions.hallOtherSearch;
    
    /**
     * Constructor for the Hall
     */
    public Hall() {
        this.items = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            this.items.add(Item.TORCH_FROM_HALL);
        }
        this.guardianHealth = 1;
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
        if (!this.getHasBeenSearched()) {
            this.setHasBeenSearched(true);
            if (!this.guardianIsDead) {
                this.setGuardianIsAngry(true);
                return this.guardianDeadSearch + "\n" 
                        + this.guardianAliveSearch;
            }
        }
        if (!this.guardianIsDead) {
            this.setGuardianIsAngry(true);
            return this.guardianAliveSearch;
        }
        ArrayList<Item> itemsInRoom = this.getItems();
        if (itemsInRoom.isEmpty()) {
            return "There are no items to be found here.";
        }
        return this.guardianDeadSearch;
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
            return -1;
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
    public String attack() {
        String battleScript = GameStrings.NothingToAttackHereString;
        if (!this.guardianIsDead && this.guardianIsAngry) {
            battleScript = "Yaas die fool";
            this.setGuardianIsAngry(true);
            battleScript = battleScript + attackGuardian();
            if (!this.guardianIsDead) {
                int damageTaken = DiceRoller.getDamage();
                Game.player.setHealth(Game.player.getHealth() - damageTaken);
                battleScript = battleScript + "\nThe Guardian hits you for "
                    + damageTaken + " damage.";
                if (Game.player.getHealth() < 1) {
                    Game.player.setHealth(0);
                    Game.state = false;
                    battleScript = battleScript + "\nYou are dead.";
                }
            }
            return battleScript;
        }
        return battleScript;
    }

    private String attackGuardian() {
        int damage = DiceRoller.getDamage();
        this.guardianHealth = this.guardianHealth - damage;
        String results = "\nYou hit the Guardian for " + damage + " damage.";
        if (this.guardianHealth < 1) {
            this.guardianIsDead = true;
            this.guardianIsAngry = false;
            this.guardianHealth = 0;
            results = results 
                    + "\nThe dark form dissolves in a cloud of mist.";
        }
        return results;
    }
}
