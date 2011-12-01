/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangeRate;

import java.math.BigDecimal;
import java.util.*;
import java.util.HashSet;

/**
 *
 * @author ronald
 */
public final class Calculator {
    
    private Set<ExchangeRate> exchangeRates;
    private final int roundingScale = 20;
    
    // constructor has default visibility
    Calculator(Set<ExchangeRate> rates){
        this.exchangeRates = rates;
    }
    
    //default 
    Set<ExchangeRate> getExchangeRates(){
        return new HashSet<ExchangeRate>(exchangeRates);
    }
    
    public CurrencyValue convert(CurrencyValue from, Currency to) throws ExchangeRateCalculatorException {
        ExchangeRate requiredExchangeRate = findExchangeRate(from, to);
        
        // we're set, lets convert!
        BigDecimal newValue;
        newValue = from.getValue().divide(requiredExchangeRate.getExchangeRate(), roundingScale, BigDecimal.ROUND_HALF_EVEN)  ;
        
        return new CurrencyValue(to, newValue);
    }

    private ExchangeRate findExchangeRate(CurrencyValue from, exchangeRate.Currency to) throws CalculatorException {
        ExchangeRate requiredExchangeRate = null;
        for(ExchangeRate rate : exchangeRates){
            if (rate.canConvert((from.getCurrency()), to)){
                requiredExchangeRate = rate;
                break;
            }
        }
        if (requiredExchangeRate == null) throw new CalculatorException("no exchangerate found that can convert this");
        return requiredExchangeRate;
    }

    
}
