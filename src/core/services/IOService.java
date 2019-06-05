/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.services;

import java.util.Scanner;

/**
 *
 * @author esose
 */
public class IOService {
    
    private static Scanner input = new Scanner(System.in);
    
    public static String getNextLine() {
//        ConsoleLogger.output("> ");
        String retVal = input.nextLine();
        return retVal;
    }
    
    
    
}
