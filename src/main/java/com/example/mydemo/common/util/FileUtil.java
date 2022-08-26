package com.example.mydemo.common.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {
    public static String readString(String filePath) {
        return readString(new File(filePath));
    }

    public static String readString(File file) {
        try (FileReader reader = new FileReader(file)) {
            char[] chars = new char[(int) file.length()];
            int length = reader.read(chars);
            return new String(chars, 0, length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
