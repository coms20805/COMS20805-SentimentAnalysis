package com.uob.esclient.search;

import com.uob.esclient.post.Post;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StringMatcher implements Matcher {
    private TransportClient client;

    public StringMatcher(TransportClient client) {
        this.client = client;
    }

    private SearchRequestBuilder buildQuery(String query) {
        MatchQueryBuilder content = QueryBuilders.matchQuery("content", query);
        return client.prepareSearch().setQuery(content);
    }


    @Override
    public List<Post> findPosts(String query) {
        List<Post> posts = new ArrayList<>();
        SearchRequestBuilder searchRequestBuilder = buildQuery(query);
        SearchResponse res = searchRequestBuilder.execute().actionGet();

        for (SearchHit h : res.getHits()) {
            System.out.println(h.getSourceAsMap());
        }
        return Collections.unmodifiableList(posts);
    }

    @Override
    public List<Post> findPosts(String query, int limit) {
        return findPosts(query);
    }
}
