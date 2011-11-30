/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangeRate;

import java.util.ArrayList;
import java.util.List;

/**
 * List of exchange rates.
 * @author Freek
 */
public final class ExchangeRates {
    private List<ExchangeRate> exchangeRates = new ArrayList<ExchangeRate>();
    
    /**
     * Set the specified exchange rate.
     * When the exchange rate already exists in the list, it is replaced.
     * @param exchangeRate The exchange rate to add/replace in the list.
     */
    public void setExchangeRate(ExchangeRate exchangeRate)
    {
        // remove possibly pre-existing exchange rate
        exchangeRates.remove(exchangeRate);
        exchangeRates.add(exchangeRate);
    }
    
    /**
     * Get exchange rate for specified currencies.
     * @param firstCurrency
     * @param secondCurrency
     * @return The exchange rate.
     * @throws CalculatorException 
     */
    public ExchangeRate getExchangeRate(Currency firstCurrency, Currency secondCurrency) throws CalculatorException
    {
        for (ExchangeRate exchangeRate : exchangeRates)
        {
            if (exchangeRate.getFirstCurrency().equals(firstCurrency) &&
                exchangeRate.getSecondCurrency().equals(secondCurrency))
            {
                return exchangeRate;
            }
        }
        
        throw new CalculatorException("Exchange rate not found.");
    }
}
