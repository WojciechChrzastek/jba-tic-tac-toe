package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        printField(takeInput());
    }

    static void printField(char[] input) {
        String line = "| %s %s %s |\n";

        System.out.println("---------");
        System.out.printf(line, input[0], input[1], input[2]);
        System.out.printf(line, input[3], input[4], input[5]);
        System.out.printf(line, input[6], input[7], input[8]);
        System.out.println("---------");
    }

    static char[] takeInput() {
        System.out.print("Enter cells: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().toCharArray();
    }
}
