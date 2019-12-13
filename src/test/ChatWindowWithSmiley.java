package test;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/**
 * @author 邓梁
 * @date 2019/12/13 15:10
 * @email 18221221@bjtu.edu.cn
 */
public class ChatWindowWithSmiley extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        TextFlow textFlow = new TextFlow();
        textFlow.setPadding(new Insets(10));
        textFlow.setLineSpacing(10);
        TextField textField = new TextField();
        Button button = new Button("Send");
        button.setPrefWidth(70);

        VBox container = new VBox();
        container.getChildren().addAll(textFlow, new HBox(textField, button));
        VBox.setVgrow(textFlow, Priority.ALWAYS);

        // Textfield re-sizes according to VBox
        textField.prefWidthProperty().bind(container.widthProperty().subtract(button.prefWidthProperty()));

        // On Enter press
        textField.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) {
                button.fire();
            }
        });

        button.setOnAction(e -> {
            Text text;
            if(textFlow.getChildren().size()==0){
                text = new Text(textField.getText());
            } else {
                // Add new line if not the first child
                text = new Text("\n" + textField.getText());
            }
            if(textField.getText().contains(":)")) {
                ImageView imageView = new ImageView("http://files.softicons.com/download/web-icons/network-and-security-icons-by-artistsvalley/png/16x16/Regular/Friend%20Smiley.png");
                // Remove :) from text
                text.setText(text.getText().replace(":)"," "));
                textFlow.getChildren().addAll(text, imageView);
            } else {
                textFlow.getChildren().add(text);
            }
            textField.clear();
            textField.requestFocus();
        });

        Scene scene = new Scene(container, 300, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
