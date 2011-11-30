/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangeRate;

/**
 * ExchangeRate
 * @author ronald
 */
public final class ExchangeRate {
    private Currency firstCurrency;
    private Currency secondCurrency;
    private double exchangeRate;
    
    public Currency getFirstCurrency() {
        return firstCurrency;
    }
    
    public Currency getSecondCurrency() {
        return secondCurrency;
    }
    
    public double getExchangeRate() {
        return exchangeRate;
    }
    
    public ExchangeRate(double exchangeRate, Currency firstCurrency, Currency secondCurrency)
    {
        this.exchangeRate = exchangeRate;
        this.firstCurrency = firstCurrency;
        this.secondCurrency = secondCurrency;
    }

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
