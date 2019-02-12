package com.sentimentanalysis.SentimentAnalysis;

import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class QueryController {

    @CrossOrigin(origins="*")
    @GetMapping("/api/search")
    public Result search(@RequestParam(value="query", defaultValue="") String query) {
        // This is temporary
        // TODO: modularise and refactor

        List<Post> posts;
        try {
            posts = ESQueryController.searchQuery(query);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        double averageScore = posts.stream().mapToDouble(Post::getScore).sum() / posts.size();

        return new Result(averageScore, posts);
    }

    @CrossOrigin(origins="*")
    @GetMapping("/api/median")
    public MedianResponse median(@RequestParam(value="query", defaultValue="") String query) {
        MedianResponse response;
        try {
            response = ESQueryController.medianQuery(query);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        return response;
    }
}
