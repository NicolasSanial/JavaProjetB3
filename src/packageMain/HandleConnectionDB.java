package packageMain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class HandleConnectionDB {

    public HandleConnectionDB() {

    }

    private static Connection con = null;

    private static HandleConnectionDB instance = new HandleConnectionDB();

    public static HandleConnectionDB getInstance() {

        return HandleConnectionDB.instance;
    }

    public static Connection getConnection() {

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/" + "user=root&password=root");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return con;
    }

    public static Connection closeConnection() {

        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) { /* ignored */}
        }

        return con;
    }

    public static ResultSet executeQuery(String query) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        con = HandleConnectionDB.getConnection();

        // Create statement
        try {
            stmt = con.createStatement();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        try {
            rs = stmt.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return rs;
    }
}
