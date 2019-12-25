package view.controller;

import com.gn.GNAvatarView;
import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import manager.Manager;
import utils.PersonButton;
import utils.changeScene;
import utils.convertImage;

import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * @author 邓梁
 * @date 2019/12/21 12:42
 * @email 18221221@bjtu.edu.cn
 * FriendInfoView的操纵类
 */
public class FriendInfoController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

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
    private JFXButton returnButton;

    @FXML
    private JFXDatePicker birthdayPicker;

    @FXML
    private JFXTextField nicknameTextField;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXCheckBox boyCheckBox;

    @FXML
    private JFXCheckBox girlCheckBox;

    @FXML
    public static PersonButton data;


    @FXML
    void back(MouseEvent event) {
        changeScene.getChangeScene().fade(changeScene.getChangeScene().getContainer(), changeScene.getChangeScene().getWelcomePane(), anchorPane);
    }

    @FXML
    void save(MouseEvent event) {
        String nickname = nicknameTextField.getText();
        Manager.getManager().out("SAVEINFO" + "#" + "B" + " " + Manager.getManager().user.account +
                " " + data.account + " " + nickname);

        data.nickname = nickname;
        data.setText(data.name + "(" + data.nickname + ")");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Success Dialog");
        alert.setHeaderText("Save successfully");
        alert.setContentText("Save successfully");
        alert.showAndWait();
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
        avatarView.setImage(convertImage.getConvertImage().toWrite(data.avatar, "100002info"));
        accountTextField.setText(data.account);
        addressTextField.setText(data.address);
        nameTextField.setText(data.name);
        telephoneTextField.setText(data.telephone);
        ageTextField.setText(String.valueOf(data.age));
        birthdayPicker.setValue(LocalDate.parse(data.birthday));
        nicknameTextField.setText(data.nickname);
        if (data.sex == 1){
            boyCheckBox.setSelected(true);
        }else{
            girlCheckBox.setSelected(true);
        }
    }
}
