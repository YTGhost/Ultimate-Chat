package view.controller;

import com.gn.GNAvatarView;
import com.jfoenix.controls.*;
import de.jensd.fx.fontawesome.Icon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import manager.Manager;
import utils.*;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author 邓梁
 * @date 2019/12/16 13:45
 * @email 18221221@bjtu.edu.cn
 * ChatView的操纵类
 */
public class ChatController implements Initializable {

    @FXML
    private AnchorPane friendsAnchorPane;

    @FXML
    private AnchorPane userPane;

    @FXML
    private JFXTabPane tabPane;

    @FXML
    private Accordion friendAccordion;

    @FXML
    private AnchorPane groupsPane;

    @FXML
    private JFXButton lookupButton;

    @FXML
    private AnchorPane container;

    @FXML
    private GNAvatarView avatarView;

    @FXML
    private changeScene changeScene;

    @FXML
    private JFXButton menuButton;

    @FXML
    private JFXTextField userName;

    @FXML
    private AnchorPane welcomePane;

    @FXML
    private JFXBadge badge;


    @FXML
    void min(MouseEvent event) {
        Manager.getManager().stage.setIconified(true);
    }

    @FXML
    void close(MouseEvent event) {
        Manager.getManager().out("STATUS#0" + " " + Manager.getManager().user.account);
        System.exit(0);
    }

    @FXML
    void openLookUp(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/fxml/LookUpView.fxml"));
            if (changeScene.getChangeScene().getContainer().getChildren().get(0).getId().equals("chat")){
                Manager.getManager().setChatTo(Manager.getManager().user.account);
            }
            changeScene.fade(container, root, (AnchorPane) container.getChildren().get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void openApply(MouseEvent event){
        if (Manager.getManager().getMessageQueue().getSize() == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Empty Failed");
            alert.setContentText("No message alert now");
            alert.showAndWait();
            return;
        }
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/fxml/ApplyAddView.fxml"));
            if (changeScene.getChangeScene().getContainer().getChildren().get(0).getId().equals("chat")){
                Manager.getManager().setChatTo(Manager.getManager().user.account);
            }
            changeScene.fade(container, root, (AnchorPane) container.getChildren().get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // 初始化TabPane
        tabPane.setTabMaxWidth(240);
        tabPane.setTabMinWidth(240);

        // 实例化changeScene工具
        changeScene = utils.changeScene.getChangeScene();
        changeScene.setContainer(container);
        changeScene.setWelcomePane(welcomePane);

        // 设置manager中的avatarView和usernameTextfield
        Manager.getManager().setAvatarView(avatarView);
        Manager.getManager().setTextField(userName);
        Manager.getManager().setBadge(badge);

        // 包括登出、修改状态、查看个人信息功能的ContextMenu
        ContextMenu menu = new ContextMenu();
        // 查看个人信息
        MenuItem menuItem1 = new MenuItem("userinfo");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/fxml/UserInfoView.fxml"));
            if (changeScene.getChangeScene().getContainer().getChildren().get(0).getId().equals("chat")){
                Manager.getManager().setChatTo(Manager.getManager().user.account);
            }
            menuItem1.setOnAction(event -> changeScene.fade(container, root, (AnchorPane) container.getChildren().get(0)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 切换状态
        Menu status = new Menu("Change Status");
        ToggleGroup changeGroup = new ToggleGroup();
        RadioMenuItem onlineItem = new RadioMenuItem("online");
        RadioMenuItem concealedItem = new RadioMenuItem("concealed");
        RadioMenuItem busyItem = new RadioMenuItem("busy");
        onlineItem.setStyle(String.format("-fx-graphic:url(/resource/image/online.png)"));
        concealedItem.setStyle(String.format("-fx-graphic:url(/resource/image/concealed.png)"));
        busyItem.setStyle(String.format("-fx-graphic:url(/resource/image/busy.png)"));
        onlineItem.setToggleGroup(changeGroup);
        concealedItem.setToggleGroup(changeGroup);
        busyItem.setToggleGroup(changeGroup);
        if (Manager.getManager().user.status == 1){
            onlineItem.setSelected(true);
        }else if (Manager.getManager().user.status == 2){
            concealedItem.setSelected(true);
        }else{
            busyItem.setSelected(true);
        }
        status.getItems().addAll(onlineItem, concealedItem, busyItem);

        onlineItem.setOnAction(event -> {
            Manager.getManager().user.status = 1;
            avatarView.setStroke(convertImage.getConvertImage().chooseColor(Manager.getManager().user.status));
            Manager.getManager().out("STATUS#1" + " " + Manager.getManager().user.account);
        });

        concealedItem.setOnAction(event -> {
            Manager.getManager().user.status = 2;
            avatarView.setStroke(convertImage.getConvertImage().chooseColor(Manager.getManager().user.status));
            Manager.getManager().out("STATUS#2" + " " + Manager.getManager().user.account);

        });

        busyItem.setOnAction(event -> {
            Manager.getManager().user.status = 3;
            avatarView.setStroke(convertImage.getConvertImage().chooseColor(Manager.getManager().user.status));
            Manager.getManager().out("STATUS#3" + " " + Manager.getManager().user.account);

        });


        // 登出
        MenuItem menuItem3 = new MenuItem("Login Out");
        menuItem3.setOnAction(event -> {
            Manager.getManager().out("STATUS#0" + " " + Manager.getManager().user.account);
            changeScene.fade(changeScene.getStackPane(), changeScene.getAnchorPane(), userPane);
        });

        menu.getItems().addAll(menuItem1, status, menuItem3);
        userName.setContextMenu(menu);


        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        VBox FriendsVBox = new VBox();
        VBox FamilyVBox = new VBox();
        VBox ClassmateVBox = new VBox();

        // 将Relationship中的按钮取出
        Relationship relationship = Manager.getRelationship();
        relationship.FriendsVBox = FriendsVBox;
        relationship.FamilyVBox = FamilyVBox;
        relationship.ClassmateVBox = ClassmateVBox;
        for (Map.Entry<String, PersonButton> entry : relationship.friendsGroup.entrySet()){
            FriendsVBox.getChildren().add(entry.getValue());
        }

        for (Map.Entry<String, PersonButton> entry : relationship.familyGroup.entrySet()){
            FamilyVBox.getChildren().add(entry.getValue());
        }

        for (Map.Entry<String, PersonButton> entry : relationship.classmateGroup.entrySet()){
            ClassmateVBox.getChildren().add(entry.getValue());
        }

        TitledPane FriendsPane = new TitledPane();
        FriendsPane.setText("Friends");
        FriendsPane.setContent(FriendsVBox);

        TitledPane FamilyPane = new TitledPane();
        FamilyPane.setText("Family");
        FamilyPane.setContent(FamilyVBox);

        TitledPane ClassmatePane = new TitledPane();
        ClassmatePane.setText("Classmate");
        ClassmatePane.setContent(ClassmateVBox);


        friendAccordion.getPanes().addAll(FriendsPane, FamilyPane, ClassmatePane);
        scrollPane.setContent(friendAccordion);
        scrollPane.setMaxSize(250, 548);
        scrollPane.setMinSize(250, 548);
//        vBox.getChildren().add(scrollPane);
        friendsAnchorPane.getChildren().add(scrollPane);

        // 读入登录时传送并实例化过的信息
        userName.setText(Manager.getManager().user.name);
        avatarView.setImage(convertImage.getConvertImage().toWrite(Manager.getManager().user.avatar, "User"));
        avatarView.setStroke(convertImage.getConvertImage().chooseColor(Manager.getManager().user.status));



//        badge.setStyle("-fx-background-color:#ff4081; -fx-background-radius:23.0; -fx-pref-width: 23.0; -fx-pref-height: 23.0; -fx-alignment: center;" +
//                "-fx-font-weight: BOLD; -fx-font-size: 13.0px; -fx-text-fill: WHITE;");
    }

}
