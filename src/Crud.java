
import static java.lang.Integer.parseInt;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 2ndyrGroupC
 */
public class Crud {
//initializing jdbc_driver to connect to mysql server
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //address of my database in xampp
    static final String DB_URL = "jdbc:mysql://localhost/barlas";
    //user is root .root is default user
    static final String USER = "root";
    static final String PASS = "";
// method called to connect to my database
    private Connection connect() throws ClassNotFoundException {
        //connection variable  is initialize as null and outside try to make it a accessible inside connect() method
        Connection conn = null;
        //try a set of code that has potentiality to error
        try {
            //establishing connection
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    //inserts values to table users in my database list[0] is the fname,etc.
    public void insertRegisterInfo(String[] list) throws ClassNotFoundException {
        String sql = "INSERT INTO users VALUES (id,'" + list[0] + "','" + list[1] + "','" + list[2] + "','" + list[3] + "','" + list[4] + "')";
        //calling the method connect() to establish connection
        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            //printed when error appeared
            System.out.println("sorry");
            System.out.println(e.getMessage());
        }
    }
    //validate if log in is valid or not
    public boolean validate_login(String username, String password) {
        try {
            // MySQL database connection
            Connection conn = this.connect();
            //select all the accounts that has a username equal to given username
            PreparedStatement pst = conn.prepareStatement("Select * from users where username=? and password=?");
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            //returns true if validate else false
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
// retrieving all the accounts by using select* and storing it to an arraylist to be past in my user  ui class 
    public ArrayList<ArrayList> retrieve() throws ClassNotFoundException {
        ArrayList<ArrayList>tempList=new ArrayList();
        String sql = "Select* from users";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery(sql);
            while(rs.next()) {
                int id = rs.getInt("id");
                String str1 = rs.getString("firstname");
                String str2 = rs.getString("middlename");
                String str3 = rs.getString("lastname");
                String str4 = rs.getString("username");
               //                
                tempList.add(new ArrayList<String>(Arrays.asList(String.valueOf(id),str1,str2,str3,str4)));
            }
        } catch (SQLException e) {
            System.out.println("sorry");
            System.out.println(e.getMessage());
        }
        return tempList;
                
                
    }
    
    public void updateAll(String update, String fnUpdate, String mnUpdate, String lnUpdate, String email) throws ClassNotFoundException {

        String sql = "UPDATE `users` SET `firstname`='" + fnUpdate + "',`middlename`='" + mnUpdate + "',`lastname`='" + lnUpdate +"',`username`='" +email+ "'WHERE firstname = ?";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, update);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void deleteAll(String id) throws ClassNotFoundException {
        String sql = "DELETE FROM users WHERE id= ?";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.valueOf(id));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
