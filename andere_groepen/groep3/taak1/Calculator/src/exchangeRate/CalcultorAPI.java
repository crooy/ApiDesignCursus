/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangeRate;

/**
 *
 * @author user
 */
public interface CalcultorAPI {

    /**
     * Converts a provided amount in currency "from" to an amount in currency "to"
     * @param from the currency to convert from
     * @param to the currency to convert to
     * @param amount the amount to convert; must be a positive number.
     * @throws Exception thrown when amount is a negative number, when first and second currencies are equal or when a different currency as in create() is provided .
     */
    double convert(Currencies from, Currencies to, double amount) throws Exception;
    
}
