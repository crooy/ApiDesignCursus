/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangeRate;

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
        Double givenValue = 99.0;
        Double expected = givenValue;
        
        
        CurrencyValue curVal = new CurrencyValue(givenCurrency, givenValue);
        Double actual = curVal.getValue();
        Assert.assertEquals(expected,actual);     
    }
    
    @Test
    public void testGetCurrency() throws ExchangeRateCalculatorException{
        Currency givenCurrency = new Currency("EUR");
        Double givenValue = 99.0;
        Currency expected = givenCurrency;
        
        
        CurrencyValue curVal = new CurrencyValue(givenCurrency, givenValue);
        Currency actual = curVal.getCurrency();
        Assert.assertSame(expected, actual);   
    }
    
    @Test(expected = CurrencyException.class)
    public void testGetCurrencyWithNull() throws ExchangeRateCalculatorException{
        Currency givenCurrency = null;
        Double givenValue = 99.0;
        
        new CurrencyValue(givenCurrency, givenValue);        
    }
    
    @Test(expected = CurrencyException.class)
    public void testGetValueWithNull() throws ExchangeRateCalculatorException{
        Currency givenCurrency = new Currency("EUR");
        Double givenValue = null;
        
        new CurrencyValue(givenCurrency, givenValue);        
    }   
    
    @Test(expected = CurrencyException.class)
    public void testGetValueWithNegativeValue() throws ExchangeRateCalculatorException{
        Currency givenCurrency = new Currency("EUR");
        Double givenValue = -10.0;
        
        new CurrencyValue(givenCurrency, givenValue);        
    }   
    
  
}
