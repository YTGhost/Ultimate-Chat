package utils;

import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * @author 邓梁
 * @date 2019/12/14 16:44
 * @email 18221221@bjtu.edu.cn
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

    public File toWrite(byte[] data, String imageName){
        File file = null;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        try {
            BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);
            ImageIO.write(bufferedImage, "jpg", file = new File(String.format("/resource/cache/%s.jpg", imageName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
