/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rooms;

import items.*;
import services.IOService;
import shared.Shared;

import java.util.ArrayList;
import java.util.Arrays;

import titles.Excerpts;
import titles.GameStrings;

import static housewithoneroom.Start.admin;
import static services.ConsoleLogger.output;
import static services.ConsoleLogger.showLong;

/**
 *
 * @author esose
 */
public class Library implements iRoom {

    private static final int id = RoomId.LIBRARY.getId();
    private static final String name = "Library";
    private boolean hasBeenSearched = false;
    private final int[] neighbors = {RoomId.STUDY.getId()};
    public ArrayList<iItem> items;
    private final String description = RoomDescriptions.library;
    private final String firstSearchDescription = 
            RoomDescriptions.libraryFirstSearch;
    private final String[] bookTitlesInLibrary = {""
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
    
    /**
     * Constructor for the Library
     */
    public Library() {
        this.items = new ArrayList<>();
        this.items.add(new Flashlight());
        this.items.add(new Knife());
//        this.items.add(eItem.MATCHES);
        if (admin) {
            this.items.add(new Excalibur());
        }
    }
    
    /***********************
     * Getters and setters *
     ***********************/
     
    public int getId() {
        return id;
    }
     
    public String getName() {
        return name;
    }
     
    public String getDescription() {
        return description;
    }
     
    public ArrayList<iItem> getItems() {
        return this.items;
    }
    
    /******************
     * Search methods *
     ******************/
    private String search() {
        ArrayList<iItem> itemsInRoom = this.getItems();
        if (itemsInRoom.isEmpty()) {
            return "There are no items to be found here.";
        }
        if (!this.hasBeenSearched) {
            this.hasBeenSearched = true;
            return Shared.appendDescriptionToItemsString(
                    this.firstSearchDescription, itemsInRoom);
        }
        
        return Shared.appendDescriptionToItemsString(
                    RoomDescriptions.defaultSearchDescription, itemsInRoom);
    }
    
    /*************************
     * RoomInventory Methods *
     *************************/
    public void removeItemFromItems(iItem item) {
        this.items.remove(item);
    }
    
    public void addItemToItems(iItem item) {
        this.items.add(item);
    }
    
    
    /******************
     * Custom methods *
     ******************/
    public void performCustomMethods(
            String[] inputs) {
        switch (inputs[0]) {
            case "b":
            case "browse":
                output(showBookTitles());
                break;
            case "r":
            case "read":
                output(readBook(inputs));
                break;
            case "s":
            case "search":
                showLong(this.search());
                break;
            default:
                output(GameStrings.PerformCustomMethodsBadInput);
        }
    }

    private String showBookTitles() {
        return Arrays.toString(this.bookTitlesInLibrary);
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

    /*****************
     *    MOVEMENT   *
     *****************/
    public int go(String direction) {
        switch (direction) {
            case "ahead":
            case "forward":
            case "straight":
                return this.neighbors[0];
            default:
                return -1;
        }
    }
    
    /******************
     *    Attacking   *
     ******************/
    public void attack() {
        output(GameStrings.NothingToAttackHereString);
    }


    /******************
     *      Other     *
     ******************/
    public void roomActions() {
        String input = IOService.getNextLine();
        showLong("You entered " + input);
    }

}
