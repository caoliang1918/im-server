package org.zhongweixian.live.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by caoliang on 2019-06-03
 */
public class BaseController {

    public Map<String, Object> convertToMap(Object obj) {

        Map<String, Object> map = new HashMap<String, Object>();
        if (obj == null) {
            return map;
        }
        String str = null;
        if (obj instanceof String) {
            str = obj.toString();
        } else {
            str = JSONObject.toJSONString(obj);
        }
        Gson gson = new Gson();
        map = gson.fromJson(str, map.getClass());
        return map;
    }
}
