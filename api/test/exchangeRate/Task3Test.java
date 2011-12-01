package exchangeRate;

import junit.framework.TestCase;
import exchangeRateProvider.ChangingExchangeRates;

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
    private static CalculatorModule calculatorModule;
    private static final int ROUNDING_SCALE = 50;
        
    public Task3Test(String testName) {
        super(testName);
      
        calculatorModule = CalculatorModule.create();
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
        return null;
    }

    public void testFewQueriesForOnlineCalculator() {
        Calculator c = createOnlineCZKUSDCalculator();
        doFewQueriesForOnlineCalculator(c);
    }

    static void doFewQueriesForOnlineCalculator(Calculator c) {
        // convert $5 to CZK using c:
        //assertEquals("Result is 80 CZK");

        // convert $8 to CZK using c:
        //assertEquals("Result is 127.92 CZK");

        // convert $1 to CZK using c:
        //assertEquals("Result is 15.98 CZK");

        // convert 15.97CZK to USD using c:
        //assertEquals("Result is 1$");

        fail("Implement me!");
    }

    /** Join the Calculator and show they behave sane.
     */
    public void testOnlineCalculatorComposition() throws Exception {
        Calculator c = merge(
            createOnlineCZKUSDCalculator(),
            Task1Test.createSKKtoCZK()
        );

        // convert 16CZK to SKK using c:
        // assertEquals("Result is 20 SKK");

        // convert 500SKK to CZK using c:
        // assertEquals("Result is 400 CZK");

        doFewQueriesForOnlineCalculator(c);
    }
    
    /** Merge all exchange rates of calculator 1 with calculator 2.
     * Implement this using your API, preferably this method just delegates
     * into some API method which does the actual work, without requiring
     * API clients to code anything complex.
     */
    public static Calculator merge(Calculator one, Calculator two) throws ExchangeRateCalculatorException {
        return calculatorModule.getCalculatorFactory().create(one, two);
    }
}

