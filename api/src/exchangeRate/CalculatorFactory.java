/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangeRate;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ronald
 */
class CalculatorFactory implements ICalculatorFactory
{
    private ExchangeRates exchangeRates;
    
    public Calculator create(Currency firstCurrency, Currency secondCurrency) throws ExchangeRateCalculatorException
    {
        // get exchange rates from list
        ExchangeRate firstExchangeRate = exchangeRates.getExchangeRate(firstCurrency, secondCurrency);
        ExchangeRate secondExchangeRate = exchangeRates.getExchangeRate(secondCurrency, firstCurrency);
        
        List<ExchangeRate> exchangeRates = new ArrayList<ExchangeRate>();
        exchangeRates.add(firstExchangeRate);
        exchangeRates.add(secondExchangeRate);
        
        return new Calculator(exchangeRates);
    }
    
    public Calculator create(Calculator firstCalculator, Calculator secondCalculator) throws ExchangeRateCalculatorException
    {
        List<ExchangeRate> exchangeRates = 
                mergeExchangeRates(firstCalculator.getExchangeRates(), secondCalculator.getExchangeRates());
        
        return new Calculator(exchangeRates);
    }

    CalculatorFactory(ExchangeRates exchangeRates)
    {
        assert(exchangeRates != null);
        this.exchangeRates = exchangeRates;
    }
    
    private CalculatorFactory()
    {
    }

    private List<ExchangeRate> mergeExchangeRates(List<ExchangeRate> first, List<ExchangeRate> second) throws ExchangeRateCalculatorException
    {
        List<ExchangeRate> mergedRates = new ArrayList<ExchangeRate>();
        mergedRates.addAll(first);
        for (ExchangeRate exchangeRate : second)
        {
            int index = mergedRates.indexOf(exchangeRate);
            if (index != -1)
            {
                mergedRates.add(exchangeRate);
            }
            else
            {
                ExchangeRate firstExchangeRate = mergedRates.get(index);
                // when exchange rate is the same, no actions have to be done
                if (!firstExchangeRate.getExchangeRate().equals(exchangeRate.getExchangeRate()))
                {
                    throw new ExchangeRateCalculatorException("Unable to merge exchange rates, different exchange rate values found for same currencies.");
                }
            }
        }    
        return mergedRates;
    }    
}
