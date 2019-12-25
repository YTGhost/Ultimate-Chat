package utils.factory;

import com.gn.GNAvatarView;
import com.jfoenix.controls.JFXSnackbar;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import manager.Manager;
import utils.*;

import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author 邓梁
 * @date 2019/12/24 12:54
 * @email 18221221@bjtu.edu.cn
 * 实现更换状态的工厂业务类
 */
public class Status implements SignOperation {

    private String data;

    @Override
    public void op() {
        StringTokenizer parse = new StringTokenizer(data, " ");
        String account = parse.nextToken();
        int status = Integer.parseInt(parse.nextToken());


        for (Map.Entry<String, PersonButton> entry : Manager.getRelationship().friendsGroup.entrySet()){
            if (entry.getKey().equals(account)){
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        entry.getValue().imageView.setStroke(convertImage.getConvertImage().chooseColor(status));
                        if (status == 0){
                            entry.getValue().imageView.setImage(entry.getValue().OffImageView);
                        }else {
                            entry.getValue().imageView.setImage(entry.getValue().OnImageView);
                        }
                    }
                });
            }
        }

        for (Map.Entry<String, PersonButton> entry : Manager.getRelationship().familyGroup.entrySet()){
            if (entry.getKey().equals(account)){
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        entry.getValue().imageView.setStroke(convertImage.getConvertImage().chooseColor(status));
                        if (status == 0){
                            entry.getValue().imageView.setImage(entry.getValue().OffImageView);
                        }else {
                            entry.getValue().imageView.setImage(entry.getValue().OnImageView);
                        }
//                        JFXSnackbar snackbar = new JFXSnackbar(changeScene.getChangeScene().getStackPane());
//                        snackbar.fireEvent(new JFXSnackbar.SnackbarEvent(new Label("Hello")));
                    }
                });
            }
        }

        for (Map.Entry<String, PersonButton> entry : Manager.getRelationship().classmateGroup.entrySet()){
            if (entry.getKey().equals(account)){
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        entry.getValue().imageView.setStroke(convertImage.getConvertImage().chooseColor(status));
                        if (status == 0){
                            entry.getValue().imageView.setImage(entry.getValue().OffImageView);
                        }else {
                            entry.getValue().imageView.setImage(entry.getValue().OnImageView);
                        }
//                        JFXSnackbar snackbar = new JFXSnackbar(changeScene.getChangeScene().getStackPane());
//                        snackbar.fireEvent(new JFXSnackbar.SnackbarEvent(new Label("Hello")));
                    }
                });
            }
        }

    }

    @Override
    public void setData(String data) {
        this.data = data;
    }
}
