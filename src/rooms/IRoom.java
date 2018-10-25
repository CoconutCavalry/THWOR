/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rooms;

import items.Item;
import java.util.ArrayList;

/**
 *
 * @author esose
 */
public interface IRoom {
    /* initialized properties */
    
    /* Constructor - save for later???*/
    
    /* methods shared by all Rooms */
    int getId();
    String getName();
    String getDescription();
    ArrayList<Item> getItems();
    void removeItemFromItems(Item item);
    void addItemToItems(Item item);
    void performCustomMethods(String[] inputs);
    int go(String direction);
    void attack();
    
}
