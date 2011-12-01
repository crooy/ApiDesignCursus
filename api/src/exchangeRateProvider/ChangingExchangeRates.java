/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangeRateProvider;

import exchangeRate.Currency;
import exchangeRate.ExchangeRate;
import exchangeRate.ExchangeRateCalculatorException;
import exchangeRate.ExchangeRates;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class ChangingExchangeRates extends ExchangeRates {
    private List<ExchangeRate> exchangeRates = new ArrayList<ExchangeRate>();
    private static final int ROUNDING_SCALE = 50;
    
    private boolean rateDirectionUp = false;
    private int numberOfCalls = 0;
    
    public ChangingExchangeRates() throws ExchangeRateCalculatorException
    {
        // add initial exchange rates
        exchangeRates.add(new ExchangeRate(BigDecimal.ONE.divide(new BigDecimal(16), ROUNDING_SCALE, RoundingMode.HALF_EVEN), new Currency("USD"), new Currency("CZK")));
        exchangeRates.add(new ExchangeRate(new BigDecimal(16), new Currency("CZK"), new Currency("USD")));
    }

    /**
     * Set the specified exchange rate.
     * When the exchange rate already exists in the list, it is replaced.
     * @param exchangeRate The exchange rate to add/replace in the list.
     */
    @Override
    public void setExchangeRate(ExchangeRate exchangeRate)
    {
        // remove possibly pre-existing exchange rate
        boolean removed = exchangeRates.remove(exchangeRate);
        boolean added = exchangeRates.add(exchangeRate);
    }
    
    @Override
    public ExchangeRate getExchangeRate(Currency firstCurrency, Currency secondCurrency) throws ExchangeRateCalculatorException {
        
        ExchangeRate foundExchangeRate = findExchangeRate(firstCurrency, secondCurrency);
        
        // update the exchange rate
        updateExchangeRate(foundExchangeRate);

        return foundExchangeRate;
    }
    
    private ExchangeRate findExchangeRate(Currency firstCurrency, Currency secondCurrency) throws ExchangeRateCalculatorException
    {
        for (ExchangeRate exchangeRate : exchangeRates)
        {
            if (exchangeRate.getFirstCurrency().equals(firstCurrency) &&
                exchangeRate.getSecondCurrency().equals(secondCurrency))
            {
                return new ExchangeRate(exchangeRate);
            }            
        }
        
        throw new ExchangeRateCalculatorException("Exchange rate not found."); 
    }
    
    private BigDecimal inverse(BigDecimal number)
    {
        return BigDecimal.ONE.divide(number, ROUNDING_SCALE, RoundingMode.HALF_EVEN);
    }
    
    private void updateExchangeRate(ExchangeRate exchangeRate) throws ExchangeRateCalculatorException
    {
        Currency usd = new Currency("USD");
        Currency czk = new Currency("CZK");
        BigDecimal upperLimit = new BigDecimal(16);
        BigDecimal lowerLimit = new BigDecimal(15);
        BigDecimal delta = new BigDecimal("0.01");
        
        if (exchangeRate.getFirstCurrency().equals(usd) &&
            exchangeRate.getSecondCurrency().equals(czk))
        {
            numberOfCalls++;
            if (numberOfCalls >= 100)
            {
                rateDirectionUp = !rateDirectionUp;
                numberOfCalls = 0;
            }
            // update this exchange rate
            BigDecimal rate = null;
            if (rateDirectionUp)
            {
                // increment exchange rate by removing it from the list and adding a new
                rate = lowerLimit.add(new BigDecimal(numberOfCalls).multiply(delta));
                
                ExchangeRate newExchangeRate = new ExchangeRate(inverse(rate), exchangeRate.getFirstCurrency(), exchangeRate.getSecondCurrency());
                setExchangeRate(newExchangeRate);
            }
            else
            {
                // decrement exchange rate by removing it from the list and adding a new
                rate = upperLimit.subtract(new BigDecimal(numberOfCalls).multiply(delta));

                ExchangeRate newExchangeRate = new ExchangeRate(inverse(rate), exchangeRate.getFirstCurrency(), exchangeRate.getSecondCurrency());
                setExchangeRate(newExchangeRate);
            }
            
            if (rate != null)
            {
                ExchangeRate newReverseExchangeRate = new ExchangeRate(rate, exchangeRate.getSecondCurrency(), exchangeRate.getFirstCurrency());
                setExchangeRate(newReverseExchangeRate);
            }
        }
    }
}
