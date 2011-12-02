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
public class ExchangeRateImpl implements ExchangeRate {
    
    private static final double max = 16;
    private static final double min = 15;
    
    private double curr = max;
    private double step = -0.01;
    
    @Override
    public BigDecimal get() {
        
        double result = curr;
        
        // Update for next time.
        {
            double next = curr + step;

            if(next <= min || next >= max) step *= -1;

            curr = next;
        }
        
        return new BigDecimal(result);
    }    
}
