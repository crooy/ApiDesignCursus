/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangeRate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
        
        Set<ExchangeRate> exchangeRates = new HashSet<ExchangeRate>();
        exchangeRates.add(firstExchangeRate);
        exchangeRates.add(secondExchangeRate);
        
        return new Calculator(exchangeRates);
    }
    
    public Calculator create(Calculator firstCalculator, Calculator secondCalculator) throws ExchangeRateCalculatorException
    {
        Set<ExchangeRate> exchangeRates = 
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

    private Set<ExchangeRate> mergeExchangeRates(Set<ExchangeRate> first, Set<ExchangeRate> second) throws ExchangeRateCalculatorException
    {
        TreeSet<ExchangeRate> mergedRates = new TreeSet<ExchangeRate>();
        mergedRates.addAll(first);
        mergedRates.addAll(second);
        
        if (second.size() + first.size() != mergedRates.size()){
            throw new MergeCalculatorException("Calculators cannot contain the same exchangeRates");
        }
            
        return mergedRates;
    }    
}
