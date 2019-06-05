package items;

public class Torch implements iWeapon {

    private String name = "torch";
    private String description = "The torch has a wooden handle " +
            "with a cloth wrapped around one end. The cloth is " +
            "burning slowly but brightly.";
    private int size = 1;
    private int damage = 10;
    private String damageType = "fire";

    public Torch() {}

    public int getDamage() {
        return damage;
    }

    public String getDamageType() {
        return damageType;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getSize() {
        return size;
    }

//    public String getStats() {
//        return "Name: " + this.getName() +
//                "\nDamage: " + this.getDamage() +
//                "\nSize: " + Double.toString(this.getSize());
//    }

    public String toStringShort() {
        return "a " + this.name;
    }
}
