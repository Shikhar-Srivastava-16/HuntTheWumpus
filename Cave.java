public class Cave {
    
    //attributes
    public int caveNum;
    public boolean hasSuperBat;
    public boolean hasArrow;
    public boolean hasPit;
    
    //Getters
    public boolean hasSuperBat() {
        return hasSuperBat;
    }
    
    public boolean hasArrow() {
        return hasArrow;
    }
    
    public boolean hasPit() {
        return hasPit;
    } 
    
    //Constructors
    public Cave(int caveNum) {
        
        this.caveNum = caveNum;
        this.hasSuperBat = false;
        this.hasArrow = false;
        this.hasPit = false;

    }

    
}
