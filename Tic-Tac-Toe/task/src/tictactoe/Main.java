package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int i = 1;
        char[][] field = createBlankField();
        printField(field);
        while (!(findState(field).equals("X wins") ||
                findState(field).equals("O wins") ||
                findState(field).equals("Draw"))) {
            int[] move = takeMove(field);
            printFieldAfterMove(field, move, determinePlayer(i));
            findState(field);
            i++;
        }
        printResult(findState(field));
        scanner.close();
    }

    private static char[][] createBlankField() {
        char[][] field = new char[3][3];
        field[0] = new char[]{' ', ' ', ' '};
        field[1] = new char[]{' ', ' ', ' '};
        field[2] = new char[]{' ', ' ', ' '};
        return field;
    }

    private static void printField(char[][] field) {
        String line = "| %s %s %s |\n";
        System.out.println("---------");
        System.out.printf(line, field[0][0], field[0][1], field[0][2]);
        System.out.printf(line, field[1][0], field[1][1], field[1][2]);
        System.out.printf(line, field[2][0], field[2][1], field[2][2]);
        System.out.println("---------");
    }

    private static int[] takeMove(char[][] field) {
        Integer[] possibleCoordinates = {1, 2, 3};
        int[] coordinates = {0, 0};
        int c0 = 4;
        int c1 = 4;
        boolean hasC0;
        boolean hasC1;
        boolean isEmpty;

        do {
            System.out.print("Enter the coordinates: ");
            String coordinatesInput = scanner.nextLine();
            String[] cArray = coordinatesInput.split(" ");
            hasC0 = false;
            hasC1 = false;
            isEmpty = false;

            if (cArray[0].matches("[0-9]+") && cArray[1].matches("[0-9]+")) {
                c0 = Integer.parseInt(cArray[0]);
                if (!(Arrays.asList(possibleCoordinates).contains(c0))) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (Arrays.asList(possibleCoordinates).contains(c0)) {
                    hasC0 = true;
                    c1 = Integer.parseInt(cArray[1]);
                    if (!(Arrays.asList(possibleCoordinates).contains(c1))) {
                        System.out.println("Coordinates should be from 1 to 3!");
                    } else if (Arrays.asList(possibleCoordinates).contains(c1)) {
                        hasC1 = true;
                        if (checkIfEmpty(c0, c1, field)) {
                            isEmpty = true;
                        } else {
                            System.out.println("This cell is occupied! Choose another one!");
                        }
                    }
                } else {
                    System.out.println("You should enter numbers!");
                }
            }
        }
        while (!(hasC0 && hasC1 && isEmpty));

        coordinates[0] = c0;
        coordinates[1] = c1;
        return coordinates;
    }

    private static boolean checkIfEmpty(int c0, int c1, char[][] field) {
        int[] c = mapInputToNormalCoordinates(c0, c1);
        return field[c[0]][c[1]] == ' ';
    }

    private static char determinePlayer(int i) {
        return i % 2 == 0 ? 'O' : 'X';
    }

    private static void printFieldAfterMove(char[][] field, int[] coordinates, char c) {
        int[] normalCoordinates = mapInputToNormalCoordinates(coordinates[0], coordinates[1]);
        field[normalCoordinates[0]][normalCoordinates[1]] = c;
        printField(field);
    }

    public static int[] mapInputToNormalCoordinates(int n1, int n2) {
        int i = Integer.parseInt(String.valueOf(n1).concat(String.valueOf(n2)));

        switch (i) {
            case 11:
                n1 = 2;
                n2 = 0;
                break;
            case 21:
                n1 = 2;
                n2 = 1;
                break;
            case 31:
                n1 = 2;
                n2 = 2;
                break;
            case 12:
                n1 = 1;
                n2 = 0;
                break;
            case 22:
                n1 = 1;
                n2 = 1;
                break;
            case 32:
                n1 = 1;
                n2 = 2;
                break;
            case 13:
                n1 = 0;
                n2 = 0;
                break;
            case 23:
                n1 = 0;
                n2 = 1;
                break;
            case 33:
                n1 = 0;
                n2 = 2;
                break;
        }
        return new int[]{n1, n2};
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

    private static String findState(char[][] field) {
        boolean xHasRow = false;
        boolean oHasRow = false;
        String result = "";

        char[] col1 = new char[]{field[0][0], field[1][0], field[2][0]};
        char[] col2 = new char[]{field[0][1], field[1][1], field[2][1]};
        char[] col3 = new char[]{field[0][2], field[1][2], field[2][2]};
        char[] down = new char[]{field[0][0], field[1][1], field[2][2]};
        char[] up = new char[]{field[2][0], field[1][1], field[0][2]};

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
            result = "Impossible";
        } else if (
                ((countChar(col1, ' ') > 0) && (!xHasRow && !oHasRow)) ||
                        ((countChar(col2, ' ') > 0) && (!xHasRow && !oHasRow)) ||
                        ((countChar(col3, ' ') > 0) && (!xHasRow && !oHasRow))
        ) {
            result = "Game not finished";
        } else if (
                (countChar(col1, ' ') == 0) &&
                        (countChar(col2, ' ') == 0) &&
                        (countChar(col3, ' ') == 0) && (!xHasRow && !oHasRow)
        ) {
            result = "Draw";
        } else if (xHasRow) {
            result = "X wins";
        } else if (oHasRow) {
            result = "O wins";
        }
        return result;
    }

    private static void printResult(String result) {
        System.out.println(result);
    }
}
