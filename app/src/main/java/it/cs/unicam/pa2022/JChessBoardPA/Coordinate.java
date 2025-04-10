package it.cs.unicam.pa2022.JChessBoardPA;

import java.util.Objects;

/**
 * It's a class that keeps track of a coordinate
 * and converts chars to its integer value
 */
public class Coordinate {
    private char x;
    private int y,width;

    public Coordinate(String x, int y){
        Objects.requireNonNull(x);
        this.x=x.charAt(0);
        this.y=y;
        convertToNumber(x);
    }

    public Coordinate(int width, int y){
        this.width = width;
        this.y=y;
        convertToString();
    }

    public Coordinate(String x){
        Objects.requireNonNull(x);
        this.x=x.charAt(0);
        this.y= Integer.parseInt(x.substring(1));
        convertToNumber(x);
    }


    private void convertToNumber(String num){
        width = num.charAt(0)-96;
    }
    private void convertToString(){
        this.x= (char)(width+96);
    }

    public char getX() {
        return x;
    }

    public void setX(char x) {
        this.x = x;
    }
    public int getxInt(){
        return this.width;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return x+""+y;
    }
}
