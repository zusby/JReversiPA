package it.cs.unicam.pa2022.JReversiPA.Model;

import it.cs.unicam.pa2022.JChessBoardPA.*;
import it.cs.unicam.pa2022.JReversiPA.controller.Input;
import it.cs.unicam.pa2022.JReversiPA.view.Printer;

import java.util.ArrayList;
import java.util.List;


/**
 * Simple implementation of the "Reversi" or "Othello" game build using the JChessboard library.
 */
public class Game {
    protected final DefaultChessBoard ChessBoard = new DefaultChessBoard();
    private DefaultColors turnColor = DefaultColors.BLACK;
    private int whiteScore, blackScore;
    private Printer printer = new Printer(ChessBoard);
    private Input input = new Input();
    private boolean isGameRunning = true;

    public Game() {
        ChessBoard.addPiece(new Piece(new Coordinate("d", 5), DefaultColors.BLACK));
        ChessBoard.addPiece(new Piece(new Coordinate("d", 4), DefaultColors.WHITE));
        ChessBoard.addPiece(new Piece(new Coordinate("e", 5), DefaultColors.WHITE));
        ChessBoard.addPiece(new Piece(new Coordinate("e", 4), DefaultColors.BLACK));
    }

    /**
     * Method used to start a game
     */
    public void start() {
        updateScoreBoard();
        printer.printBoard();
        System.out.println("Black starts first.\nExample command: e6");
        while (gameIsRunning()) {
            makeMove();
        }
        endGame();
    }


    /**
     * If the game is running, set it to false and print out the winner
     */
    private void endGame() {
        this.isGameRunning = false;
        if (whiteScore == blackScore) {
            System.out.println("It's a Draw!!");
        } else if (whiteScore > blackScore) {
            System.out.println("White wins!");
        } else {
            System.out.println("Black wins!");
        }
    }

    /**
     * The function takes in a coordinate and a color, and if the coordinate is a valid move, it will update the board and
     * the score
     */
    private void makeMove() {
        System.out.print(turnColor + " make your move: ");
        Coordinate newPos = new Coordinate(input.move());
        Piece newPiece = new Piece(newPos, turnColor);
        manageTurn();
        List<Coordinate> paths = getValidMovePaths(newPos, turnColor);
        if (!paths.isEmpty()) {
            for (Coordinate s : paths) {
                ChessBoard.removePiece(s);
                ChessBoard.addPiece(new Piece(s, turnColor));
            }
            ChessBoard.addPiece(newPiece);
            updateScoreBoard();
            printer.printBoard();
            changeTurn();
        } else
            System.out.println("Invalid Coordinate");
    }

    /**
     * This function returns a list of valid move paths for a given piece at a given coordinate on a given board
     *
     * @param coord The coordinate of the piece you want to move.
     * @param color The color of the piece
     * @return A list of coordinates that are valid moves for the piece.
     */
    private List<Coordinate> getValidMovePaths(Coordinate coord, DefaultColors color) {
        List<Coordinate> paths = new ArrayList<>();
        if (ChessBoard.getPieceAt(coord) != null) {
            return paths;
        }
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0) {
                    continue;
                }
                int newX = coord.getxInt() + x;
                int newY = coord.getY() + y;
                if (newY < 1 || newY > 8 || newX < 1 || newX > 8) {
                    continue;
                }
                Coordinate newCoord = new Coordinate(newX, newY);
                Piece piece = ChessBoard.getPieceAt(newCoord);
                if (piece != null && piece.getColor() != color) {
                    paths.addAll(checkDirection(coord, new Coordinate(x, y)));
                }
            }
        }
        return paths;
    }

    /**
     * Given a direction taken from @getValidMovePaths checks if there is an ally piece at the end of it
     * and if so, flip all the enemy pieces.
     *
     * @param from      starting position
     * @param direction direction taken
     * @return a list of elements to flip
     */
    private List<Coordinate> checkDirection(Coordinate from, Coordinate direction) {
        List<Coordinate> path = new ArrayList<>();
        int dirX = from.getxInt() + direction.getxInt();
        int dirY = from.getY() + direction.getY();
        if (dirY < 1 || dirY > 8 || dirX < 1 || dirX > 8)
            path = new ArrayList<>();
        Piece temp = ChessBoard.getPieceAt(new Coordinate(dirX, dirY));
        while (temp != null) {
            if (temp.getColor() == turnColor) {
                return path;
            }
            path.add(temp.getPos());
            dirX += direction.getxInt();
            dirY += direction.getY();
            temp = ChessBoard.getPieceAt(new Coordinate(dirX, dirY));
        }
        return new ArrayList<>();
    }

    /**
     * For each coordinate on the board, get all the valid moves for that coordinate and add them to the list of possible
     * moves.
     *
     * @param turnColor The color of the player whose turn it is.
     * @return A list of all possible moves for the current player.
     */
    private List<Coordinate> getAllPossibleMoves(DefaultColors turnColor) {
        List<Coordinate> possibleMoves = new ArrayList<>();
        for (int x = 1; x <= 8; x++) {
            for (int y = 1; y <= 8; y++) {
                possibleMoves.addAll(getValidMovePaths(new Coordinate(x, y), turnColor));
            }
        }
        return possibleMoves;
    }

    /**
     * If there are no possible moves for either player, the game is over. If there are no possible moves for the current
     * player, the turn is skipped
     */
    private void manageTurn() {
        List<Coordinate> possibleBlackMoves = getAllPossibleMoves(DefaultColors.BLACK);
        List<Coordinate> possibleWhiteMoves = getAllPossibleMoves(DefaultColors.WHITE);
        if (possibleWhiteMoves.isEmpty() && possibleBlackMoves.isEmpty()) {
            this.isGameRunning = false;
        }
        if (getAllPossibleMoves(turnColor).isEmpty()) {
            System.out.println("Skipping turn due to no moves available");
            changeTurn();
            if (getAllPossibleMoves(turnColor).isEmpty()) {
                System.out.println("No more moves available for both players!");
            }
        }
    }

    /**
     * If the turn color is white, change it to black, otherwise change it to white
     */
    private void changeTurn() {
        if (turnColor == DefaultColors.WHITE) {
            turnColor = DefaultColors.BLACK;
        } else {
            turnColor = DefaultColors.WHITE;
        }
    }


    /**
     * @return true if game is still running, false otherwise
     */
    private boolean gameIsRunning() {
        if (ChessBoard.getSize() == 64 || blackScore == 0 || whiteScore == 0) {
            isGameRunning = false;
        }
        return isGameRunning;
    }

    /**
     * Method to update the scoreboard
     */
    private void updateScoreBoard() {
        updateScore();
        System.out.println("White: " + this.whiteScore + "\nBlack: " + this.blackScore);
        ;
    }

    /**
     * It counts the number of pieces on the board and updates the score
     */
    private void updateScore() {
        int white = 0;
        int black = 0;
        for (Piece s : ChessBoard.getAllPieces()) {
            if (s.getColor() == DefaultColors.WHITE) {
                white++;
            } else
                black++;
        }
        whiteScore = white;
        blackScore = black;
    }
}


