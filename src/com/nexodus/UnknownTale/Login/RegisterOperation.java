package com.nexodus.UnknownTale.Login;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RegisterOperation {
    public static boolean isRegister(String username, String password, String nickname, JFrame frame) {

        try{
            Connection myConn = MySQLConnection.getConnection();
            String mySqlQuery =
                    "INSERT INTO account(Username, Password, Nickname) VALUES(?,?,?)";


            PreparedStatement pst=(PreparedStatement) myConn.prepareStatement(mySqlQuery);
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, nickname);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Account Registered!");
            return true;

        }catch (Exception exception) {
            JOptionPane.showMessageDialog(frame, "Database error: " + exception.getMessage());
        }

        return false;
    }
}
