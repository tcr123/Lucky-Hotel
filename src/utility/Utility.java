package utility;
/**
 * @author js
 */

import java.util.Scanner;

public class Utility {

    private static Scanner scanner = new Scanner(System.in);


    //enter something(can set limit and whether is it ok to enter space
    private static String readKeyboard(int limit, boolean blankReturn) {

        String line = "";

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();


            if (line.length() == 0) {
                if (blankReturn) return line; //if blankReturn=true;can enter space
                else continue;              //if  blankReturn=false,cannot enter space
            }

            //to set limit of words entered
            if (line.length() < 1 || line.length() > limit) {
                System.out.println("Please re-enter(length should in between 0-" + limit);
                continue;
            }
            break;

        }
        return line;
    }

    //for main menu use
    public static char readMenu() {
        char c;
        for (; ; ) {
            String str = readKeyboard(1, false);
            c = str.charAt(0);
            if (c != '1' && c != '2' && c != '3' && c != '4' && c != '5') {
                System.out.println("Please re-enter");
            } else break;
        }
        return c;
    }
    //for those that can't just enter space
    public static char readChar() {
        String str = readKeyboard(1, false);
        return str.charAt(0);
    }


    //for those that can just enter space
    public static char readChar(char defaultValue) {
        String str = readKeyboard(1, true);
        return (str.length() == 0) ? defaultValue : str.charAt(0);
    }


    //enter int(cannot set default value and cannot enter space)
    public static int readInt() {
        int n;
        for (; ; ) {
            String str = readKeyboard(10, false);
            try {
                n = Integer.parseInt(str);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please re-enter an integer");
            }
        }
        return n;
    }

    //enter int(can set default value and  ok to enter space)
    public static int readInt(int defaultValue) {
        int t;
        for (; ; ) {
            String str = readKeyboard(10, true);

            if (str.equals("")) {
                return defaultValue;
            }

            try {
                t = Integer.parseInt(str);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please re-enter an integer");
            }
        }
        return t;
    }

    public static double readDouble() {
        double value = scanner.nextDouble();
        if (value > 5.0) {
            System.out.println("Please re-enter an double less than or equal to 5");
        } else if (value < 0) {
            System.out.println("Please re-enter an double more than 0");
        } 
        return value;
    }


    //enter String(cannot set default value and cannot enter space)
    public static String readString(int limit) {
        return readKeyboard(limit, false);
    }

    //enter String(can set default value and  ok to enter space)
    public static String readString(int limit, String defaultValue) {
        String str = readKeyboard(limit, true);
        return str.equals("") ? defaultValue : str;
    }


    //except entering y or n ,else will be an infinite loop
    public static char readConfirm() {
        System.out.println("Enter Y/N");
        char a;
        for (; ; ) {
            String s = readKeyboard(1, false).toUpperCase();
            a = s.charAt(0);
            if (a == 'Y' || a == 'N') {
                break;
            } else {
                System.out.println("Please re-enter(Y/N)");
            }
        }
        return a;
    }

}
