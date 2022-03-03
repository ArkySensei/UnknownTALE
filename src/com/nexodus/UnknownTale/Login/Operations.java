package com.nexodus.UnknownTale.Login;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Operations {
    public static boolean isLogin(String username, String password, JFrame frame) {
        boolean detect=false;
        try{
            Connection myConn = MySQLConnection.getConnection();
            String mySqlQuery =
                    "SELECT UID, Username, Nickname FROM account WHERE Username = '"+
                    username+
                    "' AND Password = '"+
                    password+
                    "'";
            PreparedStatement preparedStatement = myConn.prepareStatement(mySqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                LoginSession.UID = resultSet.getInt("UID");
                LoginSession.Username = resultSet.getString("Username");
                LoginSession.Nickname = resultSet.getString("Nickname");
                detect = true;
                return true;
            }
        }catch (Exception exception) {
            JOptionPane.showMessageDialog(frame, "Database error: " + exception.getMessage());
        }
        if(detect!=true){
            JOptionPane.showMessageDialog(frame, "Username or Password entered incorrectly");
        }
        return false;
    }
}
