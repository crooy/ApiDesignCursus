/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangeRate;

/**
 *
 * @author user
 */
public final class ExchangeRate {
    private String _srcCurrency;
    private String _destCurrency;
    private int _srcValue;
    private int _destValue;
    
    public static ExchangeRate create(String srcCurrency, String destCurrency, int srcValue, int destValue) {
        return new ExchangeRate(srcCurrency, destCurrency, srcValue, destValue);
    }
            
    private ExchangeRate(String srcCurrency, String destCurrency, int srcValue, int destValue) throws IllegalArgumentException
    {
        if (srcCurrency == null) throw new IllegalArgumentException("srcCurrency is null");
        if (destCurrency == null) throw new IllegalArgumentException("destCurrency is null");
        if (destValue == 0) throw new IllegalArgumentException("destValue 'divisor' is 0");
        
        _srcCurrency = srcCurrency;
        _destCurrency = destCurrency;
        _srcValue = srcValue;
        _destValue = destValue;
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
        return _srcValue;
    }

    /**
     * @return the _destValue
     */
    public int getDestValue() {
        return _destValue;
    }
}
