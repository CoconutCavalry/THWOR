package characters;

import shared.Shared;
import items.Item;
import java.util.ArrayList;
import shared.TakeArgs;

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
    //private Item[] hands = {rHand, lHand};
    private Item[] pockets = new Item[4];
    private int _backpackSpaceAvailable = 0;
    //this size is relative, not universal.
    private double _inventorySize;
    private double _remainingInventorySpace;
    
    public Player(){}
    
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

    public Item[] getItemsInHands() {
        Item[] retVal = {this.getLHand(), this.getRHand()};
        return retVal;
    }

    private Item getRHand() {
        return this.rHand;
    }
    private void setRHand(Item item) {
        this.rHand = item;
    }

    private Item getLHand() {
        return this.lHand;
    }
    private void setLHand(Item item) {
        this.lHand = item;
    }

    /**
     * Overwrites whatever items used to be in-hand
     * @param items an array of items to put in-hand
     */
    public void setItemsInHand(Item[] items) {
        if (items != null && items.length > 0 && items.length <= 2) {
            this.setRHand(items[0]);
            if(items.length == 2) {
                this.setLHand(items[1]);
            }
        } else {
            this.setLHand(null);
            this.setRHand(null);
        }
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
    
    public String showStats() {
        return "Name: " + this._name + "\n"
                + "Gender: " + this._gender + "\n"
                + "Inventory: " + this._inventorySize;
    }

    public TakeArgs takeItem(Item item) {
        TakeArgs returnArgs = new TakeArgs();
        if (this.addItemToInventory(item)) {
            returnArgs.message = "You took the " + item.getName() + ".";
            returnArgs.success = true;
            return returnArgs;
        }
        returnArgs.message = "You don't have enough space in your inventory \n"
                + "for that item.";
        return returnArgs;
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

    public String pocket(Item item) {
        if (this.getRHand() == item) {
            if (this.addItemToInventory(item)){
                this.setRHand(null);
                return "You pocketed the " + item.getName() + ".";
            }
            return "You don't have space in your inventory to pocket that item.";
        } else if (this.getLHand() == item) {
            if (this.addItemToInventory(item)){
                this.setLHand(null);
                return "You pocketed the " + item.getName() + ".";
            }
            return "You don't have space in your inventory to pocket that item.";
        } else {
            return "You do not have that item equipped.";
        }
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
        Item[] items = this.getItemsInHands();
        Item lHandItem = items[0];
        if (lHandItem != null) {
            retVal += "You are holding the " + lHandItem.getName() + " in your left hand.\n";
        }
        Item rHandItem = items[1];
        if (rHandItem != null) {
            retVal += "You are holding the " + rHandItem.getName() + " in your right hand.\n";
        }
        return retVal;
    }
    
    
}
