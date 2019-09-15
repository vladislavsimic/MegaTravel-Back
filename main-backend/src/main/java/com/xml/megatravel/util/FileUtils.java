package com.xml.megatravel.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {

    public static File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        final File file = new File("C:/Users/acer/" + multipartFile.getOriginalFilename());
        final FileOutputStream fos = new FileOutputStream(file);
        fos.write(multipartFile.getBytes());
        fos.close();
        return file;
    }

    public static String getFileIdFromUrl(String fileUrl) {
        return fileUrl.substring(fileUrl.lastIndexOf('/') + 1);
    }
}
