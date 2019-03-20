package rooms;

import characters.SimpleMonster;
import housewithoneroom.Game;
import items.iItem;
import shared.Shared;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import titles.GameStrings;

import java.util.ArrayList;

import static services.ConsoleLogger.output;

public class RoomBase implements iRoom{

    public int getId() {
        throw new NotImplementedException();
    }

    public String getName() {
        throw new NotImplementedException();
    }

    public String getDescription() {
        throw new NotImplementedException();
    }

    public ArrayList<iItem> getItems() {
        throw new NotImplementedException();
    }

    public void removeItemFromItems(iItem item) {
        throw new NotImplementedException();
    }

    public void addItemToItems(iItem item) {
        throw new NotImplementedException();
    }

    public void performCustomMethods(String[] inputs) {
//        switch (inputs[0]) {
//            case "s":
//            case "search":
//                output(this.search());
//                break;
//            default:
                output(GameStrings.PerformCustomMethodsBadInput);
//        }
    }

//    private String search() {
//        ArrayList<iItem> itemsInRoom = this.getItems();
//        if (itemsInRoom.isEmpty()) {
//            return "There are no items to be found here.";
//        }
//        if (!this.hasBeenSearched) {
//            this.hasBeenSearched = true;
//        }
//        return Shared.appendDescriptionToItemsString(
//                RoomDescriptions.defaultSearchDescription, itemsInRoom);
//    }

    /**
     *
     * @return null for now because there is no monster yet
     */
    public SimpleMonster getMonster() {
        return null;
    }

    public int go(String direction) {
        return -1;
    }

    public void attack() {
        // First, check if there is a monster to fight
        SimpleMonster monster = this.getMonster();
        if (monster == null) {
            output(GameStrings.NothingToAttackHereString);
            return;
        } else if (monster.isDead()) {
            output("The " + monster.getName() + " is dead.");
        } else {
            // If a monster exists, attack it
            Shared.attack(monster);
            if (monster.isDead()) {
                output("You have slain the " + monster.getName() + ".");
            } else {
                // If the monster is still alive, defend against its attack:
                Shared.defend(monster);
                if (Game.player.isDead()) {
                    Game.state = false;
                    output(Game.player.death);
                }
            }
        }
    }
}

