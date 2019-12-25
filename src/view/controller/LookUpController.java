package view.controller;

import com.gn.GNAvatarView;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import manager.Manager;
import utils.Notify;
import utils.changeScene;
import utils.convertImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

/**
 * @author 邓梁
 * @date 2019/12/19 14:11
 * @email 18221221@bjtu.edu.cn
 * LookUpView的操纵类
 */
public class LookUpController implements Initializable {

    @FXML
    private AnchorPane lookUpPane;

    @FXML
    private JFXTabPane tabPane;

    @FXML
    private TextField friendsTextField;

    @FXML
    private TextField groupsTextField;

    @FXML
    private JFXButton friendButton;

    @FXML
    private JFXButton groupButton;


    @FXML
    void searchFriends(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String account = friendsTextField.getText();
            if (!account.matches("^[0-9]*$")) {
                return;
            }
            // F代表是查找好友
            Manager.getManager().out("LOOKUP" + "#" + "F" + " " + account);
            Notify.getInstance().toNotify();
            String content = Manager.getManager().content;
            if (content.equals("Not Found")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Not Found");
                alert.setContentText("The account you entered is not found");
                alert.showAndWait();
                return;
            } else {
                StringTokenizer parse = new StringTokenizer(content, " ");
                String name = parse.nextToken();
                int status = Integer.parseInt(parse.nextToken());
                byte[] avatar = convertImage.getConvertImage().stringToBytes(parse);
                GNAvatarView imageView = new GNAvatarView(convertImage.getConvertImage().toWrite(avatar, "searchFriend"));
                imageView.setPrefSize(75, 75);
                imageView.setStroke(convertImage.getConvertImage().chooseColor(status));
                imageView.setStrokeWidth(5);
                friendButton.setText(name);
                friendButton.setGraphic(imageView);
                friendButton.setStyle("-fx-alignment: CENTER_LEFT;");

                ContextMenu contextMenu = new ContextMenu();
                MenuItem menuItem1 = new MenuItem("Add Friend");
                changeScene changeScene = utils.changeScene.getChangeScene();
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/view/fxml/ApplyView.fxml"));
                    Manager.getManager().setData(account);
                    menuItem1.setOnAction(event1 -> {
                        if (Manager.getRelationship().friendsGroup.containsKey(account) ||
                        Manager.getRelationship().familyGroup.containsKey(account) ||
                        Manager.getRelationship().classmateGroup.containsKey(account)){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error Dialog");
                            alert.setHeaderText("Add failed");
                            alert.setContentText("He is already your friend");
                            alert.showAndWait();
                            return;
                        }
                        if (changeScene.getChangeScene().getContainer().getChildren().get(0).getId().equals("chat")){
                            Manager.getManager().setChatTo(Manager.getManager().user.account);
                        }
                        changeScene.fade(changeScene.getContainer(), root, lookUpPane);
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
                contextMenu.getItems().add(menuItem1);
                friendButton.setContextMenu(contextMenu);
            }
        }
    }

    @FXML
    void searchGroups(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String account = groupsTextField.getText();
            if (!account.matches("^[0-9]*$")) {
                return;
            }
            // G代表是查找群组
            Manager.getManager().out("LOOKUP" + "#" + "G" + " " + account);

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabPane.setTabMaxWidth(680);
        tabPane.setTabMinWidth(680);
    }
}
