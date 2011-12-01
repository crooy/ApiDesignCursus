/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangeRate;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author frank
 */
public class break_groep3_task_1to2_test_for_task1 {
    
    public break_groep3_task_1to2_test_for_task1() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void TestImplementCalculatorInterface() throws Exception {
        // in task 2, a method is added to an interface from task 1
        // this inner class implements that interface
        class CalculatorTestImplementation implements CalcultorAPI {

            @Override
            public double convert(Currencies from, Currencies to, double amount) throws Exception {
                return 0.0;
            }
            
        }
        
        CalculatorTestImplementation calc = new CalculatorTestImplementation();
        assertEquals(0.0, calc.convert(null, null, 0.0), 0.0);
    }
    
    @Test
    public void TestUseCalculatorClass() throws Exception {
        // in task 2, the factory method for Calculator objects is moved to another class
        // this statement uses that method
        Calculator c = Calculator.create(Currencies.eCZK, Currencies.eSKK, 1.0);
    }
    
    @Test
    public void TestFromDoubleToBigDecimal() throws Exception {
        // in task 2, the calculator switched from doubles to BigDecimals
        // BigDecimals are a lot more picky about their input, such as NaNs
        // this statement initializes the calculator with a NaN
        Calculator c = Calculator.create(Currencies.eCZK, Currencies.eSKK, Double.NaN);
    }
    
    @Test
    public void TestThrownException() throws Exception {
        // in task 2, the message string given with an Exception was changed
        // this test depends on the message string
        Calculator c = Calculator.create(Currencies.eCZK, Currencies.eSKK, 1.0);
        try {
            c.convert(Currencies.eCZK, Currencies.eUSD, 1.0);
        }
        catch (Exception ex) {
            assertEquals("Currencies must not be equal and eCZK or eSKK!", ex.getMessage());
        }
    }
}
