package home.games.f3ir.engine;

import java.util.Random;

public class GameEngine {
    private int[][] board;  // Игровое поле
    private int score = 0;

    public GameEngine(int size) {
        board = new int[size][size];
        fillBoard();
    }

    // Заполнение поля случайными числами (0-4 для 5 типов фигур)
    private void fillBoard() {
        Random random = new Random();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = random.nextInt(5);
            }
        }
    }

    // Проверка совпадений (3 в ряд)
    public boolean checkMatches() {
        boolean hasMatches = false;
        // Проверка по горизонтали и вертикали
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length - 2; j++) {
                // Горизонталь
                if (board[i][j] == board[i][j+1] && board[i][j] == board[i][j+2]) {
                    for (int k = j; k < j + 3; k++) board[i][k] = -1;
                    hasMatches = true;
                    score += 30;
                }
                // Вертикаль
                if (board[j][i] == board[j+1][i] && board[j][i] == board[j+2][i]) {
                    for (int k = j; k < j + 3; k++) board[k][i] = -1;
                    hasMatches = true;
                    score += 30;
                }
            }
        }
        return hasMatches;
    }

    // Обновление поля после удаления совпадений
    public void updateBoard() {
        // Сдвигаем фишки вниз
        for (int col = 0; col < board[0].length; col++) {
            for (int row = board.length - 1; row >= 0; row--) {
                if (board[row][col] == -1) {
                    for (int k = row; k > 0; k--) {
                        board[k][col] = board[k-1][col];
                    }
                    board[0][col] = new Random().nextInt(5); // Новая фишка сверху
                }
            }
        }
    }

    public int[][] getBoard() {
        return board;
    }

    public int getScore() {
        return score;
    }

    public boolean swapGems(int row1, int col1, int row2, int col2) {
        if (row1 < 0 || row1 >= board.length || col1 < 0 || col1 >= board[0].length) return false;
        if (row2 < 0 || row2 >= board.length || col2 < 0 || col2 >= board[0].length) return false;

        int temp = board[row1][col1];
        board[row1][col1] = board[row2][col2];
        board[row2][col2] = temp;

        return true;
    }
}