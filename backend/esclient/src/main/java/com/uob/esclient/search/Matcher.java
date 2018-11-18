package com.uob.esclient.search;

import com.uob.esclient.post.Post;

import org.elasticsearch.common.settings.Settings;

import java.util.List;

public interface Matcher {

    List<Post> findPosts(String query);

    List<Post> findPosts(String query, int limit);

}
