/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import exchangeRate.Calculator;
import exchangeRate.CalculatorImpl;
import exchangeRate.Currencies;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import junit.framework.TestCase;

/**
 *
 * @author user
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({})
public class Task1test extends TestCase {
    
    
    public Task1test(String testName) {
        super(testName);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }


/** Finish the Calculator API, and then write bodies of methods inside
 * of this class to match the given tasks. To fulfill your task, use the
 * API define in the <code> exchangeRate</code> package.
 * Do not you reflection, or other “hacks” as your code
 * shall run without any runtime permissions.
 */



    // @Override
    // protected void setUp() throws Exception {
    // }
    //  @Override
    // protected void tearDown() throws Exception {
    // }
    //
    // Imagine that there are three parts of the whole system:
    // 1. there is someone who knows the current exchange rate
    // 2. there is someone who wants to calculate the exchange
    // 3. party 1 provides party 2 with an object that realizes the API.
    // Please design such API
    //
    /** Create a calculator that understands two currencies, CZK and
     *  USD. Make 1 USD == 17 CZK. This is a method provided for #1 group -
     *  e.g. those that know the exchange rate. They somehow need to create
     *  the objects from the API and tell them the exchange rate. The API itself
     *  knows nothing about any rates, before the createCZKtoUSD method is called.
     *
     * Creation of the calculator shall not require subclassing of any class
     * or interface on the client side.
     *
     * @return prepared calculator ready for converting USD to CZK and CZK to USD
     */
    public static Calculator createCZKtoUSD() {

        //our code
        Calculator c = null;
        try {
            c = CalculatorImpl.create(Currencies.eUSD, Currencies.eCZK, 17);
        } catch (Exception e) {
            System.out.println(e);
        }

        return c;
    }

    /** Create calculator that understands two currencies, CZK and
     *  SKK. Make 100 SKK == 80 CZK. Again this is method for the #1 group -
     *  it knows the exchange rate, and needs to use the API to create objects
     *  with the exchange rate. Anyone shall be ready to call this method without
     *  any other method being called previously. The API itself shall know
     *  nothing about any rates, before this method is called.
     *
     * Creation of the calculator shall not require subclassing of any class
     * or interface on the client side.
     * 
     * @return prepared calculator ready for converting SKK to CZK and CZK to SKK
     */
    public static Calculator createSKKtoCZK() {

        //our code
        Calculator c = null;
        try {
            c = CalculatorImpl.create(Currencies.eSKK, Currencies.eCZK, 0.8);
        } 
        catch (Exception e) {
            System.out.println(e);
        }

        return c;
    }

    //
    // now the methods for group #2 follow:
    // this group knows nothing about exchange rates, but knows how to use
    // the API to calculate exchanges. It somehow (by calling a method provided by 
    // group #1) gets an object from the API and uses it to do the rate caluclations.
    //
    /** Use the calculator from <code>createCZKtoUSD</code> method and do few calculations
     * with it.
     */
    public void testExchangeCZKUSD() throws Exception {
        Calculator c = createCZKtoUSD();
        
        if(null == c) {
            throw new Exception("Failed to created Calculator CZK to USD");
        } 
                    
        double result = c.convert(Currencies.eUSD, Currencies.eCZK, 5);
        System.out.println("5$ to CZK is " + result);
        // convert $5 to CZK using c:
        assertEquals(result, 85.0d);

        // convert $8 to CZK
        result = c.convert(Currencies.eUSD, Currencies.eCZK, 8);
        System.out.println("8$ to CZK is " + result);

        assertEquals(result, 136.0d);


        // convert 1003CZK to USD
        result = c.convert(Currencies.eCZK, Currencies.eUSD, 1003);
        System.out.println("1003CZK to USD is " + result);

        assertEquals(result, 59.0d);

        // assertEquals("Result is 59 USD");
    }

    /** Use the calculator from <code>createSKKtoCZK</code> method and do a few calculations
     * with it.
     */
    public void testExchangeSKKCZK() throws Exception {
        Calculator c = createSKKtoCZK();
        
        if(null == c) {
            throw new Exception("Failed to created Calculator CZK to USD");
        } 
        
        double result = c.convert(Currencies.eCZK, Currencies.eSKK, 16);
        System.out.println("16 CZK to SKK is " + result);
        // convert 16CZK using c:
        assertEquals(result,20.0d);

        result = c.convert(Currencies.eSKK, Currencies.eCZK, 500);
        System.out.println("500 SKK to CZK is " + result);
        // convert 500SKK to CZK
        assertEquals(result,400.0d);
    }

    /** Verify that the CZK to USD calculator knows nothing about SKK.
     */
    public void testCannotConvertToSKKwithCZKUSDCalculator() throws Exception {
        Calculator c = createCZKtoUSD();
        
        if(null == c) {
            throw new Exception("Failed to created Calculator CZK to USD");
        } 
        
        double result = -1.0;
        try {
            result = c.convert(Currencies.eUSD, Currencies.eSKK, 5);
            System.out.println("5 USD to SKK is " + result);
        } catch (Exception e) {
            System.out.println(e);
        }
        assertEquals(result, -1d);
        // convert $5 to SKK, the API shall say this is not possible

        result = -1.0;
        try {
            result = c.convert(Currencies.eSKK, Currencies.eCZK, 500);
            System.out.println("500 SKK to CZK is " + result);
        } catch (Exception e) {
            System.out.println(e);
        }
        assertEquals(result, -1d);
        // convert 500 SKK to CZK, the API shall say this is not possible
    }

    /** Verify that the CZK to SKK calculator knows nothing about USD.
     */
    public void testCannotConvertToUSDwithCZKSKKCalculator() throws Exception {
        Calculator c = createSKKtoCZK();
        
        if(null == c) {
            throw new Exception("Failed to created Calculator CZK to USD");
        } 
        
        double result = -1.0;
        try {
            result = c.convert(Currencies.eUSD, Currencies.eSKK, 5);
            System.out.println("5 USD to SKK is " + result);
        } catch (Exception e) {
            System.out.println(e);
        }
        assertEquals(result, -1d);
        // convert $5 to SKK, the API shall say this is not possible
        
        result = -1.0;
        try {
            result = c.convert(Currencies.eCZK, Currencies.eUSD, 500);
            System.out.println("500 CZK to USD is " + result);
        } catch (Exception e) {
            System.out.println(e);
        }
        assertEquals(result, -1d);
        // convert 500 CZK to USD, the API shall say this is not possible
    }
}


