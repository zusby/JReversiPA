package it.cs.unicam.pa2022.JChessBoardPA;

/**
 * A piece that has a position and eventually a rank
 */
public  class Piece{

    private DefaultColors color;
    private Coordinate pos;
    private int rank;


    public Piece(Coordinate pos, DefaultColors color) {
        this.pos=pos;
        this.color= color;
        this.rank=1;
    }

    public Piece(Coordinate pos, DefaultColors color,int rank) {
        this.pos = pos;
        this.color = color;
        this.rank = rank;
    }

    /**
     *  This function returns the color of the current object
     *
     * @return The color of the piece.
     */
    public  DefaultColors getColor() {
        return color;
    }

    /**
     * This function sets the color of the object to the color passed in.
     *
     * @param color The color of the text.
     */
    public void setColor(DefaultColors color) {
        this.color = color;
    }
    /**
     * This function returns the position of the object.
     *
     * @return The position of the object.
     */
    public Coordinate getPos() {
        return pos;
    }
    /**
     * This function sets the rank of the piece to the value of the parameter rank.
     *
     * @param rank The rank of the piece.
     */
    public void setRank(int rank) {
        this.rank=rank;
    }
    /**
     * This function returns the rank of the piece.
     *
     * @return The rank of the piece.
     */
    public int getRank(){
        return this.rank;
    }
}