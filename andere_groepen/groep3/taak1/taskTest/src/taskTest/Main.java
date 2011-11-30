/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package taskTest;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Task1test tt = new Task1test("testExchangeCZKUSD");
            tt.testExchangeCZKUSD();
            tt = new Task1test("testExchangeSKKCZK");
            tt.testExchangeSKKCZK();
            tt = new Task1test("testCannotConvertToSKKwithCZKUSDCalculator");
            tt.testCannotConvertToSKKwithCZKUSDCalculator();
            tt = new Task1test("testCannotConvertToUSDwithCZKSKKCalculator");
            tt.testCannotConvertToUSDwithCZKSKKCalculator();

            // TODO code application logic here
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
