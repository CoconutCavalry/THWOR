package characters;

import shared.Shared;
import items.Item;
import java.util.ArrayList;

import static services.ConsoleLogger.output;

/**
 *
 * @author esose
 */
public class Player {
    private String _name;
    private String _gender;
    private int _health = 10;
    private ArrayList<Item> _inventory;
    private Item lHand = null;
    private Item rHand = null;
    private Item[] pockets = new Item[4];
    private int _backpackSpaceAvailable = 0;
    //this size is relative, not universal.
    private double _inventorySize;
    private double _remainingInventorySpace;
    public String death = "You are dead.";
    
    public Player(String name, int health, String death){
        this._name = name;
        this._health = health;
        this.death = death;
    }
    
    public Player(String name, String gender) {
        this._name = name;
        this._gender = gender;
        this._inventory = new ArrayList<>();
        this._inventorySize = this.getPocketSpaceRemaining()
                       + this._backpackSpaceAvailable;
        this._remainingInventorySpace = this._inventorySize;
    }
    
    public void setPlayer(Player player) {
        this._backpackSpaceAvailable = player._backpackSpaceAvailable;
        this.setGender(player.getGender());
        this.setHealth(player.getHealth());
        this.setInventory(player.getInventory());
        
    }
    
    public String getName() { return this._name; }
    public void setName(String name) { this._name = name; }
    public String getGender() { return this._gender; }
    public void setGender(String gender) { this._gender = gender; }
    public int getHealth() { return this._health; }
    public void setHealth(int newHealth) { this._health = newHealth; }

    public ArrayList<Item> getItemsInHands() {
//        Item[] retVal = {this.getLHand(), this.getRHand()};
        ArrayList<Item> retVal = new ArrayList<>();
        retVal.add(rHand);
        retVal.add(lHand);
        return retVal;
    }

    public Item getRHand() {
        return this.rHand;
    }
    public void setRHand(Item item) {
        this.rHand = item;
    }

    public Item getLHand() {
        return this.lHand;
    }
    public void setLHand(Item item) {
        this.lHand = item;
    }

    /**
     * Overwrites whatever items used to be in-hand
     * @param items an array of items to put in-hand
     */
    public void setItemsInHand(ArrayList<Item> items) {
        if (!items.isEmpty()) {
            this.setLHand(null);
            this.setRHand(null);
        } else {
            this.setRHand(items.get(0));

        }
//        if (items != null && items.length > 0 && items.length <= 2) {
//            this.setRHand(items[0]);
//            if(items.length == 2) {
//                this.setLHand(items[1]);
//            }
//        } else {
//            this.setLHand(null);
//            this.setRHand(null);
//        }
    }
    
    public int getNumberOfEmptyHands() {
        int count = 2;
        if (this.getRHand() != null) {
            count--;
        }
        if (this.getLHand() != null) {
            count--;
        }
        return count;
    }

    public double getPocketSpaceRemaining() {
        double space = 4.0;
        for (Item item : this.pockets) {
            if (item != null) {
                space = space - item.getSize();
            }
        }
        return space;
    }

    public boolean takeItem(Item item) {
        if (this.addItemToInventory(item)) {
            output("You took the " + item.getName() + ".");
            return true;
        }
        output("You don't have enough space in your inventory \n"
                + "for that item.");
        return false;
    }

    private boolean addItemToInventory(Item item) {
        if (item.getSize() <= this._remainingInventorySpace) {
            if (this._inventory == null) {
                this._inventory = new ArrayList<>();
            }
            this._inventory.add(item);
            this._remainingInventorySpace -= item.getSize();
            return true;
        }
        return false;
    }

    private boolean removeItemFromInventory(Item item) {
        if (this.getInventory().contains(item)) {
            this._inventory.remove(item);
            this._remainingInventorySpace += item.getSize();
            return true;
        }
        return false;
    }

    public ArrayList<Item> getInventory() {
        if (this._inventory == null || this._inventory.isEmpty()) {
            this._inventory = new ArrayList<>();
        }
        return this._inventory;
    }
    public void setInventory(ArrayList<Item> items) {
        if (items == null) {
            this._inventory = new ArrayList<>();
        }
        this._inventory = items;
    }

    public String showInventory() {
        if (this._inventory == null) {
            return "Null inventory??";
        }
        if (this._inventory.isEmpty()) {
            return "You have no items in your inventory.";
        }
        return "Player inventory:\n" 
                + Shared.convertItemsInListToString(this._inventory) + "\n"
                + "Remaining space: " + Double.toString(this._remainingInventorySpace);
    }

    public Item dropItem(String itemName) {
        if (this.getInventory() == null || this.getInventory().isEmpty()) {
            return null;
        }
        for(Item item : this._inventory) {
            if (item.getName().equals(itemName)){
                this.removeItemFromInventory(item);
                return item;
            }
        }
        return null;
    }

    public String equip(Item item) {
        if (this.getRHand() == null) {
            this.setRHand(item);
            this.removeItemFromInventory(item);
            return "You equipped the " + item.getName() + ".";
        } else if (this.getLHand() == null) {
            this.setLHand(item);
            this.removeItemFromInventory(item);
            return "You equipped the " + item.getName() + ".";
        } else {
            return "You do not have a free hand.";
        }
    }

    public boolean pocket(String itemName) {
        if ((this.getRHand() != null) && (this.getRHand().getName().equals(itemName))) {
            Item item = this.getRHand();
            if (this.addItemToInventory(item)){
                this.setRHand(null);
                output("You pocketed the " + itemName + ".");
                return true;
            }
            output("You don't have space in your inventory to pocket that item.");
            return false;
        } else if ((this.getLHand() != null) && (this.getLHand().getName().equals(itemName))) {
            Item item = this.getLHand();
            if (this.addItemToInventory(item)){
                this.setLHand(null);
                output("You pocketed the " + itemName + ".");
                return true;
            }
            output("You don't have space in your inventory to pocket that item.");
            return false;
        } else {
            output("You do not have that item equipped.");
            return false;
        }
    }



    public void takeDamage(int damage) {
        int newHealth = this.getHealth() - damage;
        if (newHealth <= 0) {
            this.setHealth(0);
        } else {
            this.setHealth(newHealth);
        }
    }






    public String showStats() {
        return "Name: " + this._name + "\n"
                + "Gender: " + this._gender + "\n"
                + "Inventory: " + this._inventorySize;
    }

    public String showCharacterReport() {
        return "Character report for " + this._name + ":\n" 
                + "Gender: " + this._gender + "\n"
                + "Health: " + this._health + "\n"
                + inHandToString()
                + "Pocket space: " + this.getPocketSpaceRemaining() + "\n"
                + "Inventory size: " + this._inventorySize + "\n"
                + this.showInventory();
    }









    private String inHandToString() {
        String retVal = "Empty hands: " + this.getNumberOfEmptyHands() + "\n";
        Item lHandItem = this.getLHand();
        if (lHandItem != null) {
            retVal += "You are holding the " + lHandItem.getName() + " in your left hand.\n";
        }
        Item rHandItem = this.getRHand();
        if (rHandItem != null) {
            retVal += "You are holding the " + rHandItem.getName() + " in your right hand.\n";
        }
        return retVal;
    }
    
    
}
