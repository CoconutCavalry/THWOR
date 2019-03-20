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
public enum eItem {
    
    KNIFE(0, "a knife", "knife", 
            "A small, sharp, folding pocketknife \n"
                    + "with a wooden handle.",
            1.0, 2),
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
                    + "is burning slowly but brightly.", 1.0, 2),
    POISON_FLASK(6, "a flask", "flask",
            "A small metal flask with a mysterious \n"
                    + "fluid inside.", 1.0, 100),
    STUDY_LETTER(7, "a letter", "letter", 
            "A crumpled letter, charred and written \n"
                    + "in blotchy ink.", 0.0),
    EXCALIBUR(8, "a shining longsword", "sword",
            "The sword has strange runes carved into the blade\n" +
                    " and a dark jewel set into the pommel.", 4, 20);
    
    private int _id;
    private String _idName;
    private String _nameWithArticle;
    private String _description;
    private double _size;
    private boolean _usesBothHands = false;
    private boolean _isWeapon = false;
    private int _damage = 0;

    /**
     * Constructor for WEAPON items
     * @param id - int
     * @param nameWithArticle - short description with name
     * @param idName - identifying string
     *@param description of item
     * @param size  - inventory space necessary
     * @param damage -
     */
    eItem(int id, String nameWithArticle,
          String idName, String description, double size,
          int damage) {
        this._id = id;
        this._idName = idName;
        this._nameWithArticle = nameWithArticle;
        this._description = description;
        this._size = size;
        this._damage = damage;
        this._isWeapon = true;
    }

    /**
     * Constructor for NON-WEAPON items
     * @param id - int
     * @param nameWithArticle - short description with name
     * @param idName - identifying string
     * @param description of item
     * @param size  - inventory space necessary
     */
    eItem(int id, String nameWithArticle,
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
    private boolean getIsWeapon() {
        return this._isWeapon;
    }
    public int getDamage() {
        return this._damage;
    }
    public int getHowManyHands() {
        if (this._usesBothHands) {
            return 2;
        }
        return 1;
    }
    public String getStats() {
        String damageString = "";
        String howManyHandsString = "";
        if (this.getIsWeapon()) {
            damageString = "Damage: " + this.getDamage() + "\n";
        }
        if (this.getHowManyHands() != 1) {
            howManyHandsString = "\nThis item requires the use of both hands.";
        }
        return "Name: " + this.getName() + "\n"
                + damageString 
                + "Size: " + Double.toString(this.getSize())
                + howManyHandsString;
    }
    
    public String toStringShort(){
        return this._nameWithArticle;
    }

}
