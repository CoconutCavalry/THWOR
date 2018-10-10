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
import shared.DiceRoller;
import shared.GoArgs;
import shared.Shared;

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
        RoomId.DININGROOM.getId(), RoomId.MINESWEEPERSIM.getId()};
    private ArrayList<Item> items;
    
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
        this.guardianHealth = 20;
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

    /*****************
     *    MOVEMENT   *
     *****************/
    @Override
    public GoArgs go(String direction) {
        if (this.getGuardianIsAngry()) {
            return new GoArgs("The shadowy form laughs an eerie, haunting laugh \n"
                    + "that sends chills down your spine. \n"
                    + "'You have angered the Guardian,' it says.\n"
                    + "'You shall not leave.'");
        }
        if (direction != null) {
            switch (direction) {
                case "back":
                case "backwards":
                    return new GoArgs(this.neighbors[0]);
                case "left":
                    return new GoArgs(this.neighbors[1]);
                case "right":
                    return new GoArgs(this.neighbors[2]);
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
        AttackArgs results = new AttackArgs();
        if (!this.guardianIsDead) {
            this.guardianIsAngry = true;
            String battle = "Yaas die fool";
            int newHealth = health;
            this.setGuardianIsAngry(true);
            battle = battle + attackGuardian();
            if (!this.guardianIsDead) {
                int damageTaken = DiceRoller.getDamage();
                newHealth = newHealth - damageTaken;
                battle = battle + "\nThe Guardian hits you for " 
                    + damageTaken + " damage.";
                if (newHealth < 1) {
                    newHealth = -999;
                    battle = battle + "\nYou are dead.";
                }
            } 
            results.setHealth(newHealth);
            results.setMessage(battle);
            results.setInHand(inHand);
            return results;
        }
        return results;
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
