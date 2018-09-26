/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import items.Item;
import java.util.ArrayList;

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
}
