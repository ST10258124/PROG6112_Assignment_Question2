/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog_assignment_one_question_2;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vivek
 */
public class AccountTest {
    
    public AccountTest() {
    }

    /**
     * Test of checkUsernameAvailability method, of class Account.
     */
    @Test
    public void testCheckUsernameAvailability() {
        System.out.println("checkUsernameAvailability");
        String accountName = "Barney";
        Account instance = new Account();
        boolean result = instance.checkUsernameAvailability(accountName);
        assertEquals(true, result);
    }
    
    @Test
    public void testCheckUsernameAvailability_NotAvailable() {
        System.out.println("checkUsernameAvailability");
        String accountName = "Barney";
        Account instance = new Account();
        
        instance.createAccount(accountName, "(ABCdef123)", "Savings", "1234567890123");
        
        boolean result = instance.checkUsernameAvailability(accountName);
        assertEquals(false, result);
    }

    /**
     * Test of verifyIDNumber method, of class Account.
     */
    @Test
    public void testVerifyIDNumber() {
        System.out.println("verifyIDNumber");
        String id = "9906091234081";
        Account instance = new Account();
        boolean result = instance.verifyIDNumber(id);
        assertEquals(true, result);
    }
    
    @Test
    public void testVerifyIDNumber_InvalidID() {
        System.out.println("verifyIDNumber");
        String id = "1234567890";
        Account instance = new Account();
        boolean result = instance.verifyIDNumber(id);
        assertEquals(false, result);
    }

    /**
     * Test of checkPasswordComplexity method, of class Account.
     */
    @Test
    public void testCheckPasswordComplexity() {
        System.out.println("checkPasswordComplexity");
        String testPassword = "(ABCdef123)";
        Account instance = new Account();
        boolean result = instance.checkPasswordComplexity(testPassword);
        assertEquals(true, result);
    }
    
    @Test
    public void testCheckPasswordComplexity_NotComplex() {
        System.out.println("checkPasswordComplexity");
        String testPassword = "password12345";
        Account instance = new Account();
        boolean result = instance.checkPasswordComplexity(testPassword);
        assertEquals(false, result);
    }

    /**
     * Test of login method, of class Account.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        String loginUsername = "Barney";
        String loginPassword = "(ABCdef123)";
        Account instance = new Account();
        
        instance.createAccount(loginUsername, loginPassword, "Savings", "1234567890123");
        
        boolean result = instance.login(loginUsername, loginPassword);
        assertEquals(true, result);
    }
    
    @Test
    public void testLogin_Fail() {
        System.out.println("login");
        String loginUsername = "Barney";
        String loginPassword = "(ABCdef123)";
        Account instance = new Account();
        boolean result = instance.login(loginUsername, loginPassword);
        assertEquals(false, result);
    }
    
}
