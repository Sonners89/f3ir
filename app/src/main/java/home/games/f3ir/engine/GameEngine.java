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
        // Проверка по горизонтали
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length - 2; j++) {
                if (board[i][j] == board[i][j + 1] && board[i][j] == board[i][j + 2]) {
                    board[i][j] = board[i][j + 1] = board[i][j + 2] = -1;  // Помечаем для удаления
                    hasMatches = true;
                    score += 10;
                }
            }
        }
        return hasMatches;
    }

    // Обновление поля после удаления совпадений
    public void updateBoard() {
        // Пока оставим пустым (реализуем позже)
    }

    public int[][] getBoard() {
        return board;
    }

    public int getScore() {
        return score;
    }
}