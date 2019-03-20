/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package housewithoneroom;

import characters.Player;
import java.util.LinkedList;
import rooms.House;
import rooms.iRoom;
import titles.GameStrings;
import static services.ConsoleLogger.*;
import services.IOService;

import static services.ConsoleLogger.output;

/**
 *
 * @author esose
 */
public class Game {

    public static Player player;
    public static iRoom currentRoom;
    public static House house = new House();
    public static LinkedList<iRoom> visitedRooms = new LinkedList<>();
    public int numberOfVisitedRooms = 0;
    public static boolean state = true;
    
    public static void NewGame() {
        house = new House();
        currentRoom = house.getCorridor().getFirst();
    }
    
    public Game(int roomId) {
        house = new House();
        currentRoom = house.getCorridor().get(roomId);
    }
    
    public static void exitGame() {
        output("Are you sure you want to exit? [y/n] ");
        if (IOService.getNextLine().equals("y")) {
            state = false;
            outputLn();
            output(GameStrings.getEOGUser());
        } else {
            outputLn();
            output("Good.");
        }
    }

    public static int endGame() {
        state = false;
        output(GameStrings.endGameWin);
        return -99;
    }

    //public save(){}
}
