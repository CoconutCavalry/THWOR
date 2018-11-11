package characters;

import items.iItem;

import java.util.ArrayList;

public class NPC implements iCharacter {
    public String getName() {
        return null;
    }

    public void setName(String name) {

    }

    public String getGender() {
        return null;
    }

    public int getHealth() {
        return 0;
    }

    public void setHealth(int newHealth) {

    }

    public ArrayList<iItem> getItemsInHands() {
        return null;
    }

    public iItem getRHand() {
        return null;
    }

    public void setRHand(iItem item) {

    }

    public iItem getLHand() {
        return null;
    }

    public void setLHand(iItem item) {

    }

    public boolean takeItem(iItem item) {
        return false;
    }

    public ArrayList<iItem> getInventory() {
        return null;
    }

    public void setInventory(ArrayList<iItem> items) {

    }

    public String showInventory() {
        return null;
    }

    public iItem dropItem(String itemName) {
        return null;
    }

    public String equip(iItem item) {
        return null;
    }

    public boolean pocket(String itemName) {
        return false;
    }

    public void takeDamage(int damage) {

    }

    public String showStats() {
        return null;
    }

    public String showCharacterReport() {
        return null;
    }
}
