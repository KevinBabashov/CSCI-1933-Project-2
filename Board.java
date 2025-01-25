public class Board {

    // Instance variables
    private Piece[][] board;


    public Board() {
        this.board = new Piece[8][8]; // Instantiates a board in the constructor of the board class

    }

    // Accessor Methods


    public Piece getPiece(int row, int col) {
        return board[row][col];
    } // typical getter and setter methods


    public void setPiece(int row, int col, Piece piece) {
        this.board[row][col] = piece;

    }

    // Game functionality methods

    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        {
            Piece startPiece = board[startRow][startCol];
            char character = startPiece.getCharacter();
            boolean color = startPiece.getIsBlack();
            startPiece = new Piece(character, startRow, startCol, color);
            if (verifySourceAndDestination(startRow, startCol, endRow, endCol, color)) { //Verify here confirms that basic functions of the board are met.
                if (startPiece.isMoveLegal(this, endRow, endCol)) { // is move legal checks if the specific type of piece's move is legal or not.
                    setPiece(endRow, endCol, startPiece);
                    board[startRow][startCol] = null;
                    return true; // Performs necessary calls and steps to ensure that a piece is moved.
                }
            }
            return false;
        }
    }
    public boolean isGameOver() {
        boolean blackKing = false; // assume that both kings are captured
        boolean whiteKing = false;
        for (int i = 0; i < board.length; i++) { //iterates through the whole board and checks if both kings are alive
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != null) {
                    Piece testPiece = getPiece(i, j);
                    if (testPiece.character == '\u265a' && testPiece.isBlack) {
                        blackKing = true; // if they are they are set as true
                    } else if (testPiece.character == '\u2654' && !testPiece.isBlack) {
                        whiteKing = true;
                    }

                }
            }
        }
        if (blackKing == false) {
            System.out.println("White won the game"); // checks if black king is dead and if so then white won
            return false;
        } else if (whiteKing == false) {
            System.out.println("Black won the game"); // checks if white king is dead and if so the black wins as well as returning false to break the game while loop
        return false;
    }
        return true; // returns true if neither black king or white king are dead
        }

    // Constructs a String that represents the Board object's 2D array.
    // Returns the fully constructed String.
    public String toString() { // creates the board and is called each time the board is called.
        StringBuilder out = new StringBuilder();
        out.append(" ");
        for (int i = 0; i < 8; i++) {
            out.append("\u2003");
            out.append(i);
        }
        out.append('\n');
        for (int i = 0; i < board.length; i++) {
            out.append(i);
            out.append("|");
            for (int j = 0; j < board[0].length; j++) {
                out.append(board[i][j] == null ? "\u2001|" : board[i][j] + "|");
            }
            out.append("\n");
        }
        return out.toString();
    }
    public void clear() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) { // sets each value equal to null via for loop and manually making each null.
                board[i][j] = null;
            }
        }
    }

    // Movement helper functions

    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        if ((startRow < 0) || (startRow >= 8) || (startCol < 0) || (startCol >= 8) || (endRow < 0) || (endCol >= 8) || (endCol < 0) || (endRow >= 8)) { // checks each value if they are out of bounds
            return false; }
        else if (board[startRow][startCol] == null) { // checks if start location has no piece if so returns false
            return false; }
        else if (isBlack != board[startRow][startCol].getIsBlack()) { // checks if players color and start piece match
            return false; }
        else if ((startRow == endRow) && (startCol == endCol)) { // checks if player selected same spot as move, if so then it returns false and tells them thats invalid.
            System.out.println("You are staying in the same spot, this is not allowed.");
            return false; }
        else if (board[endRow][endCol] != null && board[endRow][endCol].getIsBlack() == isBlack) { // if the end location has a piece but it is of the same color as the player, then it states they cannot capture their own piece
            System.out.println("You are trying to capture a piece of the same color, try again.");
            return false; }
        else // if passes all conditionals then the source and destination is valid.
            return true;
    }

    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        if (Math.abs(startRow - endRow) <= 1 && Math.abs(startCol - endCol) <= 1) { // checks if values only change by at most one each with absolute value meaning that it covers all values around one spot.
            return true;
        }

        return false;
    }

    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        int minColumn = Math.min(startCol, endCol);
        int maxColumn = Math.max(startCol, endCol);
        if (startRow != endRow){ // checks if moving vertically
            return false;
        }

        for (int col = minColumn + 1 ; col < maxColumn; col++) { // if moving horizontally then it iterates through small to large and checks if each value between is null
            if (board[endRow][col] != null) {
                return false;
            }
        }

        return true;
    }
    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        int minRow = Math.min(startRow, endRow);
        int maxRow = Math.max(startRow, endRow);
        if (startCol != endCol) { // checks if moving horizontally if so then returns false
            return false;
        }

        for (int row = minRow + 1; row < maxRow; row++) { // if not moving horizontally then checks each value in the path of the two rows and sees if they are null between
            if (board[row][startCol] != null) {
                return false;
            }
        }

        return true;
    }

    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        int rowDifference = Math.abs(startRow - endRow);
        int colDifference = Math.abs(startCol - endCol);
        if (rowDifference != colDifference) { // in order for a move to be diagonal then both the row and column have to be equal. so it checks if they are and if not then returns false.
            return false;
        }

        int rowStep = (startRow < endRow) ? 1 : -1; // condensed if else statement in which 1 is when value is true and -1 is when value is false, simply accounting for both directions
        int colStep = (startCol < endCol) ? 1 : -1;// same here.
        for (int i = 1; i < rowDifference; i++) {
            int row = startRow + i * rowStep; // since row and column are equivalent in change we can use one for loop to account for change in row and column step and then check each value of that with simple conditional.
            int col = startCol + i * colStep;
            if (board[row][col] != null) {
                return false;
            }
        }

        return true;
    }

}




