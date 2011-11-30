/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangeRate;

/**
 *
 * @author ronald
 */
public final class Currency {
    
    private final String name;
    private final String sign;
    
    public Currency(String name, String sign){
        this.name = name;
        this.sign = sign;
    }
    
    public final String Name(){
        return name;
    }
    
    public final String Sign(){
        return sign;
    }
    
    @Override
    public final String toString(){
        return Sign();
    }
    
    @Override
    public boolean equals(Object other){
        if (other == null) return false;
        if (this == other) return true;
        
        boolean sameClass = other.getClass().equals(this.getClass());
        if (!sameClass) return false;
        
        Currency c = (Currency)other;
        return ( c.name.equals(this.name));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }
   
    
}
