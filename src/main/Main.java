package main;

import manager.Manager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Manager.getManager().exec(stage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
