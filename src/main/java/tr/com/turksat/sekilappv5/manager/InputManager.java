package tr.com.turksat.sekilappv5.manager;

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

    public static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static void closeScanner() {
        scanner.close();
    }

    // Format seçimi almak için eklenen metot
    public static String readFormatChoice() {
        System.out.println("Lütfen dosya formatını seçiniz:");
        System.out.println("1 - JSON formatı");
        System.out.println("2 - Normal format");
        String choice = scanner.nextLine();
        return choice;
    }
}
