package home.games.f3ir;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;

import home.games.f3ir.adapter.ImageAdapter;
import home.games.f3ir.engine.GameEngine;

public class MainActivity extends AppCompatActivity {
    private GameEngine gameEngine;
    private GridView gridView;
    private int selectedRow = -1;
    private int selectedCol = -1;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameEngine = new GameEngine(5);
        gridView = findViewById(R.id.gridView);
        updateUI();

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            selectedRow = position / 5;  // 5 - количество колонок
            selectedCol = position % 5;
            Log.d("TAG", "Selected: " + selectedRow + "," + selectedCol);
        });

        gridView.setOnTouchListener(new View.OnTouchListener() {
            private float startX, startY;
            private final int MIN_SWIPE_DISTANCE = 50;  // Минимальное расстояние для свайпа

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        return true;

                    case MotionEvent.ACTION_UP:
                        if (selectedRow == -1 || selectedCol == -1) return false;

                        float endX = event.getX();
                        float endY = event.getY();
                        float deltaX = endX - startX;
                        float deltaY = endY - startY;

                        // Определяем направление свайпа
                        if (Math.abs(deltaX) > MIN_SWIPE_DISTANCE || Math.abs(deltaY) > MIN_SWIPE_DISTANCE) {
                            if (Math.abs(deltaX) > Math.abs(deltaY)) {
                                // Горизонтальный свайп
                                if (deltaX > 0) trySwap(selectedRow, selectedCol, selectedRow, selectedCol + 1); // Вправо
                                else trySwap(selectedRow, selectedCol, selectedRow, selectedCol - 1); // Влево
                            } else {
                                // Вертикальный свайп
                                if (deltaY > 0) trySwap(selectedRow, selectedCol, selectedRow + 1, selectedCol); // Вниз
                                else trySwap(selectedRow, selectedCol, selectedRow - 1, selectedCol); // Вверх
                            }
                        }
                        return true;
                }
                return false;
            }
        });
    }

    private void trySwap(int fromRow, int fromCol, int toRow, int toCol) {
        // Проверка границ массива
        if (toRow < 0 || toRow >= 5 || toCol < 0 || toCol >= 5) {
            Log.d("TAG", "Invalid move");
            return;
        }

        // Меняем фишки местами
        gameEngine.swapGems(fromRow, fromCol, toRow, toCol);

        // Проверяем совпадения
        if (gameEngine.checkMatches()) {
            gameEngine.updateBoard();
            updateUI();
        } else {
            // Если совпадений нет - возвращаем на место
            gameEngine.swapGems(fromRow, fromCol, toRow, toCol);
        }
    }

    private void updateUI() {
        gridView.setAdapter(new ImageAdapter(this, gameEngine.getBoard()));
    }
}