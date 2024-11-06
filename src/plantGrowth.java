public class plantGrowth {
    private int count = 0;
    private int numTile;
    private int col, row;
    TileManager tileM;

    public plantGrowth(TileManager tileM, int numTile, int x, int y) {
        this.tileM = tileM;
        this.numTile = numTile;
        this.col = x;
        this.row = y;
    }

    public void setNumTile(int numTile) {
        this.numTile = numTile;
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

    public void growth() {
        count++;
        
        if (numTile == 10) {
            if (count > 2500) {
                numTile = 0;
                tileM.updateGrowth(numTile, this.col, this.row);
            }
        }

        if (numTile == 11) {
            if (count > 1000) {
                numTile = 12;
                tileM.updateGrowth(numTile, this.col, this.row);
                count = 0;
            }
        } 
        if (numTile == 12) {
            if (count > 3000) {
                numTile = 13;
                tileM.updateGrowth(numTile, this.col, this.row);
                count = 0;
            }
        } 
        if (this.numTile == 13) {
            if (count > 8000) {
                numTile = 14;
                tileM.updateGrowth(numTile, this.col, this.row);
                count = 0;
            }
        } 
        if (this.numTile == 14) {
            if (count > 20000) {
                numTile = 15;
                tileM.updateGrowth(numTile, this.col, this.row);
                count = 0;
            }
        }
    }
}
