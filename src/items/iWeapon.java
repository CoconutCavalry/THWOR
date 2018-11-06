package items;

public interface iWeapon {

    /**
     *
     * @return true if the item can be used as a weapon
     */
    boolean getIsWeapon();

    /**
     *
     * @return the item's damage
     */
    int getDamage();

    /**
     *
     * @return the item's attack verb
     */
    String getAttackVerb();

}
