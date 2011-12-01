/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangeRate;

import java.util.Set;
import java.util.HashSet;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 *
 * @author ronald
 */
public class CalculatorFactoryTest {
    
    
    
    
    @Test(expected = Exception.class) 
    public void testMergeEmptyExchangeRates() throws ExchangeRateCalculatorException{
        ExchangeRates mockRates = new ExchangeRates();
        
        CalculatorFactory tested = new CalculatorFactory(mockRates);
        
        Set<ExchangeRate> first = new HashSet<ExchangeRate>();
        Set<ExchangeRate> second = new HashSet<ExchangeRate>();
        
        tested.mergeExchangeRates(first, second);
        Assert.fail("should not be able to work with empty sets");
    }
    
    @Test(expected = Exception.class)
    public void testMergeNullExchangeRates() throws ExchangeRateCalculatorException{
        
        ExchangeRates mockRates = new ExchangeRates();
        
        CalculatorFactory tested = new CalculatorFactory(mockRates);
        
        tested.mergeExchangeRates(null, null);
        Assert.fail("should not be able to work with null sets");
    }
     
    @Test(expected = Exception.class)
    public void testMergeEqualExchangeRates() throws ExchangeRateCalculatorException{
        
        ExchangeRates mockRates = new ExchangeRates();
        
        CalculatorFactory tested = new CalculatorFactory(mockRates);
        
        Set<ExchangeRate> first = new HashSet<ExchangeRate>();
        
        tested.mergeExchangeRates(first, first);
        
        Assert.fail("should not be able to work with equal sets");
    }
}
