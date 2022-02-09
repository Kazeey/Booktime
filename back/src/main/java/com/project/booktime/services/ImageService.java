package com.project.booktime.services;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;


@Service
public class ImageService
{
    public String encodeImageFromUrl(String imageUrl) throws IOException
    {
        if (imageUrl == null)
            return "N/A";

        URL urlImage = new URL(imageUrl);
        BufferedImage image = ImageIO.read(urlImage);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();

        return Base64.getEncoder().encodeToString(imageInByte);
    }

    public String encodeImageFromPath(String imagePath) throws IOException
    {
        
    }
}
