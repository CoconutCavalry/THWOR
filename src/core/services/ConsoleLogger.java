/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.services;

import static housewithoneroom.Start.admin;

/**
 *
 * @author esose
 */
public class ConsoleLogger {

    private static boolean noLineWrap = false;
    private static boolean delay = false;

    private static int MAX_CHAR_LENGTH = 50;

    /**
     * Created in order to be able to easily change 
     * the output method for the game.
     * @param content the content to be outputted
     */
    public static void output(String content) {
//        // ES TEST: game logging experimental code
//        try {
//            if (FileWriterExperimental.getIsPlayerFileCreated()) {
//                FileWriterExperimental.writeToFile(content);
//            } else {
//                System.out.println("Test File does not exist.");
//            }
//        } catch (IOException e) {
//            System.out.println("Error creating Test File: " + e);
//        }

        if (noLineWrap) {
            showLong(content);
        } else if (admin) {
            System.out.println(content);
        } else if (delay) {
            showWithDelay(content);
        } else {
            System.out.println(content);
        }
    }

    private static void showWithDelay(String content) {
        if (admin) {
            System.out.println(content);
            return;
        }
        String[] contentArr = content.split("\n");
        for (String c : contentArr) {
            System.out.println(c);
            try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void outputForInput(String content) {
        System.out.print(content);
    }
    
    public static void outputLn(String content) {
        System.out.println(content);
    }
    public static void outputLn() {
        System.out.println();
    }
    
//    public static void outputFm(String content) {
//        System.out.printf(content);
//    }

    private static void showLong(String content) {
        String[] contentArr = content.split("\n");
        for (String c : contentArr) {
            int origin = 0;
            String remaining = c;

            String s = "";
            while (remaining.length() > MAX_CHAR_LENGTH) {
                s = remaining.substring(origin, MAX_CHAR_LENGTH);
                remaining = remaining.substring(MAX_CHAR_LENGTH);
                if (!s.contains("\n")) {
                    String[] sArr = s.split("\n");
                    printEachArrayElementOnNewLine(sArr);
                } else {
                    System.out.println(s);
                }
            }
            System.out.println(remaining);
        }
    }

    private static void printEachArrayElementOnNewLine(String[] stringArr) {
        for (String s : stringArr) {
            System.out.println(s);
        }
    }

}
