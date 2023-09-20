package prog_assignment_one_question_2;

import java.util.Scanner;

/**
 *
 * @author Vivek Rajaram (ST10258124)
 */
public class PROG_Assignment_One_Question_2 {
private static final Scanner INPUT = new Scanner(System.in);
static Account acc = new Account();
static String targetAccount, userSelection, username, password;

    public static void main(String[] args) {
        
        openingPage();
        
        Checking_acc checking = new Checking_acc(acc.getBalanceChecking());
        Saving_acc saving = new Saving_acc(acc.getBalanceSaving());
        
        do {
            dashboard(checking, saving);
            userSelection = INPUT.nextLine();
//===============================================================================
            switch(userSelection){
                case "1":
                    System.out.println("1) Checking account\n2) Savings account\nDeposit into which account?");
                    verifyTarget();
                    transactions(targetAccount, "Deposit", checking, saving);
                    break;
                    
                case "2":
                    System.out.println("1) Checking account\n2) Savings account\nWithdraw from which account?");
                    verifyTarget();
                    transactions(targetAccount, "Withdraw", checking, saving);
                    break;
                            
                case "3":
                    System.out.println("1) Checking account\n2) Savings account\nTransfer from which account?");
                    verifyTarget();
                    transactions(targetAccount, "Transfer", checking, saving);
                    break;
                            
                case "4":
                    System.exit(0);
                    
                default:
                    System.out.println(userSelection + " is not a valid option. Please try again.");
                    break;
            }
        } while (!userSelection.equals("4"));        
    }
    
    private static void openingPage(){
        
        do{
            System.out.println("Please enter the number of the option you would like to select or any other key to exit");
            System.out.println("\n1) Create account\n2) Login");
            userSelection = INPUT.nextLine();
            
            switch (userSelection){
//==================================ACCOUNT CREATION==============================                
                case "1":
                    String id_Number;
                    String accounts;
                    
                    //=============================ID VERIFICATION===============================
                    do {
                        System.out.print("Enter your ID number: ");
                        id_Number = INPUT.nextLine();
                        if (!acc.verifyIDNumber(id_Number)) System.out.println("This does not appear to be a valid ID. Please try again.");
                    } while (!acc.verifyIDNumber(id_Number));
                    //========================================================================
                    
                    System.out.println("Please enter the number of the type of account(s) you wish to open\n"
                                                     + "1) Checking account\n2) Savings account (10% interest per month)\n3) Both");
                    
                    do {
                        accounts = INPUT.nextLine();
                        if (!accounts.matches("[1-3]")) System.out.println("Invalid input. Please try again.");
                    } while (!accounts.matches("[1-3]")); //ensures that only a 1, 2, or 3 is entered
                    
                    switch (accounts) {
                        case "1":
                            accounts = "Checking";
                            break;
                            
                        case "2":
                            accounts = "Savings";
                            break;
                            
                        case "3":
                            accounts = "Checking&Savings";
                            break;
                    }
                    
                    //========================CHECK USERNAME AVAILABILITY=========================                    
                    do {
                        System.out.print("Enter the username that you'd like to use: ");
                        username = INPUT.nextLine();
                        if (!acc.checkUsernameAvailability(username)) System.out.println("Unfortunately that username is not available. Please enter another username.");
                    } while (!acc.checkUsernameAvailability(username));
                    //========================================================================
                    
                    //========================CHECK PASSWORD COMPLEXITY=========================
                    do {
                        System.out.print("Enter a password: ");
                        password = INPUT.nextLine();
                        if (!acc.checkPasswordComplexity(password)) System.out.println("Password too weak. Please ensure it is no less than 8 characters long and contains at least 1 capital letter, number and special character.");
                    } while (!acc.checkPasswordComplexity(password));
                    //========================================================================
                    
                    acc.createAccount(username, password, accounts, id_Number);
                    break;
//===================================LOGIN=======================================               
                case "2":
                    do{
                        System.out.print("Enter username: ");
                        username = INPUT.nextLine();
                        System.out.print("Enter password: ");
                        password = INPUT.nextLine();    
                    } while (!acc.login(username, password));
                    break;
//===============================================================================                    
                default:
                    System.exit(0);
            }

        } while (userSelection.equals("1"));
    }
    
    public static void dashboard(Checking_acc checking, Saving_acc saving){
        //dashboard() is meant to display users account balance information
        System.out.println("======================"
                                         + "Welcome, " + acc.getCurrentUser()
                                         + "======================");
        System.out.println("Checking Balance: " + checking.getBalanceChecking()
                                         + "\nSavings Balance: " + saving.getBalanceSaving()
                                         + "\nTotal Balance: " + ((double) checking.getBalanceChecking() + saving.getBalanceSaving())
                                         + "\n******************************************************************");
        
        System.out.println("1) Deposit\n2) Withdraw\n3) Transfer\n4) Exit");
        System.out.print("\nEnter the number of the option you wish to select: "); 
    }
    
    public static void transactions(String targetAccount, String transaction, Checking_acc checking, Saving_acc saving){
        String amount;
        boolean proceed; //when proceed is false the input is not valid and must be captured again
        
        do {
            System.out.print("Enter amount to " + transaction + ": ");
            proceed = true;
            amount = INPUT.nextLine();
            
            try{
                Double.parseDouble(amount);
            } catch(NumberFormatException e) {
                System.out.println("Invalid input. Please try again.");
                proceed = false;
            }
        } while (!proceed);
        //============================================================================
        switch (targetAccount + transaction){
            case "1Deposit":
                proceed = checking.deposit(Double.parseDouble(amount));
                break;
                
            case "2Deposit":
                proceed = saving.deposit(Double.parseDouble(amount));
                break;
                
            case "1Withdraw":
                proceed = checking.withdraw(Double.parseDouble(amount));
                break;
                
            case "2Withdraw":
                proceed = saving.withdraw(Double.parseDouble(amount));
                break;
                
            case "1Transfer": //passing objects as arguements so that one child class has access to the other in the required method
                proceed = checking.transfer(Double.parseDouble(amount), saving);
                break;
                
            case "2Transfer":
                proceed = saving.transfer(Double.parseDouble(amount), checking);
                break;
                
        }//switch
        if (!proceed) transactions(targetAccount, transaction, checking, saving);
    }
    
    public static void verifyTarget(){
        do {
            targetAccount = INPUT.nextLine();
            if (!(targetAccount.equals("1") || targetAccount.equals("2")))System.out.println("Invalid input. Please try again.");
        } while (!(targetAccount.equals("1") || targetAccount.equals("2")));
    }
    
}
