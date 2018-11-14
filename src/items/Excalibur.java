package items;

public class Excalibur implements iWeapon {
//    private int numAttacks;

    public Excalibur() {
//        this.numAttacks = 0;
    }

      
    public String getName() {
        return "sword";
    }

      
    public String getDescription() {
        return "The sword has strange runes carved into the blade\n" +
                "and a dark jewel set into the pommel.";
    }

      
    public double getSize() {
        return 0;
    }

      
    public String getStats() {
        return null;
    }

      
    public String toStringShort() {
        return "a shining longsword";
    }

      
    public int getDamage() {
        return 20;
    }

      
//    public String getAttackVerb() {
//        this.numAttacks++;
//        if (this.numAttacks % 3 == 0) {
//            return "stab";
//        }
//        return "slash";
//    }
}
