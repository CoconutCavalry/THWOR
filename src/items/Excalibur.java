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
        return "The sword has strange runes carved into the blade and a dark jewel set into the pommel.";
    }

      
    public double getSize() {
        return 0;
    }


//    public String getStats() {
//        return "Name: EXCALIBVR\n"
//                + "Damage: " + this.getDamage() + "\n"
//                + "Size: " + Double.toString(this.getSize())
//                + "\nThis item requires the use of both hands.";
//    }

      
    public String toStringShort() {
        return "a shining sword";
    }

      
    public int getDamage() {
        return 20;
    }

    public String getDamageType() {
        return "blade";
    }

      
//    public String getAttackVerb() {
//        this.numAttacks++;
//        if (this.numAttacks % 3 == 0) {
//            return "stab";
//        }
//        return "slash";
//    }
}
