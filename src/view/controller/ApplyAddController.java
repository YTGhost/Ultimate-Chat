package view.controller;

import com.gn.GNAvatarView;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import manager.Manager;
import utils.TipMessage;
import utils.User;
import utils.changeScene;
import utils.convertImage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author 邓梁
 * @date 2019/12/22 13:46
 * @email 18221221@bjtu.edu.cn
 * ApplyAddView的操纵类
 */
public class ApplyAddController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private GNAvatarView avatarView;

    @FXML
    private JFXTextArea messageTextArea;

    @FXML
    private JFXTextField nameTextField;

    @FXML
    private JFXTextField accountTextField;

    @FXML
    private JFXTextField nicknameTextField;

    @FXML
    private JFXComboBox<String> groupComboBox;

    @FXML
    private JFXButton acceptButton;

    @FXML
    private JFXButton refuseButton;

    @FXML
    private TipMessage tipMessage = (TipMessage) Manager.getManager().getMessageQueue().getFront();



    @FXML
    void Accept(){
        if (nicknameTextField.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Empty");
            alert.setContentText("Please check the nicknameTextField");
            alert.showAndWait();
            return;
        }
        if (groupComboBox.getValue().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Empty");
            alert.setContentText("Please check the groupComboBox");
            alert.showAndWait();
            return;
        }
        String friendAccount = Manager.getManager().user.account;
        String userAccount = accountTextField.getText();
        String friendNickName = nicknameTextField.getText();
        String userNickName = tipMessage.getNickname();
        String friendGroup = groupComboBox.getValue();
        String userGroup = tipMessage.getGroup();

        Manager.getManager().out("ACCEPT#" + userAccount + " " + friendAccount + " " + userGroup + " " + friendGroup + " "
        + userNickName + " " + friendNickName);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Success Dialog");
        alert.setHeaderText("Accept successfully");
        alert.setContentText("Chat with friends after re-login");
        alert.showAndWait();
        changeScene.getChangeScene().fade(changeScene.getChangeScene().getContainer(), changeScene.getChangeScene().getWelcomePane(), anchorPane);
    }

    @FXML
    void Refuse(){
        changeScene.getChangeScene().fade(changeScene.getChangeScene().getContainer(), changeScene.getChangeScene().getWelcomePane(), anchorPane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Manager.getManager().getMessageQueue().removeFront();
        Manager.getManager().getBadge().setText(String.valueOf(Manager.getManager().getMessageQueue().getSize()));

        // 初始化信息
        avatarView.setImage(convertImage.getConvertImage().toWrite(tipMessage.getAvatar(), "FriendApply"));
        nameTextField.setText(tipMessage.getName());
        accountTextField.setText(tipMessage.getAccountFrom());
        messageTextArea.setText(tipMessage.getMessage());
        groupComboBox.getItems().addAll("Friends", "Family", "Classmate");
    }
}
