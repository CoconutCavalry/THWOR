package characters;

import items.iItem;
import java.util.ArrayList;

/**
 *
 * @author esose
 */
public interface iCharacter {

    public String getName();
    public void setName(String name);
    public String getGender();
    public int getHealth();
    public void setHealth(int newHealth);
    public ArrayList<iItem> getItemsInHands();
    public iItem getRHand();
    public void setRHand(iItem item);
    public iItem getLHand();
    public void setLHand(iItem item);
    public boolean takeItem(iItem item);
    public ArrayList<iItem> getInventory();
    public void setInventory(ArrayList<iItem> items);
    public String showInventory();
    public iItem dropItem(String itemName);
    public String equip(iItem item);
    public boolean pocket(String itemName);
    public void takeDamage(int damage);
    public String showStats();
    public String showCharacterReport();

}