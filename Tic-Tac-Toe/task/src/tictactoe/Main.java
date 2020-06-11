package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        char[] inputField = takeInputField();
        printField(inputField);
        int[] coordinates = takeInputCoordinates(inputField);
        printFieldAfterMove(inputField, coordinates);
//        findState(inputField);
    }

    private static char[][] createField(char[] input) {
        char[][] field = new char[3][3];
        field[0] = new char[]{input[0], input[1], input[2]};
        field[1] = new char[]{input[3], input[4], input[5]};
        field[2] = new char[]{input[6], input[7], input[8]};
        return field;
    }

    private static void printFieldAfterMove(char[] input, int[] coordinates) {
        int n1 = coordinates[0];
        int n2 = coordinates[1];

        int[] c = mapInputToNormalCoordinates(n1, n2);

        char[][] field = createField(input);
        field[c[0]][c[1]] = 'X';
        printField(field);
    }

    private static int[] takeInputCoordinates(char[] inputField) {
        Scanner scanner = new Scanner(System.in);
        // this prevents unnecessary next line when asking for scanner.nextLine();
//        System.out.print("name: ");
//        String line = scanner.next();

        Integer[] possibleInputs = {1, 2, 3};
        int[] coordinates = {0, 0};
        int c0 = 4;
        int c1 = 4;
        boolean hasC0;
        boolean hasC1;
        boolean isEmpty;

        do {
            System.out.print("Enter the coordinates: ");
            scanner.nextLine();
            hasC0 = false;
            hasC1 = false;
            isEmpty = false;

            if (scanner.hasNextInt()) {
                c0 = scanner.nextInt();
                if (!(Arrays.asList(possibleInputs).contains(c0))) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (Arrays.asList(possibleInputs).contains(c0)) {
                    hasC0 = true;
                    if (scanner.hasNextInt()) {
                        c1 = scanner.nextInt();
                        if (!(Arrays.asList(possibleInputs).contains(c1))) {
                            System.out.println("Coordinates should be from 1 to 3!");
                        } else if (Arrays.asList(possibleInputs).contains(c1)) {
                            hasC1 = true;
                            if (checkIfEmpty(c0, c1, inputField)) {
                                isEmpty = true;
                            } else {
                                System.out.println("This cell is occupied! Choose another one!");
                            }
                        }
                    } else {
                        System.out.println("You should enter numbers!");
                    }
                }
            } else {
                System.out.println("You should enter numbers!");
            }
        }
        while (!(hasC0 && hasC1 && isEmpty));

        coordinates[0] = c0;
        coordinates[1] = c1;
        return coordinates;
    }

    public static int[] mapInputToNormalCoordinates(int n1, int n2) {
        int i = Integer.parseInt(String.valueOf(n1).concat(String.valueOf(n2)));

        switch (i) {
            case 11 :
                n1 = 2;
                n2 = 0;
                break;
            case 21 :
                n1 = 2;
                n2 = 1;
                break;
            case 31 :
                n1 = 2;
                n2 = 2;
                break;
            case 12 :
                n1 = 1;
                n2 = 0;
                break;
            case 22 :
                n1 = 1;
                n2 = 1;
                break;
            case 32 :
                n1 = 1;
                n2 = 2;
                break;
            case 13 :
                n1 = 0;
                n2 = 0;
                break;
            case 23 :
                n1 = 0;
                n2 = 1;
                break;
            case 33 :
                n1 = 0;
                n2 = 2;
                break;
        }

        return new int[]{n1, n2};
    }

    private static boolean checkIfEmpty(int c0, int c1, char[] inputField) {

        char[][] inputtedField = createField(inputField);

        int[] c = mapInputToNormalCoordinates(c0, c1);

        return inputtedField[c[0]][c[1]] == '_';
    }

    private static int countChar(char[] chars, char c) {
        int count = 0;

        for (char ch : chars) {
            if (ch == c) {
                count++;
            }
        }
        return count;
    }

    private static void printField(char[][] field) {
        String line = "| %s %s %s |\n";

        System.out.println("---------");
        System.out.printf(line, field[0][0], field[0][1], field[0][2]);
        System.out.printf(line, field[1][0], field[1][1], field[1][2]);
        System.out.printf(line, field[2][0], field[2][1], field[2][2]);
        System.out.println("---------");
    }

    private static void printField(char[] input) {
        char[][] field = createField(input);
        printField(field);

    }

    private static char[] takeInputField() {
        System.out.print("Enter cells: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().toCharArray();
    }

    private static void findState(char[] input) {

        boolean xHasRow = false;
        boolean oHasRow = false;

        char[][] field = createField(input);

        char[] col1 = new char[]{input[0], input[3], input[6]};
        char[] col2 = new char[]{input[1], input[4], input[7]};
        char[] col3 = new char[]{input[2], input[5], input[8]};
        char[] down = new char[]{input[0], input[4], input[8]};
        char[] up = new char[]{input[6], input[4], input[2]};

        int countX = countChar(col1, 'X') + countChar(col2, 'X') + countChar(col3, 'X');
        int countO = countChar(col1, 'O') + countChar(col2, 'O') + countChar(col3, 'O');

        if (
                countChar(field[0], 'X') == 3 ||
                        countChar(field[1], 'X') == 3 ||
                        countChar(field[2], 'X') == 3 ||
                        countChar(col1, 'X') == 3 ||
                        countChar(col2, 'X') == 3 ||
                        countChar(col3, 'X') == 3 ||
                        countChar(down, 'X') == 3 ||
                        countChar(up, 'X') == 3
        ) {
            xHasRow = true;
        }
        if (
                countChar(field[0], 'O') == 3 ||
                        countChar(field[1], 'O') == 3 ||
                        countChar(field[2], 'O') == 3 ||
                        countChar(col1, 'O') == 3 ||
                        countChar(col2, 'O') == 3 ||
                        countChar(col3, 'O') == 3 ||
                        countChar(down, 'O') == 3 ||
                        countChar(up, 'O') == 3
        ) {
            oHasRow = true;
        }

        if (((countX - countO) >= 2) || ((countO - countX) >= 2) || (xHasRow && oHasRow)) {
            System.out.println("Impossible");
        } else if (
                ((countChar(col1, '_') > 0) && (!xHasRow && !oHasRow)) ||
                        ((countChar(col2, '_') > 0) && (!xHasRow && !oHasRow)) ||
                        ((countChar(col3, '_') > 0) && (!xHasRow && !oHasRow))
        ) {
            System.out.println("Game not finished");
        } else if (
                (countChar(col1, '_') == 0) &&
                        (countChar(col2, '_') == 0) &&
                        (countChar(col3, '_') == 0) && (!xHasRow && !oHasRow)
        ) {
            System.out.println("Draw");
        } else if (xHasRow) {
            System.out.println("X wins");
        } else if (oHasRow) {
            System.out.println("O wins");
        }
    }
}
