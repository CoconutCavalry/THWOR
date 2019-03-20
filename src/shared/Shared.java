/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import characters.Player;
import characters.SimpleMonster;
import housewithoneroom.Game;
import items.iItem;
import items.iWeapon;
import static services.ConsoleLogger.outputLn;

import java.util.ArrayList;


/**
 *
 * @author esose
 */
public class
Shared {
    public static String convertItemsInListToString(ArrayList<iItem> items) {
        if (items == null) {
            return "";
        }
        String stringOfItems = "";
        int len = items.size();
        int count = 1;
        for (iItem item : items) {
            stringOfItems = stringOfItems + item.toStringShort();
            if (count == len) {
                stringOfItems = stringOfItems + ".";
            } else if (count == len - 1) {
                stringOfItems = stringOfItems + " and ";
            } else {
                stringOfItems = stringOfItems + ", ";
            } 
            count++;
        }
        return stringOfItems;
    }
    
    public static String appendDescriptionToItemsString(String description,
            ArrayList<iItem> items) {
        String stringOfItems = convertItemsInListToString(items);
        String returnValue = description + stringOfItems;
        return returnValue;
    }
    
    public static iItem searchForItemInListByName(String itemName,
                                                  ArrayList<iItem> listOfItems) {
        for (iItem item : listOfItems) {
            if (item != null && item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }
    
    public static boolean validateNoun(String[] inputs) {
        if (inputs.length > 1) {
            if (!inputs[1].isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Simple method for attacking simple monsters
     * @param monster
     */
    public static void attack (SimpleMonster monster) {
        int damage;
        int weaponDamage;

        damage = DiceRoller.getDamage();
        if (damage == 0) {
            outputLn("You miss the " + monster.getName() + " with your attack.");
        } else {
            weaponDamage = damage * getDamageModifier(Game.player);
            ArrayList<String> damageTypes = getDamageTypes(Game.player);
            int damageDealt = monster.takeDamage(weaponDamage, damageTypes);
            outputLn("You hit the " + monster.getName() +
                    " for " + damageDealt + " damage.");
        }
    }

    /**
     * Simple method to defend against simple monsters
     * @param monster
     */
    public static void defend (SimpleMonster monster) {
        int damage;
        int weaponDamage;

        damage = DiceRoller.getDamage();
        if (damage == 0) {
            outputLn("The " + monster.getName() + " misses you with its attack.");
        } else {
            weaponDamage = damage * monster.getStrength();
            Game.player.takeDamage(weaponDamage);
            outputLn("The " + monster.getName() + " hit you for " +
                    weaponDamage + " damage.");
        }
    }

    public static void attack (Player user, Player npc) {
        int damage;
        int weaponDamage;

        damage = DiceRoller.getDamage();
        weaponDamage = damage * getDamageModifier(user);
        npc.takeDamage(weaponDamage);
        outputLn("You hit " + npc.getName() +
                " for " + weaponDamage + " damage.\n" +
                npc.getName() + "'s health is now " + npc.getHealth() + ".");
    }

    public static void defend (Player user, Player npc) {
        int damage;
        int weaponDamage;

        damage = DiceRoller.getDamage();
        weaponDamage = damage * getDamageModifier(npc);
        user.takeDamage(weaponDamage);
        outputLn(npc.getName() + " hit you for " +
                weaponDamage + " damage.\n" +
                "Your health is now " + user.getHealth() + ".");
    }

    /**
     * Calculates the total damage from whatever weapons the player has equipped
     * @param p
     * @return
     */
    private static int getDamageModifier(Player p) {
        int retVal = 1;
        if (p.getRHand() != null && p.getRHand() instanceof iWeapon) {
            iWeapon weapon = (iWeapon)p.getRHand();
            retVal += weapon.getDamage();
        }
        if (p.getLHand() != null && p.getLHand() instanceof iWeapon) {
            iWeapon weapon = (iWeapon)p.getLHand();
            retVal += weapon.getDamage();
        }
        return retVal;
    }

    /**
     * Check equipped items for damage types
     * @param player
     * @return
     */
    private static ArrayList<String> getDamageTypes(Player player) {
        ArrayList<String> retVal = new ArrayList<>();
        if (player.getRHand() != null && player.getRHand() instanceof iWeapon) {
            iWeapon weapon = (iWeapon)player.getRHand();
            retVal.add(weapon.getDamageType());
        }
        if (player.getLHand() != null && player.getLHand() instanceof iWeapon) {
            iWeapon weapon = (iWeapon)player.getLHand();
            retVal.add(weapon.getDamageType());
        }
        return retVal;
    }

    /**
     * Check if a specific word starts with a vowel
     * - for pronoun agreement ("a" vs. "an")
     * @param word
     * @return
     */
    public static boolean startsWithVowel(String word) {
        if (word.startsWith("a") || word.startsWith("e")
                || word.startsWith("i") || word.startsWith("o")
                || word.startsWith("u")) {
            return true;
        } else {
            return false;
        }
    }
}
