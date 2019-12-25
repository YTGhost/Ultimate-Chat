package utils;

import com.gn.GNAvatarView;
import com.jfoenix.controls.JFXBadge;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.fontawesome.Icon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import manager.Manager;
import view.controller.ChatRoomController;
import view.controller.FriendInfoController;

import java.io.IOException;

/**
 * @author 邓梁
 * @date 2019/12/21 12:06
 * @email 18221221@bjtu.edu.cn
 * 实现好友按钮的工具类，extends了JFXButton
 */
public class PersonButton extends JFXButton {
    public String account;
    public String name;
    public int age;
    public int sex;
    public byte[] avatar;
    public String address;
    public int status;
    public String telephone;
    public String birthday;
    public String Group;
    public String nickname;

    public ContextMenu contextMenu;
    public MenuItem chatItem;
    public MenuItem infoItem;
    public Menu changeItem;
    public ToggleGroup changeGroup;
    public RadioMenuItem friendsItem;
    public RadioMenuItem familyItem;
    public RadioMenuItem classmateItem;
    public MenuItem deleteItem;

    public JFXBadge badge;
    public CircularQueue messageQueue = new CircularQueue(10000);
    public CircularQueue unmessageQueue = new CircularQueue(10000);
    public VBox chatVBox;
    public GNAvatarView imageView;
    public Image OnImageView;
    public Image OffImageView;


    public PersonButton(String account, String name, int age, int sex, byte[] avatar, String address, int status, String telephone, String birthday, String Group, String nickname) {
        this.account = account;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.avatar = avatar;
        this.address = address;
        this.status = status;
        this.telephone = telephone;
        this.birthday = birthday;
        this.Group = Group;
        this.nickname = nickname;

        // 开始设置ContextMenu
        contextMenu = new ContextMenu();
        chatItem = new MenuItem("To Chat");
        infoItem = new MenuItem("info");

        // 切换好友分组
        changeItem = new Menu("Change Group");
        changeGroup = new ToggleGroup();
        friendsItem = new RadioMenuItem("Friends");
        familyItem = new RadioMenuItem("Family");
        classmateItem = new RadioMenuItem("Classmate");
        friendsItem.setToggleGroup(changeGroup);
        familyItem.setToggleGroup(changeGroup);
        classmateItem.setToggleGroup(changeGroup);
        if (Group.equals("Friends")){
            friendsItem.setSelected(true);
        }else if (Group.equals("Family")){
            familyItem.setSelected(true);
        }else {
            classmateItem.setSelected(true);
        }
        changeItem.getItems().addAll(friendsItem, familyItem, classmateItem);

        friendsItem.setOnAction(event -> {
            if (this.Group.equals("Friends")){
                return;
            }
            if (this.Group.equals("Family")){
                Manager.getRelationship().familyGroup.remove(account);
                Manager.getRelationship().friendsGroup.put(account, this);
                Manager.getRelationship().FriendsVBox.getChildren().add(this);
                Manager.getRelationship().FamilyVBox.getChildren().remove(this);
            }else {
                Manager.getRelationship().classmateGroup.remove(account);
                Manager.getRelationship().friendsGroup.put(account, this);
                Manager.getRelationship().FriendsVBox.getChildren().add(this);
                Manager.getRelationship().ClassmateVBox.getChildren().remove(this);
            }
            Manager.getManager().out("CHANGE" + "#" + "G" + " " + Manager.getManager().user.account + " " +
                    this.account + " " + "Friends");
            this.Group = "Friends";
        });

        familyItem.setOnAction(event -> {
            if (this.Group.equals("Family")){
                return;
            }
            if (this.Group.equals("Friends")){
                Manager.getRelationship().friendsGroup.remove(account);
                Manager.getRelationship().familyGroup.put(account, this);
                Manager.getRelationship().FamilyVBox.getChildren().add(this);
                Manager.getRelationship().FriendsVBox.getChildren().remove(this);
            }else {
                Manager.getRelationship().classmateGroup.remove(account);
                Manager.getRelationship().familyGroup.put(account, this);
                Manager.getRelationship().FamilyVBox.getChildren().add(this);
                Manager.getRelationship().ClassmateVBox.getChildren().remove(this);
            }
            Manager.getManager().out("CHANGE" + "#" + "G" + " " + Manager.getManager().user.account + " " +
                    this.account + " " + "Family");
            this.Group = "Family";
        });

        classmateItem.setOnAction(event -> {
            if (this.Group.equals("Classmate")){
                return;
            }
            if (this.Group.equals("Family")){
                Manager.getRelationship().familyGroup.remove(account);
                Manager.getRelationship().classmateGroup.put(account, this);
                Manager.getRelationship().ClassmateVBox.getChildren().add(this);
                Manager.getRelationship().FamilyVBox.getChildren().remove(this);
            }else {
                Manager.getRelationship().friendsGroup.remove(account);
                Manager.getRelationship().classmateGroup.put(account, this);
                Manager.getRelationship().ClassmateVBox.getChildren().add(this);
                Manager.getRelationship().FriendsVBox.getChildren().remove(this);
            }
            Manager.getManager().out("CHANGE" + "#" + "G" + " " + Manager.getManager().user.account + " " +
                    this.account + " " + "Classmate");
            this.Group = "Classmate";
        });
        // 切换好友分组设置结束

        deleteItem = new MenuItem("Delete");

        chatItem.setOnAction(event -> {
            try {
                ChatRoomController.data = this;
                Parent root = FXMLLoader.load(getClass().getResource("/view/fxml/ChatRoomView.fxml"));
                changeScene.getChangeScene().fade(changeScene.getChangeScene().getContainer(), root, (AnchorPane) changeScene.getChangeScene().getContainer().getChildren().get(0));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // 打开好友的个人信息
        infoItem.setOnAction(event -> {
            try {
                FriendInfoController.data = this;
                Parent root = FXMLLoader.load(getClass().getResource("/view/fxml/FriendInfoView.fxml"));
                if (changeScene.getChangeScene().getContainer().getChildren().get(0).getId().equals("chat")){
                    Manager.getManager().setChatTo(Manager.getManager().user.account);
                }
                changeScene.getChangeScene().fade(changeScene.getChangeScene().getContainer(), root, (AnchorPane) changeScene.getChangeScene().getContainer().getChildren().get(0));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // 删除好友
        deleteItem.setOnAction(event -> {
            if (this.Group.equals("Friends")){
                Manager.getRelationship().friendsGroup.remove(this.account);
                Manager.getRelationship().FriendsVBox.getChildren().remove(this);
            }else if (this.Group.equals("Family")){
                Manager.getRelationship().familyGroup.remove(this.account);
                Manager.getRelationship().FamilyVBox.getChildren().remove(this);
            }else {
                Manager.getRelationship().classmateGroup.remove(this.account);
                Manager.getRelationship().ClassmateVBox.getChildren().remove(this);
            }
            Manager.getManager().out("CHANGE" + "#" + "D" + " " + Manager.getManager().user.account + " " +
                    this.account);
        });

        contextMenu.getItems().addAll(chatItem, infoItem, changeItem, deleteItem);
        this.setContextMenu(contextMenu);
        // 设置完毕

        // 设置大小，头像等
        this.setMinSize(250, 75);
        this.setMaxSize(250, 75);
        imageView = new GNAvatarView();
        badge = new JFXBadge(imageView, Pos.TOP_RIGHT);
        badge.getStyleClass().add("icons-badge");
        badge.setText("0");

        OffImageView = convertImage.getConvertImage().toGray(avatar, account);

        OnImageView = convertImage.getConvertImage().toWrite(avatar, account);

        if (status == 0){
            imageView.setImage(OffImageView);
        }else{
            imageView.setImage(OnImageView);
        }
        imageView.setPrefSize(50, 50);
        imageView.setStroke(convertImage.getConvertImage().chooseColor(status));

        this.setGraphic(badge);

        this.setStyle("-fx-alignment: CENTER_LEFT;");
        this.setText(name + "(" + nickname + ")");
        // 设置完毕
    }

}
