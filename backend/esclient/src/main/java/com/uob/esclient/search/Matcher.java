package com.uob.esclient.search;

import com.uob.esclient.post.Post;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.search.SearchHit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

abstract class Matcher {
    final TransportClient client;

    Matcher(TransportClient client) {
        this.client = client;
    }

    abstract SearchRequestBuilder getSearchRequestBuilder(String query);

    private SearchResponse getResponse(SearchRequestBuilder builder) {
        return builder.execute().actionGet();
    }

    public List<Post> findPosts(String searchQuery, int limit) {
        return Collections.unmodifiableList(findPosts(searchQuery).
                stream().
                limit(limit).
                collect(Collectors.toList()));
    }


    public List<Post> findPosts(String searchQuery) {
        List<Post> posts = new ArrayList<>();
        SearchRequestBuilder searchRequestBuilder = getSearchRequestBuilder(searchQuery);
        SearchResponse res = getResponse(searchRequestBuilder);

        for (SearchHit h : res.getHits()) {
            System.out.println(h.getSourceAsMap());
        }
        return Collections.unmodifiableList(posts);
    }
}
