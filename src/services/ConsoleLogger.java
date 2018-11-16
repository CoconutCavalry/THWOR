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

    private static boolean noLineWrap = false;

    private static int MAX_CHAR_LENGTH = 50;

    /**
     * Created in order to be able to easily change 
     * the output method for the game.
     * @param content the content to be outputted
     */
    public static void output(String content) {
        showLong(content);
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
        if (noLineWrap) {
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
        } else {
            System.out.println(content);
        }


    }

    private static void printEachArrayElementOnNewLine(String[] stringArr) {
        for (String s :
                stringArr) {
            System.out.println(s);
        }
    }

}
