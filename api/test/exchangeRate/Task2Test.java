package exchangeRate;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    
    private static CalculatorModule calculatorModule;
    private static final int ROUNDING_SCALE = 50;
    
    public Task2Test(String testName) throws ExchangeRateCalculatorException {
        super(testName);
        
        calculatorModule = CalculatorModule.create();
        
        calculatorModule.setExchangeRate(new ExchangeRate(BigDecimal.ONE.divide(new BigDecimal(15), ROUNDING_SCALE, RoundingMode.HALF_EVEN), new Currency("USD"), new Currency("CZK")));
        calculatorModule.setExchangeRate(new ExchangeRate(BigDecimal.ONE.divide(new BigDecimal(20), ROUNDING_SCALE, RoundingMode.HALF_EVEN), new Currency("USD"), new Currency("SKK")));
        calculatorModule.setExchangeRate(new ExchangeRate(new BigDecimal(75).divide(new BigDecimal(100), ROUNDING_SCALE, RoundingMode.HALF_EVEN), new Currency("CZK"), new Currency("SKK")));
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
    public static Calculator createTripleCalculator() throws ExchangeRateCalculatorException {
        // Rates: 1USD = 15CZK
        // Rates: 1USD = 20SKK
        // Rates: 75CZK = 100SKK
        Calculator calculatorUSDtoCZK = calculatorModule.getCalculatorFactory().create(new Currency("USD"), new Currency("CZK"));
        //Calculator calculatorUSDtoSKK = calculatorModule.getCalculatorFactory().create(new Currency("USD"), new Currency("SKK"));
        //Calculator calculatorCZKtoSKK = calculatorModule.getCalculatorFactory().create(new Currency("CZK"), new Currency("SKK"));
        return calculatorUSDtoCZK;
    }

    /** Use the calculator that understands three currencies.
     */
    public void testCalculatorForUSDandCZKandSKK() throws Exception {
        Calculator c = createTripleCalculator();
        
        Currency usd = new Currency("USD");
        Currency czk = new Currency("CZK");
        Currency ssk = new Currency("SSK");
        
        // convert $5 to CZK using c:
        CurrencyValue result = c.convert(new CurrencyValue(usd, new BigDecimal(5)), czk);
        assertEquals(0,result.getValue().compareTo(new BigDecimal (75)));
        assertEquals(result.getCurrency(), czk);
        
        
        // convert $5 to SKK using c:
        // assertEquals("Result is 100 SSK");
        result = c.convert(new CurrencyValue(usd, new BigDecimal(5)), ssk);
        assertEquals(0,result.getValue().compareTo(new BigDecimal (100)));
        assertEquals(result.getCurrency(), ssk);


        // convert 200SKK to CZK using c:
        // assertEquals("Result is 150 CZK");
        result = c.convert(new CurrencyValue(ssk, new BigDecimal(200)), czk);
        assertEquals(0,result.getValue().compareTo(new BigDecimal (150)));
        assertEquals(result.getCurrency(), czk);


        // convert 200SKK to USK using c:
        // assertEquals("Result is 10 USD");
        result = c.convert(new CurrencyValue(ssk, new BigDecimal(200)), usd);
        assertEquals(0,result.getValue().compareTo(new BigDecimal (10)));
        assertEquals(result.getCurrency(), usd);
    }

    /** Merge all exchange rates of calculator 1 with calculator 2.
     * Implement this using your API, preferably this method just delegates
     * into some API method which does the actual work, without requiring
     * API clients to code anything complex.
     */
    public static Calculator merge(Calculator one, Calculator two) throws ExchangeRateCalculatorException {
        return calculatorModule.getCalculatorFactory().create(one, two);
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

        // convert $8 to CZK using c:
        // assertEquals("Result is 136 CZK");

        // convert 1003CZK to USD using c:
        // assertEquals("Result is 59 USD");

        // convert 16CZK using c:
        // assertEquals("Result is 20 SKK");

        // convert 500SKK to CZK using c:
        // assertEquals("Result is 400 CZK");

    }
}
