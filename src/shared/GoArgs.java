/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

/**
 *
 * @author esose
 */
public class GoArgs {
    public int roomId = -1;
    public String message = "You cannot move in that direction from here.";
    
    public GoArgs() {}
    
    public GoArgs(int _roomId) {
        this.roomId = _roomId;
    }
    public GoArgs(String _message) {
        this.message = _message;
    }
}
