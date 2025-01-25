public class Rook { // instance variables
    int row;
    int col;
    boolean isBlack;

    public Rook(int row, int col, boolean isBlack) { // constructor method
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack)) { // checks if verify source and destination
            return board.verifyVertical(this.row, this.col, endRow, endCol) || board.verifyHorizontal(this.row, this.col, endRow, endCol); // checks if rook moved horizontal or vertical as it can't do both.
        }
        return false;
    }
}
