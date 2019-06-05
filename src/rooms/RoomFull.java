package rooms;

import items.iItem;
import shared.Shared;
import titles.GameStrings;

import java.util.ArrayList;

import static core.services.ConsoleLogger.output;

public class RoomFull extends RoomBase {

    private ArrayList<iItem> items;
    private boolean hasBeenSearched = false;

    @Override
    public ArrayList<iItem> getItems() {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        return this.items;
    }

    @Override
    public void removeItemFromItems(iItem item) {
        this.items.remove(item);
    }

    @Override
    public void addItemToItems(iItem item) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        this.items.add(item);
    }

    public void performCustomMethods(String[] inputs) {
        switch (inputs[0]) {
            case "s":
            case "search":
                output(this.search());
                break;
            default:
        output(GameStrings.PerformCustomMethodsBadInput);
        }
    }

    private String search() {
        ArrayList<iItem> itemsInRoom = this.getItems();
        if (itemsInRoom.isEmpty()) {
            return "There are no items to be found here.";
        }
        if (!this.hasBeenSearched) {
            this.hasBeenSearched = true;
        }
        return Shared.appendDescriptionToItemsString(
                RoomDescriptions.defaultSearchDescription, itemsInRoom);
    }

//    public boolean hasBeenSearched() {
//        return this.hasBeenSearched;
//    }
}
