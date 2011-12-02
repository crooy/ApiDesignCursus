/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangeRate;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author user
 */
public class DefaultExchangeRate implements ExchangeRate {
   
    BigDecimal rate = null;
    
    DefaultExchangeRate(BigDecimal defaultRate) {
        rate = defaultRate;
    }
    
    @Override
    public BigDecimal get() {
        
        return rate;
    }    
}
