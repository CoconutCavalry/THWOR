/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rooms;

import shared.Shared;
import items.Item;
import java.util.ArrayList;

/**
 *
 * @author esose
 */
public abstract class Room {

    /**
     * Not sure if this will be implemented or not
     */
    int _id;
    /**
     * The name of the room/type of room
     */
    String _name;
    /**
     * A description of the room"s appearance
     */
    String _description;
    /**
     * A description of the items in the room
     */
    String _firstSearchDescription;
    String _defaultSearchDescription = "You see ";
    /**
     * self-explanatory
     */
    boolean _hasBeenSearched = false;
    /**
     * A list of items to be found in the room
     */
    public ArrayList<Item> items;
    /**
     * The rooms that can be entered from this room
     */
    Room[] _neighbors;
    
    public Room() {}
    
    public Room(int id, String name, String description, 
            Room[] neighbors) {
        this._id = id;
        this._name = name;
        this._description = description;
        this._neighbors = neighbors;
    }
    
    public int getId() {
        return this._id;
    }
    
    public String getName() {
        return this._name;
    }
    
    public String getDescription() {
        return this._description;
    }
    
    public ArrayList<Item> getItemsList() {
        return this.items;
    }

    public Room[] getNeighbors() {
        return this._neighbors;
    }

    public String getItemsInRoom() {
        String stringOfItems = "";
        stringOfItems = Shared.convertItemsInListToString(items);
        return stringOfItems;
    }

    public boolean areItemsAlwaysVisble() {
        return true;
    }

    public boolean hasBeenSearched() {
        return this._hasBeenSearched;
    }

    /**
     * Reveals items in the room
     */
    public String searchRoom() {
        if (this.items.isEmpty()) {
            return "There are no items to be found here.";
        }
        
        if (!this._hasBeenSearched) {
            this._hasBeenSearched = true;
            return this._firstSearchDescription + this.getItemsInRoom();
        }
        
        return this._defaultSearchDescription + this.getItemsInRoom();
    }

    
}
