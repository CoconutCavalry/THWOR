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
public class AttackArgs {
    private int _health = -1;
    private Item[] _inHand = null;
    private String _message = "There is nothing to attack here.";
    
    public int getHealth() {
        return this._health;
    }
    public void setHealth(int num) {
        this._health = num;
    }
    public Item[] getInHand() {
        return this._inHand;
    }
    public void setInHand(Item[] items) {
        this._inHand = items;
    }
    public String getMessage() {
        return this._message;
    }
    public void setMessage(String m) {
        this._message = m;
    }
    
    public AttackArgs() {}
    public AttackArgs(int h, Item[] ih) {
        this._health = h;
        this._inHand = ih;
    }
}
