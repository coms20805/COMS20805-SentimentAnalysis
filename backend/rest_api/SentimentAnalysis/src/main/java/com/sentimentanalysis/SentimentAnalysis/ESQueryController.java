package com.sentimentanalysis.SentimentAnalysis;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ESQueryController {

    public static List<Post> esQuery(String query) throws UnknownHostException, UnirestException {

        HttpResponse<JsonNode> jsonNodeHttpResponse = Unirest.get("https://es-app.herokuapp.com/query").
                queryString("literal_query", query). //can alternatively pass a map
                asJson();

        String stringifiedJsonList = jsonNodeHttpResponse.getBody().getObject().get("result").toString();
        Gson gson = new Gson();
        Post posts[] = gson.fromJson(stringifiedJsonList, Post[].class);
        return Arrays.stream(posts).collect(Collectors.toList());
    }
}