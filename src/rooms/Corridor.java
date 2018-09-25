/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rooms;

import items.Item;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author esose
 */
public final class Corridor {
    public LinkedList<IRoom>_corridor = new LinkedList<>();
    //public RoomDescriptions descriptions = new RoomDescriptions();
    
    public Corridor() {
        //initializeCorridor();
        //this._corridor.add(initializeLibrary());
        this._corridor.add(new Library());
        this._corridor.add(new Study());
    }
    
    public void initializeCorridor() {
        IRoom library = initializeLibrary();
        //Room study = initializeStudy();
        this._corridor.add(library);
        //this._corridor.add(study);
//        library._neighbors[0] = study;
//        study._neighbors[0] = library;
        
    }

    private IRoom initializeLibrary() {
        IRoom library = new Library();
        return library;
//        library._id = 0;
//        library._description = RoomDescriptions.library;
//        library._name = "Library";
//        library.items = initializeLibraryItems();
//        library._firstSearchDescription = RoomDescriptions.libraryFirstSearch;
//        return library;
    }

    /*
    private ArrayList<Item> initializeLibraryItems() {
        ArrayList<Item> libraryItems = new ArrayList<>();
        // attempt at directly adding from the Item enum class
        libraryItems.add(Item.FLASHLIGHT);
        libraryItems.add(Item.KNIFE);
        libraryItems.add(Item.MATCHES);
        return libraryItems;
    }

    private Room initializeStudy() {
        Room study = new Room();
        study._id = 1;
        study._description = RoomDescriptions.study;
        study._name = "Study";
        study.items = initializeStudyItems();
        study._firstSearchDescription = RoomDescriptions.studyFirstSearch;
        return study;
    }

    private ArrayList<Item> initializeStudyItems() {
        // only happens after search / moving stone on hearth ...
        return new ArrayList<>();
    }
*/
    
}
