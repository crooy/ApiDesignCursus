/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangeRate;

import org.junit.Test;


/**
 *
 * @author ronald
 */
public class ExchangeRateTest {
    
    @Test
    public void testBasicConstruction() throws ExchangeRateCalculatorException{
        Double dollarForOneEuroGiven = 2.0;
        Currency givenCurrency1 = new Currency("EUR");
        Currency givenCurrency2 = new Currency("USD");
        ExchangeRate rate = new ExchangeRate(dollarForOneEuroGiven, givenCurrency1, givenCurrency2 );        
    }
    
}
