package characters;

import items.iItem;

import java.util.ArrayList;

public class MonsterComplex implements iCharacter {

    /*
     *
     * Private Properties
     */
    private String name;
    private NpcTypes type;
    private String gender;
    private int health = 10;
    private ArrayList<iItem> inventory;
    private iItem lHand = null;
    private iItem rHand = null;
    public String death = "You are dead.";
    private boolean dead;

    /**
     * Constructor
     */
    public MonsterComplex(String name, NpcTypes type, int health, iItem rHand) {
        this.name = name;
        this.type = type;
        this.health = health;
        this.rHand = rHand;
        this.gender = "male";
        this.dead = false;
    }

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

    public boolean isDead() {
        return this.dead;
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
