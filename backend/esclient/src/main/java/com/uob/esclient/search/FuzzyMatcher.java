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

public class FuzzyMatcher extends Matcher {

    public FuzzyMatcher(TransportClient client) {
        super(client);
    }

    @Override
    SearchRequestBuilder queryBuilder(String query) {
        MultiMatchQueryBuilder fuzzyMmQueryBuilder = multiMatchQuery(
                query, "content").fuzziness("AUTO");

        BoolQueryBuilder b = boolQuery().should(fuzzyMmQueryBuilder);
        SearchRequestBuilder srb = client.prepareSearch().setQuery(b);
        return srb;
    }
}
