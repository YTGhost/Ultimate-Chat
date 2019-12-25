package utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author 邓梁
 * @date 2019/12/24 9:03
 * @email 18221221@bjtu.edu.cn
 * 拓展Bubble工具类的消息气泡
 */
public class Message extends Bubble{

    public int sign;    // 1为对方发来的，0为自己发的
    public ImageView image;

    public Message(String message, int sign){
        super(message);
        this.sign = sign;
    }

    public Message(ImageView image, int sign){
        super(image);
        this.sign = sign;
    }

}
