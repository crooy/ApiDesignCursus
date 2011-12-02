/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import exchangeRate.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ronald
 */
public class DiffTest {
    @Test(expected = NullPointerException.class)
    public void testDifferentException() throws Exception{
        Calculator c = null;
        
        c = CalculatorImpl.create(Currencies.eUSD, null, 17);
        c.convert(Currencies.eUSD, Currencies.eUSD, 2);
    }
    
}

