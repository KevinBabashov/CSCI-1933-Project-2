
import java.util.Scanner; // scanner import
public class Game {

    public static void main(String[] args) { // main method of game
        Board myBoard = new Board();
        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", myBoard); // initializes board
        System.out.println("Initial board:"); // prints initial board and the board
        System.out.print(myBoard);
        String turn = "whites"; // sets first turn as white
        while (myBoard.isGameOver()) { // makes while loop for turns until king is killed
            System.out.println("It is " + turn + " turn");
            System.out.println("Where would you like to move? Format: [StartRow,StartColumn,EndRow,EndColumn] (Use space between each)");
            Scanner input = new Scanner(System.in);
            String userInput = input.nextLine();
            String[] userArray = userInput.split(" "); // splits input by spaces
            int startRow = Integer.parseInt(userArray[0]);
            int startCol = Integer.parseInt(userArray[1]);
            int endRow = Integer.parseInt(userArray[2]);
            int endCol = Integer.parseInt(userArray[3]); // sets spaces to each int value associated with the row and column they are
            if ((startRow >= 0) && (startRow < 8) && (startCol >= 0) && (startCol < 8) && (endRow >= 0) && (endCol < 8) && (endCol >= 0) && (endRow < 8)) { // checks if points are within board beofre trying to make an instance of it.
                Piece piece = myBoard.getPiece(startRow, startCol);
                if (piece != null) { // checks if piece is null as there is no isblack to a null space
                    if (myBoard.verifySourceAndDestination(startRow, startCol, endRow, endCol, piece.getIsBlack())) {
                        if (piece.getIsBlack() == true && turn.equals("blacks")) { // checks if its black turn and if there color is black if so it lets black go
                            if (myBoard.movePiece(startRow, startCol, endRow, endCol)) {
                                turn = "whites"; // switches turn to white
                                System.out.println("Board:");
                                System.out.println(myBoard); // prints board
                            }
                            else {System.out.println("Invalid Movement, please try again");}
                        } else if (piece.getIsBlack() == false && turn.equals("whites")) { // if the is black is false meaning its white and if the turn is white then white turn goes
                            if (myBoard.movePiece(startRow, startCol, endRow, endCol)) {
                                turn = "blacks"; // switches to blacks turn
                                System.out.println("Board:");
                                System.out.println(myBoard); // prints board
                            }
                            else {System.out.println("Invalid Movement, please try again");} // if invalid movement then it will state here
                        }
                        else {System.out.println("You are moving the wrong color, please try again");} // if you try to move color that isn't yours
                    }
                }
                else {System.out.println("That is an empty space, try again.");} // if piece is null then it tells the user that its an empty space
            }
            else{System.out.println("Your input was not on the board, please try again.");} // input wasnt on board, else case of original if conditional 
            }
        }
    }