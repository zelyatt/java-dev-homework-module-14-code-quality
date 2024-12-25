import java.util.Random;

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
