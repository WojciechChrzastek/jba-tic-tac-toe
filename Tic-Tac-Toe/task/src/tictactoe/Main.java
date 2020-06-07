package tictactoe;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    char[] input = takeInput();
    printField(input);
    findState(input);
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

  private static void findState(char[] input) {

    boolean xHasRow = false;
    boolean oHasRow = false;

    char[][] field = new char[3][3];
    field[0] = new char[]{input[0], input[1], input[2]};
    field[1] = new char[]{input[3], input[4], input[5]};
    field[2] = new char[]{input[6], input[7], input[8]};

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

  private static void printField(char[] input) {
    String line = "| %s %s %s |\n";

    System.out.println("---------");
    System.out.printf(line, input[0], input[1], input[2]);
    System.out.printf(line, input[3], input[4], input[5]);
    System.out.printf(line, input[6], input[7], input[8]);
    System.out.println("---------");
  }

  private static char[] takeInput() {
    System.out.print("Enter cells: ");
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine().toCharArray();
  }
}
