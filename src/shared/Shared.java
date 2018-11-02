/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import characters.Player;
import items.Item;
import java.util.ArrayList;

import static services.ConsoleLogger.output;
import static services.ConsoleLogger.outputLn;

/**
 *
 * @author esose
 */
public class Shared {
    public static String convertItemsInListToString(ArrayList<Item> items) {
        if (items == null) {
            return "";
        }
        String stringOfItems = "";
        int len = items.size();
        int count = 1;
        for (Item item : items) {
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
            ArrayList<Item> items) {
        String stringOfItems = convertItemsInListToString(items);
        String returnValue = description + stringOfItems;
        return returnValue;
    }
    
    public static Item searchForItemInListByName(String itemName, 
            ArrayList<Item> listOfItems) {
        for (Item item : listOfItems) {
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

        // Round 1
        damage = DiceRoller.getDamage();
        weaponDamage = damage * GetDamageModifier(user);
        npc.takeDamage(weaponDamage);
        outputLn(user.getName() + " hit " + npc.getName() +
                " for " + weaponDamage + ", " + npc.getName() + "'s new health: " +
                npc.getHealth());

//
//        // Round 2
//        damage = DiceRoller.getDamage();
//        weaponDamage = damage * GetDamageModifier(npc);
//        user.takeDamage(weaponDamage);
//        output("hit for " + weaponDamage + ", new health: " + user.getHealth());

    }

    private static int GetDamageModifier(Player p) {
        int retVal = 1;
        if (p.getRHand() != null) {
            retVal += p.getRHand().getDamage();
        }
        if (p.getLHand() != null) {
            retVal += p.getLHand().getDamage();
        }
        return retVal;
    }






}
