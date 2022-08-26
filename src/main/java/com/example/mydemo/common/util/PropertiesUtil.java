package com.example.mydemo.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class PropertiesUtil {
    public static Properties getProperties(String filePath){
        return getProperties(new File(filePath));
    }

    public static Properties getProperties(File file){
        String content = FileUtil.readString(file);
        char[] chars = new char[(int) file.length()];
        content.getChars(0, chars.length, chars, 0);
        List<String> list = new ArrayList<>();
        int begin = 0;
        for(int i = 0; i < chars.length; i++){
            if(chars[i] == '\n' || i == chars.length - 1){
                if(i == chars.length - 1){
                    i++;
                }
                list.add(new String(chars, begin, i - begin).trim());
                begin = i + 1;
            }
        }
        Map<String, String> map = list.stream().filter(str -> str.contains("=") && !str.startsWith("#")).collect(Collectors.toMap(
                str -> str.split("=")[0],
                str -> str.split("=")[1]
        ));
        Properties props = new Properties();
        props.putAll(map);
        return props;
    }
}
