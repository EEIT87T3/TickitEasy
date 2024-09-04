package util;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

// 參考、改寫自 https://kaisheng714.github.io/articles/object-mapper
// 參考自 https://kucw.io/blog/2020/6/java-jackson/
// 參考、改寫自 ChatGPT
public class JsonUtil {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    private JsonUtil() {
    }

	/*
	 * method 名稱：BeanToJsonString
	 * 用途：將 Bean 物件轉換為 JsonString
	 * @param：Object（要轉換為 JsonString 的 Bean 物件）
	 * @return：String
	*/
    public static String beanToJsonString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
        	System.out.println("beanToJsonString 過程發生 JsonProcessingException；原物件為：");
        	System.out.println(obj);
        	e.printStackTrace();
            return "";
        }
    }

	/*
	 * method 名稱：JsonStringToBean
	 * 用途：將 JsonString 轉換為 Bean 物件
	 * @param：String（JsonString）、Object.class（要接收資料的 Bean Class 的 class 物件，請參考備註）
	 * @return：（Bean 物件）
	 * 備註：用法為 `YourBean yourBean = JsonUtil.toObject(jsonString, YourBean.class);`
	*/
    public static <T> T jsonStringToBean(String json, Class<T> objectClass) {
        try {
            return objectMapper.readValue(json, objectClass);
        } catch (IOException e) {
        	System.out.println("jsonStringToBean 過程發生 JsonProcessingException；原字串為：");
        	System.out.println(json);
        	e.printStackTrace();
            return null;
        }
    }
    
    /*
	 * method 名稱：ListBeanToJsonString
	 * 用途：將 List<Bean> 轉換為 JsonString
	 * @param：List<Object>（要轉換為 JsonString 的 List<Bean> 物件）
	 * @return：String
	*/
    public static String listBeanToJsonString(List<Object> list) {
        try {
            return objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
        	System.out.println("listBeanToJsonString 過程發生 JsonProcessingException；原物件為：");
        	System.out.println(list);
        	e.printStackTrace();
            return "";
        }
    }
    
    /*
	 * method 名稱：JsonStringToListBean
	 * 用途：將 JsonString 轉換為 List<Bean>
	 * @param：String（JsonString）、Object.class（請參考備註）
	 * @return：List<Bean>
	 * 備註：用法為 `List<YourBean> list = JsonUtil.jsonStringToList(jsonString, YourBean.class);`
	*/
    public static <T> List<T> jsonStringToListBean(String json, Class<T> objectClass) {
        JavaType type = TypeFactory.defaultInstance().constructCollectionType(List.class, objectClass);
        try {
            return objectMapper.readValue(json, type);
        } catch (JsonProcessingException e) {
            System.out.println("jsonStringToListBean 過程發生 JsonProcessingException；原字串為：");
            System.out.println(json);
            e.printStackTrace();
            return null;
        }
    }
}
