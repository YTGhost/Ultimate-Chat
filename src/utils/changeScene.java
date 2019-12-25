package utils;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * @author 邓梁
 * @date 2019/12/19 12:43
 * @email 18221221@bjtu.edu.cn
 * 更换场景的工具类
 */
public class changeScene {
    private static changeScene changeScene = new changeScene();

    private StackPane stackPane;
    private AnchorPane anchorPane;
    private AnchorPane container;
    private AnchorPane welcomePane;

    public static changeScene getChangeScene(){
        return changeScene;
    }

    public StackPane getStackPane() {
        return stackPane;
    }

    public void setStackPane(StackPane stackPane) {
        this.stackPane = stackPane;
    }

    public void setContainer(AnchorPane container){
        this.container = container;
    }

    public void setWelcomePane(AnchorPane welcomePane){
        this.welcomePane = welcomePane;
    }

    public AnchorPane getContainer(){
        return container;
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public AnchorPane getWelcomePane(){
        return welcomePane;
    }

    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    // chatView中界面的切换
    public void fade(AnchorPane container, Parent toadd, AnchorPane toremove){
        container.getChildren().add(toadd);

        FadeTransition fadeTransition1 = new FadeTransition(Duration.millis(2000));
        FadeTransition fadeTransition2 = new FadeTransition(Duration.millis(2000));

        fadeTransition1.setOnFinished((ActionEvent event1) -> {
            container.getChildren().remove(toremove);
        });
        fadeTransition1.setNode(toadd);
        fadeTransition1.setFromValue(0);
        fadeTransition1.setToValue(1);

        fadeTransition2.setNode(toremove);
        fadeTransition2.setFromValue(1);
        fadeTransition2.setToValue(0);

        fadeTransition1.play();
        fadeTransition2.play();

    }

    // 登录时的fade切换
    public void fade(StackPane container, Parent toadd, AnchorPane toremove){
        container.getChildren().add(toadd);

        FadeTransition fadeTransition1 = new FadeTransition(Duration.millis(2000));
        FadeTransition fadeTransition2 = new FadeTransition(Duration.millis(2000));

        fadeTransition1.setOnFinished((ActionEvent event1) -> {
            container.getChildren().remove(toremove);
        });
        fadeTransition1.setNode(toadd);
        fadeTransition1.setFromValue(0);
        fadeTransition1.setToValue(1);

        fadeTransition2.setNode(toremove);
        fadeTransition2.setFromValue(1);
        fadeTransition2.setToValue(0);

        fadeTransition1.play();
        fadeTransition2.play();

    }
}
