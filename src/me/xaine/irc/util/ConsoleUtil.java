package me.xaine.irc.util;

public class ConsoleUtil {

    public static String RESET_COLOURS = "\u001B[m";
    public static void setConsoleTextColour(int colourCode) {
        System.out.print("\u001B[38:5:"+colourCode+"m");
    }
    public static void setConsoleBackgroundColour(int colourCode) {
        System.out.print("\u001B[48:5:"+colourCode+"m");
    }
    public static void resetConsoleColours() {
        System.out.print(RESET_COLOURS);
    }

}
