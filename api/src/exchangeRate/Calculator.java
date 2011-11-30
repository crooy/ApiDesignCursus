/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangeRate;

/**
 *
 * @author ronald
 */
public class Calculator {
    private ExchangeRate exchangeRate;
    
    public CurrencyValue convert(CurrencyValue from, Currency to) {
        // find the conversion direction
        if (exchangeRate.getSecondCurrency().equals(to))
        {
            // same direction as ExchangeRate
        }
        else if (exchangeRate.getFirstCurrency.equals(to)) {
            // other direction
        }
        else {
            throw new CalculatorException("the 'to'-currency is not valid");
        }
    }
}
