/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangeRate;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;


/**
 *
 * @author ronald
 */
public class CurrencyValueTest extends TestCase{
    
    
    @Test
    public void testGetValue() throws CurrencyException {
        Currency givenCurrency = new Currency("EUR");
        Double givenValue = 99.0;
        Double expected = givenValue;
        
        
        CurrencyValue curVal = new CurrencyValue(givenCurrency, givenValue);
        Double actual = curVal.getValue();
        Assert.assertEquals(expected,actual);     
    }
    
    @Test
    public void testGetCurrency() throws CurrencyException{
        Currency givenCurrency = new Currency("EUR");
        Double givenValue = 99.0;
        Currency expected = givenCurrency;
        
        
        CurrencyValue curVal = new CurrencyValue(givenCurrency, givenValue);
        Currency actual = curVal.getCurrency();
        Assert.assertSame(expected, actual);   
    }
    
    @Test(expected = CurrencyException.class)
    public void testGetCurrencyWithNull() throws CurrencyException{
        Currency givenCurrency = null;
        Double givenValue = 99.0;
        
        CurrencyValue curVal = new CurrencyValue(givenCurrency, givenValue);
        
    }
   
    
}
