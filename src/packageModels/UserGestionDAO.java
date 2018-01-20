package packageModels;

public class UserGestionDAO extends UserGestionList {

    /**
     * Constructor extends constructor UserGestionList for the singleton
     */
    public UserGestionDAO(){
        super();
    }

    // initialisation du singleton
    private static UserGestionDAO instance = null;

    /**
     * Method how allow to get the userList instance
     */
    public static UserGestionDAO getInstance(){
        if(instance == null){
            instance = new UserGestionDAO();
        }
        return instance;
    }

    @Override
    public void addUserList (User user){
        User u = getUserById(user.getId());
        if(searchUserByLogin(user.getLogin())== false && u == null){
            //Get connection
            //Add request insert for user
            //Close connexion

            super.addUserList(user);
        }
    }

    @Override
    public void modifyUserList (User user){
        User u = getUserById(user.getId());
        if(searchUserByLogin(user.getLogin()) == true && u != null){
            //Get connection
            //Add request update for user
            //Close connexion

            super.modifyUserList(user);
        }
    }
}