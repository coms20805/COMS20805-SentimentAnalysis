package com.sentimentanalysis.SentimentAnalysis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.*;

@RestController
public class QueryController  extends WebMvcConfigurerAdapter {

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

        return new Result(0.5f, posts);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

}
