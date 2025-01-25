import java.util.Scanner;

public class Piece {
    // Create Instance Variables
    char character;
    int row;
    int col;
    boolean isBlack;
    public Piece(char character, int row, int col, boolean isBlack) { // constructor
        this.character = character;
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    public char getCharacter() {
        return this.character;
    } // getter method
    public void setPosition(int row, int col) { // setter method
        this.row = row;
        this.col = col;

    }
    public boolean getIsBlack() {
        return isBlack;
    } // getter method
    public boolean isMoveLegal(Board board, int endRow, int endCol) { // uses switch to determine which unicode is the one of the piece being selected
        switch (this.character) {
            case '\u2659':
            case '\u265f':
                Pawn pawn = new Pawn(row, col, isBlack);
                if(pawn.isMoveLegal(board, endRow, endCol)) {
                    promotePawn(endRow, isBlack);
                    return true;
                }
            case '\u2656':
            case '\u265c':
                Rook rook = new Rook(row, col, isBlack);
                return rook.isMoveLegal(board, endRow, endCol);
            case '\u265e':
            case '\u2658':
                Knight knight = new Knight(row, col, isBlack);
                return knight.isMoveLegal(board, endRow, endCol);
            case '\u265d':
            case '\u2657':
                Bishop bishop = new Bishop(row, col, isBlack);
                return bishop.isMoveLegal(board, endRow, endCol);
            case '\u265b':
            case '\u2655':
                Queen queen = new Queen(row, col, isBlack);
                return queen.isMoveLegal(board, endRow, endCol);
            case '\u265a':
            case '\u2654':
                King king = new King(row, col, isBlack);
                return king.isMoveLegal(board, endRow, endCol);
            default:
                System.out.println("Move impossible, try again.");
                return false;
        }
    }
    public void promotePawn(int row, boolean isBlack) {
        if (row == 0 || row == 7) { // checks if pawn is in the last row or not of each respective color
            Scanner myScanner = new Scanner(System.in); // asks user what they want pawn to be
            System.out.println("What would you like your pawn to become? (Options: Rook, Knight, Bishop, Queen)");
            String type = myScanner.nextLine();
            if (type.equals("Queen") || type.equals("queen")) { // checks each input with the unicoe value character of it based on if its black or not.
                if (this.isBlack) { // If case represents chess color is black, but not needed for game player to play game so simplified in code.
                    this.character = '\u265b';
                } else {
                    this.character = '\u2655';
                }
            } else if (type.equals("Rook") || type.equals("rook")) {
                if (this.isBlack) {
                    this.character = '\u265c';
                } else {
                    this.character = '\u2656';
                }
            } else if (type.equals("Bishop") || type.equals("bishop")) {
                if (this.isBlack) {
                    this.character = '\u265d';
                } else {
                    this.character = '\u2657';
                }
            } else if (type.equals("Knight") || type.equals("knight")) {
                if (this.isBlack) {
                    this.character = '\u265e';
                } else {
                    this.character = '\u2658';
                }
            }

        }
    }
    public String toString() {
        return "" + this.character; // to string of character or chess piece in this case
    }


}
