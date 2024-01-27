public class Cave {
    
    //attributes
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
    public Cave(boolean hasSuperBat, boolean hasArrow, boolean hasPit) {
        this.hasSuperBat = hasSuperBat;
        this.hasArrow = hasArrow;
        this.hasPit = hasPit;
    }

    
}
