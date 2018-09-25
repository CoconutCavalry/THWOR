/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

/**
 * Experimenting with using items as enum
 * @author esose
 */
public enum Item {
    
    KNIFE(0, "a", "knife", 
            "A small, sharp, folding pocketknife with a wooden handle.",
            1.0),
    FLASHLIGHT(1, "a", "flashlight",
            "A large flashlight with a cracked and faded blue plastic casing.",
            2.5),
    MATCHES(2, "a box of", "matches",
            "A small box of matches.", 1.0), 
    KEY(3, "a black", "key", "A small, black, iron key.", 0.5);
    
    private int _id;
    private String _name;
    private String _article;
    private String _description;
    private double _size;
    private boolean _usesBothHands = false;

    private Item(int id, String article, String name, String description, 
             double size) {
        this._id = id;
        this._name = name;
        this._article = article;
        this._description = description;
        this._size = size;
        
    }
    
    public String getName() {
        return this._name;
    }
    public String getDescription() {
        return this._description;
    }
    public double getSize() {
        return this._size;
    }
    
    public String toStringShort(){
        return this._article + " " + this._name;
    }
    
    public String toStringLong(){
        return this._article + " " + this._name
                + ": " + this._description;
    }


}

/**
 *
 * @author esose
 */
//public class Item {
//    private int _id;
//    private String _name;
//    private String _article;
//    private String _description;
//    private double _size;
//    private boolean _usesBothHands = false;
//    
//    
//    public Item(int id, String article, String name, String description, 
//             double size) {
//        this._id = id;
//        this._name = name;
//        this._article = article;
//        this._description = description;
//        this._size = size;
//        
//    }
//    
//    
//    public String getName() {
//        return this._name;
//    }
//    public String getDescription() {
//        return this._description;
//    }
//    
//    public String toStringShort(){
//        return this._article + " " + this._name;
//    }
//    
//    public String toStringLong(){
//        return this._article + " " + this._name
//                + ": " + this._description;
//    }
//}
