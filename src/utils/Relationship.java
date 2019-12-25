package utils;

import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 邓梁
 * @date 2019/12/21 12:06
 * @email 18221221@bjtu.edu.cn
 * 设置好友分组关系的工具类
 */
public class Relationship {
    public Map<String, PersonButton> friendsGroup = new HashMap<>();
    public Map<String, PersonButton> familyGroup = new HashMap<>();
    public Map<String, PersonButton> classmateGroup = new HashMap<>();
    public VBox FriendsVBox;
    public VBox FamilyVBox;
    public VBox ClassmateVBox;


    public void add(PersonButton personButton){
        if (personButton.Group.equals("Friends")){
            friendsGroup.put(personButton.account, personButton);
        }else if (personButton.Group.equals("Classmate")){
            classmateGroup.put(personButton.account, personButton);
        }else {
            familyGroup.put(personButton.account, personButton);
        }
    }

    public void remove(PersonButton personButton){
        if (personButton.Group.equals("Friends")){
            friendsGroup.remove(personButton.account);
        }else if (personButton.Group.equals("Classmate")){
            classmateGroup.remove(personButton.account);
        }else{
            familyGroup.remove(personButton.account);
        }
    }
}
