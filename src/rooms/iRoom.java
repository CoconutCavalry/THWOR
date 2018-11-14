/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rooms;

import items.eItem;
import items.iItem;

import java.util.ArrayList;

/**
 *
 * @author esose
 */
public interface iRoom {
    /* methods shared by all Rooms */
    int getId();
    String getName();
    String getDescription();
    ArrayList<iItem> getItems();
    void removeItemFromItems(iItem item);
    void addItemToItems(iItem item);
    void performCustomMethods(String[] inputs);
    int go(String direction);
    void attack();
    
}
