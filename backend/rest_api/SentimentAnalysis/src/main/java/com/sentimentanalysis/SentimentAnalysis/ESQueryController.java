package com.sentimentanalysis.SentimentAnalysis;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import org.json.JSONObject;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ESQueryController {

    public static List<Post> searchQuery(String query) throws UnknownHostException, UnirestException {

        HttpResponse<JsonNode> jsonNodeHttpResponse = Unirest.get("https://es-app.herokuapp.com/search").
                queryString("literal_query", query). //can alternatively pass a map
                asJson();

        String stringifiedJsonList = jsonNodeHttpResponse.getBody().getObject().get("result").toString();
        Gson gson = new Gson();
        Post posts[] = gson.fromJson(stringifiedJsonList, Post[].class);
        return Arrays.stream(posts).collect(Collectors.toList());
    }

    public static MedianResponse medianQuery(String query) throws UnknownHostException, UnirestException {
        HttpResponse<JsonNode> jsonNodeHttpResponse = Unirest.get("https://es-app.herokuapp.com/median").
                queryString("framework", query).
                asJson();

        Gson gson = new Gson();

        String responseBody = jsonNodeHttpResponse.getBody().getObject().toString();

        Type type = new TypeToken<Map<String, Double>>(){}.getType();
        MedianResponse response = new MedianResponse(gson.fromJson(responseBody, type));

        return response;
    }
}