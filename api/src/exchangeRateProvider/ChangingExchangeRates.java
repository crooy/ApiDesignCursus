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
    
    public ChangingExchangeRates() throws ExchangeRateCalculatorException
    {
        // add initial exchange rates
        exchangeRates.add(new ExchangeRate(BigDecimal.ONE.divide(new BigDecimal(16), ROUNDING_SCALE, RoundingMode.HALF_EVEN), new Currency("USD"), new Currency("CZK")));
        exchangeRates.add(new ExchangeRate(new BigDecimal(16), new Currency("CZK"), new Currency("USD")));
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
    
    private void updateExchangeRate(ExchangeRate exchangeRate) throws ExchangeRateCalculatorException
    {
        Currency usd = new Currency("USD");
        Currency czk = new Currency("CZK");
        BigDecimal upperLimit = new BigDecimal(16);
        BigDecimal lowerLimit = new BigDecimal(15);
        BigDecimal delta = new BigDecimal(1).divide(new BigDecimal(100), ROUNDING_SCALE, RoundingMode.HALF_EVEN);
        
        if (exchangeRate.getFirstCurrency().equals(usd) &&
            exchangeRate.getSecondCurrency().equals(czk))
        {
            // update this exchange rate
            BigDecimal rate = exchangeRate.getExchangeRate();
            if (rate.compareTo(lowerLimit) >= 0)
            {
                // increment exchange rate by removing it from the list and adding a new
                rate = rate.add(delta);
                ExchangeRate newExchangeRate = new ExchangeRate(rate, exchangeRate.getFirstCurrency(), exchangeRate.getSecondCurrency());
                setExchangeRate(newExchangeRate);
            }
            else if (rate.compareTo(upperLimit) <= 0)
            {
                // decrement exchange rate by removing it from the list and adding a new
                rate = rate.subtract(delta);
                ExchangeRate newExchangeRate = new ExchangeRate(rate, exchangeRate.getFirstCurrency(), exchangeRate.getSecondCurrency());
                setExchangeRate(newExchangeRate);
            }
        }
    }
}
