package items;

public class Excalibur implements iItem, iWeapon {
    private int numAttacks = 0;

    public Excalibur() {}

    @Override
    public String getName() {
        return "sword";
    }

    @Override
    public String getDescription() {
        return "The sword has strange runes carved into the blade\n" +
                "and a dark jewel set into the pommel.";
    }

    @Override
    public double getSize() {
        return 0;
    }

    @Override
    public String getStats() {
        return null;
    }

    @Override
    public String toStringShort() {
        return "a shining longsword";
    }

    @Override
    public boolean getIsWeapon() {
        return true;
    }

    @Override
    public int getDamage() {
        return 20;
    }

    @Override
    public String getAttackVerb() {
        numAttacks++;
        if (numAttacks % 3 == 0) {
            return "stab";
        }
        return "slash";
    }
}
