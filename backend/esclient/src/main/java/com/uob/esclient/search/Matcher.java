package com.uob.esclient.search;

import com.uob.esclient.utils.PostUtils;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.search.SearchHit;

import java.util.ArrayList;
import java.util.Arrays;
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

    public <P> List<P> findPosts(SearchQuery sq, Class<P> postClazz) {
        SearchRequestBuilder searchRequestBuilder = buildSearchRequest(sq.literalQuery, sq.fieldToCompareAgainst);
        SearchResponse res = getResponse(searchRequestBuilder);


        return Arrays.stream(res.getHits().getHits())
                .map((x) -> PostUtils.toPost(x.getSourceAsMap(), postClazz))
                .limit(sq.limit)
                .collect(Collectors.toList());
    }
}
