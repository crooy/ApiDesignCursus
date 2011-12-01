/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangeRate;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author user
 */
public interface ICalculatorFactory {
    Calculator create(Currency firstCurrency, Currency secondCurrency) throws ExchangeRateCalculatorException;
    Calculator create(Calculator firstCalculator, Calculator secondCalculator) throws ExchangeRateCalculatorException;
    Calculator create(Collection<Pair<Currency, Currency>> currencyPairs) throws ExchangeRateCalculatorException;
}
