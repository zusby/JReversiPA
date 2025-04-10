
package it.cs.unicam.pa2022.JReversiPA.controller;

import java.util.Scanner;

/**
 * Class that manages System.in input and filters it in order to get a proper coordinate.
 */
public class Input {
    Scanner sc = new Scanner(System.in);

    /**
     * The function takes in a move, checks if it's valid, and if it's not, it calls itself again
     *
     * @return a valid move
     */
    public String move(){
        String move = sc.next();
        if(move.length()!=2||!isCharValid(move)||!isIntValid(move)){
            System.out.println("\n"+move+" Is an invalid Coordinate");
            return move();
        }
        return move;
    }

    /**
     * It returns true if the first character of the string is between 'a' and 'h' inclusive, and false otherwise
     *
     * @param move the 'move' that the user wants to make
     * @return The method isCharValid is returning a boolean value.
     */
    private boolean isCharValid(String move){
        for(char a='a'; a<='h';a++){
            if(move.charAt(0)==a){
                return true;
            }
        }
        return false;
    }
    /**
     * This method checks if the integer part of the move is valid
     *
     * @param move the move that the user wants to make
     * @return The method is returning a boolean value.
     */
    private boolean isIntValid(String move){
        if(Integer.parseInt(move.substring(1))>8||Integer.parseInt(move.substring(1))<1){
            return false;
        }
        return true;
    }
}
