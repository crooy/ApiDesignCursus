/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import exchangeRate.ExchangeRate;

/**
 *
 * @author user
 */
public class DynamicExchangeRate extends ExchangeRate {
    
    public DynamicExchangeRate linkedRate = null;
    
    public ExchangeRate getCurrentExchangeRate(String srcCurrency, String destCurrency) {
        return null;
    }
    
    public DynamicExchangeRate(String srcCurrency, String destCurrency, int srcValue, int destValue)
    {
        super(srcCurrency, destCurrency, srcValue, destValue);
    }
    
    private boolean goingUp = false;
    // In this function we return the rate but also updated the current rate
    // based on the rules as defined in the task
    @Override public double getRate()
    {
       double rate = _destValue / _srcValue;
       double currentRate = rate;
       if ((_srcCurrency.equals("USD") && _destCurrency.equals("CZK")) || (_destCurrency.equals("USD") && _srcCurrency.equals("CZK")))
       {
            if (rate >= (1/16.01)) 
            {
                 goingUp = false;
                 rate -= 0.01;
            }
            else
            if (rate <= (1/15.01)) 
            {
                 goingUp = true;
                 rate += 0.01;
            }
            else
            {
                if (goingUp) 
                {
                    rate += 0.01;
                }
                else
                {
                     rate -= 0.01;
                }
            }
            if((_srcCurrency.equals("USD") && _destCurrency.equals("CZK"))){
                _destValue = (rate * _destValue) / currentRate;
                if (linkedRate != null){
                    linkedRate._srcValue = _destValue;
                }
            }
            else
            {
                _srcValue = (rate * _srcValue) / currentRate;
                if (linkedRate != null){
                    linkedRate._destValue = _srcValue;
                }
            }
            
       }
       return currentRate;

    }
}
