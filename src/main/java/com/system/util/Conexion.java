package com.system.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe"; 
    private static final String USER = "user";  
    private static final String PASSWORD = "pass";  

    private Conexion() {}

    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
