package packageMain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class JDBC {

    private static Connection con = null;
    private static JDBC instance;
    final static String DRIVER = "com.mysql.jdbc.Driver";
    final static String URL = "";
    final static String LOGIN = "";
    final static String PWD = "";

    public JDBC() {

        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, LOGIN, PWD);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            System.exit(-1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }


    public static JDBC getInstance() {

        if (instance == null) instance = new JDBC();
        return instance;
    }


    public static Connection getConnection() {

        return con;
    }


    public static Connection closeConnection() {

        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {/* ignored */}
        }

        return con;
    }


    public static ResultSet executeQuery(String query) {

        Statement stmt = null;
        ResultSet rs = null;

        con = JDBC.getConnection();

        // Create statement
        try {
            stmt = con.createStatement();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            System.exit(-1);
        }
        // Execute query
        try {
            rs = stmt.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            System.exit(-1);
        }

        return rs;
    }
}
