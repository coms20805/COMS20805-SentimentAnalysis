package com.sentimentanalysis.SentimentAnalysis;

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
            posts = ESQueryController.esQuery(query);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        double averageScore = posts.stream().mapToDouble(Post::getScore).sum() / posts.size();

        return new Result(averageScore, posts);
    }
}
