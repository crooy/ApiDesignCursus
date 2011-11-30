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
    
    public final String toString(){
        return Sign();
    }
    
    public boolean equals(Object other){
        if (other == null) return false;
        
        boolean sameClass = other.getClass().equals(this.getClass());
        if (!sameClass) return false;
        
        Currency c = (Currency)other;
        return ( c.name.equals(this.name));
    }
   
    
}
