import java.sql.*;

public class dbConnect {
    Statement st = null;
    ResultSet rs = null;
    Connection connection = null;
    int customerid = 0;
    int balance = 0;

    public dbConnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/BANK?characterEncoding=latin1", "root", "Ishaan@67");
            System.out.println("Success");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public boolean login(int cid, int PIN) throws SQLException {
        st = connection.createStatement();
        rs = st.executeQuery("select * from Customer where customerid='" + cid + "' and PIN='" + PIN + "'");
        if (rs.next()) {
            customerid = rs.getInt("customerid");
            return true;
        } else {
            return false;
        }
    }

    public int getBalance(int cid) throws SQLException {
        rs = st.executeQuery("select Balance from Account where customerid=" + cid + "");
        if (rs.next()) {
            balance = rs.getInt("balance");
            return balance;
        } else {
            return 0;
        }

    }

    public boolean withdrawmoney(int cid,int amount) throws SQLException{
        if(amount>balance){
            return false;
        }else{
            st.executeUpdate("update Account set balance="+(balance-amount)+" where customerid="+cid);
            return true;
        }


    }
    public boolean deposit(int cid,int amount) throws SQLException{
            int a=st.executeUpdate("update Account set balance="+(balance+amount)+" where customerid="+cid);
            if(a==1) return true;
            else return false;
    }
    public boolean changePIN(int cid,int uPIN) throws SQLException{

        int a= st.executeUpdate("update Customer set PIN="+uPIN+"where customerid="+cid);
        if(a==1) return true;
        else return false;
    }
    public int getPIN(int cid) throws SQLException{
        rs=st.executeQuery("select PIN from Customer where customer id="+cid+"");
        if(rs.next()){
            return rs.getInt("PIN");
        }
        else{
            return -1;
        }
    }
}