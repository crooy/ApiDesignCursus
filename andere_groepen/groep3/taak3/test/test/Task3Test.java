
package test;

import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;
import exchangeRate.Calculator;
import exchangeRate.CalculatorImpl;
import exchangeRate.Currencies;
import exchangeRate.ExchangeRate;
import exchangeRate.ExchangeRateImpl;


/** The exchange rates are not always the same. They are changing. Day by day, hour by hour,        
  * minute by minute. For every bank it is important to always have the actual exchange rate
  * available in the system. Create a pluggable Calculator that will always have up-to-date 
  * values of its exchange rates.

  * The quest for today is to allow 3rd party developer to write a calculator that adjusts its      
  * exchange rate every time it is queried. This calculator is written by independent vendor,  
  * the vendor knows only your Calculator API, he does not know what the whole system looks 
  * like and how the calculator is supposed to be used to calculate exchanges
  **/
public class Task3Test extends TestCase {
    public Task3Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
    }

    @Override
    protected void tearDown() throws Exception {
    }

    // Backward compatibly enhance your existing API to support following
    // usecases:
    //


    /** Without knowing anything about the surrounding system, write an
     * implementation of calculator that will return different rates every time
     * it is queried. Convert USD to CZK and vice versa. Start with the rate of
     * 1USD = 16CZK and adjust it in favor of CZK by 0.01 CZK with every query.
     * As soon as you reach 1USD = 15CZK adjust it by 0.01 CZK in favor of USD
     * until you reach 1USD = 16CZK
     *
     * @return new instance of "online" USD and CZK calculator starting with rate 1USD = 16CZK
     */
    public static Calculator createOnlineCZKUSDCalculator() {
        // initial rate: 1USD = 16CZK
        // 2nd query 1USD = 15.99CZK
        // 3rd query 1USD = 15.98CZK
        // until 1USD = 15.00CZK
        // then 1USD = 15.01CZK
        // then 1USD = 15.02CZK
        // and so on and on up to 1USD = 16CZK
        // and then another round to 15, etc.
        Calculator c = null;
        ExchangeRate ru = new ExchangeRateImpl();
        try {
            c = CalculatorImpl.create(Currencies.eUSD, Currencies.eCZK, ru);
        } catch (Exception e) {
            System.out.println(e);
        }

        return c;
    }

    public void testFewQueriesForOnlineCalculator() {
        Calculator c = createOnlineCZKUSDCalculator();
        doFewQueriesForOnlineCalculator(c);
    }

    static void doFewQueriesForOnlineCalculator(Calculator c) {
        
        double result = 0;
        
        // convert $5 to CZK using c:
        //assertEquals("Result is 80 CZK");
        try {
            result = c.convert(Currencies.eUSD, Currencies.eCZK, 5);
        } catch (Exception ex) {
            Logger.getLogger(Task3Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("5$ to CZK is " + result);
        assertEquals(result, 80.0d);

        // convert $8 to CZK using c:
        //assertEquals("Result is 127.92 CZK");
        try {
            result = c.convert(Currencies.eUSD, Currencies.eCZK, 8);
        } catch (Exception ex) {
            Logger.getLogger(Task3Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("8$ to CZK is " + result);
        assertEquals(result, 127.92d);

        // convert $1 to CZK using c:
        //assertEquals("Result is 15.98 CZK");
        try {
            result = c.convert(Currencies.eUSD, Currencies.eCZK, 1);
        } catch (Exception ex) {
            Logger.getLogger(Task3Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("1$ to CZK is " + result);
        assertEquals(result, 15.98d);

        // convert 15.97CZK to USD using c:
        //assertEquals("Result is 1$");
        try {
            result = c.convert(Currencies.eCZK, Currencies.eUSD, 15.97);
        } catch (Exception ex) {
            Logger.getLogger(Task3Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("15.97CZK to USD is " + result);
        assertEquals(result, 1.0d);

        //fail("Implement me!");
    }

    /** Join the Calculator and show they behave sane.
     */
    public void testOnlineCalculatorComposition() throws Exception {
        Calculator c = Task2Test.merge(
            createOnlineCZKUSDCalculator(),
            Task1test.createSKKtoCZK()
        );

        // convert 16CZK to SKK using c:
        // assertEquals("Result is 20 SKK");
        double result = c.convert(Currencies.eCZK, Currencies.eSKK, 16);
        System.out.println("16 CZK to SKK is " + result);
        assertEquals(result,20.0d);

        // convert 500SKK to CZK using c:
        // assertEquals("Result is 400 CZK");
        result = c.convert(Currencies.eSKK, Currencies.eCZK, 500);
        System.out.println("500 SKK to CZK is " + result);
        assertEquals(result,400.0d);

        doFewQueriesForOnlineCalculator(c);
    }
}
