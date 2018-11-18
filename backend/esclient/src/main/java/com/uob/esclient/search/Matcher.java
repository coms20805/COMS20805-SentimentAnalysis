package com.uob.esclient.search;

import com.uob.esclient.post.Post;

import java.util.List;

interface Matcher {

    List<Post> findPosts(String query);

    List<Post> findPosts(String query, int limit);

}
