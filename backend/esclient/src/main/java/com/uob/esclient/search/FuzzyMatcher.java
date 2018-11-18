package com.uob.esclient.search;

import com.uob.esclient.factory.ClientFactory;
import com.uob.esclient.post.Post;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.search.SearchHit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;
import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.multiMatchQuery;

public class FuzzyMatcher implements Matcher {
    private final TransportClient client;

    public FuzzyMatcher(TransportClient client) {
        this.client = client;
    }

    private SearchResponse getResponse(SearchRequestBuilder builder) {
        return builder.execute().actionGet();
    }


    private SearchRequestBuilder fuzzyQueryBuilder(String query) {
        MultiMatchQueryBuilder fuzzyMmQueryBuilder = multiMatchQuery(
                query, "content").fuzziness("AUTO");

        BoolQueryBuilder b = boolQuery().should(fuzzyMmQueryBuilder);
        SearchRequestBuilder srb = client.prepareSearch().setQuery(b);
        return srb;
    }

    @Override
    public List<Post> findPosts(String query) {
        List<Post> posts = new ArrayList<>();
        SearchRequestBuilder searchRequestBuilder = fuzzyQueryBuilder(query);
        SearchResponse res = getResponse(searchRequestBuilder);

        for (SearchHit h : res.getHits()) {
            System.out.println(h.getSourceAsMap());
        }
        return Collections.unmodifiableList(posts);
    }


    @Override
    public List<Post> findPosts(String query, int limit) {
        return findPosts(query).
                stream().
                limit(limit).
                collect(Collectors.toList());
    }
}
