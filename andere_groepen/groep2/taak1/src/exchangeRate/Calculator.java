package exchangeRate;

import java.util.ArrayList;

public final class Calculator {
    ArrayList<ExchangeRate> _rates = new ArrayList<ExchangeRate>();
    
    public static Calculator create() {
        return new Calculator();
    }
     
    private Calculator() {        
    }
    
    public void addExchangeRate(ExchangeRate rate) {
        for (ExchangeRate er : _rates) {
            if (   er.getSrcCurrency().compareTo(rate.getSrcCurrency()) == 0
                && er.getDestCurrency().compareTo(rate.getDestCurrency()) == 0) {
                _rates.remove(er);
            }
        }
        _rates.add(rate);
    }
    
    public double convert(String srcCurrency, String destCurrency, double amount) throws IllegalArgumentException, ArithmeticException, ConversionNotSupportedException {
        if (srcCurrency == null) throw new IllegalArgumentException("srcCurrency is null");
        if (destCurrency == null) throw new IllegalArgumentException("destCurrency is null");
        
        for (ExchangeRate er : _rates) {
            String src = er.getSrcCurrency();
            String dest = er.getDestCurrency();
            
            if (src.compareTo(srcCurrency) == 0 && dest.compareTo(destCurrency) == 0) {
                return amount * er.getDestValue() / er.getSrcValue();
            }
        }
        
        throw new ConversionNotSupportedException();
    }
}

