
package test;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;
import exchangeRate.Calculator;
import exchangeRate.CalculatorImpl;
import exchangeRate.Currencies;

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
        
        Calculator c1 = null;
        Calculator c2 = null;
        Calculator c3 = null;
        
        try {
            c1 = CalculatorImpl.create(Currencies.eUSD, Currencies.eCZK, 15);
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            c2 = CalculatorImpl.create(Currencies.eUSD, Currencies.eSKK, 20);
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            c3 = CalculatorImpl.create(Currencies.eSKK, Currencies.eCZK, 0.75);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        c1 = merge(c1, c2);
        c1 = merge(c1, c3);
        
        return c1;
    }

    /** Use the calculator that understands three currencies.
     */
    public void testCalculatorForUSDandCZKandSKK() throws Exception {
        Calculator c = createTripleCalculator();

        if(null == c) {
            throw new Exception("Failed to create Triple Calculator");
        } 
                    
        // convert $5 to CZK using c:
        // assertEquals("Result is 75 CZK");
        double result = c.convert(Currencies.eUSD, Currencies.eCZK, 5);
        System.out.println("5$ to CZK is " + result);
        assertEquals(result, 75.0d);

        // convert $5 to SKK using c:
        // assertEquals("Result is 100 SKK");
        result = c.convert(Currencies.eUSD, Currencies.eSKK, 5);
        System.out.println("5$ to SKK is " + result);
        assertEquals(result, 100.0d);
        
        // convert 200SKK to CZK using c:
        // assertEquals("Result is 150 CZK");
        result = c.convert(Currencies.eSKK, Currencies.eCZK, 200);
        System.out.println("200SKK to CZK is " + result);
        assertEquals(result, 150.0d);

        // convert 200SKK to USK using c:
        // assertEquals("Result is 10 USD");
        result = c.convert(Currencies.eSKK, Currencies.eUSD, 200);
        System.out.println("5SKK to USD is " + result);
        assertEquals(result, 10.0d);
    }

    /** Merge all exchange rates of calculator 1 with calculator 2.
     * Implement this using your API, preferably this method just delegates
     * into some API method which does the actual work, without requiring
     * API clients to code anything complex.
     */
    public static Calculator merge(Calculator one, Calculator two) {
        Calculator result = null;
        
        try {
            result = one.merge(two);
        } catch (Exception ex) {
            Logger.getLogger(Task2Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }

    /** Join the Calculator from previous task, Task1Test and show that it
     * can be used to do reasonable exchanges.
     */
    public void testCalculatorComposition() throws Exception {
        Calculator c = merge(
            Task1test.createCZKtoUSD(),
            Task1test.createSKKtoCZK()
        );

        if(null == c) {
            throw new Exception("Failed to merge Calculators");
        } 
                    
        // convert $5 to CZK using c:
        // assertEquals("Result is 85 CZK");
        double result = c.convert(Currencies.eUSD, Currencies.eCZK, 5);
        System.out.println("5$ to CZK is " + result);
        assertEquals(result, 85.0d);

        // convert $8 to CZK using c:
        // assertEquals("Result is 136 CZK");
        result = c.convert(Currencies.eUSD, Currencies.eCZK, 8);
        System.out.println("8$ to CZK is " + result);
        assertEquals(result, 136.0d);

        // convert 1003CZK to USD using c:
        // assertEquals("Result is 59 USD");
        result = c.convert(Currencies.eCZK, Currencies.eUSD, 1003);
        System.out.println("1003CZK to USD is " + result);
        assertEquals(result, 59.0d);

        // convert 16CZK using c:
        // assertEquals("Result is 20 SKK");
        result = c.convert(Currencies.eCZK, Currencies.eSKK, 16);
        System.out.println("16 CZK to SKK is " + result);
        assertEquals(result,20.0d);

        // convert 500SKK to CZK using c:
        // assertEquals("Result is 400 CZK");
        result = c.convert(Currencies.eSKK, Currencies.eCZK, 500);
        System.out.println("500 SKK to CZK is " + result);
        assertEquals(result,400.0d);

    }
}
