package utils;

import utils.factory.AddFriend;
import utils.factory.Send;
import utils.factory.Status;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 邓梁
 * @date 2019/12/21 20:58
 * @email 18221221@bjtu.edu.cn
 * 工厂设计模式的工具类
 */
public class SignFactory {
    private Map<String, SignOperation> signOperationMap = new HashMap<>();

    public SignFactory() {
        signOperationMap.put("ADDFRIEND", new AddFriend());
        signOperationMap.put("SEND", new Send());
        signOperationMap.put("STATUS", new Status());
    }

    public SignOperation getOp(String sign, String data){
        signOperationMap.get(sign).setData(data);
        return signOperationMap.get(sign);
    }

}
