package com.nexodus.UnknownTale.Login;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnection {
    public static Connection getConnection() throws  Exception{
        String dbRoot = "jdbc:mysql://";
        String hostName = "localhost/";
        String dbName = "unknowntale?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String dbUrl = dbRoot+hostName+dbName;

        String hostUsername = "root";
        String hostPassword = "";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection myConn = (Connection) DriverManager.getConnection(dbUrl, hostUsername, hostPassword);

        return myConn;
    }
}
