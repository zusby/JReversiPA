package it.cs.unicam.pa2022.JReversiPA.view;


import it.cs.unicam.pa2022.JChessBoardPA.Coordinate;
import it.cs.unicam.pa2022.JChessBoardPA.DefaultChessBoard;
import it.cs.unicam.pa2022.JChessBoardPA.Piece;

/**
 * > The Printer is a class that prints the chessboard to the console
 */
public  class Printer {
    private final DefaultChessBoard chessBoard;



    public Printer(DefaultChessBoard chessboard){
        this.chessBoard = chessboard;
    }

    public void printBoard() {
        System.out.print("  ");
        for (int i = 1; i <= 8; i++) {
            System.out.print(" " + (char) ('a' + i - 1) + " ");
        }
        System.out.println();
        for (int col = 1; col <= 8; col++) {
            System.out.print(col + " ");
            for (int row = 1; row <= 8; row++) {
                Coordinate position = new Coordinate(row, col);
                Piece piece = chessBoard.getPieceAt(position);
                if (piece == null) {
                    System.out.print(" - ");
                } else {
                    System.out.print(" " + piece.getColor().getSymbol() + " ");
                }
            }
            System.out.println();
        }
    }
}
