package rooms;

import characters.SimpleMonster;
import items.iItem;

import java.util.ArrayList;

/**
 *
 * @author esose
 */
public interface iRoom {
    /* methods shared by all Rooms */

    /**
     * @return the room's id
     * this roomId is used for navigation between rooms
     */
    int getId();

    /**
     * @return the name of the room
     */
    String getName();

    /**
     * @return the description of the room that is triggered by the "describe" command
     */
    String getDescription();

    /**
     * @return a list of the available items in the room
     */
    ArrayList<iItem> getItems();

    /**
     * Removes an item from the room's list of available items
     * @param item to remove
     */
    void removeItemFromItems(iItem item);

    /**
     * Adds an item to the room's list of available items
     * @param item to add
     */
    void addItemToItems(iItem item);

    /**
     * For room-specific actions and puzzles, etc.
     * @param inputs to parse
     */
    void performCustomMethods(String[] inputs);

    /**
     * If the room itself contains a monster, this returns a reference to that monster
     * Used to decide if the player is able to leave a room
     * @return reference to the monster contained by the room
     */
    SimpleMonster getMonster();

    /**
     * @param direction entered by player
     * @return roomId if direction is valid, -1 if direction is invalid,
     *         and -2 if a different message is generated by the room itself (door locked, etc.)
     */
    int go(String direction);

    /**
     *
     */
    void attack();
    
}
