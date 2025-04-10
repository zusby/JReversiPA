package it.cs.unicam.pa2022.JChessBoardPA;


/**
 * Default enum to flag the color of something white or black.
 */
public enum DefaultColors {
    WHITE('W'), BLACK('B');

    private char symbol;

    DefaultColors(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}


