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
        
        boolean directionEqualToExchangeRate = getExchangeDirection(to);
        verifyCurrencyFrom(directionEqualToExchangeRate, from);
        
        // we're set, lets convert!
        double newValue;
        if (directionEqualToExchangeRate) {
            newValue = from.getValue() / exchangeRate.getExchangeRate();
        }
        else {
            newValue = from.getValue() * exchangeRate.getExchangeRate();
        }
        
        return new CurrencyValue(to, newValue);
    }

    /**
     * Verifies that the 'from' currency is a valid currency.
     * @param directionEqualToExchangeRate
     * @param from
     * @throws CalculatorException 
     */
    private void verifyCurrencyFrom(boolean directionEqualToExchangeRate, CurrencyValue from) throws CalculatorException {
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
    }

    /**
     * Find out if the 'direction' of the exchange is equal to the direction
     * of the exchangeRate member. The direction for the exchange is:
     * from --> to
     * And for the exchangeRate:
     * FirstCurrency --> SecondCurrency
     * @param to
     * @return true if the direction is equal, false if it is not
     * @throws CalculatorException 
     */
    private boolean getExchangeDirection(Currency to) throws CalculatorException {
        boolean directionEqualToExchangeRate;
        
        if (exchangeRate.getSecondCurrency().equals(to))
        {
            return true;
        }
        else if (exchangeRate.getFirstCurrency().equals(to)) {
            return false;
        }
        else {
            throw new CalculatorException("the 'to'-currency is not valid");
        }
    }
}
