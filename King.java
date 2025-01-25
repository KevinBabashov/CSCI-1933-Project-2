public class King { // instance variables
    int row;
    int col;
    boolean isBlack;
    public King(int row, int col, boolean isBlack) { // constructor
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        return board.verifyAdjacent(this.row, this.col, endRow, endCol) && board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack); // checks if adjacent and verifies source and destination
    }
}