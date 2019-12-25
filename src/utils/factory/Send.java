package utils.factory;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import manager.Manager;
import utils.*;
import view.controller.ChatRoomController;

import java.io.IOException;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author 邓梁
 * @date 2019/12/23 23:49
 * @email 18221221@bjtu.edu.cn
 * 实现的发送信息的工厂业务类
 */
public class Send implements SignOperation {

    private String data;

    @Override
    public void op() {
        StringTokenizer parse = new StringTokenizer(data, " ");
        String sign = parse.nextToken();
        String accountFrom = parse.nextToken();
        if (sign.equals("I")){
            byte[] temp = convertImage.getConvertImage().stringToBytes(parse);
            ImageView avatar = new ImageView(convertImage.getConvertImage().toWrite(temp, "image"));

            for (Map.Entry<String, PersonButton> entry : Manager.getRelationship().friendsGroup.entrySet()){
                if (entry.getKey().equals(accountFrom)){
                    if(Manager.getManager().getChatTo().equals(entry.getKey())){
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Message message1 = new Message(avatar, 1);
                                entry.getValue().messageQueue.addBack(message1);
                                HBox hBox = new HBox(message1);
                                hBox.setAlignment(Pos.CENTER_LEFT);
                                entry.getValue().chatVBox.getChildren().add(hBox);
                                return;
                            }
                        });
                    }
                    entry.getValue().unmessageQueue.addBack(new Message(avatar, 1));
                    // 设置消息到达的标志，并将详细放入
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            entry.getValue().badge.setText(String.valueOf(entry.getValue().unmessageQueue.getSize()));
                        }
                    });
                }
            }

            for (Map.Entry<String, PersonButton> entry : Manager.getRelationship().familyGroup.entrySet()){
                if (entry.getKey().equals(accountFrom)){
                    if(Manager.getManager().getChatTo().equals(entry.getKey())){
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Message message1 = new Message(avatar, 1);
                                entry.getValue().messageQueue.addBack(message1);
                                HBox hBox = new HBox(message1);
                                hBox.setAlignment(Pos.CENTER_LEFT);
                                entry.getValue().chatVBox.getChildren().add(hBox);
                                return;
                            }
                        });
                    }
                    entry.getValue().unmessageQueue.addBack(new Message(avatar, 1));
                    // 设置消息到达的标志，并将详细放入
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            entry.getValue().badge.setText(String.valueOf(entry.getValue().unmessageQueue.getSize()));
                        }
                    });
                }
            }

            for (Map.Entry<String, PersonButton> entry : Manager.getRelationship().classmateGroup.entrySet()){
                if (entry.getKey().equals(accountFrom)){
                    if(Manager.getManager().getChatTo().equals(entry.getKey())){
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Message message1 = new Message(avatar, 1);
                                entry.getValue().messageQueue.addBack(message1);
                                HBox hBox = new HBox(message1);
                                hBox.setAlignment(Pos.CENTER_LEFT);
                                entry.getValue().chatVBox.getChildren().add(hBox);
                                return;
                            }
                        });
                    }
                    entry.getValue().unmessageQueue.addBack(new Message(avatar, 1));
                    // 设置消息到达的标志，并将详细放入
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            entry.getValue().badge.setText(String.valueOf(entry.getValue().unmessageQueue.getSize()));
                        }
                    });
                }
            }


        }else {
            StringBuilder temp = new StringBuilder(parse.nextToken());
            while(parse.hasMoreTokens()){
                temp.append(" ");
                temp.append(parse.nextToken());
            }
            String message = temp.toString();

            for (Map.Entry<String, PersonButton> entry : Manager.getRelationship().friendsGroup.entrySet()){
                if (entry.getKey().equals(accountFrom)){
                    if(Manager.getManager().getChatTo().equals(entry.getKey())){
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Message message1 = new Message(message, 1);
                                entry.getValue().messageQueue.addBack(message1);
                                HBox hBox = new HBox(message1);
                                hBox.setAlignment(Pos.CENTER_LEFT);
                                entry.getValue().chatVBox.getChildren().add(hBox);
                                return;
                            }
                        });
                    }
                    entry.getValue().unmessageQueue.addBack(new Message(message, 1));
                            // 设置消息到达的标志，并将详细放入
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            entry.getValue().badge.setText(String.valueOf(entry.getValue().unmessageQueue.getSize()));
                        }
                    });
                }
            }

            for (Map.Entry<String, PersonButton> entry : Manager.getRelationship().familyGroup.entrySet()){
                if (entry.getKey().equals(accountFrom)){
                    if(Manager.getManager().getChatTo().equals(entry.getKey())){
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Message message1 = new Message(message, 1);
                                entry.getValue().messageQueue.addBack(message1);
                                HBox hBox = new HBox(message1);
                                hBox.setAlignment(Pos.CENTER_LEFT);
                                entry.getValue().chatVBox.getChildren().add(hBox);
                                return;
                            }
                        });
                    }
                    entry.getValue().unmessageQueue.addBack(new Message(message, 1));
                    // 设置消息到达的标志，并将详细放入
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            entry.getValue().badge.setText(String.valueOf(entry.getValue().unmessageQueue.getSize()));
                        }
                    });
                }
            }

            for (Map.Entry<String, PersonButton> entry : Manager.getRelationship().classmateGroup.entrySet()){
                if (entry.getKey().equals(accountFrom)){
                    if(Manager.getManager().getChatTo().equals(entry.getKey())){
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Message message1 = new Message(message, 1);
                                entry.getValue().messageQueue.addBack(message1);
                                HBox hBox = new HBox(message1);
                                hBox.setAlignment(Pos.CENTER_LEFT);
                                entry.getValue().chatVBox.getChildren().add(hBox);
                                return;
                            }
                        });
                    }
                    entry.getValue().unmessageQueue.addBack(new Message(message, 1));
                    // 设置消息到达的标志，并将详细放入
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            entry.getValue().badge.setText(String.valueOf(entry.getValue().unmessageQueue.getSize()));
                        }
                    });
                }
            }

        }


    }

    @Override
    public void setData(String data) {
        this.data = data;
    }
}
