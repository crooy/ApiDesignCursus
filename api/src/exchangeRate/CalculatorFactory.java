/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangeRate;

/**
 *
 * @author ronald
 */
public class CalculatorFactory implements ICalculatorFactory
{
    private ExchangeRates exchangeRates;
    
    public Calculator create(Currency firstCurrency, Currency secondCurrency) throws CalculatorException
    {
        // get exchange rates from list
        ExchangeRate firstExchangeRate = exchangeRates.getExchangeRate(firstCurrency, secondCurrency);
        ExchangeRate secondExchangeRate = exchangeRates.getExchangeRate(secondCurrency, firstCurrency);
        
        return new Calculator(firstExchangeRate, secondExchangeRate);
    }
    
    private CalculatorFactory()
    {
    }
    
    CalculatorFactory(ExchangeRates exchangeRates)
    {
        this.exchangeRates = exchangeRates;
    }
}
