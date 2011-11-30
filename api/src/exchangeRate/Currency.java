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
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Currency other = (Currency) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }
    
  
   
    
}
