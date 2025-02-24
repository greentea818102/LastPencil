package lastpencil;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Players {
    private final Scanner sc;
    private final String n1;
    private final String n2;
    private int remainingPencils;

    public Players ( Scanner sc , String n1 , String n2 ) {
        this.sc = sc;
        this.n1 = n1;
        this.n2 = n2;
    }

    public void game () {
        System.out.println ( "How many pencils would you like to use:" );
        remainingPencils = validPencils ();
        System.out.printf ( "Who will be the first (%s, %s):%n" , n1 , n2 );
        String currentPlayer = validName ();
        while (remainingPencils > 0) {
            System.out.println ( "|".repeat ( remainingPencils ) );
            System.out.println ( currentPlayer + "'s turn" );
            int takenPencils = currentPlayer.equals(n1)?validMoveForPlayer ():validMoveForBot ();

            if (currentPlayer.equals ( n2 )) {
                System.out.println ( takenPencils );
            }
                remainingPencils -= takenPencils;
                if (remainingPencils == 0) {
                    System.out.println ( (currentPlayer.equals(n1) ? n2 : n1)  + " won!" );
                    break;
                }
            currentPlayer = currentPlayer.equals(n1) ? n2 : n1;

        }
    }

    public int validPencils () {
        while (true) {
            String input = sc.nextLine ().trim();
            if (input.isEmpty () || !input.matches ( "\\d+" )) {
                System.out.println ( "The number of pencils should be numeric" );
            } else  {
                int pencils = Integer.parseInt ( input );
                if(pencils > 0) {
                    return pencils;
                }
                else if ( pencils == 0) {
                    System.out.println ( "The number of pencils should be positive" );
                }
                else {
                    System.out.println ( "The number of pencils should be numeric" );
                }
                }
        }
}
    public String validName(){

        while(true) {
            String input = sc.nextLine ().trim ();
            if (input.equals (n1) || input.equals (n2)) {
                return input;
            }
            else {
                System.out.printf ( "Choose between %s and %s %n", n1, n2 );
            }
        }
    }
    public int validMoveForPlayer() {
        while (true) {
            String input = sc.nextLine ().trim ();
            if (input.matches ("[1-3]")) {
                int move = Integer.parseInt(input);
                if(move<= remainingPencils) {
                    return move;
                }
                else{
                    System.out.println ( "Too many pencils were taken" );
                }
            }
            else {
                System.out.println ( "Possible values: '1', '2' or '3'" );

            }
        }
    }
    public int validMoveForBot(){

        int randomMove = new Random().nextInt (1,4);
        if (remainingPencils == 1) {
            return 1;
        }
        if(remainingPencils % 4 == 1) {
            return randomMove;
        }
        else {
            int pencilsToTake = (remainingPencils -1)%4;
            return pencilsToTake == 0? randomMove : pencilsToTake;
        }
    }
    }
