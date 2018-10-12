/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.Input;

/**
 *
 * @author tanmay
 */
import java.sql.*;
import javax.swing.*;

public class MySqlConnect {
    Connection conn = null;
    public static Connection ConnectDB() {
    try{  
        Class.forName("com.mysql.jdbc.Driver");  
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hostel","tanmay","9867531983"); 
        //JOptionPane.showMessageDialog(null,"Connect to Database");
        return conn;
    }catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
        return null;
    }  
}  

    
}
