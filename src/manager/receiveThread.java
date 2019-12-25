package manager;

import utils.SignFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 * @author 邓梁
 * @date 2019/12/19 17:12
 * @email 18221221@bjtu.edu.cn
 * 用于持续的监听服务端发来的信息
 */
public class receiveThread implements Runnable {
    @Override
    public void run() {
        try {
            String content = null;
            while ((content = bufferedReader.readLine()) != null){
                System.out.println(content);
                if (!content.substring(0,1).equals("#")){
                    Manager.getManager().content = content;
                    System.out.println(content);
                    Manager.getManager().sign = 1;
                    while (Manager.getManager().sign == 1){
                        System.out.print("");
                        // 循环直到content被取走
                    }
                }else{
                    SignFactory signFactory = new SignFactory();
                    content = content.substring(1);
                    StringTokenizer parse = new StringTokenizer(content, "#");
                    String sign = parse.nextToken();
                    String data = parse.nextToken();
                    // 测试用
//                    System.out.println(sign);
//                    System.out.println(data);
                    // 测试用
                    signFactory.getOp(sign, data).op();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Socket s;
    BufferedReader bufferedReader = null;
    public receiveThread(Socket s) throws IOException {
        this.s = s;
        bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
    }
}
