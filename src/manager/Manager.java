package manager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author 邓梁
 * @date 2019/12/6 16:43
 * @email 18221221@bjtu.edu.cn
 * 全局的操控类
 */
public class Manager {

    public BufferedReader in;
    public PrintStream out;
    public Socket socket;
    public Stage stage;

    private static Manager manager = new Manager();

    public static Manager getManager(){
        return manager;
    }

    public Stage getStage(){
        return stage;
    }

    public Manager(){

    }

    private void connect(){
        try {
            socket = new Socket("182.92.66.200",30000);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void out(String data){
        out.println(data);
    }

    public String in (){
        String content = null;
        try {
            content = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }



    public void exec(Stage stage){
        this.stage = stage;

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/fxml/LoginView.fxml"));
            stage.initStyle(StageStyle.TRANSPARENT);

            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

        connect();

//        out.println("Login#test");
    }
}
