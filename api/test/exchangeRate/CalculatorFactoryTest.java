/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangeRate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author user
 */
public class CalculatorFactoryTest extends TestCase {
    
    private static final int ROUNDING_SCALE = 50;
    
    private ICalculatorFactory factory;
    
    @Override
    protected void setUp() throws Exception {
        ExchangeRates exchangeRates = new ExchangeRates();
        exchangeRates.setExchangeRate(new ExchangeRate(BigDecimal.ONE.divide(new BigDecimal(17), ROUNDING_SCALE, RoundingMode.HALF_EVEN), new Currency("USD"), new Currency("CZK")));
        exchangeRates.setExchangeRate(new ExchangeRate(new BigDecimal(17), new Currency("CZK"), new Currency("USD")));
        exchangeRates.setExchangeRate(new ExchangeRate(new BigDecimal(100).divide(new BigDecimal(80)), new Currency("SKK"), new Currency("CZK")));
        exchangeRates.setExchangeRate(new ExchangeRate(new BigDecimal(80).divide(new BigDecimal(100)), new Currency("CZK"), new Currency("SKK")));

        factory = new CalculatorFactory(exchangeRates);
    }

    @Override
    protected void tearDown() throws Exception {
    }
    
    @Test
    public void testMergeExchangeRates() throws ExchangeRateCalculatorException {
      
        Currency usd = new Currency("USD");
        Currency czk = new Currency("CZK");
        Currency skk = new Currency("SKK");
        
        Calculator calc1 = factory.create(usd, czk);
        Calculator calc2 = factory.create(skk, czk);

        Calculator mergedCalculator = factory.create(calc1, calc2);
        
        CurrencyValue val1 = mergedCalculator.convert(new CurrencyValue(usd, new BigDecimal(12)), czk);
        CurrencyValue val2 = mergedCalculator.convert(new CurrencyValue(skk, new BigDecimal(100)), czk);
        CurrencyValue val3 = mergedCalculator.convert(new CurrencyValue(czk, new BigDecimal(1003)), usd);
        
        assertEquals(val1.getCurrency(), czk);
        assertEquals(0,val1.getValue().compareTo(new BigDecimal (204)));
        assertEquals(val2.getCurrency(), czk);
        assertEquals(0,val2.getValue().compareTo(new BigDecimal (80)));
        assertEquals(val3.getCurrency(), usd);
        assertEquals(0,val3.getValue().compareTo(new BigDecimal (59)));
    }
}
