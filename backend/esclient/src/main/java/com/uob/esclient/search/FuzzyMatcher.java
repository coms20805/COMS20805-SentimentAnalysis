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

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;
import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.multiMatchQuery;

public class FuzzyMatcher implements Matcher {
    private final TransportClient client;

    public FuzzyMatcher(TransportClient client) {
        this.client = client;
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
        SearchResponse res = searchRequestBuilder.execute().actionGet();

        for (SearchHit h : res.getHits()) {
            System.out.println(h.getSourceAsMap());
        }
        return Collections.unmodifiableList(posts);
    }

    @Override
    public List<Post> findPosts(String query, int limit) {
        return null;
    }

    public static void main(String[] args) throws IOException {
        TransportClient client = ClientFactory.createClient();


        IndexResponse response = client.prepareIndex("twitter", "_doc")
                .setSource(jsonBuilder()
                        .startObject()
                        .field("user", "kimothy")
                        .field("c", new Date())
                        .field("content", "this is very python cool stuff")
                        .endObject()
                ).get();
        Matcher s = new FuzzyMatcher(client);
        s.findPosts("pytho");
    }
}
