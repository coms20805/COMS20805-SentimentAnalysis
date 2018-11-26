package com.uob.esclient.search;

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

    abstract SearchRequestBuilder buildSearchRequest(String query, String fieldToCompareAgainst);

    private SearchResponse getResponse(SearchRequestBuilder builder) {
        return builder.execute().actionGet();
    }

    public List<?> findPosts(SearchQuery sq) {
        return Collections.unmodifiableList(findPosts(sq.query, sq.postClazz, sq.fieldToCompareAgainst).
                stream().
                limit(sq.limit).
                collect(Collectors.toList()));
    }


    public <P> List<P> findPosts(String literalQuery, Class<P> postClazz, String fieldToCompareAgainst) {
        List<P> posts = new ArrayList<>();
        SearchRequestBuilder searchRequestBuilder = buildSearchRequest(literalQuery, fieldToCompareAgainst);
        SearchResponse res = getResponse(searchRequestBuilder);

        for (SearchHit h : res.getHits()) {
            System.out.println(h.getSourceAsMap());
        }
        return Collections.unmodifiableList(posts);
    }
}
