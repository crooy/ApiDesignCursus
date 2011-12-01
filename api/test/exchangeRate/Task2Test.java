package exchangeRate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
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
        calculatorModule.setExchangeRate(new ExchangeRate(new BigDecimal(15), new Currency("CZK"), new Currency("USD")));
        calculatorModule.setExchangeRate(new ExchangeRate(BigDecimal.ONE.divide(new BigDecimal(20), ROUNDING_SCALE, RoundingMode.HALF_EVEN), new Currency("USD"), new Currency("SKK")));
        calculatorModule.setExchangeRate(new ExchangeRate(new BigDecimal(20), new Currency("SKK"), new Currency("USD")));
        calculatorModule.setExchangeRate(new ExchangeRate(new BigDecimal(75).divide(new BigDecimal(100), ROUNDING_SCALE, RoundingMode.HALF_EVEN), new Currency("CZK"), new Currency("SKK")));
        calculatorModule.setExchangeRate(new ExchangeRate(new BigDecimal(100).divide(new BigDecimal(75), ROUNDING_SCALE, RoundingMode.HALF_EVEN), new Currency("SKK"), new Currency("CZK")));
    }

    @Override
    protected void setUp() throws Exception {
    }

    @Override
    protected void tearDown() throws Exception {
    }

         public static Calculator createCZKtoUSD() throws ExchangeRateCalculatorException {
         return calculatorModule.getCalculatorFactory().create(new Currency("CZK"), new Currency("USD"));
     }

     public static Calculator createSKKtoCZK() throws ExchangeRateCalculatorException {
         return calculatorModule.getCalculatorFactory().create(new Currency("SKK"), new Currency("CZK"));
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
        List<Pair<Currency, Currency>> exchangeList = new ArrayList<Pair<Currency, Currency>>();
        exchangeList.add(new Pair<Currency, Currency>(new Currency("USD"), new Currency("CZK")));
        exchangeList.add(new Pair<Currency, Currency>(new Currency("USD"), new Currency("SKK")));
        exchangeList.add(new Pair<Currency, Currency>(new Currency("CZK"), new Currency("SKK")));
        return calculatorModule.getCalculatorFactory().create(exchangeList);
    }

    /** Use the calculator that understands three currencies.
     */
    public void testCalculatorForUSDandCZKandSKK() throws Exception {
        Calculator c = createTripleCalculator();
        
        Currency usd = new Currency("USD");
        Currency czk = new Currency("CZK");
        Currency skk = new Currency("SKK");
        
        // convert $5 to CZK using c:
        // 75 czk
        CurrencyValue result = c.convert(new CurrencyValue(usd, new BigDecimal(5)), czk);
        assertEquals(0,result.getValue().compareTo(new BigDecimal (75)));
        assertEquals(result.getCurrency(), czk);
        
        
        // convert $5 to SKK using c:
        // assertEquals("Result is 100 SSK");
        result = c.convert(new CurrencyValue(usd, new BigDecimal(5)), skk);
        assertEquals(0,result.getValue().compareTo(new BigDecimal (100)));
        assertEquals(result.getCurrency(), skk);


        // convert 200SKK to CZK using c:
        // assertEquals("Result is 150 CZK");
        result = c.convert(new CurrencyValue(skk, new BigDecimal(200)), czk);
        assertEquals(0,result.getValue().compareTo(new BigDecimal (150)));
        assertEquals(result.getCurrency(), czk);


        // convert 200SKK to USK using c:
        // assertEquals("Result is 10 USD");
        result = c.convert(new CurrencyValue(skk, new BigDecimal(200)), usd);
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
            createCZKtoUSD(),
            createSKKtoCZK()
        );
        
        Currency usd = new Currency("USD");
        Currency czk = new Currency("CZK");
        Currency skk = new Currency("SKK");

        // convert $5 to CZK using c:
        // assertEquals("Result is 75 CZK");
        CurrencyValue result = c.convert(new CurrencyValue(usd, new BigDecimal(5)), czk);
        assertEquals(0,result.getValue().compareTo(new BigDecimal(75)));
        assertEquals(result.getCurrency(), czk);

        // convert $8 to CZK using c:
        // assertEquals("Result is 120 CZK");
        result = c.convert(new CurrencyValue(usd, new BigDecimal(8)), czk);
        assertEquals(0,result.getValue().compareTo(new BigDecimal (120)));
        assertEquals(result.getCurrency(), czk);

        // convert 1003CZK to USD using c:
        // assertEquals("Result is 59 USD");
        result = c.convert(new CurrencyValue(czk, new BigDecimal(1003)), usd);
        assertEquals(0,result.getValue().compareTo(new BigDecimal(1003).divide(new BigDecimal(15), 20, RoundingMode.HALF_EVEN)));
        assertEquals(result.getCurrency(), usd);

        // convert 15CZK using c:
        // assertEquals("Result is 20 SKK");
        result = c.convert(new CurrencyValue(czk, new BigDecimal(15)), skk);
        assertEquals(0,result.getValue().compareTo(new BigDecimal (20)));
        assertEquals(result.getCurrency(), skk);
        
        // convert 500SKK to CZK using c:
        // assertEquals("Result is 375 CZK");
        result = c.convert(new CurrencyValue(skk, new BigDecimal(500)), czk);
        assertEquals(0,result.getValue().compareTo(new BigDecimal (375)));
        assertEquals(result.getCurrency(), czk);
    }
}
