package view.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import manager.Manager;
import utils.Bubble;
import utils.Message;
import utils.PersonButton;
import utils.convertImage;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * @author 邓梁
 * @date 2019/12/23 20:06
 * @email 18221221@bjtu.edu.cn
 * ChatRoomView的操纵类
 */
public class ChatRoomController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXTextField nameTextField;

    @FXML
    private JFXTextArea messageTextArea;

    @FXML
    private JFXButton sendButton;

    @FXML
    private JFXButton imageButton;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    public VBox chatVBox;

    @FXML
    public static PersonButton data;



    @FXML
    void Send(MouseEvent event){
        String content = null;
        content = messageTextArea.getText();
        messageTextArea.setText("");
        if (content.equals("")){
            return;
        }
        String fromAccount = Manager.getManager().user.account;
        String toAccount = data.account;
        Manager.getManager().out("SEND#" + "T" + " " + fromAccount + " " + toAccount + " " + content);
        Message message = new Message(content, 0);
        data.messageQueue.addBack(message);
        HBox hBox = new HBox(message);
        hBox.setAlignment(Pos.CENTER_RIGHT);
        chatVBox.getChildren().add(hBox);
    }

    @FXML
    void sendImage(MouseEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource Image File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        File avatar = fileChooser.showOpenDialog(Manager.getManager().getStage());
        String fileName = avatar.getName();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        byte[] avatarView = convertImage.getConvertImage().toByteArray(avatar, suffix);
        ImageView image = new ImageView(avatar.toURI().toString());

        String fromAccount = Manager.getManager().user.account;
        String toAccount = data.account;
        Manager.getManager().out("SEND#" + "I" + " " + fromAccount + " " + toAccount + " " + Arrays.toString(avatarView));

        Message message = new Message(image, 0);
        data.messageQueue.addBack(message);
        HBox hBox = new HBox(message);
        hBox.setAlignment(Pos.CENTER_RIGHT);
        chatVBox.getChildren().add(hBox);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        data.chatVBox = this.chatVBox;
        Manager.getManager().setChatTo(data.account);
        nameTextField.setText(data.name);

        int unsize = data.unmessageQueue.getSize();
        for (int i = 0; i < unsize; i++) {
            data.messageQueue.addBack(data.unmessageQueue.getFront());
            data.unmessageQueue.removeFront();
        }
        data.badge.setText("0");

        int size = data.messageQueue.getSize();
        for (int i = 0; i < size; i++) {
            Message message = (Message) data.messageQueue.getIndex(i);
            HBox hBox = new HBox(message);
            if (message.sign == 1){
                hBox.setAlignment(Pos.CENTER_LEFT);
            }else{
                hBox.setAlignment(Pos.CENTER_RIGHT);
            }
            chatVBox.getChildren().add(hBox);
        }

        scrollPane.setContent(chatVBox);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        imageButton.setStyle(String.format("-fx-graphic:url(/resource/image/image.png)"));
    }
}
