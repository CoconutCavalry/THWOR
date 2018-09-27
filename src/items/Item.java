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
    
    KNIFE(0, "a knife", "knife", 
            "A small, sharp, folding pocketknife \n"
                    + "with a wooden handle.",
            1.0),
    FLASHLIGHT(1, "a flashlight", "flashlight",
            "A large flashlight with a cracked and \n"
                    + "faded blue plastic casing.",
            2.5),
    MATCHES(2, "a box of matches", "matches",
            "A small box of matches.", 1.0), 
    BLACK_KEY_TO_HALL_FROM_STUDY(3, "a black key", "key", 
            "A small, black, iron key.", 0.5),
    MESSAGE_FROM_FIREPLACE_IN_STUDY(4, "a small, torn message", "message", 
            "The scrap of paper has a single word \n"
                    + "scrawled into it: CoconutCavalry", 0.1),
    TORCH_FROM_HALL(5, "a burning torch", "torch", 
            "The torch has a wooden handle with a \n"
                    + "cloth wrapped around one end. The cloth \n"
                    + "is burning slowly but brightly.", 1.0),
    POISON_FLASK(6, "a flask", "flask",
            "A small metal flask with a mysterious \n"
                    + "fluid inside.", 1.0),
    STUDY_LETTER(7, "a letter", "letter", 
            "A crumpled letter, charred and written \n"
                    + "in blotchy ink.", 0.0);
    
    private int _id;
    private String _idName;
    private String _nameWithArticle;
    private String _description;
    private double _size;
    private boolean _usesBothHands = false;

    private Item(int id, String nameWithArticle, 
            String idName, String description, double size) {
        this._id = id;
        this._idName = idName;
        this._nameWithArticle = nameWithArticle;
        this._description = description;
        this._size = size;
        
    }
    
    public String getName() {
        return this._idName;
    }
    public String getDescription() {
        return this._description;
    }
    public double getSize() {
        return this._size;
    }
    
    public String toStringShort(){
        return this._nameWithArticle;
    }
    
    public String toStringLong(){
        return this._nameWithArticle
                + ": " + this._description;
    }


}

/**
 *
 * @author esose
 */
//public class Item {
//    private int _id;
//    private String _idName;
//    private String _article;
//    private String _description;
//    private double _size;
//    private boolean _usesBothHands = false;
//    
//    
//    public Item(int id, String article, String name, String description, 
//             double size) {
//        this._id = id;
//        this._idName = name;
//        this._article = article;
//        this._description = description;
//        this._size = size;
//        
//    }
//    
//    
//    public String getName() {
//        return this._idName;
//    }
//    public String getDescription() {
//        return this._description;
//    }
//    
//    public String toStringShort(){
//        return this._article + " " + this._idName;
//    }
//    
//    public String toStringLong(){
//        return this._article + " " + this._idName
//                + ": " + this._description;
//    }
//}
