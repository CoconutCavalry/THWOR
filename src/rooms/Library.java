/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rooms;

import adventures.LibraryBookshelfAdventure;
import items.*;
import services.IOService;
import shared.Shared;

import java.util.ArrayList;
import java.util.Arrays;

import titles.Excerpts;
import titles.GameStrings;

import static housewithoneroom.Start.admin;
import static services.ConsoleLogger.output;

/**
 *
 * @author esose
 */
public class Library implements iRoom {

    private final Door[] doors = {new Door(RoomId.STUDY.getId(),
            "\nStraight ahead in the distance, you see a wooden door.")};

    private static final int id = RoomId.LIBRARY.getId();
    private static final String name = "Library";
    private boolean hasBeenSearched = false;
//    private final int[] neighbors = {RoomId.STUDY.getId()};
    public ArrayList<iItem> items;
    private final String description = RoomDescriptions.library;
    private final String firstSearchDescription = 
            RoomDescriptions.libraryFirstSearch;

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
        return description + doors[0].getDescription();
    }
     
    public ArrayList<iItem> getItems() {
        return this.items;
    }

    private String doorsToString() {
        String doorsString = "";
        for (Door d : doors) {
            doorsString += "\n" + d.getDescription();
        }
        return doorsString;
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
                LibraryBookshelfAdventure.browseLibrary();
                break;
            case "r":
            case "read":
                break;
            case "s":
            case "search":
                output(this.search());
                break;
            default:
                output(GameStrings.PerformCustomMethodsBadInput);
        }
    }

    /*****************
     *    MOVEMENT   *
     *****************/
    public int go(String direction) {
        switch (direction) {
            case "ahead":
            case "forward":
            case "straight":
                return this.doors[0].getRoomId();
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


}
