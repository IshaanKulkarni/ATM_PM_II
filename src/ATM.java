import java.sql.SQLException;
import java.util.Scanner;

public class ATM {
//    This is the amount of money at the start of the day in the machine
    static int available=500000;
    Scanner sc=new Scanner(System.in);
    dbConnect db;
    public static void main(String[] args) throws SQLException{
        ATM at=new ATM();
        at.Login();
    }
    public ATM(){
        db=new dbConnect();
    }
    int customerid,PIN;
    public void Login() throws SQLException {
        System.out.println("Enter your customerid");
        customerid=sc.nextInt();
        System.out.println("Enter your PIN please");
        PIN=sc.nextInt();
        boolean flag=db.login(customerid,PIN);
        if(flag==true){
            System.out.println("Login Successful!");
            Display_Menu();
        }
        else{
            System.out.println("Login falied! Try again");
            Login();
        }
    }


    public void Display_Menu() throws SQLException{
        System.out.println("*********Menu**********");
        System.out.println("1.Check balance");
        System.out.println("2.Withdraw");
        System.out.println("3.Deposit");
        System.out.println("4.Change PIN");
        System.out.println("5.Logout");
        System.out.println("************************");
        System.out.println("Enter your choice:");
        int choice=sc.nextInt();
        switch(choice){
            case 1: check_balance();
            break;
            case 2: withdraw();
            break;
            case 3: Deposit();
            break;
            case 4: changePIN();
            break;
            case 5: Logout();
            break;
            default: System.out.println("Please select a valid choice");
        }

    }

    public void check_balance() throws SQLException{
        int amount=db.getBalance(customerid);
        System.out.println("Account Balance is "+amount);
        Display_Menu();
    }
    public void withdraw() throws SQLException{
        System.out.println("Enter amount to withdraw: ");
        int amount=sc.nextInt();
        boolean flag=db.withdrawmoney(customerid,amount);
        if(flag&&amount<=available){
            System.out.println("Please collect cash!");
            available=available-amount;
            int rem=db.getBalance(customerid);
            System.out.println("Remaining balance is:" +rem);
            Display_Menu();
        }else{
            System.out.println("Not enough balance!");
            Display_Menu();
        }
    }
    public void Deposit() throws SQLException{
        System.out.println("Enter amount to deposit: ");
        int amount=sc.nextInt();
        boolean flag=db.deposit(customerid,amount);
        available=available+amount;
        System.out.println("Money deposited successfully!");
        int rem=db.getBalance(customerid);
        System.out.println("Updated balance in account is: "+rem);
        Display_Menu();

    }
    public void changePIN() throws SQLException{
        System.out.println("Please enter your existing PIN");
        int PINE=sc.nextInt();
        int PIN=db.getPIN(customerid);
        if(PINE==PIN){
            System.out.print("Enter your new PIN: ");
            int PINU=sc.nextInt();
            boolean a=db.changePIN(customerid,PINU);
            if(a) {
                System.out.print("PIN successfully updated!");
            }
            Display_Menu();
        }
        else{
            System.out.println("Please enter valid PIN");
            Display_Menu();
        }
    }
    public void Logout() throws SQLException{
        System.out.println("Bye! Glad to be of service!");
        Login();
    }
}

