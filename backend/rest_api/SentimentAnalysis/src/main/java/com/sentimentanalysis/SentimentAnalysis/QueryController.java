package com.sentimentanalysis.SentimentAnalysis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;

@RestController
public class QueryController {

    @GetMapping("/search")
    public Result search(@RequestParam(value="query", defaultValue="") String query) {
        // Create dummy response
        Post post1 = new Post(0.0f, query + " is very bad", "/example/url/", new Date(999999999));
        Post post2 = new Post(1.0f, query + " is very good", "/example/url/", new Date(999999999));
        ArrayList<Post> posts = new ArrayList<Post>();
        posts.add(post1);
        posts.add(post2);
        return new Result(0.5f, posts);
    }
}
