package view.controller;

import com.gn.GNAvatarView;
import com.jfoenix.controls.*;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import manager.Manager;
import org.mindrot.jbcrypt.BCrypt;
import utils.convertImage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

/**
 * @author 邓梁
 * @date 2019/12/12 13:58
 * @email 18221221@bjtu.edu.cn
 */
public class CreateAccountViewController implements Initializable {

    @FXML
    private JFXTextField nameField;

    @FXML
    private JFXTextField nameValidation;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXTextField passwordValidation1;

    @FXML
    private JFXTextField passwordValidation2;

    @FXML
    private JFXTextField passwordValidation3;

    @FXML
    private JFXPasswordField verifyField;

    @FXML
    private JFXTextField verifyValidation;

    @FXML
    private JFXTextField telephoneField;

    @FXML
    private JFXTextField telephoneValidation;

    @FXML
    private Label lblLogin;

    @FXML
    private JFXDatePicker birthdayPicker;

    @FXML
    private JFXCheckBox manCheckBox;

    @FXML
    private JFXCheckBox girlCheckBox;

    @FXML
    private JFXButton load;

    @FXML
    private GNAvatarView avatarView;

    @FXML
    private int nameSign = 0;

    @FXML
    private int passwordSign1 = 0;

    @FXML
    private int passwordSign2 = 0;

    @FXML
    private int passwordSign3 = 0;

    @FXML
    private int verifySign = 0;

    @FXML
    private int telSign = 0;

    @FXML
    private int sexSign = 0;

    @FXML
    private File avatar;

    @FXML
    private String suffix;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // 确保名字的长度为1~18
        nameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String image = "error";
                String content = "Length of 1~18";
                String color = "red";
                nameSign = 0;
                if (nameField.getText().matches("^.{1,18}$")) {
                    image = "correct";
                    content = "Verified!";
                    color = "green";
                    nameSign = 1;
                }
                nameValidation.setText(content);
                nameValidation.setStyle(String.format("-fx-background-image:url('/resource/image/%s.png'); -fx-text-inner-color: %s;", image, color));
            }
        });

        // 确保密码长度为6~18
        passwordField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String image = "error";
                String content = "Length of 6~18";
                String color = "red";
                passwordSign1 = 0;
                if (passwordField.getText().matches("^.{6,18}$")) {
                    image = "correct";
                    content = "Verified!";
                    color = "green";
                    passwordSign1 = 1;
                }
                passwordValidation1.setText(content);
                passwordValidation1.setStyle(String.format("-fx-background-image:url('/resource/image/%s.png'); -fx-text-inner-color: %s;", image, color));
            }
        });

        // 确保密码至少有一个大写字母
        passwordField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String image = "error";
                String content = "At least one upper case";
                String color = "red";
                passwordSign2 = 0;
                System.out.println(passwordField.getText());
                if (passwordField.getText().matches(".*[A-Z]+.*")) {
                    image = "correct";
                    content = "Verified!";
                    color = "green";
                    passwordSign2 = 1;
                }
                passwordValidation2.setText(content);
                passwordValidation2.setStyle(String.format("-fx-background-image:url('/resource/image/%s.png'); -fx-text-inner-color: %s;", image, color));
            }
        });

        // 确保密码至少有一个数字
        passwordField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String image = "error";
                String content = "At least one digit";
                String color = "red";
                passwordSign3 = 0;
                System.out.println(passwordField.getText());
                if (passwordField.getText().matches(".*\\d+.*")) {
                    image = "correct";
                    content = "Verified!";
                    color = "green";
                    passwordSign3 = 1;
                }
                passwordValidation3.setText(content);
                passwordValidation3.setStyle(String.format("-fx-background-image:url('/resource/image/%s.png'); -fx-text-inner-color: %s;", image, color));
            }
        });

        // 确保两次输入密码相同
        verifyField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String image = "error";
                String content = "password must be consistent";
                String color = "red";
                verifySign = 0;
                System.out.println(passwordField.getText());
                if (passwordField.getText().equals(verifyField.getText())) {
                    image = "correct";
                    content = "Verified!";
                    color = "green";
                    verifySign = 1;
                }
                verifyValidation.setText(content);
                verifyValidation.setStyle(String.format("-fx-background-image:url('/resource/image/%s.png'); -fx-text-inner-color: %s;", image, color));
            }
        });

        // 确保手机号码格式正确
        telephoneField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String image = "error";
                String content = "Validation error!";
                String color = "red";
                telSign = 0;
                if (telephoneField.getText().matches("^(13[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$")) {
                    image = "correct";
                    content = "Verified!";
                    color = "green";
                    telSign = 1;
                }
                telephoneValidation.setText(content);
                telephoneValidation.setStyle(String.format("-fx-background-image:url('/resource/image/%s.png'); -fx-text-inner-color: %s;", image, color));
            }
        });

        avatar = new File("/resource/image/default-avatar.png");
        String fileName = avatar.getName();
        suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        Image avatar = new Image("/resource/image/default-avatar.png");
        avatarView.setImage(avatar);
    }

    @FXML
    void manSelect(MouseEvent event) {
        sexSign = 1;
        if (girlCheckBox.isSelected()) {
            girlCheckBox.setSelected(false);
        }
    }

    @FXML
    void girlSelect(MouseEvent event) {
        sexSign = 1;
        if (manCheckBox.isSelected()) {
            manCheckBox.setSelected(false);
        }
    }

    @FXML
    void loadAvatar(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource Image File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        avatar = fileChooser.showOpenDialog(Manager.getManager().getStage());
        String fileName = avatar.getName();
        suffix = fileName.substring(fileName.lastIndexOf(".") + 1);

        Image image = new Image(avatar.toURI().toString());
        avatarView.setImage(image);

    }

    @FXML
    void openLoginScene(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/fxml/LoginView.fxml"));
        Scene accountScene = lblLogin.getScene();
        root.translateYProperty().set(accountScene.getHeight());
        StackPane rootPane = (StackPane) accountScene.getRoot();
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
    void pressCreate(KeyEvent event) {

    }

    @FXML
    void clickCreate(MouseEvent event) {
        String username = nameField.getText();
        String password = BCrypt.hashpw(passwordField.getText(), BCrypt.gensalt());
        String telephone = telephoneField.getText();
        String birthday = null;
        byte[] avatar = convertImage.getConvertImage().toByteArray(this.avatar, this.suffix);
        String sex = null;
        if (manCheckBox.isSelected()) {
            sex = "Man";
        } else {
            sex = "girl";
        }

        try {
            birthday = birthdayPicker.getValue().toString();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("You don't put birthday");
            alert.setContentText("Please put birthday into TextField");
            alert.showAndWait();
            return;
        }
        if (nameSign == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Your username does not meet requirements");
            alert.setContentText("Please put username into TextField");
            alert.showAndWait();
            return;
        }

        if (passwordSign1 == 0 || passwordSign2 == 0 || passwordSign3 == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Your password does not meet requirements");
            alert.setContentText("Please put password into TextField");
            alert.showAndWait();
            return;
        }

        if (verifySign == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Password verification error");
            alert.setContentText("Please check if the password input is the same twice");
            alert.showAndWait();
            return;
        }

        if (telSign == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Your telephone does not meet requirements");
            alert.setContentText("Please put telephone into TextField");
            alert.showAndWait();
            return;
        }

        if (sexSign == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("You did not choose a gender");
            alert.setContentText("Please choose a gender");
            alert.showAndWait();
            return;
        }

        Manager.getManager().out("REGISTER" + "#" + username + " " + password + " " + telephone + " " + birthday
                + " " + sex + " " + Arrays.toString(avatar));

        String content = null;
        while ((content = Manager.getManager().in()) != null) {
            StringTokenizer parse = new StringTokenizer(content, " ");
            String sign = parse.nextToken();

            // 待修改...


            if (sign.equals("Email_exists")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Telephone exists");
                alert.setContentText("Please change your Telephone");
                alert.showAndWait();
                return;
            } else if (sign.equals("SEND_verification_code")) {
                String code = parse.nextToken();
                TextInputDialog textInputDialog = new TextInputDialog("Please enter a six-digit verification code");
                textInputDialog.setTitle("E-mail verification");
                textInputDialog.setHeaderText("We have sent a verification code to your email address, please check your email address and fill in the six verification code");
                textInputDialog.setContentText("Please enter the code:");
                Optional<String> result = textInputDialog.showAndWait();
                if (result.isPresent() && code.equals(result.get())) {
                    Manager.getManager().out("CHECKSUCCESS");
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Success Dialog");
                    alert.setHeaderText("Create successfully");
                    alert.setContentText("You can sign in with your username or email right now!");
                    alert.showAndWait();
                    return;
                } else if (result.isPresent() && !code.equals(result.get())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Create failed");
                    alert.setContentText("Please check if the verification code is wrong");
                    alert.showAndWait();
                    return;
                } else {
                    return;
                }
            }
        }
    }
}
