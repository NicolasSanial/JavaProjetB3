package packageMain;

import javafx.application.Application;
import packageConnection.ConnectionView;
import packageModels.UserGestionDAO;

public class Main {

    /**
     * entry point of the application
     * @param args
     */
    public static void main(String[] args) {

        UserGestionDAO.getInstance().loadUserintoList();
        // launching of the first view
        Application.launch(ConnectionView.class, args);
    }
}
