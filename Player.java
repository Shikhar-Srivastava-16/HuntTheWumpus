public class Player {
    
    //attributes
    private int caveNum;
    private int qtyArrows;

    //getters and setters
    public int getCaveNum() {
        return caveNum;
    }

    public void setCaveNum(int caveNum) {
        if (caveNum <= 20 || caveNum >= 1) {
            this.caveNum = caveNum;
        }
    }

    public int getQtyArrows() {
        return qtyArrows;
    }
    
    public void setQtyArrows(int qtyArrows) {
        this.qtyArrows = qtyArrows;
    }

    //constructor
    public Player(int caveNum, int qtyArrows) {
        this.caveNum = caveNum;
        this.qtyArrows = qtyArrows;
    }
}
