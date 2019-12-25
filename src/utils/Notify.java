package utils;

import manager.Manager;

/**
 * @author 邓梁
 * @date 2019/12/19 19:58
 * @email 18221221@bjtu.edu.cn
 * 用于提醒挂起的GUI的提醒工具类
 */
public class Notify {
    public static Notify notify = new Notify();

    public static Notify getInstance(){
        return notify;
    }

    public void toNotify(){
        while (true){
            if (Manager.getManager().sign == 1){
                break;
            }
            System.out.print("");
        }
        Manager.getManager().sign = 0;
    }
}
