/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import items.Item;
import java.util.ArrayList;

/**
 *
 * @author esose
 */
public class CommandsObject {
    public String message;
    public String[] inputs;
    public int numberOfInputs;
    public ArrayList<Item> items;
    
    public CommandsObject() {}
    
    public CommandsObject(String _message, String[] _inputs, 
            ArrayList<Item> _items) {
        this.message = _message;
        this.inputs = _inputs;
        this.items = _items;
        this.numberOfInputs = this.inputs.length;
    }
}
