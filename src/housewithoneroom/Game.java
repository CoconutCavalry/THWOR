/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package housewithoneroom;

import characters.Player;
import java.util.LinkedList;
import rooms.House;
import rooms.IRoom;
import titles.GameStrings;

import static services.ConsoleLogger.output;

/**
 *
 * @author esose
 */
public class Game {
    
//    public static GameStrings gameStrings;
    public static Player player;
    public static IRoom currentRoom;
    public static House house = new House();
    public static LinkedList<IRoom> visitedRooms = new LinkedList<>();
    public int numberOfVisitedRooms = 0;
    public static boolean state = true;
    
    public static void NewGame() {
//        gameStrings = new GameStrings();
        house = new House();
        currentRoom = house.getCorridor().getFirst();
    }
    
    public Game(int roomId) {
//        gameStrings = new GameStrings();
        house = new House();
        currentRoom = house.getCorridor().get(roomId);
    }
    
    public static void exitGame() {
        state = false;
        output(GameStrings.getEOGUser());
    }
    
    //public save(){}
}
