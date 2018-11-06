package items;

public class Knife implements iItem, iWeapon {

    public Knife() {}

    @Override
    public String getName() {
        return "knife";
    }

    @Override
    public String getDescription() {
        return "A small, sharp, folding pocketknife with \n" +
                "a wooden handle.";
    }

    @Override
    public double getSize() {
        return 1;
    }

    @Override
    public String getStats() {
        return null;
    }

    @Override
    public String toStringShort() {
        return "a knife";
    }

    @Override
    public boolean getIsWeapon() {
        return true;
    }

    @Override
    public int getDamage() {
        return 2;
    }

    @Override
    public String getAttackVerb() {
        return "slash";
    }
}
