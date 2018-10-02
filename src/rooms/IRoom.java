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
import shared.GoArgs;

/**
 *
 * @author esose
 */
public interface IRoom {
    /* initialized properties */
    String defaultSearchDescription = "You see ";
    
    /* Constructor - save for later???*/
//    public Room Room(int id, String name, String description, 
//            Room[] neighbors);
    
    /* methods shared by all Rooms */
    public String getName();
    public String getDescription();
    public ArrayList<Item> getItems();
    public void removeItemFromItems(Item item);
    public void addItemToItems(Item item);
    public CommandsObject performCustomMethods(
            String[] inputs, Player player);
    public GoArgs go(String direction);
    public AttackArgs attack(int health, Item[] inHand);
    
}
