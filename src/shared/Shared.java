/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import characters.Player;
import items.iItem;
import items.iWeapon;

import java.util.ArrayList;

import static services.ConsoleLogger.outputLn;

/**
 *
 * @author esose
 */
public class Shared {
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

    public static void attack (Player user, Player npc) {
        int damage;
        int weaponDamage;

        damage = DiceRoller.getDamage();
        weaponDamage = damage * GetDamageModifier(user);
        npc.takeDamage(weaponDamage);
        outputLn("You hit " + npc.getName() +
                " for " + weaponDamage + " damage.\n" +
                npc.getName() + "'s health is now " + npc.getHealth() + ".");
    }

    public static void defend (Player user, Player npc) {
        int damage;
        int weaponDamage;

        damage = DiceRoller.getDamage();
        weaponDamage = damage * GetDamageModifier(user);
        user.takeDamage(weaponDamage);
        outputLn(npc.getName() + " hit you for " +
                weaponDamage + " damage.\n" +
                "Your health is now " + user.getHealth() + ".");
    }

    private static int GetDamageModifier(Player p) {
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






}
