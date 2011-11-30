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
public class ExchangeRates {
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
}
