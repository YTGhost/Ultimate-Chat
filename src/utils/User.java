package utils;

/**
 * @author 邓梁
 * @date 2019/12/21 13:27
 * @email 18221221@bjtu.edu.cn
 * 放置User信息的工具类
 */
public class User {
    public String account;
    public String name;
    public int age;
    public int sex;
    public byte[] avatar;
    public String address;
    public int status;
    public String telephone;
    public String birthday;

    public User(String account, String name, int age, int sex, byte[] avatar, String address, String telephone, String birthday, int status) {
        this.account = account;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.avatar = avatar;
        this.address = address;
        this.telephone = telephone;
        this.birthday = birthday;
        this.status = status;
    }
}
