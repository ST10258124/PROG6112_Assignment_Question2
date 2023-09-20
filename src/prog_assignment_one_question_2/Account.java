package prog_assignment_one_question_2;

import java.util.ArrayList;

/**
 *
 * @author Vivek Rajaram (ST10258124)
 */


public class Account {

    private ArrayList <String> username = new ArrayList<String>();
    private ArrayList <String> password = new ArrayList<String>();
    private ArrayList <String> idNumber = new ArrayList<String>();
    private ArrayList <String> accounts = new ArrayList<String>();
    private ArrayList <Double> balanceSavings = new ArrayList <Double>();
    private ArrayList <Double> balanceChecking = new ArrayList <Double>();
    
    private String currentUser;
    private String currentAcc;
    
    public Account (){
        currentAcc = "";
        currentUser = "";
    }
    
//===========================ACCOUNT CREATION METHODS==============================
    public boolean checkUsernameAvailability(String accountName){
        return !username.contains(accountName);
    }
    
    public boolean verifyIDNumber(String id){
        return (id.matches("(\\d){13}") && !idNumber.contains(id));
        //checks that an ID number consists of 13 numerical values and does not already exist
    }
    
    public boolean checkPasswordComplexity(String testPassword) {
        
        boolean passNumeric = false, passSpecial = false;
        char passCharacter; //for the character currently being tested
        
            if ((testPassword.length() >= 8) && (!testPassword.toLowerCase().equals(testPassword))){ //if second condition is true then capital letters are present
            
                for (int i = 0; i < testPassword.length(); i++) { //to check each character of the string for a special or numeric character

                    passCharacter = testPassword.charAt(i);
                    
                    if ((!Character.isDigit(passCharacter)) && (passCharacter != ' ') && (!Character.isAlphabetic(passCharacter))) {
                        passSpecial = true; //is special if not a alphabet and not a number and not a space
                    } else if (Character.isDigit(passCharacter)) {
                        passNumeric = true;
                    }
                } //for loop close  
            }        
        return ((passNumeric) && (passSpecial));        
    }
    
    public void createAccount(String accUsername, String accPassword, String accTypes, String accIDNumber){
        username.add(accUsername);
        password.add(accPassword);
        accounts.add(accTypes);
        idNumber.add(accIDNumber);
        balanceSavings.add(0.0);
        balanceChecking.add(0.0);
    }
//===============================================================================
    public boolean login(String loginUsername, String loginPassword){
        if (username.lastIndexOf(loginUsername) != -1) { //first checks if username exists. if true then checks password array at corresponding index for match
            if (loginPassword.equals(password.get(username.lastIndexOf(loginUsername)))){
                currentUser = loginUsername;
                currentAcc = accounts.get(username.indexOf(currentUser));
                return true;
            }
        }
        
        System.out.println("Username or password incorrect.");
        return false;
    }
    
    public double getBalanceChecking(){
        return balanceChecking.get(username.indexOf(currentUser));
    }
    
    public double getBalanceSaving(){
        return balanceSavings.get(username.indexOf(currentUser));
    }
    
    public String getCurrentAcc() {
        return currentAcc;
    }
    
    public String getCurrentUser() {
        return currentUser;
    }
    
}
