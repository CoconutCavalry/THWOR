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
    private int _health = 100;
    private ArrayList<Item> _inventory;
    private int _numberOfEmptyHands = 2;
    private int _numberOfPockets = 4;
    private double[] _pockets = {1.0, 1.0, 1.0, 1.0};
    private int _backpackSpaceAvailable = 0;
    //this size is relative, not universal.
    private double _inventorySize;
    private double _remainingInventorySpace;
    
    public Player(){}
    
    public Player(String name, String gender) {
        this._name = name;
        this._gender = gender;
        this._inventory = new ArrayList<>();
        this._inventorySize = this._numberOfPockets 
                       + this._backpackSpaceAvailable;
        this._remainingInventorySpace = this._inventorySize;
    }
    
    public String getName() {
        return this._name;
    }
    public String getGender() {
        return this._gender;
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
        return this._inventory;
    }
    
    public void setInventory(ArrayList<Item> items) {
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
                + Shared.getItemsInListToString(this._inventory) + "\n"
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

    public String showCharacterReport() {
        return "Character report for " + this._name + ":\n" 
                + "Gender: " + this._gender + "\n"
                + "Health: " + this._health + "\n"
                + "Empty hands: " + this._numberOfEmptyHands + "\n"
                + "Pockets: " + this._numberOfPockets + "\n"
                + "Inventory size: " + this._inventorySize + "\n"
                + this.showInventory();
    }
    
    
}
