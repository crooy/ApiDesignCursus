/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangeRate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author GROUP0
 */
public final class Calculator extends Observable {
    private List<ExchangeRate> exchangeRates;
    private List<Observer> observers = new LinkedList<Observer>();

    Calculator(String currencyFrom, String currencyTo, double factor) {
        ExchangeRate er = new ExchangeRate(currencyFrom, currencyTo, factor);
        exchangeRates = new LinkedList();
        exchangeRates.add(er);
    }

    Calculator(List<ExchangeRate> exchangeRateList) {
        if (exchangeRateList == null) {
            throw new IllegalArgumentException("The given exchangeRateList is null, this is not allowed");
        }
        if (exchangeRateList.isEmpty()) {
            throw new IllegalArgumentException("The given exchangeRateList is empty, this is not allowed");
        }
        exchangeRates = new LinkedList();
        this.exchangeRates.addAll(removeDoubles(exchangeRateList));
    }

    private List<ExchangeRate> removeDoubles(List<ExchangeRate> exchangeRateList) {
        List<ExchangeRate> newList = new LinkedList<ExchangeRate>();

        for (ExchangeRate givenER: exchangeRateList) {
            boolean found = false;
            for (ExchangeRate ourER: newList) {
                if (ourER.getCurrencyFrom().equalsIgnoreCase(givenER.getCurrencyFrom()) && ourER.getCurrencyTo().equalsIgnoreCase(givenER.getCurrencyTo())) {
                    found = true;
                }
            }
            if (!found) {
                newList.add(givenER);
            }
        }
        return newList;
    }

    public double convert(String currencyFrom, String currencyTo, double amount) {
        if (currencyFrom == null || currencyFrom.isEmpty()) {
            throw new IllegalArgumentException("currencyFrom is null or empty");
        }
        if (currencyTo == null || currencyTo.isEmpty()) {
            throw new IllegalArgumentException("currencyTo is null or empty");
        }
        boolean fromSeen = false;
        boolean toSeen = false;
        ExchangeRate reverseER = null;
        for (ExchangeRate er: exchangeRates) {
            if (currencyFrom.equalsIgnoreCase(er.getCurrencyFrom()) && currencyTo.equalsIgnoreCase(er.getCurrencyTo())) {
                setChanged();
                notifyObservers(er);
                clearChanged();
                return amount * er.getFactor();
            }
            if (currencyFrom.equalsIgnoreCase(er.getCurrencyTo()) && currencyTo.equalsIgnoreCase(er.getCurrencyFrom())) {
                reverseER = er;
            }
            if (er.getCurrencyFrom().equalsIgnoreCase(currencyFrom) || er.getCurrencyTo().equalsIgnoreCase(currencyFrom))
                fromSeen = true;
            if (er.getCurrencyFrom().equalsIgnoreCase(currencyTo) || er.getCurrencyTo().equalsIgnoreCase(currencyTo))
                toSeen = true;
        }
        if (fromSeen && toSeen) {
            setChanged();
            notifyObservers(reverseER);
            clearChanged();
            return amount / reverseER.getFactor();
        }
        if (!fromSeen && !toSeen)
            throw new IllegalArgumentException("Given currencies not valid (String incorrect).");
        if (!fromSeen)
            throw new IllegalArgumentException("Given currencyFrom not valid (String incorrect).");
        throw new IllegalArgumentException("Given currencyTo not valid (String incorrect).");
    }

    public List<ExchangeRate> getExchangeRates() {
        return new ArrayList<ExchangeRate>(exchangeRates);
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
        observers.add(o);
    }

    @Override
    public synchronized void deleteObserver(Observer o) {
        super.deleteObserver(o);
        observers.remove(o);
    }

    @Override
    public synchronized void deleteObservers() {
        super.deleteObservers();
        observers.clear();
    }

    List<Observer> getObservers() {
        return observers;
    }
    
}
