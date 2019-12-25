package utils.factory;

import javafx.application.Platform;
import manager.Manager;
import utils.SignOperation;
import utils.TipMessage;
import utils.convertImage;

import java.util.StringTokenizer;

/**
 * @author 邓梁
 * @date 2019/12/21 20:57
 * @email 18221221@bjtu.edu.cn
 * 实现的添加好友工厂业务类
 */
public class AddFriend implements SignOperation {

    private String data;


    @Override
    public void op() {
        StringTokenizer parse = new StringTokenizer(data, " ");
        String accountFrom = parse.nextToken();
        String name = parse.nextToken();
        String nickname = parse.nextToken();
        String group = parse.nextToken();
        String message = parse.nextToken();
        byte[] avatar = convertImage.getConvertImage().stringToBytes(parse);

        // 创建消息队列
        Manager.getManager().getMessageQueue().addBack(new TipMessage(accountFrom, name, nickname, group, message, avatar));
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Manager.getManager().getBadge().setText(String.valueOf(Manager.getManager().getMessageQueue().getSize()));
            }
        });
    }

    @Override
    public void setData(String data) {
        this.data = data;
    }
}
