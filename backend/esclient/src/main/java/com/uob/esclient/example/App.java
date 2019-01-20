package com.uob.esclient.example;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Value;

public class App {


    /* A generic Post that represents the documents
    stored in the elasticsearch database
    */
    @Value private static class Post {
        private String content;
        private String timestamp;
        private String url;
        private double score;

    }

    public static void main(String[] args) throws UnirestException {

        /*
        ---  Deprecated ----

        ElasticClient client = new ElasticClient();
        SearchQuery searchQuery = SearchQuery.builder().with((sq) -> {
            sq.limit = 100;
            sq.fieldToCompareAgainst = "content";
            sq.strategy = Strategy.FUZZY_MATCH;
            sq.literalQuery = "pythn";
        }).build();

        List<Post> posts = client.findPosts(searchQuery, Post.class);
        posts.forEach(System.out::println);

        */

        HttpResponse<JsonNode> jsonNodeHttpResponse = Unirest.get("https://es-app.herokuapp.com/query").
                queryString("literal_query", "golang"). //can alternatively pass a map
                asJson();

        String stringifiedJsonList = jsonNodeHttpResponse.getBody().getObject().get("result").toString();
        Gson gson = new Gson();
        Post posts[] = gson.fromJson(stringifiedJsonList, Post[].class);
        Arrays.stream(posts).forEach(System.out::println);
    }
}
