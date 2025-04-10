package it.cs.unicam.pa2022.JChessBoardPA;

import java.util.*;

public  class DefaultChessBoard implements Chessboard{
    private Map<String,Piece> board = new HashMap<>();

    @Override
    public  void removePiece(Coordinate pos){
        Objects.requireNonNull(pos);
        board.put(pos.toString(),null);
    }

    @Override
    public void addPiece(Piece piece){
        Objects.requireNonNull(piece);
        board.put(piece.getPos().toString(),piece);
    }

    @Override
    public Piece getPieceAt(Coordinate pos){
        Objects.requireNonNull(pos);
        return board.get(pos.toString());

    }
    @Override
    public List<Piece> getAllPieces(){
        List<Piece> list = new ArrayList<>();
        list.addAll(board.values());
        return list;
    }

    public int getSize(){
        return board.size();
    }
}
