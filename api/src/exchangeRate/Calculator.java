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
    
    public CurrencyValue convert(CurrencyValue from, Currency to) throws CalculatorException {
        assert (!exchangeRate.getFirstCurrency().equals(exchangeRate.getSecondCurrency()));
        
        boolean directionEqualToExchangeRate =
                                getExchangeDirection(to);
        
        if (directionEqualToExchangeRate)
        {
            if (! exchangeRate.getFirstCurrency().equals(from)) {
                throw new CalculatorException("the 'from'-currency is not valid");
            }
        }
        else {
            if (! exchangeRate.getSecondCurrency().equals(from)) {
                throw new CalculatorException("the 'from'-currency is not valid");
            }
        }
        
        return new CurrencyValue(to, 0.0);
    }

    private boolean getExchangeDirection(Currency to) throws CalculatorException {
        boolean directionEqualToExchangeRate;
        
        if (exchangeRate.getSecondCurrency().equals(to))
        {
            directionEqualToExchangeRate = true;
        }
        else if (exchangeRate.getFirstCurrency.equals(to)) {
            directionEqualToExchangeRate = false;
        }
        else {
            throw new CalculatorException("the 'to'-currency is not valid");
        }
        
        return directionEqualToExchangeRate;
    }
}
