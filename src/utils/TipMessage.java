package utils;

/**
 * @author 邓梁
 * @date 2019/12/22 12:50
 * @email 18221221@bjtu.edu.cn
 * 好友申请工具类（用于放入消息队列中）
 */
public class TipMessage {
    private String accountFrom;

    private String name;

    private String nickname;

    private String group;

    private String message;

    private byte[] avatar;

    public TipMessage(String accountFrom, String name, String nickname, String group, String message, byte[] avatar) {
        this.accountFrom = accountFrom;
        this.name = name;
        this.nickname = nickname;
        this.group = group;
        this.message = message;
        this.avatar = avatar;
    }

    public String getAccountFrom() {
        return accountFrom;
    }

    public String getName() {
        return name;
    }

    public String getNickname(){
        return nickname;
    }

    public String getGroup(){
        return group;
    }

    public String getMessage() {
        return message;
    }

    public byte[] getAvatar() {
        return avatar;
    }
}
