/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangeRate;

/**
 *
 * @author ronald
 */
public final class ExchangeRate {
    private Currency to;
    private Currency from;
    private double rate;
    
    public Currency getTo() {
        return to;
    }
    
    public Currency getFrom() {
        return from;
    }
    
    public double getRate() {
        return rate;
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
        if (this.to != other.to && (this.to == null || !this.to.equals(other.to))) {
            return false;
        }
        if (this.from != other.from && (this.from == null || !this.from.equals(other.from))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + (this.to != null ? this.to.hashCode() : 0);
        hash = 19 * hash + (this.from != null ? this.from.hashCode() : 0);
        return hash;
    }
}
