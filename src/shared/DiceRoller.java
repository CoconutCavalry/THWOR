/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import java.util.Random;

/**
 *
 * @author esose
 */
public class DiceRoller {
    static Random rand = new Random();
    
    public static int getValue1to10() {
        return rand.nextInt(10);
    }
    
    public static int getDamage() {
        return getValue1to10();
    }
}
