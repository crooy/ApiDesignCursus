/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangeRate;

import java.math.BigDecimal;


/**
 * Calculator converts in between two provided currencies as specified in the provided exchange rate. 
 * @author user
 */
public class CalculatorImpl implements Calculator {

    private CalculatorImpl(Currencies first, Currencies second, double rate) {
       
        ratesMap = new java.util.HashMap<String,java.math.BigDecimal>();
        
        ratesMap.put(first.toString()+second.toString(), new BigDecimal(rate));
        ratesMap.put(second.toString()+first.toString(), new BigDecimal(1/rate));
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

        CalculatorImpl c = new CalculatorImpl(first, second, rate);
        
        return c;
    }
    
    @Override
    public double convert(Currencies from, Currencies to, double amount) throws Exception {
        double result = 0.0d;

        if (0.0d > amount) {
            throw new Exception("Amount must be a positive number!");
        } else {
            
            String key = from.toString()+to.toString();
            BigDecimal rate = ratesMap.get(key);
            
            if(null == rate) {
                throw new Exception("Cannot convert in between " + from + " to " + to); 
            }
            else {
                result = amount * rate.doubleValue();
            }
        }
       
        return result;
    }
    
    private java.util.HashMap<String,java.math.BigDecimal> ratesMap;

    @Override
    public Calculator merge(Calculator other) throws Exception {
        
        if(null == other) {
            throw new Exception("Other is null!");
        } else if(this == other) {
            throw new Exception("Other is this!");
        }
        
        CalculatorImpl otherC = (CalculatorImpl)other;

        ratesMap.putAll(otherC.ratesMap);
       
//        System.out.println("Added calculator " + other + "--- " + otherC.map);
        
        return this;
    }
}
