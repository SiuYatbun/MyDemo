package com.example.mydemo.common.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TemplateUtil {
    private static final Logger logger = LoggerFactory.getLogger(TemplateUtil.class);
    private static final Configuration cfg;

    static {
        cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setNumberFormat("#");  //FreeMarker会默认格式化数字，这样设置不再格式化数字
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);
    }

    public static String processTemplate(String templateName, Map<String, Object> map) {
        String result = null;
        try {
            //获取资源文件另一种方式: new ClassPathResource("/template").getFile()
            cfg.setDirectoryForTemplateLoading(ResourceUtils.getFile("classpath:template"));
            Template template = cfg.getTemplate(templateName, "UTF-8");
            StringWriter out = new StringWriter();
            template.process(map, out);
            out.flush();
            result = out.toString();
            out.close();
        } catch (TemplateException | IOException e) {
            logger.error("TemplateUtil processTemplate error", e);
        }
        return result;
    }

    public static Map<String, Object> dataToMap(Object obj) {
        Map<String, Object> map = new HashMap<>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = ReflectionUtils.getField(field, obj);
            if (Objects.isNull(value)) {
                value = "";
            }
            map.put(field.getName(), value);
        }
        return map;
    }
}
