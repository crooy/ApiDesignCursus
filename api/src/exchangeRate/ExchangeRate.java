/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangeRate;

import java.math.BigDecimal;

/**
 * ExchangeRate
 * @author ronald
 */
public final class ExchangeRate {
    private Currency firstCurrency;
    private Currency secondCurrency;
    private BigDecimal exchangeRate;
    
    public Currency getFirstCurrency() {
        return firstCurrency;
    }
    
    public Currency getSecondCurrency() {
        return secondCurrency;
    }
    
    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }
    
    /**
     * 
     * @param first
     * @param second
     * @return True if the 'direction' of the currencies is equal to that of this ExchangeRate
     */
    public boolean canConvert(Currency first, Currency second) {
        if (second == null || first == null) throw new NullPointerException();
        return firstCurrency.equals(first) && secondCurrency.equals(second);
    }

    public ExchangeRate reverse() throws ExchangeRateCalculatorException
    {
        return new ExchangeRate(BigDecimal.ONE.divide(exchangeRate), secondCurrency, firstCurrency);
    }
    
    public ExchangeRate(BigDecimal exchangeRate, Currency firstCurrency, Currency secondCurrency) throws ExchangeRateCalculatorException
    {
        if (exchangeRate == null) throw new ExchangeRateCalculatorException("value cannot be null");
        if (firstCurrency == null) throw new ExchangeRateCalculatorException("first currenty cannot be null");
        if (secondCurrency == null) throw new ExchangeRateCalculatorException("second currenty cannot be null");
        if (exchangeRate.compareTo(BigDecimal.ZERO) <= 0 ) throw new ExchangeRateCalculatorException("rate cannot be negative or 0.0 ");
        if (firstCurrency.equals(secondCurrency)) throw new ExchangeRateCalculatorException("cannot define two equal currencies");
        
        this.exchangeRate = exchangeRate;
        this.firstCurrency = firstCurrency;
        this.secondCurrency = secondCurrency;
    }

    /**
     * Compares this class with another exchange rate based on the currencies.
     * Note that the exchange rate is not taken into account in the comparison.
     * @param obj The right-hand side of the comparison.
     * @return True when currencies of both objects are equal, otherwise return False.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ExchangeRate other = (ExchangeRate) obj;
        if (this.firstCurrency != other.firstCurrency && (this.firstCurrency == null || !this.firstCurrency.equals(other.firstCurrency))) {
            return false;
        }
        if (this.secondCurrency != other.secondCurrency && (this.secondCurrency == null || !this.secondCurrency.equals(other.secondCurrency))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (this.firstCurrency != null ? this.firstCurrency.hashCode() : 0);
        hash = 59 * hash + (this.secondCurrency != null ? this.secondCurrency.hashCode() : 0);
        return hash;
    }
}
