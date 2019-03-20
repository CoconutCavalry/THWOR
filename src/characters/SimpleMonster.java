package characters;

import items.iItem;
import shared.Shared;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SimpleMonster {

    private String name;
    private int health;
    private int strength;
    private String weakness;
    private boolean dead;
    private String pronoun;
//    private ArrayList<iItem> items;

    public SimpleMonster(String name, int health, int strength, String weakness) {

        // Gremlin, Goblin, Orc, Troll, etc.
        this.name = name;

        // Damage multiplier value
        this.strength = strength;

        // Weakness
        this.weakness = weakness;

        this.health = health;
        this.dead = false;
        this.setPronoun(name);
    }

    private void setPronoun(String name) {
        if (Shared.startsWithVowel(name)) {
            this.pronoun = "an";
        } else {
            this.pronoun = "a";
        }
    }

    public String getPronoun() {
        return this.pronoun;
    }

    public int takeDamage(int damage, ArrayList<String> damageTypes) {
        for (String damageType : damageTypes) {
            if (damageType.equals(weakness)) {
                damage = damage * 2;
            }
        }
        this.health = this.health - damage;
        if (this.health <= 0) {
            this.health = 0;
            this.dead = true;
        }
        return damage;
    }

    public boolean isDead() {
        return this.dead;
    }

    public int getStrength() {
        return this.strength;
    }

    public String getName() {
        return this.name;
    }

    /**
     * The pronoun + name combo ("an orc")
     * @return
     */
    public String getNameLong() {
        return this.pronoun + " " + this.name;
    }

}
