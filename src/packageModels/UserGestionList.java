package packageModels;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserGestionList {

    /* constructor for the singleton */
    protected UserGestionList() {}

    /* initialisation du singleton */
    private static UserGestionList instance = new UserGestionList();

    /* method how allow you to get the user list instance */
    public UserGestionList getInstance(){
        if(instance == null){
            instance = new UserGestionList();
        }
        return instance;
    }

    /* Init of the only one user list in the appli */
    private List<User> listUser = new ArrayList<User>();

    public List<User> getListUser() {
        return listUser;
    }

    /* method how search if user login exist or not  */

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

    /* method how search object user with id */

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

    /* CRUD LIST */

    public void addUserList (User user){
        User u = getUserById(user.getId());
        if(searchUserByLogin(user.getLogin())== false && u == null){
            listUser.add(user);
        }
    }

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

    public void removeUserList(int id){
        User u = getUserById(id);
        if (u != null){
            removeUserList(u);
        }
    }
    public void removeUserList(User user){
        User u = getUserById(user.getId());
        if(searchUserByLogin(user.getLogin()) == true && u != null) {
            listUser.remove(u);
        }
    }

    /* method how load data in list at the start of appli */
    public void loadUserintoList(/* User user */){

        /* On va aller récup dans la BD plus tard, là on met des données en dur */
        listUser.add(new User(1, "tyraelium","password" ,"nicolas","sanial","nico.san@smile.fr"));
        listUser.add(new User(2, "platon","password123" ,"john-loup","pautard","john.pau@smile.fr"));
        listUser.add(new User(3, "aristote","passwordabc" ,"mickael","dicurzio","micka.dic@smile.fr"));

    }

}

