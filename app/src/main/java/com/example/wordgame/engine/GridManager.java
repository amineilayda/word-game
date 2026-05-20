package com.example.wordgame.engine;

import com.example.wordgame.model.LetterTile;

import java.util.ArrayList;
import java.util.Random;

public class GridManager {
    private int rows, cols;
    private String[][] grid;
    private Random random = new Random();

//oyun tahtası üretme
    private final String HIGH_FREQ = "AEİLRN";
    private final String MED_FREQ = "KMTSYD";
    private final String LOW_FREQ = "JĞFVZPĞÜŞÖÇ";

    public GridManager(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new String[rows][cols];
    }

    // harf ürt
    public String generateRandomLetter() {
        int chance = random.nextInt(100);
        if (chance < 60) {
            return String.valueOf(HIGH_FREQ.charAt(random.nextInt(HIGH_FREQ.length())));
        } else if (chance < 90) {
            return String.valueOf(MED_FREQ.charAt(random.nextInt(MED_FREQ.length())));
        } else {
            return String.valueOf(LOW_FREQ.charAt(random.nextInt(LOW_FREQ.length())));
        }
    }



    public void initializeGrid() {
        do {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    grid[i][j] = generateRandomLetter();
                }
            }
        } while (!hasValidWordOnGrid());


        if (cols >= 8) {
            grid[3][4] = "E";
            grid[3][5] = "L";
            grid[3][6] = "E";
            grid[3][7] = "K";
        }

        if (cols >= 3) {
            grid[1][0] = "E";
            grid[1][1] = "B";
            grid[1][2] = "E";
        }

        grid[0][0] = "A";
        grid[0][1] = "D";
        grid[0][2] = "A";
    }

    private boolean hasValidWordOnGrid() {

        return true;
    }

    //letter tile objesine çevirir
    public LetterTile[][] getLetterTiles() {
        LetterTile[][] tiles = new LetterTile[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                tiles[i][j] = new LetterTile(grid[i][j], i, j, 1);
            }
        }
        return tiles;
    }


    //test
    public void applyGravity() {
        for (int j = 0; j < cols; j++) {
            int emptyRow = rows - 1;
            for (int i = rows - 1; i >= 0; i--) {
                if (grid[i][j] != null) {
                    String temp = grid[i][j];
                    grid[i][j] = null;
                    grid[emptyRow][j] = temp;
                    emptyRow--;
                }
            }

            for (int i = emptyRow; i >= 0; i--) {
                grid[i][j] = generateRandomLetter();
            }
        }
    }


    public void applyGravityAndFill() {
        for (int j = 0; j < cols; j++) {
            int emptySpot = rows - 1; // en alt satırdan başlar

            for (int i = rows - 1; i >= 0; i--) {
                if (grid[i][j] != null) {
                    String temp = grid[i][j];
                    grid[i][j] = null;
                    grid[emptySpot][j] = temp;
                    emptySpot--; //yukarı geç
                }
            }

            for (int i = emptySpot; i >= 0; i--) {
                grid[i][j] = generateRandomLetter();
            }
        }
    }


    public void clearCells(ArrayList<LetterTile> selectedPath) {
        for (LetterTile tile : selectedPath) {
            grid[tile.getRow()][tile.getCol()] = null; // sil
        }
    }
}