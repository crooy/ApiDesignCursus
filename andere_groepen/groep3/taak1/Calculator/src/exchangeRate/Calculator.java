/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangeRate;

/**
 * Calculator converts in between two provided currencies as specified in the provided exchange rate. 
 * @author user
 */
public class Calculator implements CalcultorAPI {

    private Calculator() {
    }

    /**
     * Creates a currency calculator which converts in between currencies "first" and "second" at the specified rate.
     * @param first the first currency this calculator accepts.
     * @param second the second currency this calculator accepts; must never be equal to first currency.
     * @param rate the exchange rate to convert from first currency to second; must be a non-zero positive number.
     * @throws Exception thrown when rate is a negative number or zero, or first and second currencies are equal.
     */ 
    
    public static Calculator create(Currencies first, Currencies second, double rate) throws Exception {
        if (0.0d >= rate) {
            throw new Exception("Rate must be a non-zero positive number!");
        } else if(first == second) {
            throw new Exception("Different currencies are required!");
        }

        Calculator c = new Calculator();
        c.firstCurrency = first;
        c.secondCurrency = second;
        c.exchangeRate = rate;

        return c;
    }
    /**
     * Converts a provided amount in currency "from" to an amount in currency "to"
     * @param from the currency to convert from
     * @param to the currency to convert to
     * @param amount the amount to convert; must be a positive number.
     * @throws Exception thrown when amount is a negative number, when first and second currencies are equal or when a different currency as in create() is provided .
     */
    @Override
    public double convert(Currencies from, Currencies to, double amount) throws Exception {
        double result = 0.0d;

        if (0.0d > amount) {
            throw new Exception("Amount must be a positive number!");
        } else if (((from == this.firstCurrency) || (from == this.secondCurrency))
                && ((to == this.firstCurrency) || (to == this.secondCurrency))
                && (from != to)) {
            if (from == this.firstCurrency) {
                result = amount * exchangeRate;
            } else {
                result = amount / exchangeRate;
            }
        } else {
            throw new Exception("Currencies must not be equal and " + firstCurrency + " or " + secondCurrency + "!");
        }

        return result;
    }
    private Currencies firstCurrency;
    private Currencies secondCurrency;
    private double exchangeRate;
}
