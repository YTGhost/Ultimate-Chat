package manager;

import com.gn.GNAvatarView;
import com.jfoenix.controls.JFXBadge;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

/**
 * @author 邓梁
 * @date 2019/12/6 16:43
 * @email 18221221@bjtu.edu.cn
 * 全局的操控类
 */
public class Manager {

    public static String data = null;
    private static Manager manager = new Manager();
    private static Relationship relationship = new Relationship();
    public BufferedReader in;
    public PrintStream out;
    public Socket socket;
    public Stage stage;
    public String content;
    public int sign = 0;
    public User user;
    private Queue messageQueue = new CircularQueue(10000);
    private double xOffset = 0;
    private double yOffset = 0;
    private GNAvatarView avatarView;
    private JFXTextField userName;
    private JFXBadge badge;
    private String chatTo = null;

    public Manager() {

    }

    public static Manager getManager() {
        return manager;
    }

    public static Relationship getRelationship() {
        return relationship;
    }

    public String getChatTo() {
        return chatTo;
    }

    public void setChatTo(String account) {
        this.chatTo = account;
    }

    public void loadSaveInfo() {
        this.avatarView.setImage(convertImage.getConvertImage().toWrite(user.avatar, "UserAvatar"));
        this.userName.setText(user.name);
    }

    // 用来设置ChatView中的用户头像
    public void setAvatarView(GNAvatarView avatarView) {
        this.avatarView = avatarView;
    }

    // 用来设置ChatView中的用户名称
    public void setTextField(JFXTextField userName) {
        this.userName = userName;
    }

    public Queue getMessageQueue() {
        return messageQueue;
    }

    public Stage getStage() {
        return stage;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        Manager.data = data;
    }

    public JFXBadge getBadge() {
        return badge;
    }

    // 用来设置Badge
    public void setBadge(JFXBadge badge) {
        this.badge = badge;
    }

    // 用于与服务端连接
    private void connect() {
        try {
            socket = new Socket("###", 30000);
//            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void out(String data) {
        out.println(data);
    }

    // 弃用方法
    public String in() {
        String content = null;
        try {
            content = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    // 项目从此开始执行
    public void exec(Stage stage) throws IOException {
        this.stage = stage;

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/fxml/LoginView.fxml"));
            stage.initStyle(StageStyle.TRANSPARENT);

            //grab your root here
            root.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });

            //move around here
            root.setOnMouseDragged(event -> {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            });

            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
        connect();
        Thread receiveThread = new Thread(new receiveThread(socket));
        receiveThread.start();

    }
}
