public class Knight { // instance variables
    int row;
    int col;
    boolean isBlack;

    public Knight(int row, int col, boolean isBlack) { // constructor
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
       if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack)) {
            if (Math.abs(this.row - endRow) == 2 && Math.abs(this.col - endCol) == 1) // checks if the L shape is going horizontally first
                return true;
            else if (Math.abs(this.row - endRow) == 1 && Math.abs(this.col - endCol) == 2) // checks all cases if the L shape is going vertically first.
                return true;
        }
        return false;
    }
}
