import java.util.Scanner;

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


