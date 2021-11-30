import java.util.Scanner;

public class Menu {

    public static final String ANSI_RESET="\u001B[0m";
    public static final String ANSI_GREEN ="\u001B[32m";
    public static final String ANSI_BUE ="\u001B[34m";
    public static final String ANSI_RED ="\u001B[31m";
    static Scanner sc = new Scanner(System.in);

    public static int mainMenu(){
        System.out.print("*|| " + ANSI_BUE + "What do you want to do?" + ANSI_RESET + " ||*\n\n" +
                 ANSI_BUE + "1. Access list of user\n" +
                "2. Add new user\n\n" +
                "0. EXIT. \n\n" + ANSI_RESET);
        System.out.print("||* " + ANSI_GREEN +  "Enter you choice:");

        return sc.nextInt();

    }

}
