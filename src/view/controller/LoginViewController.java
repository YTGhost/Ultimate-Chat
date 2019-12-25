package view.controller;

import com.gn.GNAvatarView;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.util.Pair;
import manager.Manager;
import utils.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

/**
 * @author 邓梁
 * @date 2019/12/12 13:57
 * @email 18221221@bjtu.edu.cn
 * LoginView的操纵类
 */
public class LoginViewController implements Initializable {

    @FXML
    public StackPane rootPane;

    @FXML
    public AnchorPane anchorPane;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane slidingPane;

    @FXML
    private Tab tabUser;

    @FXML
    private Label lblUser;

    @FXML
    private Tab tabAdmin;

    @FXML
    private Label lblCreateAccount;

    @FXML
    private JFXTextField accountTextField;

    @FXML
    private JFXPasswordField userPasswordTextField;

    @FXML
    private ImageView closeImageView;

    @FXML
    private ImageView minImageView;

    @FXML
    private Label lblForgetPassword;

    @FXML
    private GNAvatarView avatarView;

    @FXML
    private JFXButton statusButton;

    @FXML
    private int status = 1;


    @FXML
    void openForgetPasswordScene(MouseEvent event) {
        TextInputDialog textInputDialog = new TextInputDialog("Please enter your telephone number");
        textInputDialog.setTitle("SMS verification");
        textInputDialog.setHeaderText("Put your telephone number here");
        textInputDialog.setContentText("Please enter the telephone number:");
        Optional<String> result = textInputDialog.showAndWait();
        if (result.isPresent()) {
            Manager.getManager().out("FORGET#" + result.get());
        } else {
            return;
        }
        String content = null;
        Notify.getInstance().toNotify();
        content = Manager.getManager().content;
        System.out.println(content);
        if (content.equals("Not Found")) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Not Found");
            alert1.setContentText("Please try again");
            alert1.showAndWait();
            return;
        }
        TextInputDialog textInputDialog2 = new TextInputDialog("Please enter a six-digit verification code");
        textInputDialog.setTitle("SMS verification");
        textInputDialog.setHeaderText("We have sent a verification code to your telephone. please check your telephone and fill in the six verification code");
        textInputDialog.setContentText("Please enter the code:");
        Optional<String> result2 = textInputDialog.showAndWait();

        if (result2.isPresent() && content.equals(result2.get())) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success Dialog");
            alert.setHeaderText("Check Success");
            alert.setContentText("You can change your password right now!");
            alert.showAndWait();

            //
            Dialog<Pair<String, String>> dialog = new Dialog<>();
            dialog.setTitle("Change Password");
            dialog.setHeaderText("You can Change Password here");

            ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            PasswordField passwordField = new PasswordField();
            passwordField.setPromptText("Password");
            PasswordField verifyField = new PasswordField();
            verifyField.setPromptText("Verify");

            grid.add(new Label("Password:"), 0, 0);
            grid.add(passwordField, 1, 0);
            grid.add(new Label("Verify"), 0, 1);
            grid.add(verifyField, 1, 1);

            Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);


            dialog.getDialogPane().setContent(grid);

            Platform.runLater(() -> passwordField.requestFocus());

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == loginButtonType) {
                    return new Pair<>(passwordField.getText(), verifyField.getText());
                }
                return null;
            });

            Optional<Pair<String, String>> result1 = dialog.showAndWait();

            result1.ifPresent(usernamePassword -> {
                if (usernamePassword.getKey().equals(usernamePassword.getValue())) {
                    Manager.getManager().out(usernamePassword.getKey());
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Success Dialog");
                    alert1.setHeaderText("You have successfully changed your password");
                    alert1.setContentText("Change Successfully");
                    alert1.showAndWait();
                } else {
                    Alert alert1 = new Alert(Alert.AlertType.ERROR);
                    alert1.setTitle("Error Dialog");
                    alert1.setHeaderText("You set a different password twice");
                    alert1.setContentText("Please try again");
                    alert1.showAndWait();
                }
            });
        } else if (result.isPresent() && !content.equals(result.get())) {
            Manager.getManager().out("Verify failed");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Change failed");
            alert.setContentText("Please check if the verification code is wrong");
            alert.showAndWait();
            return;
        } else {
            return;
        }
    }

    @FXML
    void min(MouseEvent event) {
        Manager.getManager().stage.setIconified(true);
    }

    @FXML
    void close(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void openCreateAccountScene(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/fxml/CreateAccountView.fxml"));
        Scene LoginScene = lblCreateAccount.getScene();
        root.translateYProperty().set(LoginScene.getHeight());
        rootPane.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(900), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        timeline.setOnFinished((ActionEvent event1) -> {
            rootPane.getChildren().remove(anchorPane);
        });
    }

    @FXML
    void userlogin(MouseEvent event) {
        String account = accountTextField.getText();
        String password = userPasswordTextField.getText();
        int status = this.status;

        if (account.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("You don't put Account");
            alert.setContentText("Please put Account into TextField");
            alert.showAndWait();
            return;
        }

        if (password.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("You don't put password");
            alert.setContentText("Please put password into TextField");
            alert.showAndWait();
            return;
        }

        Manager.getManager().out("LOGIN" + "#" + account + " " + password + " " + status);
        Notify.getInstance().toNotify();
        String content = Manager.getManager().content;
        try {
            if (content.equals("User verification Passed")) {

                // 获得个人信息
                Notify.getInstance().toNotify();
                content = Manager.getManager().content;
                StringTokenizer parse = new StringTokenizer(content, " ");
                int age = Integer.parseInt(parse.nextToken());
                int sex = Integer.parseInt(parse.nextToken());
                String name = parse.nextToken();
                String address = parse.nextToken();
                String telephone = parse.nextToken();
                String birthday = parse.nextToken();
                byte[] avatar = convertImage.getConvertImage().stringToBytes(parse);
                Manager.getManager().user = new User(account, name, age, sex, avatar, address, telephone, birthday, status);
                Manager.getManager().setChatTo(account);


                // 获取好友信息
                Notify.getInstance().toNotify();
                while(!(content = Manager.getManager().content).equals("**OVER**")){
                    parse = new StringTokenizer(content, " ");
                    String friendAccount = parse.nextToken();
                    String friendName = parse.nextToken();
                    int friendAge = Integer.parseInt(parse.nextToken());
                    int friendSex = Integer.parseInt(parse.nextToken());
                    String friendAddress = parse.nextToken();
                    int friendStatus = Integer.parseInt(parse.nextToken());
                    String friendTelephone = parse.nextToken();
                    String friendBirthday = parse.nextToken();
                    String Group = parse.nextToken();
                    String nickname = parse.nextToken();
                    byte[] friendAvatar = convertImage.getConvertImage().stringToBytes(parse);

                    // 将信息添加至PersonButton中
                    PersonButton personButton = new PersonButton(friendAccount, friendName, friendAge, friendSex,
                            friendAvatar, friendAddress, friendStatus, friendTelephone, friendBirthday, Group, nickname);

                    Manager.getRelationship().add(personButton);
                    Notify.getInstance().toNotify();
                }


                Parent root = FXMLLoader.load(getClass().getResource("/view/fxml/ChatView.fxml"));
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success Dialog");
                alert.setHeaderText("Verification Success");
                alert.setContentText("User verification Passed");
                alert.showAndWait();
                changeScene.getChangeScene().fade(rootPane, root, anchorPane);
                return;
            } else if (content.equals("User verification Failed")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Verification Failed");
                alert.setContentText("User verification Failed");
                alert.showAndWait();
                return;
            } else if (content.equals("User is online")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("User is online");
                alert.setContentText("User is online");
                alert.showAndWait();
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        changeScene.getChangeScene().setStackPane(rootPane);
        changeScene.getChangeScene().setAnchorPane(anchorPane);

        Image image = new Image("/resource/cache/avatar.jpg");
        avatarView.setImage(image);

        Tooltip tooltip = new Tooltip();
        tooltip.setText("test");
        accountTextField.setTooltip(tooltip);

        // 初始化最初显示的icon为在线，此时status也为1
        statusButton.setStyle(String.format("-fx-graphic:url(/resource/image/online.png)"));
        avatarView.setStroke(Color.GREEN);

        // 设置contextMenu在button上
        ContextMenu contextMenu = new ContextMenu();
        MenuItem menuItem1 = new MenuItem("online");
        MenuItem menuItem2 = new MenuItem("concealed");
        MenuItem menuItem3 = new MenuItem("busy");

        menuItem1.setStyle(String.format("-fx-graphic:url(/resource/image/online.png)"));
        menuItem2.setStyle(String.format("-fx-graphic:url(/resource/image/concealed.png)"));
        menuItem3.setStyle(String.format("-fx-graphic:url(/resource/image/busy.png)"));

        // 选择在线状态
        menuItem1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                status = 1;
                statusButton.setStyle(String.format("-fx-graphic:url(/resource/image/online.png)"));
                avatarView.setStroke(Color.GREEN);
            }
        });

        // 选择隐身状态
        menuItem2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                status = 2;
                statusButton.setStyle(String.format("-fx-graphic:url(/resource/image/concealed.png)"));
                avatarView.setStroke(Color.YELLOW);
            }
        });

        // 选择忙碌状态
        menuItem3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                status = 3;
                statusButton.setStyle(String.format("-fx-graphic:url(/resource/image/busy.png)"));
                avatarView.setStroke(Color.RED);
            }
        });

        // 将menuItem加到contextmenu中
        contextMenu.getItems().addAll(menuItem1, menuItem2, menuItem3);
        statusButton.setContextMenu(contextMenu);

        // 为了左键单击时contextMenu也能显示
        statusButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                contextMenu.show(avatarView.getScene().getWindow());
            }
        });
    }
}
