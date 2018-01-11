package packageMain;

import javafx.application.Application;
import packageConnection.ConnectionView;

public class Main {

    public static void main(String[] args) {

        // launching of the first view
        Application.launch(ConnectionView.class, args);
    }
}
