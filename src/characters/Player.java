/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    private int _health = 5;
    private ArrayList<Item> _inventory;
    private Item[] _hands = new Item[2];
    private Item[] _pockets = new Item[4];
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
    public Item[] getItemsInHand() { return this._hands; }
    
    public void setItemsInHand(Item[] items) {
        if (items != null) {
            if (items.length <= 2) {
                int count = 0;
                for (Item item : items) {
                    if (item != null) {
                        this._hands[count] = item;
                    } else { 
                        this._hands[count] = null;
                    }
                    count++;
                }
            }
        } else {
            this._hands = null;
        }
    }
    
    public int getNumberOfEmptyHands() {
        int count = 2;
        for (Item item : this._hands) {
            if (item != null) {
                count--;
            }
        }
        return count;
    }
    public double getPocketSpaceRemaining() {
        double space = 4.0;
        for (Item item : this._pockets) {
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
        if (item.getSize() <= this._remainingInventorySpace) {
            if (this._inventory == null) {
                this._inventory = new ArrayList<>();
            }
            this._inventory.add(item);
            this._remainingInventorySpace -= item.getSize();
            returnArgs.message = "You took the " + item.getName() + ".";
            returnArgs.success = true;
            return returnArgs;
        }
        returnArgs.message = "You don't have enough space in your inventory \n"
                + "for that item.";
        return returnArgs;
        
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
        if (this._inventory.isEmpty() || this._inventory == null) {
            return null;
        }
        for(Item item : this._inventory) {
            if (item.getName().equals(itemName)){
                this._inventory.remove(item);
                this._remainingInventorySpace += item.getSize();
                return item;
            }
        }
        return null;
    }
    
    public void equip(Item item) {
        for (int i = 0; i < 2; i++) {
            if (this._hands[i] == null) {
                this._hands[i] = item;
                return;
            }
        }
    }

    public String showCharacterReport() {
        return "Character report for " + this._name + ":\n" 
                + "Gender: " + this._gender + "\n"
                + "Health: " + this._health + "\n"
                + "Empty hands: " + this.getNumberOfEmptyHands() + "\n"
                + "Pocket space: " + this.getPocketSpaceRemaining() + "\n"
                + "Inventory size: " + this._inventorySize + "\n"
                + this.showInventory();
    }
    
    
}
