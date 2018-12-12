package com.uob.esclient.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.Map;

public class PostUtils {

    public static <P> P toPost(Map<String, Object> mmap, Class<P> postClazz) {
        Gson gson = new Gson();
        JsonElement json = gson.toJsonTree(mmap);
        return gson.fromJson(json, postClazz);
    }
}
