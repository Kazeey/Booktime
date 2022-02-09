package com.project.booktime.services;

import com.project.booktime.params.Constants;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Base64;


@Service
public class ImageService
{
    public String encodeImageFromUrl(String imageUrl) throws IOException
    {
        if (imageUrl == null)
            return Constants.NON_ACQUIS;

        URL urlImage = new URL(imageUrl);
        BufferedImage image = ImageIO.read(urlImage);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOutputStream);
        byteArrayOutputStream.flush();
        byte[] imageInByte = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();

        return Base64.getEncoder().encodeToString(imageInByte);
    }

    public String encoreImageFromPath(String imagePath) throws IOException
    {
        InputStream inputStream = new FileInputStream(imagePath);
        byte[] bytes = IOUtils.toByteArray(inputStream);
        return Base64.getEncoder().encodeToString(bytes);
    }
}
