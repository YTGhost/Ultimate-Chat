package utils;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

/**
 * @author 邓梁
 * @date 2019/12/14 16:44
 * @email 18221221@bjtu.edu.cn
 * 实现转换Image等一系列与图片相关的工具类
 */
public class convertImage {
    private static convertImage convertImage = new convertImage();

    public convertImage(){
    }

    public static convertImage getConvertImage(){
        return convertImage;
    }

    public byte[] toByteArray(File file, String formatName){
        byte[] data = null;
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, formatName, byteArrayOutputStream);
            data = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public Image toWrite(byte[] data, String imageName){
        File file = null;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        try {
            BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);
            ImageIO.write(bufferedImage, "jpg", file = new File(String.format("src\\resource\\cache\\%s.jpg", imageName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Image(file.toURI().toString());
    }

    public Image toGray(byte[] data, String imageName){
        File file = null;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        try {
            BufferedImage img = ImageIO.read(byteArrayInputStream);

            int width = img.getWidth();
            int height = img.getHeight();

            for(int y = 0; y < height; y++){
                for(int x = 0; x < width; x++){
                    int p = img.getRGB(x,y);

                    int a = (p>>24)&0xff;
                    int r = (p>>16)&0xff;
                    int g = (p>>8)&0xff;
                    int b = p&0xff;

                    //calculate average
                    int avg = (r+g+b)/3;

                    //replace RGB value with avg
                    p = (a<<24) | (avg<<16) | (avg<<8) | avg;

                    img.setRGB(x, y, p);
                }
            }
            ImageIO.write(img, "jpg", file = new File(String.format("src\\resource\\cache\\%s.jpg", imageName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Image(file.toURI().toString());
    }

    public byte[] stringToBytes(StringTokenizer parse){
        StringBuilder temp = new StringBuilder(parse.nextToken());
        while(parse.hasMoreTokens()){
            temp.append(" ");
            temp.append(parse.nextToken());
        }
        String image = temp.toString();
        String[] byteValues = image.substring(1, image.length() - 1).split(",");
        byte[] avatar = new byte[byteValues.length];
        for (int i = 0, len = avatar.length; i < len; i++) {
            avatar[i] = Byte.parseByte(byteValues[i].trim());
        }
        return avatar;
    }

    public Color chooseColor(int status){
        Color color = Color.GRAY;
        switch (status){
            case 0:
                color = Color.GRAY;
                break;
            case 1:
                color = Color.GREEN;
                break;
            case 2:
                color = Color.YELLOW;
                break;
            case 3:
                color = Color.RED;
                break;
        }
        return color;
    }
}
