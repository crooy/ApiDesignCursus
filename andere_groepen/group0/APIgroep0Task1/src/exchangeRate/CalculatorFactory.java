/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangeRate;

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
     * @return 
     */
    public Calculator create(String currencyFrom, String currencyTo, double factor) {
        return new CalculatorImpl(currencyFrom, currencyTo, factor);
    }

    private class CalculatorImpl implements Calculator {
        private String currencyFrom;
        private String currencyTo;
        private double factor;

        private CalculatorImpl(String currencyFrom, String currencyTo, double factor) {
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

        @Override
        public double convert(String currencyFrom, String currencyTo, double amount) {
            if (currencyFrom == null || currencyFrom.isEmpty()) {
                throw new IllegalArgumentException("currencyFrom is null or empty");
            }
            if (currencyTo == null || currencyTo.isEmpty()) {
                throw new IllegalArgumentException("currencyTo is null or empty");
            }
            if (currencyFrom.equalsIgnoreCase(this.currencyFrom) && currencyTo.equalsIgnoreCase(this.currencyTo)) {
                return amount * factor;
            }
            if (currencyFrom.equalsIgnoreCase(this.currencyTo) && currencyTo.equalsIgnoreCase(this.currencyFrom)) {
                return amount / factor;
            }
            if (!currencyFrom.equalsIgnoreCase(this.currencyFrom) && !currencyTo.equalsIgnoreCase(this.currencyTo)) {
                if (!currencyFrom.equalsIgnoreCase(this.currencyTo) && !currencyTo.equalsIgnoreCase(this.currencyFrom)) {
                    throw new IllegalArgumentException("Given currencies not valid (String incorrect).");
                }
                throw new IllegalArgumentException("Given currencyFrom not valid (String incorrect).");
            }
            throw new IllegalArgumentException("Given currencyTo not valid (String incorrect).");
        }

    }
}
