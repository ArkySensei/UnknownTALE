package com.nexodus.UnknownTale.Login;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserCheck {
    public static boolean CheckUser(String username, JFrame frame) {
        boolean chk=true;
        ResultSet rs;
        try{
            Connection myConn = MySQLConnection.getConnection();
            String mySqlQuery =
                    "SELECT * FROM account WHERE Username = ?";


            PreparedStatement pst=(PreparedStatement) myConn.prepareStatement(mySqlQuery);
            pst.setString(1, username);
            rs = pst.executeQuery();
            if(rs.next()) {
                chk= false;
                JOptionPane.showMessageDialog(null,"Username Exist!");
            }



        }catch (Exception exception) {
            JOptionPane.showMessageDialog(frame, "Database error: " + exception.getMessage());
        }

        return chk;
    }
}
