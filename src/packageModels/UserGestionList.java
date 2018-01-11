package packageModels;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserGestionList {

    /**
     * Constructor for the singleton
     */
    private UserGestionList() {
        loadUserintoList();
    }

    // initialisation du singleton
    private static UserGestionList instance = null;

    /**
     * Method how allow to get the userList instance
     */
    public static UserGestionList getInstance(){
        if(instance == null){
            instance = new UserGestionList();
        }
        return instance;
    }

    // Init of the only one user list in the appli
    private List<User> listUser = new ArrayList<User>();

    /**
     * Return the list
     */
    public List<User> getListUser() {
        return listUser;
    }

    //RESEARCH METHOD

    /**
     * Method how search if user login exist or not
     * @param login
     */
    public boolean searchUserByLogin(String login){
        boolean result = false;
        Iterator<User> it = listUser.iterator();
        while(it.hasNext()){
            User user = it.next();
            if(user.getLogin() == login){
                result = true;
            }
        }
    return result;
    }

    /**
     * Method how search object user with login
     * @param login
     */
    public User searchObjUserByLogin(String login){
        User u = null;
        Iterator<User> it = listUser.iterator();
        while(it.hasNext()){
            User user = it.next();
            if(user.getLogin() == login){
                u = user;
            }
        }
        return u;
    }

    /**
     * Method how search object user with id
     * @param id
     */
    public User getUserById(int id){
        User u = null;
        Iterator<User> it = listUser.iterator();
        while(it.hasNext()){
            User user = it.next();
            if(user.getId() == id){
                u = user;
            }
        }
        return u;
    }

    /**
     * Method how check if the password given correspond to login given
     * @param login
     * @param password
     */
    public boolean checkPassword(String login, String password){
        boolean result = false;
        User user = searchObjUserByLogin(login);
        if (searchUserByLogin(login) == true && user != null){
            if(password == user.getPassword()){
                result = true;
            }
        }
        return result;
    }

    // CRUD LIST

    /**
     * Method how add user into list
     * @param user
     * TODO : Faire hériter addUserList() de UserGestionDAO avec un super.return ...?
     */
    public void addUserList (User user){
        User u = getUserById(user.getId());
        if(searchUserByLogin(user.getLogin())== false && u == null){
            listUser.add(user);
        }
    }

    /**
     * Method how add user into list
     * @param user
     * TODO : Faire hériter modifyUserList() de UserGestionDAO avec un super.return ...?
     */
    public void modifyUserList(User user){
        User u = getUserById(user.getId());
        if(searchUserByLogin(user.getLogin()) == true && u != null){
            u.setLogin(user.getLogin());
            u.setPassword(user.getPassword());
            u.setFirstName(user.getFirstName());
            u.setLastName(user.getLastName());
            u.setEmail(user.getEmail());
            u.setBirthday(user.getBirthday());
        }
    }

    /**
     * Method to call to remove user by id (call removeUserList(User) with an user parameter
     * @param id
     */
    public void removeUserList(int id){
        User u = getUserById(id);
        if (u != null){
            removeUserList(u);
        }
    }

    /**
     * Method how remove user from list
     * @param user
     * TODO : Faire hériter modifyUserList() de UserGestionDAO avec un super.return ...?
     */
    public void removeUserList(User user){
        User u = getUserById(user.getId());
        if(searchUserByLogin(user.getLogin()) == true && u != null) {
            listUser.remove(u);
        }
    }

    /**
     *  Method how load test data in list when application start
     */
    public void loadUserintoList(/* User user */){

        /* On va aller récup dans la BD plus tard, là on met des données en dur */
        listUser.add(new User(1, "Kant","password" ,"nicolas","sanial","nico.san@smile.fr", LocalDate.of(1994, 2, 21)));
        listUser.add(new User(2, "Platon","password123" ,"john-loup","pautard","john.pau@smile.fr",LocalDate.of(1993, 3, 12)));
        listUser.add(new User(3, "Aristote","passwordabc" ,"mickael","dicurzio","micka.dic@smile.fr",LocalDate.of(1992, 4, 30)));
        listUser.add(new User(4, "Freude","1234abcd" ,"alexis","ribot","alexis.rib@smile.fr",LocalDate.of(1991, 5, 8)));
        listUser.add(new User(5, "Spinoza","12ab34cd" ,"clement","boissière","clement.boi@smile.fr",LocalDate.of(1990, 6, 24)));
        listUser.add(new User(6, "Nietzsche","pass12word34" ,"guillaume","tali","gui.tal@smile.fr",LocalDate.of(1989, 7, 15)));
    }
}

