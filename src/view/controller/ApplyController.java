package view.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import manager.Manager;
import utils.changeScene;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author 邓梁
 * @date 2019/12/20 22:10
 * @email 18221221@bjtu.edu.cn
 * ApplyView的操纵类
 */
public class ApplyController implements Initializable {

    @FXML
    private JFXComboBox<String> groupComboBox;

    @FXML
    private JFXTextField nicknameTextField;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXTextArea messageTextArea;

    @FXML
    private JFXButton sendButton;

    @FXML
    private JFXButton cancelButton;


    @FXML
    void Cancel(MouseEvent event) {
        changeScene.getChangeScene().fade(changeScene.getChangeScene().getContainer(), changeScene.getChangeScene().getWelcomePane(), anchorPane);
    }

    @FXML
    void Send(MouseEvent event) {
        if (groupComboBox.getValue().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Empty");
            alert.setContentText("Please check the groupComboBox");
            alert.showAndWait();
            return;
        }
        String accountTo = Manager.getManager().getData();
        String accountFrom = Manager.getManager().user.account;
        String nickname = nicknameTextField.getText();
        String group = groupComboBox.getValue();
        String message = messageTextArea.getText();
        Manager.getManager().out("ADDFRIEND#" + accountFrom + " " + accountTo + " " + nickname + " " + group + " " + message);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Success Dialog");
        alert.setHeaderText("Sent successfully");
        alert.setContentText("Please wait for the reply");
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        groupComboBox.getItems().addAll("Friends", "Family", "Classmate");
    }
}
