/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangeRate;

/**
 *
 * @author user
 */
public interface ICalculatorFactory {
    Calculator create(Currency firstCurrency, Currency secondCurrency) throws ExchangeRateCalculatorException;
    Calculator create(Calculator firstCalculator, Calculator secondCalculator) throws ExchangeRateCalculatorException;
}
