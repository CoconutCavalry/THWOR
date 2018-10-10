/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rooms;

import java.util.LinkedList;

/**
 *
 * @author esose
 */
public final class Corridor {
    public LinkedList<IRoom>_corridor = new LinkedList<>();
    
    public Corridor() {
        this._corridor.add(new Library());
        this._corridor.add(new Study());
        this._corridor.add(new Hall());
        this._corridor.add(new DiningRoom());
        this._corridor.add(new MinesweeperSim());
    }
    
}

// ID must match its order within the Corridor list
enum RoomId {
    LIBRARY(0),
    STUDY(1),
    HALL(2),
    DININGROOM(3),
    MINESWEEPERSIM(4);
    
    RoomId(int id) {
        this.id = id;
    }
    
    private final int id;
    public int getId() {
        return this.id;
    }
}
