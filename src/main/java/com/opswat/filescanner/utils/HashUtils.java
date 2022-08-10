package com.opswat.filescanner.utils;

import com.opswat.filescanner.exceptions.FileHashException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {
    public static String hashFile(String filePath, String alg) throws FileHashException {
        try {
            File file = new File(filePath);
            MessageDigest digest = MessageDigest.getInstance(alg);
            FileInputStream fis = new FileInputStream(file);
            byte[] byteArray = new byte[1024];
            int bytesCount;
            while ((bytesCount = fis.read(byteArray)) != -1) {
                digest.update(byteArray, 0, bytesCount);
            }
            fis.close();
            byte[] bytes = digest.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException | IOException e){
            throw new FileHashException(e.getMessage());
        }
    }
}
