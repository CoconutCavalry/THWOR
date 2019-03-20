package characters;

import java.util.ArrayList;

import items.iItem;
import shared.Shared;
import static services.ConsoleLogger.output;

/**
 *
 * @author esose
 */
public class Player implements iCharacter {
    private String _name;
    private String _gender;
    private String[] pronouns;
    private String[] male = {"Mister", "He", "His", "His"};
    private String[] female = {"Miss", "She", "Her", "Hers"};
    private int _health;
    private ArrayList<iItem> _inventory;
    private iItem lHand = null;
    private iItem rHand = null;
    public String death = "You are dead.";
    private boolean dead;

    private Boolean limitedInventory = false;
    /**
     * Not dealing with inventory space atm.
     * Bottomless. Congrats.
     */
    private iItem[] pockets = new iItem[4];
    private int _backpackSpaceAvailable = 0;
    //this size is relative, not universal.
    private double _inventorySize;
    private double _remainingInventorySpace;


    public Player(String name, int health, String death){
        this._name = name;
        this._health = health;
        this.death = death;
        this.dead = false;
    }
    
    public Player(String name, String gender) {
        this._name = name;
        this._inventory = new ArrayList<>();
        this.setGender(gender);
        this._health = 100;
        this.dead = false;
        if (limitedInventory) {
            this._inventorySize = this.getPocketSpaceRemaining()
                           + this._backpackSpaceAvailable;
            this._remainingInventorySpace = this._inventorySize;
        }
    }
    
    public String getName() { return this._name; }
    public void setName(String name) { this._name = name; }
    public String getGender() { return this._gender; }
    public String[] getPronouns() {
        return this.pronouns;
    }
    private void setGender(String gender) {
        switch (gender.toLowerCase()) {
            case "female":
            case "f":
                this._gender = "Female";
                this.pronouns = female;
                break;
            default:
                this._gender = "Male";
                this.pronouns = male;
        }
    }
    public int getHealth() { return this._health; }
    public void setHealth(int newHealth) { this._health = newHealth; }
    public boolean isDead() {
        return this.dead;
    }

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

    // only gets called by a limitedInventory method; no conditions required
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
            output("You take the " + item.getName() + ".");
            return true;
        }
        output("You don't have enough space in your inventory for that item.");
        return false;
    }

    private boolean addItemToInventory(iItem item) {
        if (this._inventory == null) {
            this._inventory = new ArrayList<>();
        }
        if (limitedInventory) {
            if (item.getSize() <= this._remainingInventorySpace) {
//                if (this._inventory == null) {
//                    this._inventory = new ArrayList<>();
//                }
                this._inventory.add(item);
                this._remainingInventorySpace -= item.getSize();
                return true;
            }
        } else {
//            if (this._inventory == null) {
//                this._inventory = new ArrayList<>();
//            }
            this._inventory.add(item);
            return true;
        }
        return false;
    }

    private boolean removeItemFromInventory(iItem item) {
        if (this.getInventory().contains(item)) {
            this._inventory.remove(item);
            if (limitedInventory) {
                this._remainingInventorySpace += item.getSize();
            }
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
        String inventory = "Player inventory:\n" + Shared.convertItemsInListToString(this._inventory);
        if (limitedInventory) {
            inventory += "\nRemaining space: " + Double.toString(this._remainingInventorySpace);
        }
        return inventory;
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
            return "You are now holding the " + item.getName() + " in your right hand.";
        } else if (this.getLHand() == null) {
            this.setLHand(item);
            this.removeItemFromInventory(item);
            return "You are now holding the " + item.getName() + " in your left hand.";
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
            this.dead = true;
        } else {
            this.setHealth(newHealth);
        }
    }






    public String showStats() {
        String stats = "Name: " + this._name + "\n" +
                "Gender: " + this._gender + "\n" +
                "Health: " + this._health;
        if (limitedInventory) {
            stats += "\nInventory: " + this._inventorySize;
        }
        return stats;
    }

    public String showCharacterReport() {
        String report = "Character report for " + this._name + ":\n"
                + "Gender: " + this._gender + "\n"
                + "Health: " + this._health + "\n"
                + inHandToString();
        if (limitedInventory) {
            report += "Pocket space: " + this.getPocketSpaceRemaining() + "\n"
                    + "Inventory size: " + this._inventorySize + "\n";
        }
        report += this.showInventory();
        return report;
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
