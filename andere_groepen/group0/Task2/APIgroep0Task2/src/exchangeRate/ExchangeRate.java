/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangeRate;

/**
 *
 * @author GROUP0
 */
public final class ExchangeRate {
    private String currencyFrom;
    private String currencyTo;
    private double factor;
    
    public ExchangeRate(String currencyFrom, String currencyTo, double factor) {
        if (Double.compare(factor, 0) <= 0) {
            throw new IllegalArgumentException("factor cannot be equal or less then zero");
        }
        if (currencyFrom == null || currencyFrom.isEmpty()) {
            throw new IllegalArgumentException("currencyFrom is null or empty");
        }
        if (currencyTo == null || currencyTo.isEmpty()) {
            throw new IllegalArgumentException("currencyTo is null or empty");
        }
        if (currencyTo.equalsIgnoreCase(currencyFrom)) {
            throw new IllegalArgumentException("currencyTo and currencyFrom cannot be the same");
        }
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.factor = factor;
    }
    
    public String getCurrencyFrom() {
        return currencyFrom;
    }
    
    public String getCurrencyTo() {
        return currencyTo;
    }
    
    public double getFactor() {
        return factor;
    }
}
