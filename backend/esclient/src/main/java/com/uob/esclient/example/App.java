package com.uob.esclient.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Value;

public class App {


    /* A generic Post that represents the documents
    stored in the elasticsearch database
    */
    @Value
    private static class Post {
        private String content;
        private String timestamp;
        private String url;
        private double score;
        private long id;

        static List<Post> parse(String jsonString) {
            Gson gson = new Gson();
            Post posts[] = gson.fromJson(jsonString, Post[].class);
            return Arrays.stream(posts).collect(Collectors.toList());
        }
    }


    private static class DailyMedians {

        static Map<String, Double> parse(String jsonString) {
            Gson gson = new Gson();
            return gson.fromJson(jsonString, new TypeToken<HashMap<String, Double>>() {
            }.getType());
        }

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

        //Two examples
        /*
        (1) The first one fetches daily median scores for  a framework
        (2) The second one fetches posts, given a framework

         */


        //Example 1: fetch the daily medians for some framework
        HttpResponse<JsonNode> jsonNodeHttpResponse = Unirest.get("https://es-app.herokuapp.com/median").
                queryString("framework", "rust"). //can alternatively pass a map
                asJson();

        String jsonString = jsonNodeHttpResponse.getBody().toString();
        Map<String, Double> medians = DailyMedians.parse(jsonString);
        System.out.println("Median scores " + medians);
        System.out.println("-----------------------------------------------------");


        //Example 2: fetch some posts related to a given framework
        HttpResponse<JsonNode> jsonResponse = Unirest.get("https://es-app.herokuapp.com/search").
                queryString("literal_query", "rust"). //can also specify limit
                asJson();


        String anotherJsonString = jsonResponse.getBody().getObject().get("result").toString();
        List<Post> posts = Post.parse(anotherJsonString);
        posts.forEach(System.out::println);
    }
}
