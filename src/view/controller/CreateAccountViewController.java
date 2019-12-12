package view.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import manager.Manager;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.net.URL;
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
    private JFXTextField usernameField;

    @FXML
    private JFXComboBox<String> leverComboBox;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXTextField emailField;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXPasswordField verifyField;

    @FXML
    private Label lblLogin;

    @FXML
    private JFXDatePicker birthdayPicker;

    @FXML
    private JFXTextField firstnameField;

    @FXML
    private JFXTextField lastnameField;

    @FXML
    private JFXTextField idField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator();
        requiredFieldValidator.setMessage("Cannot be Empty!");
        passwordField.getValidators().add(requiredFieldValidator);
        passwordField.focusedProperty().addListener((o, oldVal,newval) ->{
            if (!newval){
                passwordField.validate();
            }
        });
        usernameField.getValidators().add(requiredFieldValidator);
        usernameField.focusedProperty().addListener((o, oldVal,newval) ->{
            if (!newval){
                usernameField.validate();
            }
        });

        verifyField.getValidators().add(requiredFieldValidator);
        verifyField.focusedProperty().addListener((o, oldVal,newval) ->{
            if (!newval){
                verifyField.validate();
            }
        });

        firstnameField.getValidators().add(requiredFieldValidator);
        firstnameField.focusedProperty().addListener((o, oldVal,newval) ->{
            if (!newval){
                firstnameField.validate();
            }
        });

        lastnameField.getValidators().add(requiredFieldValidator);
        lastnameField.focusedProperty().addListener((o, oldVal,newval) ->{
            if (!newval){
                lastnameField.validate();
            }
        });

        idField.getValidators().add(requiredFieldValidator);
        idField.focusedProperty().addListener((o, oldVal,newval) ->{
            if (!newval){
                idField.validate();
            }
        });

        emailField.getValidators().add(requiredFieldValidator);
        emailField.focusedProperty().addListener((o, oldVal,newval) ->{
            if (!newval){
                emailField.validate();
            }
        });

        birthdayPicker.getValidators().add(requiredFieldValidator);
        birthdayPicker.focusedProperty().addListener((o, oldVal,newval) ->{
            if (!newval){
                birthdayPicker.validate();
            }
        });

        leverComboBox.getValidators().add(requiredFieldValidator);
        leverComboBox.focusedProperty().addListener((o, oldVal,newval) ->{
            if (!newval){
                leverComboBox.validate();
            }
        });

        leverComboBox.getItems().add("User");
        leverComboBox.getItems().add("Admin");
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
        String username = usernameField.getText();
        String password = BCrypt.hashpw(passwordField.getText(), BCrypt.gensalt());
        String verify = verifyField.getText();
        String email = emailField.getText();
        String birthday = null;
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
        String userLever = leverComboBox.getValue();
        String firstname = firstnameField.getText();
        String lastname = lastnameField.getText();
        String idnumber = idField.getText();
        if (username.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("You don't put username");
            alert.setContentText("Please put username in ComboBox");
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

        if (email.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("You don't put email");
            alert.setContentText("Please put email into TextField");
            alert.showAndWait();
            return;
        }

        if (userLever == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("You don't put userLever");
            alert.setContentText("Please put userLever into TextField");
            alert.showAndWait();
            return;
        }

        if (firstname.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("You don't put firstname");
            alert.setContentText("Please put firstname into TextField");
            alert.showAndWait();
            return;
        }

        if (lastname.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("You don't put lastname");
            alert.setContentText("Please put lastname into TextField");
            alert.showAndWait();
            return;
        }

        if (idnumber.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("You don't put idnumber");
            alert.setContentText("Please put idnumber into TextField");
            alert.showAndWait();
            return;
        }

        if (!BCrypt.checkpw(verify, password)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Password verification error");
            alert.setContentText("Please check if the password input is the same twice");
            alert.showAndWait();
            return;
        }
        Manager.getManager().out("CREATEUser" + " " + userLever + " " + username + " " + password + " " + email + " " + birthday + " " + firstname + " " + lastname + " " + idnumber);
        String content = null;
        while ((content = Manager.getManager().in()) != null) {
            StringTokenizer parse = new StringTokenizer(content, " ");
            String sign = parse.nextToken();
            if (sign.equals("Username_exists")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Username exists");
                alert.setContentText("Please change your username");
                alert.showAndWait();
                return;
            } else if (sign.equals("Email_exists")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Email exists");
                alert.setContentText("Please change your Email");
                alert.showAndWait();
                return;
            }else if(sign.equals("SEND_verification_code")){
                String code = parse.nextToken();
                TextInputDialog textInputDialog = new TextInputDialog("Please enter a six-digit verification code");
                textInputDialog.setTitle("E-mail verification");
                textInputDialog.setHeaderText("We have sent a verification code to your email address, please check your email address and fill in the six verification code");
                textInputDialog.setContentText("Please enter the code:");
                Optional<String> result = textInputDialog.showAndWait();
                if (result.isPresent() && code.equals(result.get())){
                    Manager.getManager().out("CHECKSUCCESS");
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Success Dialog");
                    alert.setHeaderText("Create successfully");
                    alert.setContentText("You can sign in with your username or email right now!");
                    alert.showAndWait();
                    return;
                }else if(result.isPresent() && !code.equals(result.get())){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Create failed");
                    alert.setContentText("Please check if the verification code is wrong");
                    alert.showAndWait();
                    return;
                }else{
                    return;
                }
            }
        }
    }
}
