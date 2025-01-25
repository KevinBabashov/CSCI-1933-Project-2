public class Queen {
    int row;
    int col;
    boolean isBlack;

    public Queen(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack)) { // checks if verify source and destination is true on piece
        return board.verifyDiagonal(this.row, this.col, endRow, endCol) || board.verifyHorizontal(this.row, this.col, endRow, endCol) || board.verifyVertical(this.row, this.col, endRow, endCol); // checks if it moved diagonally vertically or horizontally
    }
    return false;
}
}
