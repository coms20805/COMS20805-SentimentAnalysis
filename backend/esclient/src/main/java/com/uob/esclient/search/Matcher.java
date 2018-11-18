package com.uob.esclient.search;

import com.uob.esclient.post.Post;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.search.SearchHit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Matcher {
    final TransportClient client;

    public Matcher(TransportClient client) {
        this.client = client;
    }

    abstract SearchRequestBuilder queryBuilder(String query);

    private SearchResponse getResponse(SearchRequestBuilder builder) {
        return builder.execute().actionGet();
    }

    public List<Post> findPosts(String query, int limit) {
        return Collections.unmodifiableList(findPosts(query).
                stream().
                limit(limit).
                collect(Collectors.toList()));
    }

    public List<Post> findPosts(String query) {
        List<Post> posts = new ArrayList<>();
        SearchRequestBuilder searchRequestBuilder = queryBuilder(query);
        SearchResponse res = getResponse(searchRequestBuilder);

        for (SearchHit h : res.getHits()) {
            System.out.println(h.getSourceAsMap());
        }
        return Collections.unmodifiableList(posts);
    }
}
