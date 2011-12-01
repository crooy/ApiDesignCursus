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
public class ExchangeRateTest {
    
    @Test
    public void testBasicConstruction() throws ExchangeRateCalculatorException{
        BigDecimal dollarForOneEuroGiven = new BigDecimal(2.0);
        Currency givenCurrency1 = new Currency("EUR");
        Currency givenCurrency2 = new Currency("USD");
        ExchangeRate rate = new ExchangeRate(dollarForOneEuroGiven, givenCurrency1, givenCurrency2 );        
    }
    
    @Test(expected = ExchangeRateCalculatorException.class)
    public void testNullCurrency1() throws ExchangeRateCalculatorException{
        BigDecimal dollarForOneEuroGiven = new BigDecimal(2.0);
        Currency givenCurrency1 = null;
        Currency givenCurrency2 = new Currency("USD");
        ExchangeRate rate = new ExchangeRate(dollarForOneEuroGiven, givenCurrency1, givenCurrency2 );        
    }
    
    @Test(expected = ExchangeRateCalculatorException.class)
    public void testNullCurrency2() throws ExchangeRateCalculatorException{
        BigDecimal dollarForOneEuroGiven = new BigDecimal(2.0);
        Currency givenCurrency1 = new Currency("USD");
        Currency givenCurrency2 = null;
        ExchangeRate rate = new ExchangeRate(dollarForOneEuroGiven, givenCurrency1, givenCurrency2 );        
    }
    
    @Test(expected = ExchangeRateCalculatorException.class)
    public void testNullValue() throws ExchangeRateCalculatorException{
        BigDecimal dollarForOneEuroGiven = null;
        Currency givenCurrency1 = new Currency("EUR");
        Currency givenCurrency2 = new Currency("USD");
        ExchangeRate rate = new ExchangeRate(dollarForOneEuroGiven, givenCurrency1, givenCurrency2 );        
    }
    @Test(expected = ExchangeRateCalculatorException.class)
    public void testSameCurr() throws ExchangeRateCalculatorException{
        BigDecimal dollarForOneEuroGiven = new BigDecimal(1.0);
        Currency givenCurrency1 = new Currency("EUR");
        Currency givenCurrency2 = new Currency("EUR");
        ExchangeRate rate = new ExchangeRate(dollarForOneEuroGiven, givenCurrency1, givenCurrency2 );        
    }
    @Test(expected = ExchangeRateCalculatorException.class)
    public void testNegativeCurr() throws ExchangeRateCalculatorException{
        BigDecimal dollarForOneEuroGiven = new BigDecimal(-1.0);
        Currency givenCurrency1 = new Currency("USD");
        Currency givenCurrency2 = new Currency("EUR");
        ExchangeRate rate = new ExchangeRate(dollarForOneEuroGiven, givenCurrency1, givenCurrency2 );        
    }
    @Test(expected = ExchangeRateCalculatorException.class)
    public void testZeroCurr() throws ExchangeRateCalculatorException{
        BigDecimal dollarForOneEuroGiven = new BigDecimal(0.0);
        Currency givenCurrency1 = new Currency("USD");
        Currency givenCurrency2 = new Currency("EUR");
        ExchangeRate rate = new ExchangeRate(dollarForOneEuroGiven, givenCurrency1, givenCurrency2 );        
    }
    
    @Test
    public void testCanConvert() throws ExchangeRateCalculatorException{
        BigDecimal dollarForOneEuroGiven = new BigDecimal(1.0);
        Currency givenCurrency1 = new Currency("USD");
        Currency givenCurrency2 = new Currency("EUR");
        ExchangeRate rate = new ExchangeRate(dollarForOneEuroGiven, givenCurrency1, givenCurrency2 );        
        
        boolean expected = true;
        boolean actual = rate.canConvert(givenCurrency1, givenCurrency2);
        
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testCanNotConvert() throws ExchangeRateCalculatorException{
        BigDecimal dollarForOneEuroGiven = new BigDecimal(1.0);
        Currency givenCurrency1 = new Currency("USD");
        Currency givenCurrency2 = new Currency("EUR");
        Currency givenCurrency3 = new Currency("YEN");
        ExchangeRate rate = new ExchangeRate(dollarForOneEuroGiven, givenCurrency1, givenCurrency2 );        
        
        boolean expected = false;
        boolean actual = rate.canConvert(givenCurrency2, givenCurrency3);
        
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testCanNotConvertNulls() throws ExchangeRateCalculatorException{
        BigDecimal dollarForOneEuroGiven = new BigDecimal(1.0);
        Currency givenCurrency1 = new Currency("USD");
        Currency givenCurrency2 = new Currency("EUR");
        Currency givenCurrency3 = null;
        ExchangeRate rate = new ExchangeRate(dollarForOneEuroGiven, givenCurrency1, givenCurrency2 );        
        try{
            boolean actual = rate.canConvert(givenCurrency2, null);
            Assert.fail("fail1");
        }catch(NullPointerException e){
            
        }
    }
    
    @Test
    public void testEquals() throws ExchangeRateCalculatorException{
        BigDecimal dollarForOneEuroGiven1 = new BigDecimal(1.0);
        Currency givenCurrency1 = new Currency("USD");
        Currency givenCurrency2 = new Currency("EUR");
        ExchangeRate rate1 = new ExchangeRate(dollarForOneEuroGiven1, givenCurrency1, givenCurrency2 );        
        
        BigDecimal dollarForOneEuroGiven2 = new BigDecimal(1.0);
        Currency givenCurrency3 = new Currency("USD");
        Currency givenCurrency4 = new Currency("EUR");
        ExchangeRate rate2 = new ExchangeRate(dollarForOneEuroGiven2, givenCurrency1, givenCurrency2 );        
        
        Assert.assertEquals(rate1, rate2);
    }
    
    @Test
    public void testAlsoEquals() throws ExchangeRateCalculatorException{
        BigDecimal dollarForOneEuroGiven1 = new BigDecimal(1.0);
        Currency givenCurrency1 = new Currency("USD");
        Currency givenCurrency2 = new Currency("EUR");
        ExchangeRate rate1 = new ExchangeRate(dollarForOneEuroGiven1, givenCurrency1, givenCurrency2 );        
        
        BigDecimal dollarForOneEuroGiven2 = new BigDecimal(2.0);
        Currency givenCurrency3 = new Currency("USD");
        Currency givenCurrency4 = new Currency("EUR");
        ExchangeRate rate2 = new ExchangeRate(dollarForOneEuroGiven2, givenCurrency1, givenCurrency2 );        
        
        Assert.assertEquals(rate1, rate2);
    }
    
    @Test
    public void testNotEquals() throws ExchangeRateCalculatorException{
        BigDecimal dollarForOneEuroGiven1 = new BigDecimal(1.0);
        Currency givenCurrency1 = new Currency("EUR");
        Currency givenCurrency2 = new Currency("USD");
        ExchangeRate rate1 = new ExchangeRate(dollarForOneEuroGiven1, givenCurrency1, givenCurrency2 );        
        
        BigDecimal dollarForOneEuroGiven2 = new BigDecimal(2.0);
        Currency givenCurrency3 = new Currency("USD");
        Currency givenCurrency4 = new Currency("EUR");
        ExchangeRate rate2 = new ExchangeRate(dollarForOneEuroGiven2, givenCurrency1, givenCurrency2 );        
        
        Assert.assertFalse(rate1.equals(rate2));
    }
}
