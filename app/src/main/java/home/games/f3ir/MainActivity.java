package home.games.f3ir;

import android.os.Bundle;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;

import home.games.f3ir.adapter.ImageAdapter;
import home.games.f3ir.engine.GameEngine;

public class MainActivity extends AppCompatActivity {
    private GameEngine gameEngine;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameEngine = new GameEngine(5);  // Поле 5x5
        gridView = findViewById(R.id.gridView);
        updateUI();
    }

    private void updateUI() {
        gridView.setAdapter(new ImageAdapter(this, gameEngine.getBoard()));
    }
}