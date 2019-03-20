package items;

public interface iWeapon extends iItem {

    /**
     *
     * @return the item's damage
     */
    int getDamage();

    /**
     *
     * @return type of damage
     */
    String getDamageType();

    /**
     *
     * @return the item's attack verb
     */
//    String getAttackVerb();

}
