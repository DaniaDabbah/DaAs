package algorithms.mazeGenerators;

public class Position {
    private int RowIndex;
    private int ColIndex;

    public Position(int row, int col) {
        this.ColIndex = col;
        this.RowIndex = row;
    }

    public int getColumnIndex() {
        return ColIndex;
    }

    public int getRowIndex() {
        return RowIndex;
    }

    public String toString(){
        String ret = "{" + this.RowIndex + "," + this.ColIndex + "}";
        return ret;
    }
    public int Compare(Position other){
        if (this.getColumnIndex() == other.getColumnIndex() && this.getRowIndex() == other.getRowIndex()){
            return 0;
        }
        return 1;
    }

    public void setRowIndex(int rowIndex) {
        RowIndex = rowIndex;
    }

    public void setColIndex(int colIndex) {
        ColIndex = colIndex;
    }

}
