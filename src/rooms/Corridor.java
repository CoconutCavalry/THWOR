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
        this._corridor.add(new Library());      //0 
        this._corridor.add(new Study());        //1
        this._corridor.add(new Hall());         //2
        this._corridor.add(new DiningRoom());   //3
    }
    
}
