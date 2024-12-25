import java.util.Scanner;
import java.util.Random;

public class App {

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.start();
    }
}

class TicTacToe {
    private final Board board;
    private final Player human;
    private final Player computer;

    public TicTacToe() {
        board = new Board();
        human = new Player('X');
        computer = new Player('O');
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Tic Tac Toe! Enter the box number to select. Enjoy!\n");

        while (true) {
            board.display();
            if (playerTurn(scanner)) break;
            if (computerTurn()) break;
        }
    }

    private boolean playerTurn(Scanner scanner) {
        while (true) {
            System.out.print("Your turn: ");
            int input = scanner.nextInt() - 1;

            if (board.isValidMove(input)) {
                board.makeMove(input, human.getSymbol());
                if (board.checkWinner(human.getSymbol())) {
                    board.display();
                    System.out.println("You won the game! Thanks for playing.");
                    return true;
                }
                if (board.isFull()) {
                    board.display();
                    System.out.println("It's a draw! Thanks for playing.");
                    return true;
                }
                return false;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }

    private boolean computerTurn() {
        System.out.println("Computer's turn:");
        int move = board.getRandomMove();
        board.makeMove(move, computer.getSymbol());
        if (board.checkWinner(computer.getSymbol())) {
            board.display();
            System.out.println("You lost the game! Thanks for playing.");
            return true;
        }
        if (board.isFull()) {
            board.display();
            System.out.println("It's a draw! Thanks for playing.");
            return true;
        }
        return false;
    }
}

class Board {
    private final char[] boxes = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };

    public void display() {
        System.out.println("\n " + boxes[0] + " | " + boxes[1] + " | " + boxes[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + boxes[3] + " | " + boxes[4] + " | " + boxes[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + boxes[6] + " | " + boxes[7] + " | " + boxes[8] + " \n");
    }

    public boolean isValidMove(int index) {
        return index >= 0 && index < 9 && boxes[index] != 'X' && boxes[index] != 'O';
    }

    public void makeMove(int index, char symbol) {
        boxes[index] = symbol;
    }

    public boolean checkWinner(char symbol) {
        return (checkLine(symbol, 0, 1, 2) || checkLine(symbol, 3, 4, 5) || checkLine(symbol, 6, 7, 8) || // Rows
                checkLine(symbol, 0, 3, 6) || checkLine(symbol, 1, 4, 7) || checkLine(symbol, 2, 5, 8) || // Columns
                checkLine(symbol, 0, 4, 8) || checkLine(symbol, 2, 4, 6));                                // Diagonals
    }

    private boolean checkLine(char symbol, int a, int b, int c) {
        return boxes[a] == symbol && boxes[b] == symbol && boxes[c] == symbol;
    }

    public boolean isFull() {
        for (char box : boxes) {
            if (box != 'X' && box != 'O') return false;
        }
        return true;
    }

    public int getRandomMove() {
        Random random = new Random();
        int move;
        do {
            move = random.nextInt(9);
        } while (!isValidMove(move));
        return move;
    }
}

class Player {
    private final char symbol;

    public Player(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}
