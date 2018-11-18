package com.uob.esclient.search;

import com.uob.esclient.post.Post;

import java.util.List;

interface Matcher {

    List<Post> findPostsClosestTo(String query);

    List<Post> findPostsClosestTo(String query, int limit);

}
