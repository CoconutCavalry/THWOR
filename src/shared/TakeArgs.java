/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import items.Item;

/**
 *
 * @author esose
 */
public class TakeArgs {
    public int itemId = -1;
    public boolean success = false;
    public String message = "No item of that name is available.";
    public Item item;
    
    public TakeArgs() {}
    
    public TakeArgs(Item _item) {
        this.item = _item;
    }
    public TakeArgs(String _message) {
        this.message = _message;
        this.success = true;
    }
//    public TakeArgs(boolean _success) {
//        this.success = _success;
//    }
}
