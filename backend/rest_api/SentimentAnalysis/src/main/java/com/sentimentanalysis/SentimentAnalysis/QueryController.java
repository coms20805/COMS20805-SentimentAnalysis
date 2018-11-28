package com.sentimentanalysis.SentimentAnalysis;

import com.uob.esclient.client.ElasticClient;
import com.uob.esclient.search.SearchQuery;
import com.uob.esclient.search.Strategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class QueryController  extends WebMvcConfigurerAdapter {

    @GetMapping("/api/search")
    public Result search(@RequestParam(value="query", defaultValue="") String query) {
        // This is temporary
        // TODO: modularise and refactor

        ElasticClient elasticClient = new ElasticClient();

        SearchQuery searchQuery = SearchQuery.builder().with((sq) -> {
            sq.limit = 100;
            sq.fieldToCompareAgainst = "content";
            sq.strategy = Strategy.FUZZY_MATCH;
            sq.literalQuery = query;
        }).build();

        List<Post> posts = elasticClient.findPosts(searchQuery, Post.class);

        return new Result(0.5f, posts);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

}
