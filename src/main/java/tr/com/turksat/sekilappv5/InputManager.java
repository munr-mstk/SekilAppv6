package tr.com.turksat.sekilappv5;

import java.util.Scanner;

public class InputManager {

    private static final Scanner scanner = new Scanner(System.in);

    public static int readInt(String prompt) {
        System.out.print(prompt);
        return scanner.nextInt();
    }

    public static char readChar(String prompt) {
        System.out.print(prompt);
        return scanner.next().charAt(0);
    }

    public static void closeScanner() {
        scanner.close();
    }
}

