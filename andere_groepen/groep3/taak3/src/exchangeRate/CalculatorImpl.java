/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangeRate;

import java.math.BigDecimal;
import java.math.BigInteger;


/**
 * Calculator converts in between two provided currencies as specified in the provided exchange rate. 
 * @author user
 */
public class CalculatorImpl implements Calculator {

    private CalculatorImpl(Currencies first, Currencies second, double rate) {
       
        ExchangeRate updater = new DefaultExchangeRate(new BigDecimal(rate));
        
        exchangeRates.put(first.toString()+second.toString(), updater);        
    }
    
    private CalculatorImpl(Currencies first, Currencies second, ExchangeRate exchangeRate) {
       
        exchangeRates.put(first.toString()+second.toString(), exchangeRate);        
    }

    /**
     * Creates a currency calculator which converts in between currencies "first" and "second" at the specified rate.
     * @param first the first currency this calculator accepts.
     * @param second the second currency this calculator accepts; must never be equal to first currency.
     * @param rate the exchange rate to convert from first currency to second; must be a non-zero positive number.
     * @throws Exception thrown when rate is a negative number or zero, or first and second currencies are equal.
     */ 
    
    public static Calculator create(Currencies first, Currencies second, double rate) throws Exception {
        if (null == first || null == second) {
            throw new Exception("A valid currency must be specified!");
        } else if (0.0d >= rate) {
            throw new Exception("Rate must be a non-zero positive number!");
        } else if(first == second) {
            throw new Exception("Different currencies are required!");
        }

        CalculatorImpl c = new CalculatorImpl(first, second, rate);
        
        return c;
    }
    
    /**
     * Creates a currency calculator which converts in between currencies "first" and "second" at the specified rate.
     * @param first the first currency this calculator accepts.
     * @param second the second currency this calculator accepts; must never be equal to first currency.
     * @param rate the exchange rate to convert from first currency to second; must be a non-zero positive number.
     * @throws Exception thrown when rate is a negative number or zero, or first and second currencies are equal.
     */ 
    
    public static Calculator create(Currencies first, Currencies second, ExchangeRate exchangeRate) throws Exception {
        if (null == first || null == second) {
            throw new Exception("A valid currency must be specified!");
        }
        else if(null == exchangeRate) {
            throw new Exception("Updater cannot be null!");
        }
        CalculatorImpl c = new CalculatorImpl(first, second, exchangeRate);
        
        return c;
    }
    
    
    @Override
    public double convert(Currencies from, Currencies to, double amount) throws Exception {
        double result = 0.0d;

         if (null == from || null == to) {
            throw new Exception("A valid currency must be specified!");
        } else if (0.0d > amount) {
            throw new Exception("Amount must be a positive number!");
        } else {
            
            String key = from.toString() + to.toString();
            
            ExchangeRate exr = exchangeRates.get(key);
            
            BigDecimal rate = new BigDecimal(0);
            
            if(null == exr) {
                key = to.toString() + from.toString();
                exr = exchangeRates.get(key);
                
                if(null == exr) {                
                    throw new Exception("Cannot convert in between " + from + " to " + to); 
                }        
                
                rate = exr.get();
                double newRate = 1.0d / rate.doubleValue();
                rate = new BigDecimal(newRate);
            }
            else {
                rate = exr.get();
            }
      
            result = amount * rate.doubleValue();     
        }
       
        return result;
    }
    
    @Override
    public Calculator merge(Calculator other) throws Exception {
        
        if(null == other) {
            throw new Exception("Other is null!");
        } else if(this == other) {
            throw new Exception("Other is this!");
        }
        
        CalculatorImpl otherC = (CalculatorImpl)other;            

        exchangeRates.putAll(otherC.exchangeRates);
       
//        System.out.println("Added calculator " + other + "--- " + otherC.map);
        
        return this;
    }
    
    private java.util.HashMap<String, ExchangeRate> exchangeRates = new java.util.HashMap<String,ExchangeRate>();    
}
