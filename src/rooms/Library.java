/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rooms;

import characters.Player;
import shared.Shared;
import items.Item;
import java.util.ArrayList;
import java.util.Arrays;
import shared.CommandsObject;
import shared.GoArgs;
import titles.Excerpts;

/**
 *
 * @author esose
 */
public class Library implements IRoom {

    private boolean _hasBeenSearched = false;
    private final int[] _neighbors = {1};
    public ArrayList<Item> items;
    private final String _description = RoomDescriptions.library;
    private final String _firstSearchDescription = 
            RoomDescriptions.libraryFirstSearch;
    private final String[] _bookTitlesInLibrary = {""
            + "The Tragic Youth of Ambrose Fogarty: \n"
            + "\tYoung Master Fogarty, by KM",
            "\nThe Tragic Youth of Ambrose Fogarty: \n"
            + "\tFogarty, Esquire, by KM",
            "\nThe Tragic Youth of Ambrose Fogarty: \n"
            + "\tIn Her Majesty's Service, by KM",
            "\nThe Perilous Adventures of Jon Legenn, by KM",
            "\nMcEver, by KM",
            "\nThe Complete Clocktower Books, by KM and FM"
            };
    
    public Library() {
        this.items = new ArrayList<>();
        this.items.add(Item.FLASHLIGHT);
        this.items.add(Item.KNIFE);
        this.items.add(Item.MATCHES);
    }
    
    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getName() {
        return "Library";
    }

    @Override
    public String getDescription() {
        return _description;
    }

    @Override
    public boolean hasBeenSearched() {
        return this._hasBeenSearched;
    }

    @Override
    public boolean areItemsAlwaysVisble() {
        return true;
    }

    @Override
    public int[] getNeighbors() {
        return this._neighbors;
    }

    @Override
    public ArrayList<Item> getItemsList() {
        return this.items;
    }

    @Override
    public String getItemsInRoom() {
        String stringOfItems = "";
        stringOfItems = Shared.getItemsInListToString(this.items);
        return stringOfItems;
    }

    public String search() {
        if (this.items.isEmpty()) {
            return "There are no items to be found here.";
        }
        
        if (!this._hasBeenSearched) {
            this._hasBeenSearched = true;
            return this._firstSearchDescription + this.getItemsInRoom();
        }
        
        return this.defaultSearchDescription + this.getItemsInRoom();
    }
    
    @Override
    public void removeItemFromItems(Item item) {
        this.items.remove(item);
    }
    
    @Override
    public void addItemToItems(Item item) {
        this.items.add(item);
    }
    
    @Override
    public CommandsObject performCustomMethods(
            String[] inputs, Player player) {
        CommandsObject commandsToReturn = new CommandsObject();
        commandsToReturn.items = player.getInventory();
        switch (inputs[0]) {
            case "b":
            case "browse":
                commandsToReturn.message = showBookTitles();
                return commandsToReturn;
            case "r":
            case "read":
                commandsToReturn.message = readBook(inputs);
                return commandsToReturn;
            case "s":
            case "search":
                commandsToReturn.message = this.search();
                return commandsToReturn;
            default:
                return null;
        }
    }

    private String showBookTitles() {
        return Arrays.toString(this._bookTitlesInLibrary);
    }

    private String readBook(String[] commands) {
        if (commands.length > 1) {
            switch (commands[1]) {
                case "m":
                    return Excerpts.mcEver; 
                case "l":
                    return Excerpts.legenn;
                case "f":
                    return Excerpts.fogarty;
                case "c":
                    return Excerpts.clocktower;
                default:
                    return "Try 'm', 'l', 'f', or 'c'.";
            }
        }
        return "Try including a title after 'read'.";
    }

    @Override
    public GoArgs go(String direction) {
        if (direction != null) {
            switch (direction) {
                case "ahead":
                case "forward":
                case "straight":
                    return new GoArgs(this._neighbors[0]);
                default:
                    return new GoArgs();
            }
        }
        return new GoArgs();
    }
    
}
