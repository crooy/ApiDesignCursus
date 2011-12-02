
import exchangeRate.Calculator;
import exchangeRate.ExchangeRate;
import junit.framework.TestCase;
/** There are many currencies around the world and many banks manipulate
 * with more than one or two at the same time. As banks are usually the
 * best paying clients, which is true even in case of your Calculator API,
 * it is reasonable to listen to their requests.
 * <p>
 * The quest for today is to enhance your existing calculator API to hold
 * information about many currencies and allow exchanges between any of 
 * them. Also, as conversion rates for different currencies usually arise 
 * from various bank departments, there is another important need. There is 
 * a need to compose two calculators into one by merging all the information 
 * about currencies they know about.
 */
public class Task2Test extends TestCase {
    public Task2Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
    }

    @Override
    protected void tearDown() throws Exception {
    }

    // As in Task1Test, keep in mind, that there are three parts
    // of the whole system:
     // 1. there is someone who knows the current exchange rate
     // 2. there is someone who wants to calculate the exchange
     // 3. party 1 provides party 2 with an object that realizes the API.
    // 
    // Please backward compatibly enhance your existing API to support     
    // following use cases:
    //
    
    /** Create a calculator that understands three currencies, USD, CZK and
     *  SKK. See below for the rates to be used. This is method for the group of users
     *  that knows the exchange rate, and needs to use the API to create objects
     *  with the exchange rates. Anyone shall be ready to call this method without
     *  any other method being called previously. The API itself shall know
     *  nothing about any rates, before this method is called.
     */
    public static Calculator createTripleCalculator() {
        // Rates: 1USD = 15CZK
        // Rates: 1USD = 20SKK
        // Rates: 75CZK = 100SKK
         Calculator calc = Calculator.create();
         calc.addExchangeRate(ExchangeRate.create("CZK", "USD", 15, 1));
         calc.addExchangeRate(ExchangeRate.create("USD", "CZK", 1, 15));
         calc.addExchangeRate(ExchangeRate.create("USD", "SKK", 1, 20));
         calc.addExchangeRate(ExchangeRate.create("SKK", "USD", 20, 1));
         calc.addExchangeRate(ExchangeRate.create("CZK", "SKK", 75, 100));
         calc.addExchangeRate(ExchangeRate.create("SKK", "CZK", 100, 75));
         return calc;
    }

    /** Use the calculator that understands three currencies.
     */
    public void testCalculatorForUSDandCZKandSKK() throws Exception {
        Calculator c = createTripleCalculator();

        // convert $5 to CZK using c:
        // assertEquals("Result is 75 CZK");
        assertEquals(75, c.convert("USD", "CZK", 5), 0.001);
        
        // convert $5 to SKK using c:
        // assertEquals("Result is 100 SKK");
         assertEquals(100, c.convert("USD", "SKK", 5), 0.001);

        // convert 200SKK to CZK using c:
        // assertEquals("Result is 150 CZK");
         assertEquals(150, c.convert("SKK", "CZK", 200), 0.001);

        // convert 200SKK to USK using c:
        // assertEquals("Result is 10 USD");
         assertEquals(10, c.convert("SKK", "USD", 200), 0.001);
    }

    /** Merge all exchange rates of calculator 1 with calculator 2.
     * Implement this using your API, preferably this method just delegates
     * into some API method which does the actual work, without requiring
     * API clients to code anything complex.
     */
    public static Calculator merge(Calculator one, Calculator two) {
        return Calculator.merge(one, two);
    }

    /** Join the Calculator from previous task, Task1Test and show that it
     * can be used to do reasonable exchanges.
     */
    public void testCalculatorComposition() throws Exception {
        Calculator c = merge(
            Task1Test.createCZKtoUSD(),
            Task1Test.createSKKtoCZK()
        );

        // convert $5 to CZK using c:
        // assertEquals("Result is 85 CZK");
         assertEquals(85, c.convert("USD", "CZK", 5), 0.001);

        // convert $8 to CZK using c:
        // assertEquals("Result is 136 CZK");
         assertEquals(136, c.convert("USD", "CZK", 8), 0.001);

        // convert 1003CZK to USD using c:
        // assertEquals("Result is 59 USD");
         assertEquals(59, c.convert("CZK", "USD", 1003), 0.001);

        // convert 16CZK using c:
        // assertEquals("Result is 20 SKK");
         assertEquals(20, c.convert("CZK", "SKK", 16), 0.001);

        // convert 500SKK to CZK using c:
        // assertEquals("Result is 400 CZK");
         assertEquals(400, c.convert("SKK", "CZK", 500), 0.001);

    }
}


