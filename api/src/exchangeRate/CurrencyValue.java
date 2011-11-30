/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangeRate;

import java.math.BigDecimal;

/**
 *
 * @author ronald
 */
public class CurrencyValue {
    private final BigDecimal value;
    private final Currency currency;
    
    public CurrencyValue(Currency currency, BigDecimal value) throws CurrencyValueException{
        if (value == null) throw new CurrencyValueException("value cannot be null");
        if (currency == null) throw new CurrencyValueException("currency cannot be null");
        if (value.compareTo(BigDecimal.ZERO) < 0) throw new CurrencyValueException("value must be greater than zero");
        
        this.value = value;
        this.currency = currency;
    }
    
    public final BigDecimal getValue(){
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
