/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangeRate;

import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;


/**
 *
 * @author ronald
 */
public class CurrencyValueTest {
    
    
    @Test
    public void testGetValue() throws ExchangeRateCalculatorException {
        Currency givenCurrency = new Currency("EUR");
        BigDecimal givenValue = new BigDecimal(99.0);
        BigDecimal expected = givenValue;
        
        
        CurrencyValue curVal = new CurrencyValue(givenCurrency, givenValue);
        BigDecimal actual = curVal.getValue();
        Assert.assertEquals(expected,actual);     
    }
    
    @Test
    public void testGetCurrency() throws ExchangeRateCalculatorException{
        Currency givenCurrency = new Currency("EUR");
        BigDecimal givenValue = new BigDecimal(99.0);
        Currency expected = givenCurrency;
        
        
        CurrencyValue curVal = new CurrencyValue(givenCurrency, givenValue);
        Currency actual = curVal.getCurrency();
        Assert.assertSame(expected, actual);   
    }
    
    @Test(expected = CurrencyValueException.class)
    public void testGetCurrencyWithNull() throws ExchangeRateCalculatorException{
        Currency givenCurrency = null;
        BigDecimal givenValue = new BigDecimal(99.0);
        
        new CurrencyValue(givenCurrency, givenValue);        
    }
    
    @Test(expected = CurrencyValueException.class)
    public void testGetValueWithNull() throws ExchangeRateCalculatorException{
        Currency givenCurrency = new Currency("EUR");
        BigDecimal givenValue = null;
        
        new CurrencyValue(givenCurrency, givenValue);        
    }   
    
    @Test(expected = CurrencyValueException.class)
    public void testGetValueWithNegativeValue() throws ExchangeRateCalculatorException{
        Currency givenCurrency = new Currency("EUR");
        BigDecimal givenValue = new BigDecimal(-10);
        
        new CurrencyValue(givenCurrency, givenValue);        
    }   
    
  
}
