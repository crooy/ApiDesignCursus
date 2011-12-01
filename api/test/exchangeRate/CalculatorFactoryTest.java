/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangeRate;

import java.util.Set;
import java.util.HashSet;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author ronald
 */
public class CalculatorFactoryTest {
    
    
    
    
    @Test
    public void testMergeEmptyExchangeRates() throws ExchangeRateCalculatorException{
        ExchangeRates mockRates = mock(ExchangeRates.class);
        
        CalculatorFactory tested = new CalculatorFactory(mockRates);
        
        Set<ExchangeRate> first = new HashSet<ExchangeRate>();
        Set<ExchangeRate> second = new HashSet<ExchangeRate>();
        
        tested.mergeExchangeRates(first, second);
        fail("should not be able to work with empty sets");
    }
    
    @Test
    public void testMergeNullExchangeRates() throws ExchangeRateCalculatorException{
        
        ExchangeRates mockRates = mock(ExchangeRates.class);
        
        CalculatorFactory tested = new CalculatorFactory(mockRates);
        
        tested.mergeExchangeRates(null, null);
        fail("should not be able to work with null sets");
    }
     
    @Test
    public void testMergeEqualExchangeRates() throws ExchangeRateCalculatorException{
        
        ExchangeRates mockRates = mock(ExchangeRates.class);
        
        CalculatorFactory tested = new CalculatorFactory(mockRates);
        
        Set<ExchangeRate> first = new HashSet<ExchangeRate>();
        
        tested.mergeExchangeRates(first, first);
        
        fail("should not be able to work with equal sets");
    }
}
