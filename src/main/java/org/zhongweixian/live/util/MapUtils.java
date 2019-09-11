package org.zhongweixian.live.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : caoliang
 * @date : 2017/12/29  下午3:26
 */
public class MapUtils {

    public static Map<String, Object> objectToMap(Object obj) {
        if(obj == null){
            return null;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            try {
                map.put(field.getName(), field.get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return map;
    }
}
