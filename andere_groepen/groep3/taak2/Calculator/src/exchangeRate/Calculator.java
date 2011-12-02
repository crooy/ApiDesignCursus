/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangeRate;

/**
 *
 * @author user
 */
public interface Calculator {

    /**
     * Converts a provided amount in currency "from" to an amount in currency "to"
     * @param from the currency to convert from
     * @param to the currency to convert to
     * @param amount the amount to convert; must be a positive number.
     * @throws Exception thrown when amount is a negative number, when first and second currencies are equal or when a different currency as in create() is provided .
     */
    double convert(Currencies from, Currencies to, double amount) throws Exception;
    
    /**
     * Merges a given Calculator "other" with the Calculator on which the method is called.
     * @param other the other calculator
     * @return the merged Calculator
     * @throws Exception thrown when other is null, or other is the Calculator on which the method is called.
     */
    Calculator merge(Calculator other) throws Exception;
    
}
