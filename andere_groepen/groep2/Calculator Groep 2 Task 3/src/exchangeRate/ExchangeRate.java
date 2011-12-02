/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangeRate;

/**
 *
 * @author user
 */
public class ExchangeRate {
    protected String _srcCurrency;
    protected String _destCurrency;
    protected double _srcValue;
    protected double _destValue;
    
    public static ExchangeRate create(String srcCurrency, String destCurrency, int srcValue, int destValue) {
        return new ExchangeRate(srcCurrency, destCurrency, srcValue, destValue);
    }

    public static ExchangeRate create(String srcCurrency, String destCurrency, double srcValue, double destValue) {
        return new ExchangeRate(srcCurrency, destCurrency, srcValue, destValue);
    }
            
    protected ExchangeRate(String srcCurrency, String destCurrency, int srcValue, int destValue) throws IllegalArgumentException
    {
         this(srcCurrency, destCurrency, (double)srcValue, (double)destValue);
    }

    protected ExchangeRate(String srcCurrency, String destCurrency, double srcValue, double destValue) throws IllegalArgumentException
    {
        if (srcCurrency == null) throw new IllegalArgumentException("srcCurrency is null");
        if (destCurrency == null) throw new IllegalArgumentException("destCurrency is null");
        //if (destValue.equals(New BigDecimal(0.00)) throw new IllegalArgumentException("destValue 'divisor' is 0");
        
        _srcCurrency = srcCurrency;
        _destCurrency = destCurrency;
        _srcValue = srcValue;
        _destValue = destValue;
    }

    
    public double getRate()
    {
        return _destValue / _srcValue;
    }
    
    /**
     * @return the _srcCurrency
     */
    public String getSrcCurrency() {
        return _srcCurrency;
    }

    /**
     * @return the _destCurrency
     */
    public String getDestCurrency() {
        return _destCurrency;
    }

    /**
     * @return the _srcValue
     */
    public int getSrcValue() {
        return (int)_srcValue;
    }

    /**
     * @return the _destValue
     */
    public int getDestValue() {
        return (int)_destValue;
    }
}
