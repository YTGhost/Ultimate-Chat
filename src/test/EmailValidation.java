package test;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RegexValidator;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.event.DocumentListener;

/**
 * @author 邓梁
 * @date 2019/12/13 18:21
 * @email 18221221@bjtu.edu.cn
 */
public class EmailValidation extends Application {

    private JFXTextField textField;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10,10,10,10));
        bp.setStyle("-fx-background-color: #fff");
        Label label = new Label("Your Email");
        JFXTextField jfxTextField = new JFXTextField();
        //call the email validator
        jfxTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!jfxTextField.getText().matches("[1-9a-zA-z_]\\w{0,14}@\\w{1,}\\.(com|net|com.cn)")){
                    System.out.println("Input Validation");
                }
            }
        });
        VBox vBox = new VBox();
        vBox.setSpacing(30);

        JFXButton jfxButton = new JFXButton("Submit");
        jfxButton.setStyle(" -fx-background-color: green;-fx-text-fill: white");

        vBox.getChildren().addAll(label, jfxTextField, jfxButton);
        bp.setCenter(vBox);

        Scene scene = new Scene(bp,400,600);
        primaryStage.setTitle("Email validation in JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
