package it.cs.unicam.pa2022.JChessBoardPA;

import java.util.List;

public interface Chessboard {

    /**
     * Adds a piece to the board.
     *
     * @param piece The piece to add to the board.
     */
    void addPiece(Piece piece);

    /**
     * Removes a piece from the board
     *
     * @param coord The coordinate of the piece you want to remove.
     */
    void removePiece(Coordinate coord);

    /**
     * Returns the piece at the given position, or null if there is no piece there.
     *
     * @param pos The position of the piece you want to get.
     * @return A Piece object.
     */
    Piece getPieceAt(Coordinate pos);


    /**
     * Get all the pieces on the board.
     * @return A list of all the pieces on the board.
     */
    List<Piece> getAllPieces();

}




