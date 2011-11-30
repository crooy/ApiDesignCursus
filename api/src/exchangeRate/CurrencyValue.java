/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangeRate;

/**
 *
 * @author ronald
 */
public class CurrencyValue {
    private final Double value;
    private final Currency currency;
    
    public CurrencyValue(Currency currency, Double value){
        this.value = value;
        this.currency = currency;
    }
    
    public final Double getValue(){
        return value;
    }
    
    public final Currency getCurrency(){
        return currency;
    }
    
    @Override
    public final String toString(){
        return value.toString() + currency.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CurrencyValue other = (CurrencyValue) obj;
        if (this.value != other.getValue() && (this.value == null || !this.value.equals(other.getValue()))) {
            return false;
        }
        if (this.currency != other.getCurrency() && (this.currency == null || !this.currency.equals(other.getCurrency()))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + (this.value != null ? this.value.hashCode() : 0);
        hash = 73 * hash + (this.currency != null ? this.currency.hashCode() : 0);
        return hash;
    }
    
    
    
}
