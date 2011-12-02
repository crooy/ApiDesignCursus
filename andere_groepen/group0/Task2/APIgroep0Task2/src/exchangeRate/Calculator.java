/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangeRate;

import java.util.List;

/**
 *
 * @author GROUP0
 */
public interface Calculator {
    
    /**
     * Converts the given amount of given Currency to target currency
     * 
     * @param currencyFrom The given currency
     * @param currencyTo The target currency
     * @param amount The amount of the given currency
     * @return the converted amount in the target currency
     */
    public double convert(String currencyFrom, String currencyTo, double amount);
    
    /**
     * Gives the list of ExchangeRates in this Calculator
     * 
     * @return the list of ExchangeRates of this Calculator
     */
    public List<ExchangeRate> getExchangeRates();
}
