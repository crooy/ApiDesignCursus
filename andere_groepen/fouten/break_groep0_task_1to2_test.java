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
public class break_groep0_task_1to2_test_for_task1 {
    
    public break_groep0_task_1to2_test_for_task1() {
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
    public void TestImplementCalculatorInterface() {
        // in task 2, a method is added to an interface from task 1
        // this inner class implements that interface
        class CalculatorTestImplementation implements Calculator {

            @Override
            public double convert(String currencyFrom, String currencyTo, double amount) {
                return 0.0;
            }
            
        }
        
        CalculatorTestImplementation calc = new CalculatorTestImplementation();
        assertEquals(0.0, calc.convert(null, null, 0.0), 0.0);
    }
}
