/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package housewithoneroom;

import characters.Player;
import java.util.LinkedList;
import rooms.Corridor;
import rooms.IRoom;
import titles.GameStrings;

/**
 *
 * @author esose
 */
public class Game {
    
    public GameStrings gameStrings;
    public Player player;
    public IRoom currentRoom;
    public Corridor corridor = new Corridor();
    public LinkedList<IRoom> visitedRooms = new LinkedList<>();
    public int numberOfVisitedRooms = 0;
    public boolean state = true;
    
    public Game() {
        this.gameStrings = new GameStrings();
        this.corridor = new Corridor();
    }
    
    public String exitGame() {
        this.state = false;
        return "You have ended the game.\n"
                + "Come back soon! \n"
                + "The House is waiting.";
    }
    
    //public save(){}
    
    /**
     * Upon leaving a room, add it to the list of visited rooms.
     * This will help to save the state of the room once it has been left
     * and it will be loaded from this list if the user returns.
     * @param room 
     */
    public void leave(IRoom room) {
        visitedRooms.add(room);
    }
    
    
}
