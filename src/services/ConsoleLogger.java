/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author esose
 */
public class ConsoleLogger {
    
    /**
     * Created in order to be able to easily change 
     * the output method for the game.
     * @param content the content to be outputted
     */
    public static void output(String content) {
        System.out.print(content);
    }
    
    public static void outputLn(String content) {
        System.out.println(content);
    }
    public static void outputLn() {
        System.out.println();
    }
    
    public static void outputFm(String content) {
        System.out.printf(content);
    }
}
