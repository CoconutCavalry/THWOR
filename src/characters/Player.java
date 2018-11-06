package characters;

import items.iItem;
import shared.Shared;

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
    private ArrayList<iItem> _inventory;
    private iItem lHand = null;
    private iItem rHand = null;
    private iItem[] pockets = new iItem[4];
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
    
    public String getName() { return this._name; }
    public void setName(String name) { this._name = name; }
    public String getGender() { return this._gender; }
    public void setGender(String gender) { this._gender = gender; }
    public int getHealth() { return this._health; }
    public void setHealth(int newHealth) { this._health = newHealth; }

    public ArrayList<iItem> getItemsInHands() {
//        eItem[] retVal = {this.getLHand(), this.getRHand()};
        ArrayList<iItem> retVal = new ArrayList<>();
        retVal.add(rHand);
        retVal.add(lHand);
        return retVal;
    }

    public iItem getRHand() {
        return this.rHand;
    }
    public void setRHand(iItem item) {
        this.rHand = item;
    }

    public iItem getLHand() {
        return this.lHand;
    }
    public void setLHand(iItem item) {
        this.lHand = item;
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
        for (iItem item : this.pockets) {
            if (item != null) {
                space = space - item.getSize();
            }
        }
        return space;
    }

    public boolean takeItem(iItem item) {
        if (this.addItemToInventory(item)) {
            output("You took the " + item.getName() + ".");
            return true;
        }
        output("You don't have enough space in your inventory \n"
                + "for that item.");
        return false;
    }

    private boolean addItemToInventory(iItem item) {
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

    private boolean removeItemFromInventory(iItem item) {
        if (this.getInventory().contains(item)) {
            this._inventory.remove(item);
            this._remainingInventorySpace += item.getSize();
            return true;
        }
        return false;
    }

    public ArrayList<iItem> getInventory() {
        if (this._inventory == null || this._inventory.isEmpty()) {
            this._inventory = new ArrayList<>();
        }
        return this._inventory;
    }
    public void setInventory(ArrayList<iItem> items) {
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

    public iItem dropItem(String itemName) {
        if (this.getInventory() == null || this.getInventory().isEmpty()) {
            return null;
        }
        for(iItem item : this._inventory) {
            if (item.getName().equals(itemName)){
                this.removeItemFromInventory(item);
                return item;
            }
        }
        return null;
    }

    public String equip(iItem item) {
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
            iItem item = this.getRHand();
            if (this.addItemToInventory(item)){
                this.setRHand(null);
                output("You pocketed the " + itemName + ".");
                return true;
            }
            output("You don't have space in your inventory to pocket that item.");
            return false;
        } else if ((this.getLHand() != null) && (this.getLHand().getName().equals(itemName))) {
            iItem item = this.getLHand();
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
        iItem lHandItem = this.getLHand();
        if (lHandItem != null) {
            retVal += "You are holding the " + lHandItem.getName() + " in your left hand.\n";
        }
        iItem rHandItem = this.getRHand();
        if (rHandItem != null) {
            retVal += "You are holding the " + rHandItem.getName() + " in your right hand.\n";
        }
        return retVal;
    }
    
    
}
