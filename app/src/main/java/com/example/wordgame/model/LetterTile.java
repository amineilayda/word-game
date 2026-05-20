package com.example.wordgame.model;

public class LetterTile {
    private String letter;
    private int row;
    private int col;
    private boolean isSelected;
    private int score;

    public LetterTile(String letter, int row, int col, int score) {
        this.letter = letter;
        this.row = row;
        this.col = col;

        this.score = score;
        this.isSelected = false;
    }

    public String getLetter() { return letter; }
    public boolean isSelected() { return isSelected; }
    public void setSelected(boolean selected) { isSelected = selected; }
    public int getRow() { return row; }
    public int getCol() { return col; }
}