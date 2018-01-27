package packageMain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class JDBC {

    private static Connection connection = null;
    private static JDBC instance;

    //Attribut to connect your database
    final static String DRIVER = "com.mysql.jdbc.Driver";
    final static String URL = "jdbc:mysql://localhost/bd_projet_java";
    final static String LOGIN = "root";
    final static String PWD = "password";

    /**
     * Constructor who use data connect to open the connection when we call the class JDBC
     */
    public JDBC() {
    }

    /**
     * Return the instance of the JDBC class
     * @return instance
     */
    public static JDBC getInstance() {

        if (instance == null) instance = new JDBC();

        return instance;
    }

    /**
     * Method called to open the connection somewhere
     * @return connection
     */
    public static Connection getConnection() {
        try {

            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, LOGIN, PWD);

        } catch (SQLException ex) {

            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            System.exit(-1);

        } catch (ClassNotFoundException e) {

            e.printStackTrace();
            System.exit(-1);
        }

        return connection;
    }

    /**
     * Method called to close the connection after opened it
     * @return connection
     */
    public static Connection closeConnection() {

        if (connection != null) {

            try {

                connection.close();

            } catch (SQLException ex) {/* ignored */}
        }

        return connection;
    }

    //Je pense qu'elle sera innutile car on a besoin pour les preparedStatement de bind des parametres dans la requete et la method ne nous le permet pas
    public static ResultSet executeQuery(String query) {

        Statement stmt = null;
        ResultSet rs = null;

        connection = JDBC.getConnection();

        // Create statement
        try {

            stmt = connection.createStatement();

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
