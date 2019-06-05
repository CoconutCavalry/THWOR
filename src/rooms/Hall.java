/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rooms;

import characters.Player;
import housewithoneroom.Game;
import java.util.ArrayList;

import items.Torch;
import items.iItem;
import core.services.IOService;
import shared.Shared;
import titles.GameStrings;

import static core.services.ConsoleLogger.output;
import static core.services.ConsoleLogger.outputForInput;
import static core.services.ConsoleLogger.outputLn;

/**
 *
 * @author esose
 */
public class Hall extends RoomFull {
    
    private static final int id = RoomId.HALL.getId();
    private static final String name = "Hall";
    private boolean hasBeenSearched = false;
    private boolean guardianIsAngry = false;
    private boolean guardianIsDead = false;
    private boolean computerRoomIsLocked = true;
    private final String computerRoomPassword = "CoconutCavalry";
    private int guardianHealth;
    private final int[] neighbors = {RoomId.STUDY.getId(), RoomId.DININGROOM.getId(),
            RoomId.COMPUTERROOM.getId(), RoomId.UPSTAIRSHALLWAY.getId()};
    
    private final String description = RoomDescriptions.hall;
    private final String guardianAliveSearch = 
            RoomDescriptions.hallFirstSearch;
    private final String roomSearch =
            RoomDescriptions.hallOtherSearch;

    public Player guardian;

    /**
     * Constructor for the Hall
     */
    public Hall() {
        this.guardianHealth = 1;
        this.guardian = new Player("The Guardian", 100,
                "The dark form dissolves in a cloud of mist.");
        this.addItemToItems(new Torch());
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
        return this.description;
    }

    private boolean getHasBeenSearched() {
        return this.hasBeenSearched;
    }
    private void setHasBeenSearched() {
        if (!this.hasBeenSearched) {
            this.hasBeenSearched = true;
        }
    }
    private boolean getGuardianIsAngry() {
        return this.guardianIsAngry;
    }
    private void setGuardianIsAngry() {
        if (!this.guardianIsAngry) {
            this.guardianIsAngry = true;
        }
    }
    
    /******************
     * Search methods *
     ******************/
    private String search() {
        if (!this.getHasBeenSearched()) {
            this.setHasBeenSearched();
            if (!this.guardianIsDead) {
                this.setGuardianIsAngry();
                return this.guardianAliveSearch;
            }
        }
        // ES TEST: I think this clause is unnecessary
//        if (!this.guardianIsDead) {
//            return this.roomSearch;
//        }
        ArrayList<iItem> itemsInRoom = this.getItems();
        if (itemsInRoom.isEmpty()) {
            return "There are no items to be found here.";
        }
        return this.roomSearch;
    }

    /******************
     * Custom methods *
     ******************/
      
    public void performCustomMethods(
            String[] inputs) {
        switch (inputs[0]) {
            case "keypad":
                this.viewKeypad();
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
        if (this.getGuardianIsAngry()) {
            output(RoomDescriptions.guardianIsAngryCannotLeave);
            return -2;
        }
        switch (direction) {
            case "ahead":
            case "forward":
            case "forwards":
            case "straight":
                return Game.endGame();
            case "back":
            case "backwards":
                return this.neighbors[0];
            case "left":
                return this.neighbors[1];
            case "right":
                return tryComputerRoomDoor();
            case "up":
            case "upstairs":
            case "upwards":
                return this.neighbors[3];
            default:
                return -1;
        }
    }

    private int tryComputerRoomDoor() {
        if (!this.computerRoomIsLocked) {
            return this.neighbors[2];
        }
        output("The door is locked.");
        return -2;
    }

    /******************
     *    Attacking   *
     ******************/
      
    public void attack() {
        String battleScript = GameStrings.NothingToAttackHereString;
        if (!this.guardianIsDead && this.guardianIsAngry) {
            Shared.attack(Game.player, this.guardian);
            if (this.guardian.getHealth() == 0) {
                this.guardianIsDead = true;
                this.guardianIsAngry = false;
                outputLn(guardian.death);
            } else {
                Shared.defend(Game.player, this.guardian);
                if (Game.player.getHealth() == 0) {
                    Game.state = false;
                    output(Game.player.death);
                }
            }
        }else {
            outputLn(battleScript);
        }
    }

    /******************
     *      Other     *
     ******************/

    private void viewKeypad() {
        output("There is a simple touch-screen 'QWERTY' keyboard set into the wall. " +
                "The screen below it says:");
        outputForInput("> ENTER PASSWORD: ");

        String password = IOService.getNextLine();
        if (password.equals(this.computerRoomPassword)) {
            this.computerRoomIsLocked = false;
            output("You hear a soft click from the wall behind the keypad.");
        } else {
            output("> INCORRECT PASSWORD");
        }
    }

}
