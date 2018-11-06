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
public final class House {
    private LinkedList<IRoom> corridor = new LinkedList<>();
    
    public House() {
        this.corridor.add(new Library());
        this.corridor.add(new Study());
        this.corridor.add(new Hall());
        this.corridor.add(new DiningRoom());
        this.corridor.add(new ComputerRoom());
    }
    
    public LinkedList<IRoom> getCorridor() {
        return this.corridor;
    }
    
}

