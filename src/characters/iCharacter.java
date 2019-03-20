package characters;

import items.iItem;
import java.util.ArrayList;

/**
 *
 * @author esose
 */
public interface iCharacter {

    String getName();
    boolean isDead();
    void setName(String name);
    String getGender();
    int getHealth();
    void setHealth(int newHealth);
    ArrayList<iItem> getItemsInHands();
    iItem getRHand();
    void setRHand(iItem item);
    iItem getLHand();
    void setLHand(iItem item);
    boolean takeItem(iItem item);
    ArrayList<iItem> getInventory();
    void setInventory(ArrayList<iItem> items);
    String showInventory();
    iItem dropItem(String itemName);
    String equip(iItem item);
    boolean pocket(String itemName);
    void takeDamage(int damage);
    String showStats();
    String showCharacterReport();

}