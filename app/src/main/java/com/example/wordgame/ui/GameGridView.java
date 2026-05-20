package com.example.wordgame.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.wordgame.GameActivity;
import com.example.wordgame.model.LetterTile;
import java.util.ArrayList;

//ekranda görünen harf tablosunu çiziyor + dokunmayı yönetiyor + kelime seçtiriyor
public class GameGridView extends View {
    private int rows, cols;
    private LetterTile[][] tiles;
    private ArrayList<LetterTile> selectedPath = new ArrayList<>();
    private Paint paint = new Paint();
    private float cellSize;

    public GameGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setGridData(int rows, int cols, LetterTile[][] tiles) {
        this.rows = rows;
        this.cols = cols;
        this.tiles = tiles;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (tiles == null) return;


        cellSize = (float) getWidth() / cols;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (tiles[i][j] != null) {
                    drawTile(canvas, i, j);
                }
            }
        }
        drawSelectionPath(canvas);
    }


    private void drawSelectionPath(Canvas canvas) {
        if (selectedPath.size() < 2) return;

        Paint pathPaint = new Paint();
        pathPaint.setColor(Color.BLUE);
        pathPaint.setStrokeWidth(10f);
        pathPaint.setStyle(Paint.Style.STROKE);
        pathPaint.setStrokeJoin(Paint.Join.ROUND);

        android.graphics.Path path = new android.graphics.Path();
        boolean first = true;

        for (LetterTile tile : selectedPath) {
            float centerX = tile.getCol() * cellSize + cellSize / 2;
            float centerY = tile.getRow() * cellSize + cellSize / 2;
            if (first) {
                path.moveTo(centerX, centerY);
                first = false;
            } else {
                path.lineTo(centerX, centerY);
            }
        }
        canvas.drawPath(path, pathPaint);
    }






    private boolean isValidIndex(int r, int c) {
        return r >= 0 && r < rows && c >= 0 && c < cols;
    }



    private void drawTile(Canvas canvas, int r, int c) {
        float left = c * cellSize;
        float top = r * cellSize;

        // kutu çiz
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(tiles[r][c].isSelected() ? Color.YELLOW : Color.WHITE);
        canvas.drawRect(left + 5, top + 5, left + cellSize - 5, top + cellSize - 5, paint);


        paint.setColor(Color.BLACK);
        paint.setTextSize(cellSize * 0.5f);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(tiles[r][c].getLetter(), left + cellSize / 2, top + cellSize / 1.5f, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();


        int c = (int) (x / cellSize);
        int r = (int) (y / cellSize);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (isValidIndex(r, c)) {
                    selectedPath.clear();
                    addTileToPath(r, c);
                }
                break;

            case MotionEvent.ACTION_MOVE:
                if (isValidIndex(r, c)) {
                    LetterTile currentTile = tiles[r][c];

                    if (!currentTile.isSelected() && !selectedPath.isEmpty()) {
                        LetterTile lastTile = selectedPath.get(selectedPath.size() - 1);
                        if (isNeighbor(lastTile, r, c)) {
                            addTileToPath(r, c);
                        }
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
                finishSelection();
                break;
        }
        invalidate(); //her hareker ekran çixme
        return true;
    }

    private void addTileToPath(int r, int c) {
        tiles[r][c].setSelected(true);
        selectedPath.add(tiles[r][c]);  //letter tile nesnesi
    }

    private boolean isNeighbor(LetterTile last, int r, int c) {

        int rowDiff = Math.abs(last.getRow() - r);
        int colDiff = Math.abs(last.getCol() - c);
        return (rowDiff <= 1 && colDiff <= 1) && !(rowDiff == 0 && colDiff == 0);
    }

    private void startSelection(int r, int c) {  }
    private void continueSelection(int r, int c) {  }

    private void finishSelection() {
        if (selectedPath.size() >= 2) {
            StringBuilder sb = new StringBuilder(); //nesne oluşturma string değil kelime oluşturur
            for (LetterTile tile : selectedPath) {
                sb.append(tile.getLetter());
            }

            if (getContext() instanceof GameActivity) {

                ((GameActivity) getContext()).onWordSelected(sb.toString(), new ArrayList<>(selectedPath));
            }
        }

        // seçm sıfırla
        resetTileSelection();
        selectedPath.clear();
        invalidate();
    }

    private void resetTileSelection() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (tiles[i][j] != null) tiles[i][j].setSelected(false);
            }
        }
    }
}