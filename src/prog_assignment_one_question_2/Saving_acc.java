package prog_assignment_one_question_2;

/**
 *
 * @author Vivek Rajaram (ST10258124)
 */
public class Saving_acc extends Account{

    private String user;
    private double balance;
    
    public Saving_acc(double balance) {
        this.user = super.getCurrentUser();
        this.balance = balance;
    }
    
    @Override
    public double getBalanceSaving() {
        return balance;
    }
    
    public void setBalance(double transferAmount) { //used for "transfer"
        this.balance = balance + transferAmount;
    }
    
    public boolean deposit(double amount){
        if(amount < 0){
            System.out.println("Bad input. Amount cannot be less than 0. Please try again.");
            return false;
        } else {
            balance = balance + amount;
            System.out.println("Successfully deposited R" + amount + " into savings acount.\nHave a good day, " + user);
            return true;
        }
    }
    
    public boolean withdraw(double amount){
        if (amount < 0) {
            System.out.println("Bad input. Amount cannot be less than 0. Please try again.");
            return false;
        } else if (amount > balance) {
            System.out.println("Insufficient funds. Please enter a smaller amount.");
            return false;
        } else {
            balance = balance - amount;
            System.out.println("Successfully withdrew R" + amount + " from savings account.\nHave a good day, " + user);
            return true;
        }
    }
    
    public boolean transfer(double amount, Checking_acc checking){
        
        if (amount < 0) {
            System.out.println("Bad input. Amount cannot be less than 0. Please try again.");
            return false;
        } else if (amount > checking.getBalanceSaving()) {
            System.out.println("Insufficient funds. Please enter a smaller amount.");
            return false;
        } else {
            balance = balance - amount;
            checking.setBalance(amount);
            System.out.println("Successfully transfered R" + amount + " from savings to checking account.\nHave a good day, " + user);
            return true;
        }
    }
}
