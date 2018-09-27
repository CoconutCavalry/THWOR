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
    
    public Corridor() {
        this._corridor.add(new Library());
        this._corridor.add(new Study());
        this._corridor.add(new Hall());
    }
    
}
