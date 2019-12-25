package view.controller;

import com.gn.GNAvatarView;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.util.Pair;
import manager.Manager;
import utils.Notify;
import utils.User;
import utils.changeScene;
import utils.convertImage;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * @author 邓梁
 * @date 2019/12/21 12:39
 * @email 18221221@bjtu.edu.cn
 * UserInfoView的操纵类
 */
public class UserInfoController implements Initializable {

    @FXML
    private AnchorPane userInfoPane;

    @FXML
    private GNAvatarView avatarView;

    @FXML
    private JFXTextField accountTextField;

    @FXML
    private JFXTextField nameTextField;

    @FXML
    private JFXTextField ageTextField;

    @FXML
    private JFXTextField addressTextField;

    @FXML
    private JFXTextField telephoneTextField;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private JFXButton changeAvatarButton;

    @FXML
    private JFXDatePicker birthdayPicker;

    @FXML
    private JFXCheckBox boyCheckBox;

    @FXML
    private JFXCheckBox girlCheckBox;

    @FXML
    private JFXButton changePasswordButton;

    @FXML
    private User user = Manager.getManager().user;

    @FXML
    private File avatar;

    @FXML
    private String suffix;

    @FXML
    private int isAvatar = 0;


    @FXML
    void changePassword(MouseEvent event){
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
    void ChangeAvatar(){
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
        isAvatar = 1;
    }

    @FXML
    void Save(){
        if (!ageTextField.getText().matches("^[0-9]*$")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("You don't put number");
            alert.setContentText("Please only put number into ageTextField");
            alert.showAndWait();
            return;
        }
        String account = accountTextField.getText();
        String name = nameTextField.getText();
        int age = Integer.parseInt(ageTextField.getText());
        int sex = 1;
        if (girlCheckBox.isSelected()){
            sex = 0;
        }
        String address = addressTextField.getText();
        String birthday = birthdayPicker.getValue().toString();
        if (isAvatar == 1){
            byte[] avatar = convertImage.getConvertImage().toByteArray(this.avatar, this.suffix);
            Manager.getManager().out("SAVEINFO" + "#" + "Y" + " " + account + " " + name + " " + age + " " + sex + " " + address + " " + birthday + " " + Arrays.toString(avatar));
            Manager.getManager().user.avatar = avatar;
        }else{
            Manager.getManager().out("SAVEINFO" + "#" + "N" + " " + account + " " + name + " " + age + " " + sex + " " + address + " " + birthday);
        }
        Manager.getManager().user.name = name;
        Manager.getManager().user.age = age;
        Manager.getManager().user.sex = sex;
        Manager.getManager().user.address = address;
        Manager.getManager().user.birthday = birthday;
        Manager.getManager().loadSaveInfo();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Success Dialog");
        alert.setHeaderText("Save successfully");
        alert.setContentText("Save successfully");
        alert.showAndWait();

    }

    @FXML
    void Cancel(){
        changeScene.getChangeScene().fade(changeScene.getChangeScene().getContainer(), changeScene.getChangeScene().getWelcomePane(), userInfoPane);
    }

    @FXML
    void boySelect(){
        if (girlCheckBox.isSelected()) {
            girlCheckBox.setSelected(false);
        }
    }

    @FXML
    void girlSelect(){
        if (boyCheckBox.isSelected()) {
            boyCheckBox.setSelected(false);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        accountTextField.setText(user.account);
        nameTextField.setText(user.name);
        ageTextField.setText(String.valueOf(user.age));
        addressTextField.setText(user.address);
        telephoneTextField.setText(user.telephone);
        LocalDate localDate = LocalDate.parse(user.birthday);
        birthdayPicker.setValue(localDate);
        avatarView.setImage(convertImage.getConvertImage().toWrite(user.avatar, "UserAvatar"));
        if (user.sex == 1){
            boyCheckBox.setSelected(true);
        }else{
            girlCheckBox.setSelected(true);
        }
    }
}
