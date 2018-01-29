package packageModels;

import packageMain.DateUtil;
import packageMain.JDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class UserGestionDAO extends UserGestionList {

    //Attributs to controll the connection into the methods
    Connection con = null;
    PreparedStatement ps = null;

    private static UserGestionDAO instance = null;

    //Attributs string containing the differents sql query we need
    private final static String CREATE_USER_SQL = "INSERT INTO j_user (login_user,password_user,firstname,lastname,email,birthday_date) VALUES (?,?,?,?,?,?)";
    private final static String SELECT_ALL_USER_SQL = "SELECT id_user,login_user,password_user,firstname,lastname,email,birthday_date FROM j_user";
    private final static String UPDATE_USER_SQL = "UPDATE j_user SET login_user=?, password_user=?, firstname=?, lastname=?, email=?, birthday_date=? WHERE id_user=?";
    private final static String DELETE_USER_SQL = "DELETE FROM j_user WHERE id_user=?";



    /**
     * Constructor extends constructor UserGestionList for the singleton
     */
    public UserGestionDAO(){
        super();
    }

    /**
     * Method how allow to get the userList instance
     * @return instance of the UserGestionDAO class
     */
    public static UserGestionDAO getInstance(){

        if(instance == null){

            instance = new UserGestionDAO();
        }

        return instance;
    }

    /**
     * Method how execute SQL query creating user and send on BD, call addUser of the herited class UserGestList
     * @param user
     */
    @Override
    public void addUser(User user){

        if(UserGestionList.getInstance().searchUserByLogin(user.getLogin()) == false && getUserById(user.getId()) == null){

            try {

                con = JDBC.getInstance().getConnection();

                ps = con.prepareStatement(CREATE_USER_SQL);

                ps.setString(1, user.getLogin());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getFirstName());
                ps.setString(4, user.getLastName());
                ps.setString(5, user.getEmail());
                //TODO passer par un setDate et changer je type de du champ BD en Date (ça marche mais la requete génère des '' que le type date de la bd n'aime pas)
                //ps.setDate(6, new java.sql.Date(DateUtil.asDate(user.getBirthday()).getTime()));
                ps.setString(6, DateUtil.format(user.getBirthday()));

                ps.execute();

                ps.close();

                super.addUser(user);

            }catch (SQLException ex){

                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
                System.exit(-1);

            } finally {
                if ( con != null ){
                    JDBC.getInstance().closeConnection(con);
                }
            }
        }
    }

    /**
     * Method how execute SQL query modifying user and send on BD, call addUser of the herited class UserGestList
     * @param user : user we want to modify
     */
    @Override
    public void modifyUser(User user){

        if(UserGestionList.getInstance().getUserById(user.getId()) != null){

            try {

                con = JDBC.getInstance().getConnection();

                ps = con.prepareStatement(UPDATE_USER_SQL);

                ps.setString(1, user.getLogin());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getFirstName());
                ps.setString(4, user.getLastName());
                ps.setString(5, user.getEmail());
                ps.setString(6, DateUtil.format(user.getBirthday()));
                ps.setInt(7, user.getId());

                ps.execute();

                ps.close();

                super.modifyUser(user);

            }catch (SQLException ex){

                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
                System.exit(-1);

            } finally {
                if ( con != null ){
                    JDBC.getInstance().closeConnection(con);
                }
            }
        }
    }

    @Override
    public void  removeUserByObJ(User user){

        if(UserGestionList.getInstance().searchUserByLogin(user.getLogin()) == true && UserGestionList.getInstance().getUserById(user.getId()) != null){

            try {

                con = JDBC.getInstance().getConnection();

                ps = con.prepareStatement(DELETE_USER_SQL);
                ps.setInt(1, user.getId());

                ps.execute();

                ps.close();

                super.removeUserByObJ(user);

            }catch(SQLException ex){

                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
                System.exit(-1);

            } finally {
                if ( con != null ){
                    JDBC.getInstance().closeConnection(con);
                }
            }
        }
    }

    /**
     * Method called to charge DB table j_user into listUser
     */
    public void loadUserintoList(){

        try {

            con = JDBC.getInstance().getConnection();

            ps = con.prepareStatement(SELECT_ALL_USER_SQL);

            ResultSet result = ps.executeQuery();

            while (result.next()) {

                User user = new User(1, "Kant", "password", "nicolas", "sanial", "nico.san@smile.fr", LocalDate.of(1994, 2, 21));

                user.setId(result.getInt("id_user"));
                user.setLogin(result.getString("login_user"));
                user.setPassword(result.getString("password_user"));
                user.setFirstName(result.getString("firstname"));
                user.setLastName(result.getString("lastname"));
                user.setEmail(result.getString("email"));
                user.setBirthday(DateUtil.parse(result.getString("birthday_date")));

                User u = UserGestionList.getInstance().getUserById(user.getId());
                if (UserGestionList.getInstance().searchUserByLogin(user.getLogin()) == false && u == null) {

                    UserGestionList.getInstance().addUser(user);
                }
            }

            ps.close();

        }catch (SQLException ex){

            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            System.exit(-1);

        } finally {
            if ( con != null ){
                JDBC.getInstance().closeConnection(con);
            }
        }
    }
}