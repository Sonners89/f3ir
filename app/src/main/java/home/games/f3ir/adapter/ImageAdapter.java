package home.games.f3ir.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context context;
    private int[][] board;

    public ImageAdapter(Context context, int[][] board) {
        this.context = context;
        this.board = board;
    }

    @Override
    public int getCount() {
        return board.length * board[0].length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(100, 100));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) convertView;
        }

        int row = position / board.length;
        int col = position % board.length;
        int gemType = board[row][col];

        // Временная заглушка (позже заменим на картинки)
        int resId;
        switch (gemType) {
            case 0: resId = android.R.color.holo_red_light; break;
            case 1: resId = android.R.color.holo_blue_light; break;
            case 2: resId = android.R.color.holo_green_light; break;
            case 3: resId = android.R.color.holo_orange_light; break;
            case 4: resId = android.R.color.holo_purple; break;
            default: resId = android.R.color.black; // Для -1 (пустые клетки)
        }
        imageView.setBackgroundResource(resId);
        return imageView;
    }
}