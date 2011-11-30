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
public class CurrencyTest {
    
    public CurrencyTest() {
    }

    
    @Test
    public void testGetName() throws ExchangeRateCalculatorException{
        String given = "USD";
        String expected = given;
        
        Currency currency = new Currency(given);
        String actual = currency.getName();
        
        Assert.assertEquals(expected, actual);
    }
    
    @Test(expected = CurrencyException.class)
    public void testEmptyConstruction() throws ExchangeRateCalculatorException{
        String given = null;
        String expected = null;
        
        Currency currency = new Currency(given);
    }
    
    @Test(expected = CurrencyException.class)
    public void testTooShortConstruction() throws ExchangeRateCalculatorException{
        String given = "AA";
        String expected = null;
        
        Currency currency = new Currency(given);
    }   
    
    @Test(expected = CurrencyException.class)
    public void testTooLongConstruction() throws ExchangeRateCalculatorException{
        String given = "BBBBBB";
        String expected = null;
        
        Currency currency = new Currency(given);
    }    
    
    @Test(expected = CurrencyException.class)
    public void testNotACharStringConstruction() throws ExchangeRateCalculatorException{
        String given = "AA1";
        String expected = null;
        
        Currency currency = new Currency(given);
    }  
    
        @Test
    public void testUpperCharConstruction() throws ExchangeRateCalculatorException{
        String given = "aaa";
        String expected = "AAA";
        
        Currency currency = new Currency(given);
        String actual = currency.getName();
        
        Assert.assertEquals(expected, actual);
    } 
}
