package items;

public class Knife implements iWeapon {

    public Knife() {}

      
    public String getName() {
        return "knife";
    }

      
    public String getDescription() {
        return "A small, sharp, folding pocketknife with \n" +
                "a wooden handle.";
    }

      
    public double getSize() {
        return 1;
    }


    public String getStats() {
        String damageString = "Damage: " + this.getDamage() + "\n";
        return "Name: " + this.getName() + "\n"
                + damageString
                + "Size: " + Double.toString(this.getSize());
    }

      
    public String toStringShort() {
        return "a knife";
    }

      
    public int getDamage() {
        return 2;
    }

      
//    public String getAttackVerb() {
//        return "slash";
//    }
}
