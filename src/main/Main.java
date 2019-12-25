package main;
/**
 * 客户端的Main
 */

import manager.Manager;
import javafx.application.Application;
import javafx.stage.Stage;
import manager.receiveThread;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Manager.getManager().exec(stage);
    }


    public synchronized static void main(String[] args) throws IOException {
        launch(args);
    }
}
