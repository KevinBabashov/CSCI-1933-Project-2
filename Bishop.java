public class Bishop { // instance variables
    int row;
    int col;
    boolean isBlack;
    public Bishop(int row, int col, boolean isBlack) { // constructor
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        return board.verifyDiagonal(this.row, this.col, endRow, endCol) && board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack); // checks verify and diagonal for board
    }
}
