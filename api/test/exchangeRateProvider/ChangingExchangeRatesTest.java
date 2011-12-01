/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangeRateProvider;

import exchangeRate.ExchangeRateCalculatorException;
import exchangeRate.Currency;

import exchangeRate.ExchangeRate;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author user
 */
public class ChangingExchangeRatesTest extends TestCase {
    
    private static final int ROUNDING_SCALE = 20;
    
    @Test
    public void testGetExchangeRate() throws ExchangeRateCalculatorException {
        ChangingExchangeRates exchangeRates = new ChangingExchangeRates();
        
        Currency usd = new Currency("USD");
        Currency czk = new Currency("CZK");
        
        ExchangeRate exchangeRate = exchangeRates.getExchangeRate(czk, usd);
        assertEquals(0, exchangeRate.getExchangeRate().compareTo(new BigDecimal(16)));
        
        exchangeRate = exchangeRates.getExchangeRate(czk, usd);
        assertEquals(0, exchangeRate.getExchangeRate().compareTo(new BigDecimal(16)));
        
        exchangeRate = exchangeRates.getExchangeRate(usd, czk);
        assertEquals(0, exchangeRate.getExchangeRate().compareTo(BigDecimal.ONE.divide(new BigDecimal(16), ROUNDING_SCALE, RoundingMode.HALF_EVEN)));
        
        exchangeRate = exchangeRates.getExchangeRate(usd, czk);
        BigDecimal expected = BigDecimal.ONE.divide(new BigDecimal(15.99), ROUNDING_SCALE, RoundingMode.HALF_EVEN);
        BigDecimal difference = exchangeRate.getExchangeRate().subtract(expected);
        BigDecimal epsilon = BigDecimal.ONE.divide(new BigDecimal(100000));
        assertEquals(-1, difference.abs().compareTo(epsilon));        
    }
    
}
