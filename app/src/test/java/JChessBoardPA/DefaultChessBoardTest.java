package JChessBoardPA;


import it.cs.unicam.pa2022.JChessBoardPA.*;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;


import java.util.List;


public class DefaultChessBoardTest extends TestCase {
    DefaultChessBoard chessboard = new DefaultChessBoard();

    @org.junit.Test
    public void testAddPiece() {
        Piece piece = new Piece(new Coordinate(1, 1), DefaultColors.WHITE);
        chessboard.addPiece(piece);
        Piece actual = chessboard.getPieceAt(new Coordinate(1, 1));
        assertEquals(piece, actual);
    }

    @Test
    public void testRemovePiece() {
        Piece piece = new Piece(new Coordinate(2, 2),DefaultColors.WHITE);
        chessboard.addPiece(piece);
        chessboard.removePiece(new Coordinate(2, 2));
        Piece actual = chessboard.getPieceAt(new Coordinate(2, 2));
        assertNull(actual);
    }

    @Test
    public void testGetPieceAt() {
        Piece piece = new Piece(new Coordinate(3, 3),DefaultColors.WHITE);
        chessboard.addPiece(piece);
        Piece actual = chessboard.getPieceAt(new Coordinate(3, 3));
        assertEquals(piece, actual);
    }

    @Test
    public void testGetAllPieces() {
        Piece piece1 = new Piece(new Coordinate(4, 4),DefaultColors.WHITE);
        Piece piece2 = new Piece(new Coordinate(5, 5),DefaultColors.WHITE);
        chessboard.addPiece(piece1);
        chessboard.addPiece(piece2);
        List<Piece> actual = chessboard.getAllPieces();
        assertTrue(actual.contains(piece1));
        assertTrue(actual.contains(piece2));
    }

}