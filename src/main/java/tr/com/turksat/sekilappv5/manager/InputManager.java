package tr.com.turksat.sekilappv5.manager;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputManager {

    public static  Scanner scanner = new Scanner(System.in);
    private static final Pattern SELECTION_PATTERN = Pattern.compile("^[1-9]$|^1[0-2]$");
    private static final Pattern SYMBOL_PATTERN = Pattern.compile("^[*#+/]$");

    public static int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Geçersiz giriş. Lütfen geçerli bir sayı girin.");
            System.out.print(prompt);
            scanner.next();
        }
        int selection = scanner.nextInt();
        scanner.nextLine();
        return selection;
    }

    public static int readUnrestrictedInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Geçersiz giriş. Lütfen geçerli bir sayı girin.");
            System.out.print(prompt);
            scanner.next();
        }
        int number = scanner.nextInt();
        scanner.nextLine();
        return number;
    }

    public static char readChar(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();

        while (!SYMBOL_PATTERN.matcher(input).matches()) {
            System.out.println("Geçersiz sembol. Lütfen *, #, + veya / karakterlerinden birini girin.");
            System.out.print(prompt);
            input = scanner.nextLine();
        }

        return input.charAt(0);
    }

    public static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static void closeScanner() {
        scanner.close();
    }
    public static int readValidatedSelection(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();

        while (!SELECTION_PATTERN.matcher(input).matches()) {
            System.out.println("Geçersiz seçim. Lütfen 1 ile 12 arasında bir sayı girin.");
            System.out.print(prompt);
            input = scanner.nextLine();
        }

        return Integer.parseInt(input);
    }

    public static String readFormatChoice() {
        System.out.println("Lütfen dosya formatını seçiniz:");
        System.out.println("1 - JSON formatı");
        System.out.println("2 - Normal format");
        String choice = scanner.nextLine();

        while (!choice.equals("1") && !choice.equals("2")) {
            System.out.println("Geçersiz seçenek. Lütfen 1 veya 2 giriniz.");
            choice = scanner.nextLine();
        }

        return choice;
    }

    public static void clearBuffer() {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }
}
