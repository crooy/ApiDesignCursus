/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangeRate;

import java.util.LinkedList;
import java.util.List;
import java.util.Observer;

/**
 *
 * @author GROUP0
 */
public final class CalculatorFactory {
    
    /**
     * Returns a valuta converter calculator between two currencies.
     * The calculator is Observable, it's Observers will be notified when the convert function is called.
     * The two currencies cannot be the same.
     * 
     * @param currencyFrom The given currency
     * @param currencyTo The target currency
     * @param factor The conversion factor from the From currency to the To currency. 
     * The factor may not be <= 0
     * @return The value converter calculator
     */
    public Calculator create(String currencyFrom, String currencyTo, double factor) {
        return new Calculator(currencyFrom, currencyTo, factor);
    }
    
    /**
     * Returns a valuta converter calculator with various exchangeRates between two currencies.
     * The calculator is Observable, it's Observers will be notified when the convert function is called.
     * The two currencies of one ExchangeRate cannot be the same.
     * If the list contains two ExchangeRates with the From and the same To, the first ExchangeRate of the list is kept.
     * 
     * @param exchangeRates The list of ExchangeRates
     * @return The value converter calculator
     */
    public Calculator create(List<ExchangeRate> exchangeRates) {
        return new Calculator(exchangeRates);
    }
    
    /**
     * Returns a new Calculator that has the ExchangeRates from both calculators.
     * If the two calculators contain an ExchangeRate with the From and the same To, the ExchangeRate of calc1 is kept.
     * The calculator is Observable, it's Observers will be notified when the convert function is called.
     * @param calc1 The first Calculator
     * @param calc2 The second Calculator
     * @return A merged Calculator
     */
    public Calculator merge(Calculator calc1, Calculator calc2) {
        List<ExchangeRate> mergeList = new LinkedList<ExchangeRate>();
        mergeList.addAll(calc1.getExchangeRates());
        mergeList.addAll(calc2.getExchangeRates());
        Calculator c = create(mergeList);
        for (Observer o: calc1.getObservers()) {
            c.addObserver(o);
        }
        for (Observer o: calc2.getObservers()) {
            c.addObserver(o);
        }
        return c;
    }

}
