/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangeRate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author GROUP0
 */
public final class CalculatorFactory {
    
    /**
     * Returns a valuta converter calculator between two currencies.
     * The two currencies cannot be the same.
     * 
     * @param currencyFrom The given currency
     * @param currencyTo The target currency
     * @param factor The conversion factor from the From currency to the To currency. 
     * The factor may not be <= 0
     * @return The value converter calculator
     */
    public Calculator create(String currencyFrom, String currencyTo, double factor) {
        return new CalculatorImpl(currencyFrom, currencyTo, factor);
    }
    
    /**
     * Returns a valuta converter calculator with various exchangeRates between two currencies.
     * The two currencies of one ExchangeRate cannot be the same.
     * If the list contains two ExchangeRates with the From and the same To, the first ExchangeRate of the list is kept.
     * 
     * @param exchangeRates The list of ExchangeRates
     * @return The value converter calculator
     */
    public Calculator create(List<ExchangeRate> exchangeRates) {
        return new CalculatorImpl(exchangeRates);
    }
    
    /**
     * Returns a new Calculator that has the ExchangeRates from both calculators.
     * If the two calculators contain an ExchangeRate with the From and the same To, the ExchangeRate of calc1 is kept.
     * @param calc1 The first Calculator
     * @param calc2 The second Calculator
     * @return A merged Calculator
     */
    public Calculator merge(Calculator calc1, Calculator calc2) {
        List<ExchangeRate> mergeList = new LinkedList<ExchangeRate>();
        mergeList.addAll(calc1.getExchangeRates());
        mergeList.addAll(calc2.getExchangeRates());
        return create(mergeList);
    }

    private class CalculatorImpl implements Calculator {
        private List<ExchangeRate> exchangeRates;
        
        private CalculatorImpl(String currencyFrom, String currencyTo, double factor) {
            ExchangeRate er = new ExchangeRate(currencyFrom, currencyTo, factor);
            exchangeRates = new LinkedList();
            exchangeRates.add(er);
        }
        
        private CalculatorImpl(List<ExchangeRate> exchangeRateList) {
            if (exchangeRateList == null) {
                throw new IllegalArgumentException("The given exchangeRateList is null, this is not allowed");
            }
            if (exchangeRateList.isEmpty()) {
                throw new IllegalArgumentException("The given exchangeRateList is empty, this is not allowed");
            }
            exchangeRates = new LinkedList();
            this.exchangeRates.addAll(removeDoubles(exchangeRateList));
        }
        
        private List<ExchangeRate> removeDoubles(List<ExchangeRate> exchangeRateList) {
            List<ExchangeRate> newList = new LinkedList<ExchangeRate>();
            
            for (ExchangeRate givenER: exchangeRateList) {
                boolean found = false;
                for (ExchangeRate ourER: newList) {
                    if (ourER.getCurrencyFrom().equalsIgnoreCase(givenER.getCurrencyFrom()) && ourER.getCurrencyTo().equalsIgnoreCase(givenER.getCurrencyTo())) {
                        found = true;
                    }
                }
                if (!found) {
                    newList.add(givenER);
                }
            }
            return newList;
        }

        @Override
        public double convert(String currencyFrom, String currencyTo, double amount) {
            if (currencyFrom == null || currencyFrom.isEmpty()) {
                throw new IllegalArgumentException("currencyFrom is null or empty");
            }
            if (currencyTo == null || currencyTo.isEmpty()) {
                throw new IllegalArgumentException("currencyTo is null or empty");
            }
            boolean fromSeen = false;
            boolean toSeen = false;
            double reverseAnswer = 0;
            for (ExchangeRate er: exchangeRates) {
                if (currencyFrom.equalsIgnoreCase(er.getCurrencyFrom()) && currencyTo.equalsIgnoreCase(er.getCurrencyTo())) {
                    return amount * er.getFactor();
                }
                if (currencyFrom.equalsIgnoreCase(er.getCurrencyTo()) && currencyTo.equalsIgnoreCase(er.getCurrencyFrom())) {
                    reverseAnswer = amount / er.getFactor();
                }
                if (er.getCurrencyFrom().equalsIgnoreCase(currencyFrom) || er.getCurrencyTo().equalsIgnoreCase(currencyFrom))
                    fromSeen = true;
                if (er.getCurrencyFrom().equalsIgnoreCase(currencyTo) || er.getCurrencyTo().equalsIgnoreCase(currencyTo))
                    toSeen = true;
            }
            if (fromSeen && toSeen) {
                return reverseAnswer;
            }
            if (!fromSeen && !toSeen)
                throw new IllegalArgumentException("Given currencies not valid (String incorrect).");
            if (!fromSeen)
                throw new IllegalArgumentException("Given currencyFrom not valid (String incorrect).");
            throw new IllegalArgumentException("Given currencyTo not valid (String incorrect).");
        }

        @Override
        public List<ExchangeRate> getExchangeRates() {
            return new ArrayList<ExchangeRate>(exchangeRates);
        }

    }
}
