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
        return null;
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
