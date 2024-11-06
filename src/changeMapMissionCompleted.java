public class changeMapMissionCompleted {
    private int count = 0;
    private int numTile;
    private int col, row;
    TileManager tileM;

    public changeMapMissionCompleted(TileManager tileM, int numTile, int x, int y) {
        this.tileM = tileM;
        this.numTile = numTile;
        this.col = x;
        this.row = y;
    }

    public int getCol() {
        return this.col;
    }

    public int getRow() {
        return this.row;
    }
    
    public int getTimeM() {
        return this.numTile;
    }

    public void changeMap() {
        count++;

        if (numTile == 7) {
            if (count >= 3000) {
                tileM.updateGrowth(6, this.col, this.row);
                count = 0;
            }
        }
        if (numTile == 8) {
            if (count >= 1500) {
                tileM.updateGrowth(0, this.count, this.col);
                count = 0;
            }
        }
        if (numTile == 9) {
            if (count >= 15000) {
                tileM.updateGrowth(5, this.col, this.row);
                count = 0;
            }
        }
    }
}
