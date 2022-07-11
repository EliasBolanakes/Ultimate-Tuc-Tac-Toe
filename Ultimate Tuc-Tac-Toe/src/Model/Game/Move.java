package Model.Game;

public class Move {

    private int Row;
    private int Col;
    private Double moveScore;
    private Integer depth;

    public Move(int Row, int Col){

        this.Row=Row;
        this.Col=Col;
        depth = 0;
    }

    public int getRow() {
        return Row;
    }

    public void setRow(int row) {
        Row = row;
    }

    public int getCol() {
        return Col;
    }

    public void setCol(int col) {
        Col = col;
    }

    public Double getMoveScore() {
        return moveScore;
    }

    public void setMoveScore(Double moveScore) {
        this.moveScore = moveScore;
    }

    public Integer getDepth() {
        return depth;
    }

    public void incrementDepth(){
        this.depth++;
    }
}
